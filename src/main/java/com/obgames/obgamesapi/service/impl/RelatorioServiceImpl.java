package com.obgames.obgamesapi.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.obgames.obgamesapi.dto.response.RelatorioResponseDTO;
import com.obgames.obgamesapi.model.Avaliacao;
import com.obgames.obgamesapi.model.BrowserGame;
import com.obgames.obgamesapi.model.Categoria;
import com.obgames.obgamesapi.model.Usuario;
import com.obgames.obgamesapi.repository.AvaliacaoRepo;
import com.obgames.obgamesapi.repository.BrowserGameRepo;
import com.obgames.obgamesapi.repository.CategoriaRepo;
import com.obgames.obgamesapi.repository.UsuarioRepo;
import com.obgames.obgamesapi.service.RelatorioService;

import org.springframework.data.domain.Sort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class RelatorioServiceImpl implements RelatorioService{
    
    @Autowired
    private UsuarioRepo usuarioRepo;

    @Autowired
    private BrowserGameRepo browserGameRepo;

    @Autowired
    private AvaliacaoRepo avaliacaoRepo;

    @Autowired
    private CategoriaRepo categoriaRepo;

  

    @Override
    public  RelatorioResponseDTO getRelatorio(Integer tipoRelatorio, long dataInicial, long dataFinal) {
        RelatorioResponseDTO response = new RelatorioResponseDTO();

        switch (tipoRelatorio) {
            case 1:
                response.setBrowserGames(getBrowserGamesMoreEvaluated(dataInicial, dataFinal));
                break;
            case 2:
                response.setUsuarios(getUserWithMoreEvaluation(dataInicial, dataFinal));
                break;
            case 3:
                response.setBrowserGames(getBrowserGamesWithMoreAverage(dataInicial, dataFinal));
                break;
            case 4:
                response.setCategorias(getCategoryMoreEvaluated(dataInicial, dataFinal));
            break; 
        }
        return response;
        
    }

   private ArrayList<Usuario> getUserWithMoreEvaluation(long dataInicial, long dataFinal) {

    
        List<Avaliacao> avaliacoes = this.avaliacaoRepo.findAll(Sort.by("nome").ascending());
        List<Avaliacao> avaliacoesElegiveis = new ArrayList<>();
        Map<String,Integer> usuariosMap = new HashMap<String,Integer>();
        ArrayList<Usuario>  usuarios = new ArrayList<>();

        for(Avaliacao a : avaliacoes){
            if (a.getTimestamp() >= dataInicial && a.getTimestamp() <= dataFinal){ // filtro as avaliacoes pelas datas determinado
                avaliacoesElegiveis.add(a);
            }
        }

        for(Avaliacao a : avaliacoesElegiveis){
            if (usuariosMap.get(a.getUsuario().getId()) != null){//Arrays.asList(usuariosMap).contains(a.getUsuario())){
                int x = usuariosMap.get(a.getUsuario().getId()); // verifico a quantidade de avaliacoes que cada membro fez e add no hashmap
                usuariosMap.put(a.getUsuario().getId(), x+1); 
                
            } else {
                usuariosMap.put(a.getUsuario().getId(), 1); 
            }
           
        }

        usuariosMap.entrySet()
                    .stream()
                    .sorted(Map.Entry.<String, Integer>comparingByValue()
                    .reversed()) 
                    .limit(5)
                    .forEach(entry -> {
                        usuarios.add(usuarioRepo.findById(entry.getKey()).get());
                        System.out.println("Key : " + entry.getKey() + " Value : " + entry.getValue());
                    }); 

        return usuarios;

    }

    private ArrayList<BrowserGame> getBrowserGamesMoreEvaluated(long dataInicial, long dataFinal) {
        List<Avaliacao> avaliacoes = this.avaliacaoRepo.findAll();
        List<Avaliacao> avaliacoesElegiveis = new ArrayList<>();
        Map<String,Integer> browserGamesMap = new HashMap<String,Integer>();
        ArrayList<BrowserGame>  browserGames = new ArrayList<>();

        for(Avaliacao a : avaliacoes){
            if (a.getTimestamp() >= dataInicial && a.getTimestamp() <= dataFinal){ // filtro as avaliacoes pelas datas determinado
                avaliacoesElegiveis.add(a);
            }
        }

        for(Avaliacao a : avaliacoesElegiveis){
            if (browserGamesMap.get(a.getBrowserGame().getId()) != null){//Arrays.asList(usuariosMap).contains(a.getUsuario())){
                int x = browserGamesMap.get(a.getBrowserGame().getId()); // verifico a quantidade de avaliacoes que cada membro fez e add no hashmap
                browserGamesMap.put(a.getBrowserGame().getId(), x+1); 
                
            } else {
                browserGamesMap.put(a.getBrowserGame().getId(), 1); 
            }
           
        }

        browserGamesMap.entrySet()
                    .stream()
                    .sorted(Map.Entry.<String, Integer>comparingByValue()
                    .reversed()) 
                    .limit(5)
                    .forEach(entry -> {
                        browserGames.add(browserGameRepo.findById(entry.getKey()).get());
                        System.out.println("Key : " + entry.getKey() + " Value : " + entry.getValue());
                    }); 

        return browserGames;
    }

    private ArrayList<BrowserGame> getBrowserGamesWithMoreAverage(long dataInicial, long dataFinal) {
        List<Avaliacao> avaliacoes = this.avaliacaoRepo.findAll();
        List<Avaliacao> avaliacoesElegiveis = new ArrayList<>();
        Map<String,Double> browserGamesAvgMap = new HashMap<String,Double>();
        Map<String,Integer> browserGamesMap = new HashMap<String,Integer>();
        ArrayList<BrowserGame>  browserGames = new ArrayList<>();

        for(Avaliacao a : avaliacoes){
            if (a.getTimestamp() >= dataInicial && a.getTimestamp() <= dataFinal){ // filtro as avaliacoes pelas datas determinado
                avaliacoesElegiveis.add(a);
            }
        }

        for(Avaliacao a : avaliacoesElegiveis){
            if (browserGamesMap.get(a.getBrowserGame().getId()) != null){//Arrays.asList(usuariosMap).contains(a.getUsuario())){
                int x = browserGamesMap.get(a.getBrowserGame().getId()); // verifico a quantidade de avaliacoes que cada membro fez e add no hashmap
                browserGamesMap.put(a.getBrowserGame().getId(), x+1); 
                
            } else {
                browserGamesMap.put(a.getBrowserGame().getId(), 1); 
            }
           
        }

        browserGamesMap.entrySet()
                        .forEach(entry -> {
                            if (entry.getValue() >= 4) {
                                int size = 0;
                                double[] nota = {0.0}; // solucao para resolver problema de intancia de variavel dentro do forEach
                                size = avaliacaoRepo.findByBrowserGameId(entry.getKey(), Sort.by("numEstrelas").descending()).size();
                                avaliacaoRepo.findByBrowserGameId(entry.getKey(), Sort.by("numEstrelas").descending())
                                             .forEach(avaliacao -> {
                                                nota[0] = nota[0] + avaliacao.getNumEstrelas();
                                             });
                                browserGamesAvgMap.put(entry.getKey(), nota[0]/size);
                                System.out.println("browserGamesMap Key : " + entry.getKey() + " Avg Ava : " + nota[0]/size);

                            }
                            System.out.println("browserGamesMap Key : " + entry.getKey() + " Qtd Ava : " + entry.getValue());
                        }); 

        browserGamesAvgMap.entrySet()
                    .stream()
                    .sorted(Map.Entry.<String, Double>comparingByValue()
                    .reversed()) 
                    .limit(5)
                    .forEach(entry -> {
                        browserGames.add(browserGameRepo.findById(entry.getKey()).get());
                        System.out.println("browserGamesAvgMap Key : " + entry.getKey() + " Value : " + entry.getValue());
                    }); 

        return browserGames;
    }

    private ArrayList<Categoria> getCategoryMoreEvaluated(long dataInicial, long dataFinal) {
        List<Avaliacao> avaliacoes = this.avaliacaoRepo.findAll();
        List<Avaliacao> avaliacoesElegiveis = new ArrayList<>();
        Map<String,Integer> categoriaMap = new HashMap<String,Integer>();
        ArrayList<Categoria>  categorias = new ArrayList<>();

        for(Avaliacao a : avaliacoes){
            if (a.getTimestamp() >= dataInicial && a.getTimestamp() <= dataFinal){ // filtro as avaliacoes pelas datas determinado
                avaliacoesElegiveis.add(a);
            }
        }

        for(Avaliacao a : avaliacoesElegiveis){
            if (categoriaMap.get(a.getBrowserGame().getCategoria().getId()) != null){
                int x = categoriaMap.get(a.getBrowserGame().getCategoria().getId()); // verifico a quantidade de avaliacoes que cada membro fez e add no hashmap
                categoriaMap.put(a.getBrowserGame().getCategoria().getId(), x+1); 
                
            } else {
                categoriaMap.put(a.getBrowserGame().getCategoria().getId(), 1); 
            }
           
        }

        categoriaMap.entrySet()
                    .stream()
                    .sorted(Map.Entry.<String, Integer>comparingByValue()
                    .reversed()) 
                    .limit(3)
                    .forEach(entry -> {
                        categorias.add(categoriaRepo.findById(entry.getKey()).get());
                        System.out.println(" categoriaMap Key : " + entry.getKey() + " categoriaMap Value : " + entry.getValue());
                    }); 

        return categorias;
    }
}
