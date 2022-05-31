package com.joaosantosdev.feirouu.adapters.in.web.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RelatorioDTO {
    private String nome;
    private Integer quantidade;
    private Double valorCompra;
    private Double valorVenda;
    private Double valorDesconto;
    private LocalDateTime dataHora;
}
