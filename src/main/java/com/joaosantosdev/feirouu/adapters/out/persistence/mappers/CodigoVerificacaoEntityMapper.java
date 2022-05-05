package com.joaosantosdev.feirouu.adapters.out.persistence.mappers;

import com.joaosantosdev.feirouu.adapters.out.persistence.entities.CodigoVerificacaoEntity;
import com.joaosantosdev.feirouu.application.domains.models.CodigoVerificacao;

public class CodigoVerificacaoEntityMapper {
    public static CodigoVerificacao map(CodigoVerificacaoEntity codigoVerificacaoEntity){
        if(codigoVerificacaoEntity == null){
            return null;
        }
        return new CodigoVerificacao(codigoVerificacaoEntity.getId(),
                codigoVerificacaoEntity.getEmail(),
                codigoVerificacaoEntity.getCodigo(),
                codigoVerificacaoEntity.getTimestampCriacao());
    }

    public static CodigoVerificacaoEntity toEntity(CodigoVerificacao codigoVerificacao) {
        return new CodigoVerificacaoEntity(codigoVerificacao.getEmail(),
                codigoVerificacao.getCodigo(),
                codigoVerificacao.getTimestapCriacao());
    }
}
