package com.joaosantosdev.feirouu.application.domains.ports.out;

import com.joaosantosdev.feirouu.application.domains.models.Usuario;

public interface UsuarioPersistencePort {
    Long salvar(Usuario usuario);

    Boolean verificarEmail(String email);

    Usuario obterPorId(Long id);

    void atualizar(Usuario usuario);

    void redefinirSenha(Usuario usuario);
}
