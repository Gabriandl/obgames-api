package com.obgames.obgamesapi.repository;

import com.obgames.obgamesapi.model.BrowserGame;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BrowserGameRepo extends MongoRepository<BrowserGame, String>{

}
    
