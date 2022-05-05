package com.joaosantosdev.feirouu.commons.mappers;

import com.joaosantosdev.feirouu.adapters.in.web.dtos.UsuarioFormDTO;
import com.joaosantosdev.feirouu.application.domains.models.Usuario;
import com.joaosantosdev.feirouu.security.UsuarioDetails;

public class UsuarioMapper {
    public static Usuario map(UsuarioFormDTO usuarioFormDTO){
      return new Usuario(usuarioFormDTO.getNome(),
              usuarioFormDTO.getEmail(),
              usuarioFormDTO.getSenha(),
              usuarioFormDTO.getTelefone());
    }

    public static Usuario map(UsuarioDetails usuarioDetails) {
        return new Usuario(usuarioDetails.getId(), usuarioDetails.getUsername());
    }
}
