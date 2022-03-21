package com.obgames.obgamesapi.service;

import java.util.List;
import java.util.Optional;

import com.obgames.obgamesapi.model.Categoria;

public interface CategoriaService {

        Categoria createCategoria(Categoria categoria);
    
        Optional<Categoria>  updateCategoria(Categoria categoria, String id);
    
        List<Categoria> getAllCategoria();
    
        Categoria getCategoriaById(String id);
    
        void deleteCategoria(String id);
    
}
