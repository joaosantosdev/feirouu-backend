package com.joaosantosdev.feirouu.application.domains.ports.in;

import com.joaosantosdev.feirouu.application.domains.models.CodigoVerificacao;
import com.joaosantosdev.feirouu.application.domains.models.Usuario;

public interface UsuarioServicePort {
    Long salvar(Usuario usuario, CodigoVerificacao codigoVerificacao);

    Usuario obterPorId(Long usuarioId);

    void atualizar(Usuario map, CodigoVerificacao codigoVerificacao);

    void redefinirSenha(Usuario usuario, CodigoVerificacao codigoVerificacao);
}
