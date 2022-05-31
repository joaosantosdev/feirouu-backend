package com.joaosantosdev.feirouu.application.domains.services;

import com.joaosantosdev.feirouu.application.domains.models.Loja;
import com.joaosantosdev.feirouu.application.domains.ports.in.LojaServicePort;
import com.joaosantosdev.feirouu.application.domains.ports.out.LojaPersistencePort;
import com.joaosantosdev.feirouu.application.exceptions.NegocioException;
import com.joaosantosdev.feirouu.application.utils.FiltroLoja;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public class LojaService implements LojaServicePort {

    @Autowired
    private LojaPersistencePort lojaPersistencePort;

    public LojaService(LojaPersistencePort lojaPersistencePort) {
        this.lojaPersistencePort = lojaPersistencePort;
    }

    @Override
    public Loja cadastrar(Loja loja) {
        return loja.cadastrar(this.lojaPersistencePort);
    }

    @Override
    public void atualizar(Loja lojaAtualizada) {
        this.buscarPorIdUsuarioId(lojaAtualizada.getId(), lojaAtualizada.getUsuario().getId());
        lojaAtualizada.atualizar(this.lojaPersistencePort);
    }

    @Override
    public Loja buscarPorIdUsuarioId(Long id, Long usuarioId) {
        Optional<Loja> lojaOptional =  this.lojaPersistencePort.buscarPorIdUsuarioId(id, usuarioId);

        if(lojaOptional.isEmpty()){
            throw new NegocioException(404, "Loja não encontrada");
        }

        return  lojaOptional.get();
    }

    @Override
    public Loja buscarPorUsuarioId(Long id) {
        return this.lojaPersistencePort.buscarLojaPorUsuarioId(id);
    }

    @Override
    public List<Loja> buscarLojas(FiltroLoja filtros) {
        return this.lojaPersistencePort.buscarLojas(filtros);
    }

}
