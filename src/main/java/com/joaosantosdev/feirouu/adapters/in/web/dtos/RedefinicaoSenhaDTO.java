package com.joaosantosdev.feirouu.adapters.in.web.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RedefinicaoSenhaDTO {
    private String email;
    private String senha;
    private String codigoVerificacao;
}
