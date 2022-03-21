package com.obgames.obgamesapi.service;

import java.util.List;
import java.util.Optional;

import com.obgames.obgamesapi.model.Categoria;
import com.obgames.obgamesapi.repository.CategoriaRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

@Service
@Transactional
public class CategoriaServiceImpl implements CategoriaService {

    @Autowired
    private CategoriaRepo categoriaRepo;

    @Override
    public Categoria createCategoria(Categoria categoria) {
        return categoriaRepo.save(categoria);
    }

    @Override
    public Optional<Categoria> updateCategoria(Categoria categoria, String categoriaId) {
        Optional<Categoria> categoriaDb = this.categoriaRepo.findById(categoriaId);
        if (categoriaDb.isPresent()) {
            if (categoria.getId() == categoriaId) {
                categoriaRepo.save(categoria);
                return categoriaDb;
            }
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Record not found with id : " + categoria.getId());

    }

    @Override
    public List<Categoria> getAllCategoria() {
        return this.categoriaRepo.findAll();
    }

    @Override
    public Categoria getCategoriaById(String categoriaId) {

        Optional<Categoria> categoriaDb = this.categoriaRepo.findById(categoriaId);

        if (categoriaDb.isPresent()) {
            return categoriaDb.get();
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Record not found with id : " + categoriaId);
        }
    }

    @Override
    public void deleteCategoria(String categoriaId) {
        Optional<Categoria> categoriaDb = this.categoriaRepo.findById(categoriaId);

        if (categoriaDb.isPresent()) {
            this.categoriaRepo.delete(categoriaDb.get());
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Record not found with id : " + categoriaId);
        }

    }
}
