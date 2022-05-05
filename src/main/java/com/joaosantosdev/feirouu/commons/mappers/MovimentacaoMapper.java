package com.joaosantosdev.feirouu.commons.mappers;

import com.joaosantosdev.feirouu.adapters.in.web.dtos.ListagemMovimentacaoDTO;
import com.joaosantosdev.feirouu.adapters.in.web.dtos.MovimentacaoDevolucaoDTO;
import com.joaosantosdev.feirouu.adapters.in.web.dtos.MovimentacaoEntradaDTO;
import com.joaosantosdev.feirouu.adapters.in.web.dtos.MovimentacaoSaidaDTO;
import com.joaosantosdev.feirouu.application.domains.models.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class MovimentacaoMapper {

    public static Movimentacao mapEntrada(Long lojaId,
                                          Long produtoId,
                                          Usuario usuario,
                                          MovimentacaoEntradaDTO movimentacaoEntradaDTO) {
        List<MovimentacaoValorEtiqueta> valoresEtiquetas = null;

        valoresEtiquetas = movimentacaoEntradaDTO.getValoresEtiquetas().stream().
                map(item -> new MovimentacaoValorEtiqueta(item.getValor(),
                        new ProdutoChaveEtiqueta(item.getProdutoChaveEtiquetaId())))
                .collect(Collectors.toList());

        return new Movimentacao(movimentacaoEntradaDTO.getQuantidade(),
                new Produto(produtoId, new Loja(lojaId, usuario)),
                valoresEtiquetas);
    }

    public static List<Movimentacao> mapSaidas(Long lojaId,
                                               Usuario usuario,
                                               Cliente cliente,
                                               List<MovimentacaoSaidaDTO> saidas) {
        List<Movimentacao> movimentacoes = new ArrayList<>();
        for (MovimentacaoSaidaDTO dto : saidas) {
            movimentacoes.add(new Movimentacao(dto.getQuantidade(),
                    dto.getValorDesconto(),
                    new Produto(dto.getProdutoId(), new Loja(lojaId, usuario)),
                    cliente));
        }
        return movimentacoes;
    }

    public static List<Movimentacao> mapDevolucoes(List<MovimentacaoDevolucaoDTO> devolucoes) {
        List<Movimentacao> movimentacoes = new ArrayList<>();
        for (MovimentacaoDevolucaoDTO dto : devolucoes) {
            movimentacoes.add(new Movimentacao(dto.getQuantidade(), new Movimentacao(dto.getMovimentacaoReferenciaId())));
        }
        return movimentacoes;
    }

    public static ListagemMovimentacaoDTO map(Movimentacao movimentacao) {
        return new ListagemMovimentacaoDTO(movimentacao.getId(),
                movimentacao.getQuantidade(),
                movimentacao.getValorVenda(),
                movimentacao.getProduto().getId(),
                movimentacao.getProduto().getNome(),
                movimentacao.getDataHora());
    }
}
