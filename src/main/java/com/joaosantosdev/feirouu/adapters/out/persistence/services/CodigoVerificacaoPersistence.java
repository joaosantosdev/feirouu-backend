package com.joaosantosdev.feirouu.adapters.out.persistence.services;

import com.joaosantosdev.feirouu.adapters.out.persistence.entities.CodigoVerificacaoEntity;
import com.joaosantosdev.feirouu.adapters.out.persistence.mappers.CodigoVerificacaoEntityMapper;
import com.joaosantosdev.feirouu.adapters.out.persistence.repositories.CodigoVerificacaoRepository;
import com.joaosantosdev.feirouu.application.domains.models.CodigoVerificacao;
import com.joaosantosdev.feirouu.application.domains.ports.out.CodigoVerificacaoPersistencePort;
import org.springframework.stereotype.Service;

@Service
public class CodigoVerificacaoPersistence implements CodigoVerificacaoPersistencePort {

    private CodigoVerificacaoRepository codigoVerificacaoRepository;

    public CodigoVerificacaoPersistence(CodigoVerificacaoRepository codigoVerificacaoRepository){
        this.codigoVerificacaoRepository = codigoVerificacaoRepository;
    }

    @Override
    public CodigoVerificacao obterCodigoVerificacaoValido(CodigoVerificacao codigoVerificacao) {
        CodigoVerificacaoEntity codigoVerificacaoEntity = this.codigoVerificacaoRepository.obterCodigoVerificacaoValido(codigoVerificacao.getEmail(),
                codigoVerificacao.getCodigo());
        return CodigoVerificacaoEntityMapper.map(codigoVerificacaoEntity);
    }

    @Override
    public CodigoVerificacao obterUltimoCodigoVerificacao(String email) {
        CodigoVerificacaoEntity codigoVerificacaoEntity = this.codigoVerificacaoRepository.obterUltimoCodigo(email);
        return CodigoVerificacaoEntityMapper.map(codigoVerificacaoEntity);
    }

    @Override
    public void salvar(CodigoVerificacao codigoVerificacao) {
        this.codigoVerificacaoRepository.save(CodigoVerificacaoEntityMapper.toEntity(codigoVerificacao));
    }

}
