package com.obgames.obgamesapi.dto.response;

import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.obgames.obgamesapi.model.BrowserGame;
import com.obgames.obgamesapi.model.Categoria;
import com.obgames.obgamesapi.model.Usuario;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class RelatorioResponseDTO {

    ArrayList<BrowserGame> browserGames;

    ArrayList<Usuario> usuarios;

    ArrayList<Categoria> categorias;

    public ArrayList<BrowserGame> getBrowserGames() {
        return browserGames;
    }

    public void setBrowserGames(ArrayList<BrowserGame> browserGames) {
        this.browserGames = browserGames;
    }

    public ArrayList<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(ArrayList<Usuario> usuarios) {
        this.usuarios = usuarios;
    }

    public ArrayList<Categoria> getCategorias() {
        return categorias;
    }

    public void setCategorias(ArrayList<Categoria> categorias) {
        this.categorias = categorias;
    }
 
}
