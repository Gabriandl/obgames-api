package com.obgames.obgamesapi.model;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "avaliacao")
public class Avaliacao {

    @Id
    private String id;

    private Double numEstrelas = 0.0;

    private String comentario;

    @DBRef
    private ArrayList<Usuario> curtidas = new ArrayList<>();

    private Date timestamp = new Date();

    private String dataCriacao = new SimpleDateFormat("dd 'de' MMMM 'de' yyyy").format(new Date());

    @DBRef
    private BrowserGame browserGame;

    @DBRef
    private Usuario usuario;

    public Double getNumEstrelas() {
        return numEstrelas;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public BrowserGame getBrowserGame() {
        return browserGame;
    }

    public void setBrowserGame(BrowserGame browserGame) {
        this.browserGame = browserGame;
    }

    public String getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(String dataCriacao) {
        if (this.dataCriacao == null)
        this.dataCriacao = dataCriacao;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        if (this.timestamp == null)
        this.timestamp = timestamp;
    }

    public ArrayList<Usuario> getCurtidas() {
        return curtidas;
    }

    public void setCurtida(ArrayList<Usuario> curtida) {
        this.curtidas = curtida;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public void setNumEstrelas(Double numEstrelas) {
        this.numEstrelas = numEstrelas;
    }
    
}
