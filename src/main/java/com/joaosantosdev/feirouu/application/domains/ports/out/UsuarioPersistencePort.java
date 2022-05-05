package com.joaosantosdev.feirouu.application.domains.ports.out;

import com.joaosantosdev.feirouu.application.domains.models.Usuario;

public interface UsuarioPersistencePort {
    Long salvar(Usuario usuario);

    Boolean verificarEmail(String email);
}
