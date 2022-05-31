package com.joaosantosdev.feirouu.application.domains.models;

import com.joaosantosdev.feirouu.application.domains.ports.out.LojaPersistencePort;
import com.joaosantosdev.feirouu.application.exceptions.NegocioException;

import java.util.HashSet;
import java.util.Set;

public class Loja {

    private Long id;
    private String nome;
    private String descricao;
    private String telefone;
    private String email;
    private Endereco endereco;
    private Usuario usuario;

    public Loja(Long id, String nome, String descricao, String telefone, String email, Endereco endereco, Usuario usuario) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.telefone = telefone;
        this.email = email;
        this.endereco = endereco;
        this.usuario = usuario;
    }

    public Loja(String nome, String descricao, String telefone, String email, Endereco endereco, Usuario usuario) {
        this.nome = nome;
        this.descricao = descricao;
        this.telefone = telefone;
        this.email = email;
        this.endereco = endereco;
        this.usuario = usuario;
    }


    public Loja(Long lojaId, Usuario usuario) {
        this.id = lojaId;
        this.usuario = usuario;
    }


    public Loja cadastrar(LojaPersistencePort lojaPersistencePort) {
        if(lojaPersistencePort.buscarLojaPorUsuarioId(this.usuario.getId()) != null){
            throw new NegocioException(400, "Usuário já possui loja cadastrada.");
        }
        return lojaPersistencePort.cadastrar(this);
    }

    public void atualizar(LojaPersistencePort lojaPersistencePort) {
        lojaPersistencePort.atualizar(this);
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

    public String getTelefone() {
        return telefone;
    }

    public String getEmail() {
        return email;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public Usuario getUsuario() {
        return usuario;
    }

}
