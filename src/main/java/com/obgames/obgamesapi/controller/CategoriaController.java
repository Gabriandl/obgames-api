package com.obgames.obgamesapi.controller;

import java.util.List;
import java.util.Optional;

import com.obgames.obgamesapi.exceptions.ResourceNotFoundException;
import com.obgames.obgamesapi.exceptions.ResponseStatusException;
import com.obgames.obgamesapi.model.Categoria;
import com.obgames.obgamesapi.service.CategoriaService;

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
public class CategoriaController {
    
    @Autowired
    private CategoriaService categoriaService;

    @GetMapping("/categorias")
    public ResponseEntity<List<Categoria>> getAllCategoria() {
        return ResponseEntity.ok().body(categoriaService.getAllCategoria());
    }

    @GetMapping("/categorias/{id}")
    public ResponseEntity<Categoria> getCategoriaById(@PathVariable String id) throws ResourceNotFoundException {
        return ResponseEntity.ok().body(categoriaService.getCategoriaById(id));
    }

    @PostMapping("/categorias")
    public ResponseEntity < Categoria > createCategoria(@RequestBody Categoria categoria) {
        return ResponseEntity.ok().body(this.categoriaService.createCategoria(categoria));
    }

    @PutMapping("/categorias/{id}")
    public ResponseEntity<Optional<Categoria>> updateCategoria(@PathVariable String id, @RequestBody Categoria categoria) throws ResourceNotFoundException {
        categoria.setId(id);
        return ResponseEntity.ok().body(this.categoriaService.updateCategoria(categoria, id));
    }

    @DeleteMapping("/categorias/{id}")
    public HttpStatus deleteCategoria(@PathVariable String id) throws ResourceNotFoundException, ResponseStatusException {
        this.categoriaService.deleteCategoria(id);
        return HttpStatus.OK;
    }
}
