package com.obgames.obgamesapi.service;

import java.util.List;
import java.util.Optional;

import com.obgames.obgamesapi.exceptions.ResourceNotFoundException;
import com.obgames.obgamesapi.exceptions.ResponseStatusException;
import com.obgames.obgamesapi.model.BrowserGame;
import com.obgames.obgamesapi.model.Categoria;
import com.obgames.obgamesapi.repository.BrowserGameRepo;
import com.obgames.obgamesapi.repository.CategoriaRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CategoriaServiceImpl implements CategoriaService {

    @Autowired
    private CategoriaRepo categoriaRepo;

    @Autowired
    private BrowserGameRepo browserGameRepo;

    @Override
    public Categoria createCategoria(Categoria categoria) {
        return categoriaRepo.save(categoria);
    }

    @Override
    public Optional<Categoria> updateCategoria(Categoria categoria, String categoriaId) throws ResourceNotFoundException {
        Optional<Categoria> categoriaDb = this.categoriaRepo.findById(categoriaId);
        if (categoriaDb.isPresent()) {
            if (categoria.getId() == categoriaId) {
                categoriaRepo.save(categoria);
                return categoriaDb;
            }
        }
        throw new ResourceNotFoundException("404", "Record not found with id : " + categoriaId);

    }

    @Override
    public List<Categoria> getAllCategoria() {
        return this.categoriaRepo.findAll();
    }

    @Override
    public Categoria getCategoriaById(String categoriaId) throws ResourceNotFoundException {

        Optional<Categoria> categoriaDb = this.categoriaRepo.findById(categoriaId);

        if (categoriaDb.isPresent()) {
            return categoriaDb.get();
        } else {
            throw new ResourceNotFoundException("404", "Record not found with id : " + categoriaId);
        }
    }

    @Override
    public void deleteCategoria(String categoriaId) throws ResourceNotFoundException, ResponseStatusException {
        List<BrowserGame> browserGames = this.browserGameRepo.findByCategoriaId(categoriaId,Sort.by("nome").ascending());
        if (browserGames.size() == 0){
            Optional<Categoria> categoriaDb = this.categoriaRepo.findById(categoriaId);

            if (categoriaDb.isPresent()) {
                this.categoriaRepo.delete(categoriaDb.get());
            } else {
                throw new ResourceNotFoundException("404", "Record not found with id : " + categoriaId);
            }
        } else {
            throw new ResponseStatusException("5001","Essa categoria possui Browser Games vinculados. Para deletar, desvincule e tente novamente","Categoria ID: "+ categoriaId);
        }

    }
}
