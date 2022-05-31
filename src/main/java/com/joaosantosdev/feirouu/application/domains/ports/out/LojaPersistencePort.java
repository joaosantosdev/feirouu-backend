package com.joaosantosdev.feirouu.application.domains.ports.out;

import com.joaosantosdev.feirouu.application.domains.models.Loja;
import com.joaosantosdev.feirouu.application.utils.FiltroLoja;

import java.util.List;
import java.util.Optional;

public interface LojaPersistencePort {
    Loja buscarLojaPorUsuarioId(Long usuarioId);
    Loja cadastrar(Loja loja);
    Optional<Loja> buscarPorIdUsuarioId(Long id, Long usuarioId);
    void atualizar(Loja loja);

    List<Loja> buscarLojas(FiltroLoja filtros);
}
