package com.joaosantosdev.feirouu.application.domains.models;

public class MovimentacaoValorEtiqueta {
    private String valor;
    private ProdutoChaveEtiqueta produtoChaveEtiqueta;

    public MovimentacaoValorEtiqueta(String valor, ProdutoChaveEtiqueta produtoChaveEtiqueta) {
        this.valor = valor;
        this.produtoChaveEtiqueta = produtoChaveEtiqueta;
    }


    public String getValor() {
        return valor;
    }

    public ProdutoChaveEtiqueta getProdutoChaveEtiqueta() {
        return produtoChaveEtiqueta;
    }
}
