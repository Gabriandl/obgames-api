package com.obgames.obgamesapi.service;

import java.util.List;
import java.util.Optional;

import com.obgames.obgamesapi.exceptions.ResourceNotFoundException;
import com.obgames.obgamesapi.exceptions.ResponseStatusException;
import com.obgames.obgamesapi.model.Avaliacao;

public interface AvaliacaoService {

        Avaliacao createAvaliacao(Avaliacao avaliacao);
    
        Optional<Avaliacao>  updateAvaliacao(Avaliacao avaliacao, String id) throws ResourceNotFoundException, Exception;
    
        List<Avaliacao> getAllAvaliacao();
    
        Avaliacao getAvaliacaoById(String id) throws ResourceNotFoundException;
    
        void deleteAvaliacao(String id) throws ResourceNotFoundException, ResponseStatusException;

        Optional<Avaliacao> insertCurtidaIntoAvaliacao(String id, String usuarioId) throws Exception;

        Optional<Avaliacao> deleteCurtidaFromAvaliacao(String id, String usuarioId) throws Exception;

    
}
