package com.joaosantosdev.feirouu.adapters.out.persistence.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Table(name = "produtoChaveEtiqueta")
@Entity
@NoArgsConstructor
@Getter
@Setter
public class ProdutoChaveEtiquetaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String nome;

    @ManyToOne
    @JoinColumn(name = "produto_id")
    @JsonIgnore
    private ProdutoEntity produto;


    @OneToMany(mappedBy = "produtoChaveEtiqueta")
    @JsonIgnore
    private List<MovimentacaoValorEtiquetaEntity> valoresEtiquetas;

    public ProdutoChaveEtiquetaEntity(Long id, String nome, ProdutoEntity produto) {
        this.id = id;
        this.nome = nome;
        this.produto = produto;
    }

    public ProdutoChaveEtiquetaEntity(Long id) {
        this.id = id;
    }
}
