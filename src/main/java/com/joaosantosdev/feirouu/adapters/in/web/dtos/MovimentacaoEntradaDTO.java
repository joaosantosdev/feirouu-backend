package com.joaosantosdev.feirouu.adapters.in.web.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class MovimentacaoEntradaDTO {
    private Long id;
    private Integer quantidade;
    private List<MovimentacaoValorEtiquetaDTO> valoresEtiquetas;
}
