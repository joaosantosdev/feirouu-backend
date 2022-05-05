package com.joaosantosdev.feirouu.adapters.out.persistence.mappers;

import com.joaosantosdev.feirouu.adapters.out.persistence.entities.MovimentacaoEnitity;
import com.joaosantosdev.feirouu.application.domains.enums.TipoMovimentacao;
import com.joaosantosdev.feirouu.application.domains.models.Movimentacao;
import com.joaosantosdev.feirouu.commons.mappers.ProdutoMapper;

public class MovimentacaoEntityMapper {


    public static Movimentacao map(MovimentacaoEnitity movimentacaoEnitity) {
        return new Movimentacao(
                movimentacaoEnitity.getId(),
                movimentacaoEnitity.getValorDesconto(),
                movimentacaoEnitity.getValorCompra(),
                movimentacaoEnitity.getValorVenda(),
                movimentacaoEnitity.getQuantidade(),
                movimentacaoEnitity.getDataHora(),
                ProdutoMapper.map(movimentacaoEnitity.getProduto()),
                ClienteEntityMapper.map(movimentacaoEnitity.getCliente()),
                TipoMovimentacao.of(movimentacaoEnitity.getTipoMovimentacao())
        );
    }
}
