package com.joaosantosdev.feirouu.application.domains.models;

import com.joaosantosdev.feirouu.application.domains.enums.Status;
import com.joaosantosdev.feirouu.application.domains.ports.out.ProdutoPersistencePort;

import java.util.List;

public class Produto {

    private Long id;
    private String nome;
    private String descricao;
    private Double valorCompra;
    private Double valorVenda;
    private Loja loja;
    private Status status;
    private List<ProdutoChaveEtiqueta> chavesEtiquetas;

    public Produto(Long id, String nome, String descricao, Double valorCompra, Double valorVenda, Loja loja, Status status,  List<ProdutoChaveEtiqueta> chavesEtiquetas) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.valorCompra = valorCompra;
        this.valorVenda = valorVenda;
        this.loja = loja;
        this.status = status;
        this.chavesEtiquetas = chavesEtiquetas;
    }

    public Produto(Long id, String nome, String descricao, Double valorCompra, Double valorVenda, Status status, List<ProdutoChaveEtiqueta> chavesEtiquetas) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.valorCompra = valorCompra;
        this.valorVenda = valorVenda;
        this.status = status;
        this.chavesEtiquetas = chavesEtiquetas;
    }

    public Produto(Long id) {
        this.id = id;
    }

    public Produto(Long id, Loja loja) {
        this.id = id;
        this.loja = loja;
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public Double getValorCompra() {
        return valorCompra;
    }

    public Double getValorVenda() {
        return valorVenda;
    }

    public Loja getLoja() {
        return loja;
    }

    public Status getStatus() {
        return status;
    }

    public List<ProdutoChaveEtiqueta> getChavesEtiquetas() {
        return chavesEtiquetas;
    }

    public Long cadastrar(ProdutoPersistencePort produtoPersistencePort) {
        return produtoPersistencePort.cadastrar(this);
    }

    public void atualizar(ProdutoPersistencePort produtoPersistencePort) {
        produtoPersistencePort.atualizar(this);
    }
}
