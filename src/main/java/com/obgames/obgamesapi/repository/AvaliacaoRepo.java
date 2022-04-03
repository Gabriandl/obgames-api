package com.obgames.obgamesapi.repository;

import java.util.List;

import com.obgames.obgamesapi.model.Avaliacao;

import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AvaliacaoRepo extends MongoRepository<Avaliacao, String>{
    List<Avaliacao> findByBrowserGameId(String browserGameId, Sort sort);

}