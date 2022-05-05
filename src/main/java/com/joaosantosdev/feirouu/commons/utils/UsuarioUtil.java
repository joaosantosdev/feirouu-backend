package com.joaosantosdev.feirouu.commons.utils;

import com.joaosantosdev.feirouu.application.domains.models.Usuario;
import com.joaosantosdev.feirouu.commons.mappers.UsuarioMapper;
import com.joaosantosdev.feirouu.security.UsuarioDetails;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class UsuarioUtil {
    public Usuario obterUsuarioLogado(){
        UsuarioDetails usuarioDetails = (UsuarioDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return UsuarioMapper.map(usuarioDetails);
    }
}
