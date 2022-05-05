package com.joaosantosdev.feirouu.application.domains.ports.out;

import com.joaosantosdev.feirouu.application.domains.models.Loja;

import java.util.Optional;

public interface LojaPersistencePort {
    Loja buscarLojaPorUsuarioId(Long usuarioId);
    Long cadastrar(Loja loja);
    Optional<Loja> buscarPorIdUsuarioId(Long id, Long usuarioId);
    void atualizar(Loja loja);
}
