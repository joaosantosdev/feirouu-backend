package com.joaosantosdev.feirouu.adapters.in.web.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LojaDTO {

    private Long id;
    private String nome;
    private String descricao;
    private String telefone;
    private String email;
    private EnderecoDTO endereco;


}
