package com.joaosantosdev.feirouu.adapters.out.persistence.mappers;

import com.joaosantosdev.feirouu.adapters.out.persistence.entities.MovimentacaoValorEtiquetaEntity;
import com.joaosantosdev.feirouu.application.domains.models.MovimentacaoValorEtiqueta;
import com.joaosantosdev.feirouu.application.domains.models.ProdutoChaveEtiqueta;

public class MovimentacaoEtiquetaEntityMapper {

    public static MovimentacaoValorEtiqueta map(MovimentacaoValorEtiquetaEntity entity){
        return new MovimentacaoValorEtiqueta(entity.getValor(),
                new ProdutoChaveEtiqueta(entity.getProdutoChaveEtiqueta().getId(), entity.getProdutoChaveEtiqueta().getNome()));
    }
}
