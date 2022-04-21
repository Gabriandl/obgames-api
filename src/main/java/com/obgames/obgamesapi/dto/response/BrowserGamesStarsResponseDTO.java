package com.obgames.obgamesapi.dto.response;

public class BrowserGamesStarsResponseDTO {
    private String idBrowserGame;
    private Double avgEstrelas;
    private Integer qtdAvaliacoes;
    
    public BrowserGamesStarsResponseDTO(String idBrowserGame, double avgEstrelas, Integer qtdAvaliacoes) {
        this.idBrowserGame = idBrowserGame;
        this.avgEstrelas = avgEstrelas;
        this.qtdAvaliacoes = qtdAvaliacoes;
    }

    public String getIdBrowserGame() {
        return idBrowserGame;
    }

    public void setIdBrowserGame(String idBrowserGame) {
        this.idBrowserGame = idBrowserGame;
    }

    public Double getAvgEstrelas() {
        return avgEstrelas;
    }

    public void setAvgEstrelas(Double avgEstrelas) {
        this.avgEstrelas = avgEstrelas;
    }

    public Integer getQtdAvaliacoes() {
        return qtdAvaliacoes;
    }

    public void setQtdAvaliacoes(Integer qtdAvaliacoes) {
        this.qtdAvaliacoes = qtdAvaliacoes;
    }

}
