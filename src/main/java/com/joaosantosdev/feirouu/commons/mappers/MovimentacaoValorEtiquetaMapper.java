package com.joaosantosdev.feirouu.commons.mappers;

import com.joaosantosdev.feirouu.adapters.out.persistence.entities.MovimentacaoEnitity;
import com.joaosantosdev.feirouu.adapters.out.persistence.entities.MovimentacaoValorEtiquetaEntity;
import com.joaosantosdev.feirouu.adapters.out.persistence.entities.ProdutoChaveEtiquetaEntity;
import com.joaosantosdev.feirouu.application.domains.models.Movimentacao;

import java.util.List;
import java.util.stream.Collectors;

public class MovimentacaoValorEtiquetaMapper {
    public static List<MovimentacaoValorEtiquetaEntity> map(Movimentacao movimentacao, MovimentacaoEnitity movimentacaoEnitity) {
        return movimentacao.getValoresEtiquetas().stream().
                map(item -> new MovimentacaoValorEtiquetaEntity(item.getValor(),
                            new ProdutoChaveEtiquetaEntity(item.getProdutoChaveEtiqueta().getId()),
                            movimentacaoEnitity))
                .collect(Collectors.toList());
    }
}
