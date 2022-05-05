package com.joaosantosdev.feirouu.adapters.out.persistence.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Table(name = "produto")
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProdutoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String nome;

    @Column
    private String descricao;

    @Column
    private Double valorCompra;

    @Column
    private Double valorVenda;

    @ManyToOne
    @JoinColumn(name = "loja_id")
    private LojaEntity loja;

    @Column
    private Integer status;

    @OneToMany(mappedBy = "produto")
    private List<ProdutoChaveEtiquetaEntity> chavesEtiquetas;

    public ProdutoEntity(Long id) {
        this.id = id;
    }
}
