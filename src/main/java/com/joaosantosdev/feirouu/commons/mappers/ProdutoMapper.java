package com.joaosantosdev.feirouu.commons.mappers;

import com.joaosantosdev.feirouu.adapters.in.web.dtos.ProdutoChaveEtiquetaDTO;
import com.joaosantosdev.feirouu.adapters.in.web.dtos.ProdutoDTO;
import com.joaosantosdev.feirouu.adapters.out.persistence.entities.ProdutoEntity;
import com.joaosantosdev.feirouu.adapters.out.persistence.mappers.LojaEntityMapper;
import com.joaosantosdev.feirouu.application.domains.enums.Status;
import com.joaosantosdev.feirouu.application.domains.models.Produto;
import com.joaosantosdev.feirouu.application.domains.models.ProdutoChaveEtiqueta;
import com.joaosantosdev.feirouu.application.domains.models.Usuario;

import java.util.List;
import java.util.stream.Collectors;


public class ProdutoMapper {
    public static Produto map(Usuario usuario, Long lojaId, ProdutoDTO produtoDTO) {
        List<ProdutoChaveEtiqueta> chavesEtiquetas = produtoDTO.getChavesEtiquetas().stream()
                .map(item -> new ProdutoChaveEtiqueta(item.getId(), item.getNome())).collect(Collectors.toList());
        return new Produto(produtoDTO.getId(),
                produtoDTO.getNome(),
                produtoDTO.getDescricao(),
                produtoDTO.getValorCompra(),
                produtoDTO.getValorVenda(),
                LojaMapper.map(usuario, lojaId),
                Status.of(produtoDTO.getStatus()),
                chavesEtiquetas
        );
    }

    public static Produto map(ProdutoEntity produtoEntity) {
        if(produtoEntity == null){
            return null;
        }

        List<ProdutoChaveEtiqueta> chavesEtiquetas = produtoEntity.getChavesEtiquetas().stream()
                .map(item -> new ProdutoChaveEtiqueta(item.getId(), item.getNome())).collect(Collectors.toList());
        return new Produto(produtoEntity.getId(),
                produtoEntity.getNome(),
                produtoEntity.getDescricao(),
                produtoEntity.getValorCompra(),
                produtoEntity.getValorVenda(),
                LojaEntityMapper.map(produtoEntity.getLoja()),
                Status.of(produtoEntity.getStatus()),
                chavesEtiquetas
        );
    }

    public static ProdutoDTO map(Produto produto) {
        List<ProdutoChaveEtiquetaDTO> chavesEtiquetas = produto.getChavesEtiquetas().stream()
                .map(item -> new ProdutoChaveEtiquetaDTO(item.getId(), item.getNome())).collect(Collectors.toList());

        return new ProdutoDTO(produto.getId(),
                produto.getNome(),
                produto.getDescricao(),
                produto.getValorCompra(),
                produto.getValorVenda(),
                produto.getStatus().value(),
                chavesEtiquetas);
    }
}
