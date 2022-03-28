package com.obgames.obgamesapi.repository;

import com.obgames.obgamesapi.model.Avaliacao;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AvaliacaoRepo extends MongoRepository<Avaliacao, String>{
}