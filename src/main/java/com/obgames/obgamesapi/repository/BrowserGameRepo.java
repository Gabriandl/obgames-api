package com.obgames.obgamesapi.repository;

import java.util.List;
import com.obgames.obgamesapi.model.BrowserGame;

import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BrowserGameRepo extends MongoRepository<BrowserGame, String>{

    List<BrowserGame> findByCategoriaId(String categoriaId, Sort sort);

}
    
