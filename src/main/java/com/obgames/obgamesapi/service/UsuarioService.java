package com.obgames.obgamesapi.service;

import java.util.List;
import java.util.Optional;

import com.obgames.obgamesapi.exceptions.ResourceNotFoundException;
import com.obgames.obgamesapi.exceptions.ResponseStatusException;
import com.obgames.obgamesapi.model.Usuario;

public interface UsuarioService {

        Usuario createUsuario(Usuario usuario);
    
        Optional<Usuario>  updateUsuario(Usuario usuario, String id) throws ResourceNotFoundException;

        Boolean  verifyUsuarioExistsByUsername(String username);
    
        List<Usuario> getAllUsuario();
    
        Usuario getUsuarioById(String id) throws ResourceNotFoundException;
    
        void deleteUsuario(String id) throws ResourceNotFoundException, ResponseStatusException;
    
}
