package com.joaosantosdev.feirouu.application.utils;

public class Paginacao {
    private Integer pagina;
    private Integer porPagina;
    private String ordenarPor;
    private String direcao;

    public Paginacao(Integer pagina, Integer porPagina, String ordenarPor, String direcao) {
        this.pagina = pagina;
        this.porPagina = porPagina;
        this.ordenarPor = ordenarPor;
        this.direcao = direcao;
    }

    public Integer getPagina() {
        return pagina;
    }

    public Integer getPorPagina() {
        return porPagina;
    }

    public String getOrdenarPor() {
        return ordenarPor;
    }

    public String getDirecao() {
        return direcao;
    }
}
