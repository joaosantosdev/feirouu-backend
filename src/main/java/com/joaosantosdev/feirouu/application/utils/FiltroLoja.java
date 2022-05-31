package com.joaosantosdev.feirouu.application.utils;

public class FiltroLoja {
    public String nome;
    public Integer cidadeId;
    public Integer estadoId;
    public String bairro;

    public FiltroLoja(String nome, Integer cidadeId, Integer estadoId, String bairro) {
        this.nome = nome;
        this.cidadeId = cidadeId;
        this.estadoId = estadoId;
        this.bairro = bairro;
    }

    public String getNome() {
        return nome;
    }

    public Integer getCidadeId() {
        return cidadeId;
    }

    public Integer getEstadoId() {
        return estadoId;
    }

    public String getBairro() {
        return bairro;
    }
}
