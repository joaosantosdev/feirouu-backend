package com.joaosantosdev.feirouu.adapters.in.web.dtos;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EnderecoDTO {
    private Long id;
    private String numero;
    private String cep;
    private String complemento;
    private String bairro;
    private String rua;
    private Integer cidadeId;
    private Integer estadoId;
}
