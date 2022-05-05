package com.joaosantosdev.feirouu.application.domains.models;

public class Estado {
    private Integer id;
    private String nome;
    private String uf;

    public Estado(Integer id) {
        this.id = id;
    }

    public Estado(Integer id, String nome, String uf) {
        this.id = id;
        this.nome = nome;
        this.uf = uf;
    }

    public Integer getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getUf() {
        return uf;
    }
}
