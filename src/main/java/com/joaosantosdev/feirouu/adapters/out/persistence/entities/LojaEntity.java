package com.joaosantosdev.feirouu.adapters.out.persistence.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Table(name = "loja")
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LojaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String nome;

    @Column
    private String descricao;

    @Column
    private String telefone;

    @Column
    private String email;


    @ManyToOne
    @JoinColumn(name = "endereco_id")
    private EnderecoEntity endereco;

    @OneToOne
    @JoinColumn(name = "usuario_id")
    private UsuarioEntity usuario;


    public LojaEntity(Long id) {
        this.id = id;
    }
}
