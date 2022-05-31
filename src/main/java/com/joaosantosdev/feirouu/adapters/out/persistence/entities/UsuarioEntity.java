package com.joaosantosdev.feirouu.adapters.out.persistence.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Table(name = "usuario")
@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Setter
public class UsuarioEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String nome;

    @Column
    private String email;

    @Column
    private String senha;

    @Column
    private String telefone;

}
