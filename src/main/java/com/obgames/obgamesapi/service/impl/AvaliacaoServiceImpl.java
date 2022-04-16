package com.obgames.obgamesapi.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.obgames.obgamesapi.exceptions.ResourceNotFoundException;
import com.obgames.obgamesapi.exceptions.ResponseStatusException;
import com.obgames.obgamesapi.model.Avaliacao;
import com.obgames.obgamesapi.model.Usuario;
import com.obgames.obgamesapi.repository.AvaliacaoRepo;
import com.obgames.obgamesapi.repository.UsuarioRepo;
import com.obgames.obgamesapi.service.AvaliacaoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AvaliacaoServiceImpl implements AvaliacaoService {

    @Autowired
    public AvaliacaoRepo avaliacaoRepo;

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
        return this.avaliacaoRepo.findAll(Sort.by(Direction.DESC, "curtidasSize"));
    }

    @Override
    public List<Avaliacao> getAvaliacaoByBrowserGameId(String browserGameId) {
        return this.avaliacaoRepo.findByBrowserGameId(browserGameId, Sort.by(Direction.DESC, "curtidasSize"));
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
        Optional<Avaliacao> avaliacaoDb = this.avaliacaoRepo.findById(id);
        Avaliacao avaliacao = avaliacaoDb.get();
        ArrayList<Usuario> curtidas = avaliacao.getCurtidas();
        Boolean stop = false;

        for (int c=0 ; !stop ; c=0) {
            if (usuarioId.equals(curtidas.get(c).getId())){
                curtidas.remove(c);
                stop = curtidas.size() == 0 ? true : false;
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
