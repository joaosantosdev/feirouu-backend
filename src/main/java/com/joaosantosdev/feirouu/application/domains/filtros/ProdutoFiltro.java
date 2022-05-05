package com.joaosantosdev.feirouu.application.domains.filtros;

import java.util.ArrayList;

public class ProdutoFiltro {
    private String nome;
    private ArrayList<Integer> status;

    public ProdutoFiltro(String nome, ArrayList<Integer> status) {
        this.nome = nome;
        this.status = status;
    }

    public String getNome() {
        return nome;
    }

    public ArrayList<Integer>  getStatus() {
        return status;
    }
}
