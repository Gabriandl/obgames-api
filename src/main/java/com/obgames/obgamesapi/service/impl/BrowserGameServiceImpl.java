package com.obgames.obgamesapi.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.obgames.obgamesapi.dto.response.BrowserGamesStarsResponseDTO;
import com.obgames.obgamesapi.model.Avaliacao;
import com.obgames.obgamesapi.model.BrowserGame;
import com.obgames.obgamesapi.repository.AvaliacaoRepo;
import com.obgames.obgamesapi.repository.BrowserGameRepo;
import com.obgames.obgamesapi.service.BrowserGameService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

@Service
@Transactional
public class BrowserGameServiceImpl implements BrowserGameService {

    @Autowired
    private BrowserGameRepo browserGameRepo;

    @Autowired
    private AvaliacaoRepo avaliacaoRepo;

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
        List<BrowserGame>  allBrowserGames =  this.browserGameRepo.findAll(Sort.by("nome").ascending());
        List<BrowserGame> browserGamesAvgList = new ArrayList<BrowserGame>();
         //= this.browserGameRepo.findAll();;

        allBrowserGames.forEach(browserGame -> {
                                int size = 0;
                                double[] nota = {0.0}; // solucao para resolver problema de intancia de variavel dentro do forEach
                                double avg = 0.0;
                                size = avaliacaoRepo.findByBrowserGameId(browserGame.getId(), Sort.by("numEstrelas").descending()).size();
                                avaliacaoRepo.findByBrowserGameId(browserGame.getId(), Sort.by("numEstrelas").descending())
                                             .forEach(avaliacao -> {
                                                nota[0] = nota[0] + avaliacao.getNumEstrelas();
                                             });
                                avg = Double.isNaN(nota[0]/size) ? avg : nota[0]/size;
                                browserGame.setAvgEstrelas(avg);
                                browserGame.setQtdAvaliacoes(size);
                                browserGamesAvgList.add(browserGame);
                                System.out.println("browserGamesMap Key : " + browserGame.getId() + " Avg Ava : " + avg);

                            
                        }); // calcula media de avaliacao dos browsergames da lista e adiciona em um hashmap <idBrowserGame, mediaAvaliacoes>


