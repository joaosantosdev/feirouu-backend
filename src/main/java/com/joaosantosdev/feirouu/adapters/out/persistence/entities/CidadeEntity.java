package com.joaosantosdev.feirouu.adapters.out.persistence.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Table(name = "cidade")
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class CidadeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String nome;

    @ManyToOne
    @JoinColumn(name = "estado_id")
    private EstadoEntity estado;

    public CidadeEntity(Integer id) {
        this.id = id;
    }
}
