package com.joaosantosdev.feirouu.adapters.in.web.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MovimentacaoDevolucaoDTO {
    private Long id;
    private Integer quantidade;
    private Long movimentacaoReferenciaId;
}
