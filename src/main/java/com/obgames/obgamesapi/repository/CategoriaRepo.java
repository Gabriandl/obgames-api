package com.obgames.obgamesapi.repository;

import com.obgames.obgamesapi.model.Categoria;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoriaRepo extends MongoRepository<Categoria, String>{
}
    
