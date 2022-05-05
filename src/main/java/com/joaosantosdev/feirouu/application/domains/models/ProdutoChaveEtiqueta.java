package com.joaosantosdev.feirouu.application.domains.models;

public class ProdutoChaveEtiqueta {
    private Long id;
    private String nome;

    public ProdutoChaveEtiqueta(Long id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public ProdutoChaveEtiqueta(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
