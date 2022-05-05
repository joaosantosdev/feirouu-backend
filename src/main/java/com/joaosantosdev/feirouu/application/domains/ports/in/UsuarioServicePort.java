package com.joaosantosdev.feirouu.application.domains.ports.in;

import com.joaosantosdev.feirouu.application.domains.models.CodigoVerificacao;
import com.joaosantosdev.feirouu.application.domains.models.Usuario;

public interface UsuarioServicePort {
    Long salvar(Usuario usuario, CodigoVerificacao codigoVerificacao);
}
