package com.joaosantosdev.feirouu.application.domains.ports.in;

import com.joaosantosdev.feirouu.application.domains.models.Loja;
import com.joaosantosdev.feirouu.application.utils.FiltroLoja;

import java.util.List;


public interface LojaServicePort {
    Loja cadastrar(Loja loja);
    void atualizar(Loja loja);
    Loja buscarPorIdUsuarioId(Long id, Long usuarioId);

    Loja buscarPorUsuarioId(Long id);

    List<Loja> buscarLojas(FiltroLoja filtroLoja);
}
