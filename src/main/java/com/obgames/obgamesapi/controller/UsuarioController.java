package com.obgames.obgamesapi.controller;


import java.util.List;
import java.util.Optional;

import com.obgames.obgamesapi.exceptions.ResourceNotFoundException;
import com.obgames.obgamesapi.exceptions.ResponseStatusException;
import com.obgames.obgamesapi.model.Usuario;
import com.obgames.obgamesapi.service.UsuarioService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UsuarioController {
    
    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/usuarios")
    public ResponseEntity<List<Usuario>> getAllUsuario() {
        return ResponseEntity.ok().body(usuarioService.getAllUsuario());
    }

    @GetMapping("/usuarios/{id}")
    public ResponseEntity<Usuario> getUsuarioById(@PathVariable String id) throws ResourceNotFoundException {
        return ResponseEntity.ok().body(usuarioService.getUsuarioById(id));
    }

    @PostMapping("/usuarios")
    public ResponseEntity < Usuario > createUsuario(@RequestBody Usuario usuario) {
        return ResponseEntity.ok().body(this.usuarioService.createUsuario(usuario));
    }

    @PutMapping("/usuarios/{id}")
    public ResponseEntity<Optional<Usuario>> updateUsuario(@PathVariable String id, @RequestBody Usuario usuario) throws ResourceNotFoundException {
        usuario.setId(id);
        return ResponseEntity.ok().body(this.usuarioService.updateUsuario(usuario, id));
    }
    
    @DeleteMapping("/usuarios/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public HttpStatus deleteUsuario(@PathVariable String id) throws ResourceNotFoundException, ResponseStatusException {
        this.usuarioService.deleteUsuario(id);
        return HttpStatus.OK;
    }
}