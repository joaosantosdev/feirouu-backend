package com.joaosantosdev.feirouu.adapters.in.web.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class FiltroLojaDTO {
    private String nome;
    private Integer cidadeId;
    private Integer estadoId;
    private String bairro;
}
