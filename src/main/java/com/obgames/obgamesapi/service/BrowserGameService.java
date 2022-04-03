package com.obgames.obgamesapi.service;

import java.util.List;
import java.util.Optional;

import com.obgames.obgamesapi.model.BrowserGame;

public interface BrowserGameService {
    
    BrowserGame createBrowserGame(BrowserGame browserGame);
    
        Optional<BrowserGame>  updateBrowserGame(BrowserGame browserGame, String id);
    
        List<BrowserGame> getAllBrowserGame();

        List<BrowserGame> getBrowserGameByCategoriaId(String categoriaId);
    
        BrowserGame getBrowserGameById(String id);
    
        void deleteBrowserGame(String id);
}
