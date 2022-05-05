package com.joaosantosdev.feirouu.adapters.in.web.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuarioFormDTO {
    private String nome;
    private String email;
    private String senha;
    private String telefone;
    private String codigoVerificacao;
}
