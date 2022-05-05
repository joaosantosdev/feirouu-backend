package com.joaosantosdev.feirouu.application.domains.ports.out;

import com.joaosantosdev.feirouu.application.domains.models.CodigoVerificacao;

public interface EnvioCodigoVerificacaoServicePort {
    Boolean enviar(CodigoVerificacao codigoVerificacao);
}
