package com.obgames.obgamesapi.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "roles")
public class Role {

    @Id
    private String id;

    private EnumRole nome;

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public EnumRole getNome() {
        return nome;
    }
    public void setNome(EnumRole nome) {
        this.nome = nome;
    }


}
