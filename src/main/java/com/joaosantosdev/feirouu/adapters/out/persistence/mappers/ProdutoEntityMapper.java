package com.joaosantosdev.feirouu.adapters.out.persistence.mappers;

import com.joaosantosdev.feirouu.adapters.out.persistence.entities.LojaEntity;
import com.joaosantosdev.feirouu.adapters.out.persistence.entities.ProdutoChaveEtiquetaEntity;
import com.joaosantosdev.feirouu.adapters.out.persistence.entities.ProdutoEntity;
import com.joaosantosdev.feirouu.application.domains.models.Produto;

import java.util.List;
import java.util.stream.Collectors;

public class ProdutoEntityMapper {
    public static ProdutoEntity map(Produto produto) {
        ProdutoEntity produtoEntity = new ProdutoEntity(
                produto.getId(),
                produto.getNome(),
                produto.getDescricao(),
                produto.getValorCompra(),
                produto.getValorVenda(),
                LojaEntityMapper.map(produto.getLoja()),
                produto.getStatus().value(),
                null
        );
        List<ProdutoChaveEtiquetaEntity> chavesEtiquetas = produto.getChavesEtiquetas().stream()
                .map(item -> new ProdutoChaveEtiquetaEntity(item.getId(), item.getNome(), produtoEntity)).collect(Collectors.toList());

        produtoEntity.setChavesEtiquetas(chavesEtiquetas);
        return produtoEntity;
    }
}
