package com.joaosantosdev.feirouu.application.domains.ports.in;

import com.joaosantosdev.feirouu.application.domains.models.CodigoVerificacao;

public interface CodigoVerificacaoServicePort {
    CodigoVerificacao enviar(String email);
}
