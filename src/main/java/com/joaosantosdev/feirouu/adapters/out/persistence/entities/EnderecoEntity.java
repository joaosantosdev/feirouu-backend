package com.joaosantosdev.feirouu.adapters.out.persistence.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Table(name = "endereco")
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class EnderecoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String numero;

    @Column
    private String cep;

    @Column
    private String complemento;

    @Column
    private String bairro;

    @Column
    private String rua;

    @ManyToOne
    @JoinColumn(name = "cidade_id")
    private CidadeEntity cidade;

}