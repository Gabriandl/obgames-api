package com.obgames.obgamesapi.model;

import java.util.ArrayList;



public class Curtida {
    //private Usuario usuario;
    private ArrayList<Usuario> curtidas = new ArrayList<Usuario>();


    public ArrayList<Usuario> getCurtidas() {
        return curtidas;
    }

    public void setCurtida(Usuario usuario) {
        this.curtidas.add(usuario);
    }
}
