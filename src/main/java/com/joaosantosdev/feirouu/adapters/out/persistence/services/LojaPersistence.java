package com.joaosantosdev.feirouu.adapters.out.persistence.services;

import com.joaosantosdev.feirouu.adapters.out.persistence.entities.CidadeEntity;
import com.joaosantosdev.feirouu.adapters.out.persistence.entities.EnderecoEntity;
import com.joaosantosdev.feirouu.adapters.out.persistence.entities.LojaEntity;
import com.joaosantosdev.feirouu.adapters.out.persistence.mappers.LojaEntityMapper;
import com.joaosantosdev.feirouu.adapters.out.persistence.repositories.CidadeRepository;
import com.joaosantosdev.feirouu.adapters.out.persistence.repositories.EnderecoRepository;
import com.joaosantosdev.feirouu.adapters.out.persistence.repositories.LojaRepository;
import com.joaosantosdev.feirouu.application.domains.models.Loja;
import com.joaosantosdev.feirouu.application.domains.ports.out.LojaPersistencePort;
import com.joaosantosdev.feirouu.application.utils.FiltroLoja;
import com.joaosantosdev.feirouu.commons.mappers.LojaMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class LojaPersistence implements LojaPersistencePort {

    private LojaRepository lojaRepository;
    private EnderecoRepository enderecoRepository;
    private CidadeRepository cidadeRepository;

    public LojaPersistence(LojaRepository lojaRepository, EnderecoRepository enderecoRepository, CidadeRepository cidadeRepository) {
        this.lojaRepository = lojaRepository;
        this.enderecoRepository = enderecoRepository;
        this.cidadeRepository = cidadeRepository;
    }

    @Override
    public Loja buscarLojaPorUsuarioId(Long usuarioId) {
        LojaEntity lojaEntity = this.lojaRepository.findByUsuarioId(usuarioId);
        return LojaEntityMapper.map(lojaEntity);
    }

    @Override
    @Transactional
    public Loja cadastrar(Loja loja) {
        LojaEntity lojaEntity = LojaEntityMapper.map(loja);
        Optional<CidadeEntity> cidade = this.cidadeRepository.findById(lojaEntity.getEndereco().getCidade().getId());
        lojaEntity.getEndereco().setCidade(cidade.get());
        this.enderecoRepository.save(lojaEntity.getEndereco());
        this.lojaRepository.save(lojaEntity);
        return LojaEntityMapper.map(lojaEntity);
    }

    @Override
    public Optional<Loja> buscarPorIdUsuarioId(Long id, Long usuarioId) {
        Optional<LojaEntity> lojaEntityOptional = this.lojaRepository.findByIdAndUsuarioId(id, usuarioId);
        Loja loja = null;

        if (lojaEntityOptional.isPresent()) {
            loja = LojaEntityMapper.map(lojaEntityOptional.get());
        }

        return Optional.ofNullable(loja);
    }

    @Override
    public void atualizar(Loja loja) {
        LojaEntity lojaEntity = LojaEntityMapper.map(loja);
        this.enderecoRepository.save(lojaEntity.getEndereco());
        this.lojaRepository.save(lojaEntity);
    }

    @Override
    public List<Loja> buscarLojas(FiltroLoja filtros) {
        List<LojaEntity> lojaEntities = new ArrayList<>();

        if (filtros.getEstadoId() == null && filtros.getCidadeId() == null && filtros.getNome() == null && filtros.getBairro() == null) {
            lojaEntities = this.lojaRepository.findAll();
        } else {
            lojaEntities = this.lojaRepository.obterLojasFiltros(filtros.getNome(),
                    filtros.getCidadeId(),
                    filtros.getEstadoId(),
                    filtros.getBairro());
        }

        return lojaEntities.stream().map(LojaEntityMapper::map).collect(Collectors.toList());
    }

}
