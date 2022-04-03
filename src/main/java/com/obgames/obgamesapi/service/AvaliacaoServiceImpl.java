package com.obgames.obgamesapi.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.obgames.obgamesapi.exceptions.ResourceNotFoundException;
import com.obgames.obgamesapi.exceptions.ResponseStatusException;
import com.obgames.obgamesapi.model.Avaliacao;
import com.obgames.obgamesapi.model.Usuario;
import com.obgames.obgamesapi.repository.AvaliacaoRepo;
import com.obgames.obgamesapi.repository.UsuarioRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AvaliacaoServiceImpl implements AvaliacaoService {

    @Autowired
    private AvaliacaoRepo avaliacaoRepo;

    @Autowired
    private UsuarioRepo usuarioRepo;

    @Override
    public Avaliacao createAvaliacao(Avaliacao avaliacao) {
        return avaliacaoRepo.save(avaliacao);
    }

    @Override
    public Optional<Avaliacao> insertCurtidaIntoAvaliacao(String id, String usuarioId) throws Exception {
        Optional<Usuario> usuarioDb = this.usuarioRepo.findById(usuarioId);
        Optional<Avaliacao> avaliacaoDb = this.avaliacaoRepo.findById(id);
        Avaliacao avaliacao = avaliacaoDb.get();
        ArrayList<Usuario> curtidas = avaliacao.getCurtidas();
        curtidas.add(usuarioDb.get());
        avaliacao.setCurtida(curtidas);
        if (avaliacaoDb.isPresent()) {
            if (avaliacao.getId() == avaliacao.getId()) {
                avaliacaoRepo.save(avaliacao);
                return avaliacaoDb;
            }
        }
        throw new Exception();//ResourceNotFoundException("404", "Record not found with id : " + avaliacaoId);
    }

    @Override
    public Optional<Avaliacao> updateAvaliacao(Avaliacao avaliacao, String avaliacaoId) throws Exception {
        Optional<Avaliacao> avaliacaoDb = this.avaliacaoRepo.findById(avaliacaoId);
        if (avaliacaoDb.isPresent()) {
            if (avaliacao.getId() == avaliacaoId) {
                avaliacaoRepo.save(avaliacao);
                return avaliacaoDb;
            }
        }
        throw new Exception();//ResourceNotFoundException("404", "Record not found with id : " + avaliacaoId);

    }
    

    @Override
    public List<Avaliacao> getAllAvaliacao() {
        return this.avaliacaoRepo.findAll();
    }

    @Override
    public Avaliacao getAvaliacaoById(String avaliacaoId) throws ResourceNotFoundException {

        Optional<Avaliacao> avaliacaoDb = this.avaliacaoRepo.findById(avaliacaoId);

        if (avaliacaoDb.isPresent()) {
            return avaliacaoDb.get();
        } else {
            throw new ResourceNotFoundException("404", "Record not found with id : " + avaliacaoId);
        }
    }

    @Override
    public Optional<Avaliacao> deleteCurtidaFromAvaliacao(String id, String usuarioId) throws Exception {
        //Optional<Usuario> usuarioDb = this.usuarioRepo.findById(usuarioId);
        Optional<Avaliacao> avaliacaoDb = this.avaliacaoRepo.findById(id);
        Avaliacao avaliacao = avaliacaoDb.get();
        ArrayList<Usuario> curtidas = avaliacao.getCurtidas();
        String x = curtidas.get(0).getId();
        //curtidas.remove(0);

        for (int c = 0 ; c < curtidas.size() ; c++){
            if (usuarioId.equals(curtidas.get(c).getId())){
                curtidas.remove(c);
                c=0;
            }
        }
        avaliacao.setCurtida(curtidas);
        if (avaliacaoDb.isPresent()) {
            if (avaliacao.getId() == avaliacao.getId()) {
                avaliacaoRepo.save(avaliacao);
                return avaliacaoDb;
            }
        }
        throw new Exception();//ResourceNotFoundException("404", "Record not found with id : " + avaliacaoId);
    }

    @Override
    public void deleteAvaliacao(String avaliacaoId) throws ResourceNotFoundException, ResponseStatusException {
        
            Optional<Avaliacao> avaliacaoDb = this.avaliacaoRepo.findById(avaliacaoId);

            if (avaliacaoDb.isPresent()) {
                this.avaliacaoRepo.delete(avaliacaoDb.get());
            } else {
                throw new ResourceNotFoundException("404", "Record not found with id : " + avaliacaoId);
            }
        

    }
}
