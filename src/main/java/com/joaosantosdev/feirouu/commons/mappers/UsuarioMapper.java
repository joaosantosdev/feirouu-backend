package com.joaosantosdev.feirouu.commons.mappers;

import com.joaosantosdev.feirouu.adapters.in.web.dtos.RedefinicaoSenhaDTO;
import com.joaosantosdev.feirouu.adapters.in.web.dtos.UsuarioFormDTO;
import com.joaosantosdev.feirouu.adapters.in.web.dtos.UsuarioInfoDTO;
import com.joaosantosdev.feirouu.application.domains.models.Loja;
import com.joaosantosdev.feirouu.application.domains.models.Usuario;
import com.joaosantosdev.feirouu.security.UsuarioDetails;

public class UsuarioMapper {
    public static Usuario map(UsuarioFormDTO usuarioFormDTO) {
        return new Usuario(usuarioFormDTO.getNome(),
                usuarioFormDTO.getEmail(),
                usuarioFormDTO.getSenha(),
                usuarioFormDTO.getTelefone());
    }

    public static Usuario map(UsuarioDetails usuarioDetails) {
        return new Usuario(usuarioDetails.getId(), usuarioDetails.getUsername());
    }

    public static UsuarioInfoDTO mapUsuarioInfo(Usuario usuario, Loja loja) {
        return new UsuarioInfoDTO(usuario.getId(),
                usuario.getNome(),
                usuario.getEmail(),
                null,
                usuario.getTelefone(),
                loja != null ? loja.getId() : null);
    }

    public static Usuario map(Long id, UsuarioFormDTO usuarioFormDTO) {
        return new Usuario(id, usuarioFormDTO.getNome(),
                usuarioFormDTO.getEmail(),
                usuarioFormDTO.getSenha(),
                usuarioFormDTO.getTelefone());
    }

    public static Usuario map(RedefinicaoSenhaDTO redefinicaoSenhaDTO) {
        return new Usuario(redefinicaoSenhaDTO.getEmail(), redefinicaoSenhaDTO.getSenha());
    }
}
