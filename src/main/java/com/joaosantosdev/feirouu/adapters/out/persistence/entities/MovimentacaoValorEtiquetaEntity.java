package com.joaosantosdev.feirouu.adapters.out.persistence.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Table(name = "movimentacaoValorEtiqueta")
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class MovimentacaoValorEtiquetaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String valor;

    @ManyToOne
    @JoinColumn(name = "produto_chave_etiqueta_id")
    @JsonIgnore
    private ProdutoChaveEtiquetaEntity produtoChaveEtiqueta;

    @ManyToOne
    @JoinColumn(name = "movimentacao_id")
    @JsonIgnore
    private MovimentacaoEnitity movimentacao;

    public MovimentacaoValorEtiquetaEntity(String valor, ProdutoChaveEtiquetaEntity produtoChaveEtiqueta, MovimentacaoEnitity movimentacao) {
        this.valor = valor;
        this.produtoChaveEtiqueta = produtoChaveEtiqueta;
        this.movimentacao = movimentacao;
    }
}
