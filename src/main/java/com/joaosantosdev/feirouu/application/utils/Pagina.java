package com.joaosantosdev.feirouu.application.utils;

import java.util.ArrayList;

public class Pagina <T>{
    private ArrayList<T> dados = new ArrayList<>();
    private Long totalGeral;
    private Integer total;

    public Pagina(ArrayList<T> dados, Long totalGeral, Integer total) {
        this.dados = dados;
        this.totalGeral = totalGeral;
        this.total = total;
    }

    public ArrayList<T> getDados() {
        return dados;
    }

    public Long getTotalGeral() {
        return totalGeral;
    }

    public Integer getTotal() {
        return total;
    }
}
