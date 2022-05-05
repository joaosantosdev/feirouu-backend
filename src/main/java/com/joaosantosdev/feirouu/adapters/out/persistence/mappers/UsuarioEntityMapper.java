package com.joaosantosdev.feirouu.adapters.out.persistence.mappers;

import com.joaosantosdev.feirouu.adapters.out.persistence.entities.UsuarioEntity;
import com.joaosantosdev.feirouu.application.domains.models.Usuario;

public class UsuarioEntityMapper {

    public static UsuarioEntity map(Usuario usuario){
        return new UsuarioEntity(
                usuario.getId(),
                usuario.getNome(),
                usuario.getEmail(),
                usuario.getSenha(),
                usuario.getTelefone());
    }

    public static Usuario map(UsuarioEntity usuarioEntity) {
        return new Usuario(usuarioEntity.getId(),
                usuarioEntity.getNome(),
                usuarioEntity.getEmail(),
                usuarioEntity.getSenha(),
                usuarioEntity.getTelefone());
    }
}
