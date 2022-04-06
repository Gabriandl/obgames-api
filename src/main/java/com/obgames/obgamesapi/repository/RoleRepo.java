package com.obgames.obgamesapi.repository;

import java.util.Optional;

import com.obgames.obgamesapi.model.EnumRole;
import com.obgames.obgamesapi.model.Role;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface RoleRepo extends MongoRepository<Role, String> {
        Optional<Role> findByNome(EnumRole nome);
    
}
