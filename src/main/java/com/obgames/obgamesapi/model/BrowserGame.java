package com.obgames.obgamesapi.model;


import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "browserGame")
public class BrowserGame {
    
    @Id
    private String id;

    private String nome;

    private String urlImagem;

    private String urlVideo;

    private String urlJogo;

    private String descricao;

    private Date timestamp = new Date();

   private String dataCriacao = new SimpleDateFormat("dd 'de' MMMM 'de' yyyy").format(new Date());


    @DBRef
    private Categoria categoria;

    public String getNome() {
        return nome;
    }

   public String getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(String dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }


    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getUrlJogo() {
        return urlJogo;
    }

    public void setUrlJogo(String urlJogo) {
        this.urlJogo = urlJogo;
    }

    public String getUrlVideo() {
        return urlVideo;
    }

    public void setUrlVideo(String urlVideo) {
        this.urlVideo = urlVideo;
    }

    public String getUrlImagem() {
        return urlImagem;
    }

    public void setUrlImagem(String urlImagem) {
        this.urlImagem = urlImagem;
    }


}
