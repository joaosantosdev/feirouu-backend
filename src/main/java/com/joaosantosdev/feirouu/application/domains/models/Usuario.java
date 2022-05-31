package com.joaosantosdev.feirouu.application.domains.models;

import com.joaosantosdev.feirouu.application.domains.ports.out.CodigoVerificacaoPersistencePort;
import com.joaosantosdev.feirouu.application.domains.ports.out.UsuarioPersistencePort;
import com.joaosantosdev.feirouu.application.exceptions.NegocioException;

public class Usuario {
    private Long id;
    private String nome;
    private String email;
    private String senha;
    private String telefone;

    public Usuario(String nome, String email, String senha, String telefone) {
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.telefone = telefone;
    }

    public Usuario(Long id, String nome, String email, String senha, String telefone) {
        this(nome, email, senha, telefone);
        this.id = id;
    }

    public Usuario(Long id, String email) {
        this.id = id;
        this.email = email;
    }

    public Usuario(String email, String senha) {
        this.email = email;
        this.senha = senha;
    }

    public Long cadastrar(UsuarioPersistencePort usuarioPersistencePort,
                          CodigoVerificacaoPersistencePort codigoVerificacaoPersistencePort,
                          CodigoVerificacao codigoVerificacao) {
        if (usuarioPersistencePort.verificarEmail(this.getEmail())) {
            throw new NegocioException(400, "Email já possui cadastro");
        }
        codigoVerificacao.validar(codigoVerificacaoPersistencePort);
        return usuarioPersistencePort.salvar(this);
    }


    public void atualizar(UsuarioPersistencePort usuarioPersistencePort,
                          CodigoVerificacaoPersistencePort codigoVerificacaoPersistencePort,
                          CodigoVerificacao codigoVerificacao) {
        Usuario usuario = usuarioPersistencePort.obterPorId(this.id);


        if (!usuario.getEmail().trim().equals(this.getEmail())) {
            if (usuarioPersistencePort.verificarEmail(this.getEmail()))
                throw new NegocioException(400, "Email já possui cadastro");

            codigoVerificacao.validar(codigoVerificacaoPersistencePort);
        }
        usuarioPersistencePort.atualizar(this);
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public String getSenha() {
        return senha;
    }

    public String getTelefone() {
        return telefone;
    }

    public void redefinirSenha(UsuarioPersistencePort usuarioPersistencePort, CodigoVerificacaoPersistencePort codigoVerificacaoPersistencePort, CodigoVerificacao codigoVerificacao) {
        codigoVerificacao.validar(codigoVerificacaoPersistencePort);
        usuarioPersistencePort.redefinirSenha(this);

    }
}
