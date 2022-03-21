package com.obgames.obgamesapi.service;

import java.util.List;
import java.util.Optional;

import com.obgames.obgamesapi.model.BrowserGame;
import com.obgames.obgamesapi.repository.BrowserGameRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

@Service
@Transactional
public class BrowserGameServiceImpl implements BrowserGameService {

    @Autowired
    private BrowserGameRepo browserGameRepo;

    @Override
    public BrowserGame createBrowserGame(BrowserGame browserGame) {
        return browserGameRepo.save(browserGame);
    }

    @Override
    public Optional<BrowserGame> updateBrowserGame(BrowserGame browserGame, String browserGameId) {
        Optional<BrowserGame> browserGameDb = this.browserGameRepo.findById(browserGameId);
        if (browserGameDb.isPresent()) {
            if (browserGame.getId() == browserGameId) {
                browserGameRepo.save(browserGame);
                return browserGameDb;
            }
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Record not found with id : " + browserGame.getId());

    }

    @Override
    public List<BrowserGame> getAllBrowserGame() {
        return this.browserGameRepo.findAll();
    }

    @Override
    public BrowserGame getBrowserGameById(String browserGameId) {

        Optional<BrowserGame> browserGameDb = this.browserGameRepo.findById(browserGameId);

        if (browserGameDb.isPresent()) {
            return browserGameDb.get();
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Record not found with id : " + browserGameId);
        }
    }

    @Override
    public void deleteBrowserGame(String browserGameId) {
        Optional<BrowserGame> browserGameDb = this.browserGameRepo.findById(browserGameId);

        if (browserGameDb.isPresent()) {
            this.browserGameRepo.delete(browserGameDb.get());
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Record not found with id : " + browserGameId);
        }

    }
}