        return browserGamesAvgList;
    }

    @Override
    public List<BrowserGame> getBrowserGameByCategoriaId(String categoriaId) {
        return this.browserGameRepo.findByCategoriaId(categoriaId, Sort.by("nome").ascending());
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

    @Override
    public List<BrowserGame> getBrowserGamesRecomendationById(String usuarioId) {
        List<Avaliacao> avaliacoes = this.avaliacaoRepo.findAll();
        List<Avaliacao> avaliacoesElegiveis = new ArrayList<>();
        Map<String,Double> browserGamesAvgMap = new HashMap<String,Double>();
        List<String> browserGamesMap = new ArrayList<String>();
        Map<String,Integer> categoriaMap = new HashMap<String,Integer>();
        ArrayList<BrowserGame>  allBrowserGames = new ArrayList<>();

        ArrayList<BrowserGame>  elegibleBrowserGames = new ArrayList<>();

        for(Avaliacao a : avaliacoes){
            if (a.getUsuario().getId().equals(usuarioId)){ // filtro as avaliacoes feitas pelo membro 
                avaliacoesElegiveis.add(a);
            }
        }

        for(Avaliacao a : avaliacoesElegiveis){
            if (!browserGamesMap.contains(a.getBrowserGame().getId())){// browsergamesMap serve para eu saber quais jogos ja foram avaliado por esse membro 
                browserGamesMap.add(a.getBrowserGame().getId());  
            } 
           
        }
        for(Avaliacao a : avaliacoesElegiveis){
            if (categoriaMap.get(a.getBrowserGame().getCategoria().getId()) != null){ // categoriaMap me mostra quais categoria o membro avaliou
                int x = categoriaMap.get(a.getBrowserGame().getCategoria().getId()); // verifico a quantidade de avaliacoes que o membro fez a categoria e add no hashmap
                categoriaMap.put(a.getBrowserGame().getCategoria().getId(), x+1); 
                
            } else {
                categoriaMap.put(a.getBrowserGame().getCategoria().getId(), 1); 
            }
           
        }

        categoriaMap.entrySet()
                    .stream()
                    .sorted(Map.Entry.<String, Integer>comparingByValue()
                    .reversed()) 
                    .limit(2)
                    .forEach(entry -> {
                        allBrowserGames.addAll(browserGameRepo.findByCategoriaId(entry.getKey(), Sort.by("nome").ascending()));
                        System.out.println(" categoriaMap Key : " + entry.getKey() + " categoriaMap Value : " + entry.getValue());
                    }); //adiciona browsergames das 2 categorias mais avaliadas em uma lista
        
        Iterator<BrowserGame> itr = allBrowserGames.iterator();
        while (itr.hasNext())
        {
            BrowserGame browserGameItr = (BrowserGame)itr.next();
            if (browserGamesMap.contains(browserGameItr.getId()))
            itr.remove();
        } //remove os browsergames que ja foram avaliados pelo membro da lista

        allBrowserGames.forEach(browserGame -> {
                                int size = 0;
                                double[] nota = {0.0}; // solucao para resolver problema de intancia de variavel dentro do forEach
                                double avg = 0.0;
                                size = avaliacaoRepo.findByBrowserGameId(browserGame.getId(), Sort.by("numEstrelas").descending()).size();
                                avaliacaoRepo.findByBrowserGameId(browserGame.getId(), Sort.by("numEstrelas").descending())
                                             .forEach(avaliacao -> {
                                                nota[0] = nota[0] + avaliacao.getNumEstrelas();
                                             });
                                avg = Double.isNaN(nota[0]/size) ? avg : nota[0]/size;
                                browserGamesAvgMap.put(browserGame.getId(), avg);
                                System.out.println("browserGamesMap Key : " + browserGame.getId() + " Avg Ava : " + avg);

                            
                        }); // calcula media de avaliacao dos browsergames da lista e adiciona em um hashmap <idBrowserGame, mediaAvaliacoes>

        browserGamesAvgMap.entrySet()
                    .stream()
                    .sorted(Map.Entry.<String, Double>comparingByValue()
                    .reversed()) 
                    .limit(5)
                    .forEach(entry -> {
                        if (entry.getValue() > 3) {
                            elegibleBrowserGames.add(browserGameRepo.findById(entry.getKey()).get());
                            System.out.println("browserGamesAvgMap Key : " + entry.getKey() + " Value : " + entry.getValue());
                        }
                    }); // filtra apenas browsergames com media > 3 e adiciona na lista de resposta

        return elegibleBrowserGames;
    }

    @Override
    public List<BrowserGamesStarsResponseDTO> getBrowserGamesStars() {
        List<BrowserGamesStarsResponseDTO> browserGamesAvgList = new ArrayList<BrowserGamesStarsResponseDTO>();
        List<BrowserGame>  allBrowserGames = this.browserGameRepo.findAll();;

        allBrowserGames.forEach(browserGame -> {
                                int size = 0;
                                double[] nota = {0.0}; // solucao para resolver problema de intancia de variavel dentro do forEach
                                double avg = 0.0;
                                size = avaliacaoRepo.findByBrowserGameId(browserGame.getId(), Sort.by("numEstrelas").descending()).size();
                                avaliacaoRepo.findByBrowserGameId(browserGame.getId(), Sort.by("numEstrelas").descending())
                                             .forEach(avaliacao -> {
                                                nota[0] = nota[0] + avaliacao.getNumEstrelas();
                                             });
                                avg = Double.isNaN(nota[0]/size) ? avg : nota[0]/size;
                                BrowserGamesStarsResponseDTO avgStars = new BrowserGamesStarsResponseDTO(browserGame.getId(), avg, size);
                                browserGamesAvgList.add(avgStars);
                                //System.out.println("browserGamesMap Key : " + browserGame.getId() + " Avg Ava : " + avg);

                            
                        }); // calcula media de avaliacao dos browsergames da lista e adiciona em um hashmap <idBrowserGame, mediaAvaliacoes>


        return browserGamesAvgList;
        
    }


}
