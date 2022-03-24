package com.obgames.obgamesapi.service;

import java.util.List;
import java.util.Optional;

import com.obgames.obgamesapi.exceptions.ResourceNotFoundException;
import com.obgames.obgamesapi.exceptions.ResponseStatusException;
import com.obgames.obgamesapi.model.Categoria;

public interface CategoriaService {

        Categoria createCategoria(Categoria categoria);
    
        Optional<Categoria>  updateCategoria(Categoria categoria, String id) throws ResourceNotFoundException;
    
        List<Categoria> getAllCategoria();
    
        Categoria getCategoriaById(String id) throws ResourceNotFoundException;
    
        void deleteCategoria(String id) throws ResourceNotFoundException, ResponseStatusException;
    
}
