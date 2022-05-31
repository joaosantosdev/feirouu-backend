package com.joaosantosdev.feirouu.commons.utils.img;

public enum Diretorio {
    USUARIOS(1, "usuarios"),
    LOJAS(2, "lojas"),
    PRODUTOS(3, "produtos");

    private Integer tipo;
    private String descricao;

    Diretorio(Integer tipo, String descricao){
        this.tipo = tipo;
        this.descricao = descricao;
    }

     public static Diretorio get(Integer tipo){
        for(Diretorio diretorio: Diretorio.values()){
            if(diretorio.tipo.equals(tipo)){
                return diretorio;
            }
        }
        return null;
    }

    public String pasta(){
        return this.descricao;
    }

    public Integer tipo(){
        return this.tipo;
    }
}
