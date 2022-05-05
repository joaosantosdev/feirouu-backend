package com.joaosantosdev.feirouu.application.domains.ports.out;

import com.joaosantosdev.feirouu.application.domains.models.CodigoVerificacao;

public interface CodigoVerificacaoPersistencePort {
    CodigoVerificacao obterCodigoVerificacaoValido(CodigoVerificacao codigoVerificacao);
    CodigoVerificacao obterUltimoCodigoVerificacao(String email);
    void salvar(CodigoVerificacao codigoVerificacao);
}
