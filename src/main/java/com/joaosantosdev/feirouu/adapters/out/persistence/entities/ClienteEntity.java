package com.joaosantosdev.feirouu.adapters.out.persistence.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Table(name = "cliente")
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ClienteEntity {

    @Id
    @Column
    private String cpf;

    @Column
    private String nome;

    @Column
    private String email;

    @Column
    private String telefone;

    public ClienteEntity(String cpf) {
        this.cpf = cpf;
    }
}
