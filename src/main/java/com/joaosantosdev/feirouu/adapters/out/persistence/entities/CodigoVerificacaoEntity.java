package com.joaosantosdev.feirouu.adapters.out.persistence.entities;

import lombok.Getter;

import javax.persistence.*;

@Table(name = "codigoVerificacao")
@Entity
@Getter
public class CodigoVerificacaoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String email;

    @Column
    private String codigo;

    @Column
    private Long timestampCriacao;

    public CodigoVerificacaoEntity(String email, String codigo, Long timestampCriacao) {
        this.email = email;
        this.codigo = codigo;
        this.timestampCriacao = timestampCriacao;
    }

    public CodigoVerificacaoEntity() {
    }
}
