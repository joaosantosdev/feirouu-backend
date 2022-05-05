package com.joaosantosdev.feirouu.application.domains.models;

public class Endereco {

    private Long id;
    private String numero;
    private String cep;
    private String complemento;
    private String bairro;
    private String rua;
    private Cidade cidade;


    public Endereco(Long id, String numero, String cep, String complemento, String bairro, String rua, Cidade cidade) {
        this.id = id;
        this.numero = numero;
        this.cep = cep;
        this.complemento = complemento;
        this.bairro = bairro;
        this.rua = rua;
        this.cidade = cidade;
    }

    public Long getId() {
        return id;
    }

    public String getNumero() {
        return numero;
    }

    public String getCep() {
        return cep;
    }

    public String getComplemento() {
        return complemento;
    }

    public String getBairro() {
        return bairro;
    }

    public String getRua() {
        return rua;
    }

    public Cidade getCidade() {
        return cidade;
    }
}
