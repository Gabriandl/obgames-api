package com.obgames.obgamesapi.controller;

import java.util.List;
import java.util.Optional;

import com.obgames.obgamesapi.model.BrowserGame;
import com.obgames.obgamesapi.service.BrowserGameService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BrowserGameController {

    @Autowired
    private BrowserGameService browserGameService;

    @GetMapping("/browserGames")
    public ResponseEntity<List<BrowserGame>> getAllBrowserGame() {
        return ResponseEntity.ok().body(browserGameService.getAllBrowserGame());
    }

    @GetMapping("/browserGames/{id}")
    public ResponseEntity<BrowserGame> getBrowserGameById(@PathVariable String id) {
        return ResponseEntity.ok().body(browserGameService.getBrowserGameById(id));
    }

    @PostMapping("/browserGames")
    public ResponseEntity <BrowserGame> createBrowserGame(@RequestBody BrowserGame browserGame) {
        return ResponseEntity.ok().body(this.browserGameService.createBrowserGame(browserGame));
    }

    @PutMapping("/browserGames/{id}")
    public ResponseEntity<Optional<BrowserGame>> updateBrowserGame(@PathVariable String id, @RequestBody BrowserGame browserGame) {
        browserGame.setId(id);
        return ResponseEntity.ok().body(this.browserGameService.updateBrowserGame(browserGame, id));
    }

    @DeleteMapping("/browserGames/{id}")
    public HttpStatus deleteBrowserGame(@PathVariable String id) {
        this.browserGameService.deleteBrowserGame(id);
        return HttpStatus.OK;
    }
}
