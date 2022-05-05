package com.joaosantosdev.feirouu.adapters.in.web.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class ListagemMovimentacaoDTO {
    private Long id;
    private Integer quantidade;
    private Double valorVenda;
    private Long produtoId;
    private String produtoNome;
    private LocalDateTime dataHora;

}
