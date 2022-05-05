package com.joaosantosdev.feirouu.adapters.out.persistence.mappers;

import com.joaosantosdev.feirouu.adapters.out.persistence.entities.LojaEntity;
import com.joaosantosdev.feirouu.application.domains.models.Loja;

public class LojaEntityMapper {
    public static Loja map(LojaEntity lojaEntity) {
        if(lojaEntity == null){
            return null;
        }
        return new Loja(
                lojaEntity.getId(),
                lojaEntity.getNome(),
                lojaEntity.getDescricao(),
                lojaEntity.getTelefone(),
                lojaEntity.getEmail(),
                EnderecoEntityMapper.map(lojaEntity.getEndereco()),
                UsuarioEntityMapper.map(lojaEntity.getUsuario()));
    }

    public static LojaEntity map(Loja loja) {
        return new LojaEntity(loja.getId(),
                loja.getNome(),
                loja.getDescricao(),
                loja.getTelefone(),
                loja.getEmail(),
                EnderecoEntityMapper.map(loja.getEndereco()),
                UsuarioEntityMapper.map(loja.getUsuario()));
    }
}
