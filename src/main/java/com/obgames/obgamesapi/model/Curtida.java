package com.obgames.obgamesapi.model;

import java.util.ArrayList;

import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;


public class Curtida {
    //private Usuario usuario;
    private ArrayList<Usuario> curtidas = new ArrayList<Usuario>();
    public ArrayList<Usuario>  Curtida() {
        return curtidas;
    }

    public ArrayList<Usuario> getCurtidas() {
        return curtidas;
    }

    public void setCurtida(Usuario usuario) {
        this.curtidas.add(usuario);
    }
}
