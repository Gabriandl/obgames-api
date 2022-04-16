package com.obgames.obgamesapi.model;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "usuario")
public class Usuario {

    @Id
    private String id;

    private String nomeCompleto ;

    @JsonIgnore
    private String username;

    private String dataNasc;

    private Estados estado;

    private String pais;
    
    @JsonIgnore
    private String senha;
    
    @DBRef
    private Set<Role> roles ;

    private long timestamp = System.currentTimeMillis()/1000;

    private String dataCriacao = new SimpleDateFormat("dd 'de' MMMM 'de' yyyy").format(new Date());

    public Usuario(String nomeCompleto, String username, String dataNasc, Estados estado, String pais, String senha) {
        this.nomeCompleto = nomeCompleto;
        this.username = username;
        this.dataNasc = dataNasc;
        this.estado = estado;
        this.pais = pais;
        this.senha = senha;
    }

    public Usuario() {
    }

    public String getId() {
        return id;
    }

    public String getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(String dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public Estados getEstado() {
        return estado;
    }

    public void setEstado(Estados estado) {
        this.estado = estado;
    }

    public String getDataNasc() {
        return dataNasc;
    }

    public void setDataNasc(String dataNasc) {
        this.dataNasc = dataNasc;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNomeCompleto() {
        return nomeCompleto;
    }

    public void setNomeCompleto(String nomeCompleto) {
        this.nomeCompleto = nomeCompleto;
    }

    public void setId(String id) {
        this.id = id;
    }
}
