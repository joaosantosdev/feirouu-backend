package com.joaosantosdev.feirouu.application.domains.models;

public class Cidade {
    private Integer id;
    private String nome;
    private Estado estado;

    public Cidade(Integer id) {
        this.id = id;
    }

    public Cidade(Integer id, String nome, Estado estado) {
        this.id = id;
        this.nome = nome;
        this.estado = estado;
    }

    public Integer getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public Estado getEstado() {
        return estado;
    }
}
