package com.obgames.obgamesapi.repository;

import java.util.Optional;

import com.obgames.obgamesapi.model.Usuario;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepo extends MongoRepository<Usuario, String>{
    Optional<Usuario> findByUsername(String username);
    Boolean existsByUsername(String username);
}
