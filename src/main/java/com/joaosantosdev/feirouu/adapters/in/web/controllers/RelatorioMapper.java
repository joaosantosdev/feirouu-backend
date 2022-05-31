package com.joaosantosdev.feirouu.adapters.in.web.controllers;

import com.joaosantosdev.feirouu.adapters.in.web.dtos.RelatorioDTO;
import com.joaosantosdev.feirouu.application.domains.models.Movimentacao;

import java.util.List;
import java.util.stream.Collectors;

public class RelatorioMapper {
    public static List<RelatorioDTO> map(List<Movimentacao> movimentacoes) {
        return movimentacoes.stream().map(item -> new RelatorioDTO(item.getProduto().getNome(),
                item.getQuantidade(),
                item.getValorCompra(), item.getValorVenda(),
                item.getValorDesconto(), item.getDataHora())).collect(Collectors.toList());
    }
}
