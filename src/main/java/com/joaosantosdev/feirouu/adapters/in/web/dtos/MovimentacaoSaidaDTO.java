package com.joaosantosdev.feirouu.adapters.in.web.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MovimentacaoSaidaDTO {
    private Long id;
    private Integer quantidade;
    private Double valorDesconto;
    private Long produtoId;
}
