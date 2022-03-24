package com.obgames.obgamesapi.repository;

import java.util.List;
import com.obgames.obgamesapi.model.BrowserGame;
import com.obgames.obgamesapi.model.Categoria;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BrowserGameRepo extends MongoRepository<BrowserGame, String>{

    List<BrowserGame> findByCategoria(Categoria categoria);

}
    
