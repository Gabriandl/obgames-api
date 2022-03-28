package com.obgames.obgamesapi.repository;

import com.obgames.obgamesapi.model.Usuario;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepo extends MongoRepository<Usuario, String>{
}
