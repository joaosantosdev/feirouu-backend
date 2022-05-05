package com.joaosantosdev.feirouu.application.domains.ports.in;

import com.joaosantosdev.feirouu.application.domains.models.Loja;


public interface LojaServicePort {
    Long cadastrar(Loja loja);
    void atualizar(Loja loja);
    Loja buscarPorIdUsuarioId(Long id, Long usuarioId);

    Loja buscarPorUsuarioId(Long id);
}
