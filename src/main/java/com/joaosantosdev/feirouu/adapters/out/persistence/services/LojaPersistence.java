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
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;


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
    public Long cadastrar(Loja loja) {
        LojaEntity lojaEntity = LojaEntityMapper.map(loja);
        Optional<CidadeEntity> cidade = this.cidadeRepository.findById(lojaEntity.getEndereco().getCidade().getId());
        lojaEntity.getEndereco().setCidade(cidade.get());
        this.enderecoRepository.save(lojaEntity.getEndereco());
        this.lojaRepository.save(lojaEntity);
        return lojaEntity.getId();
    }

    @Override
    public Optional<Loja> buscarPorIdUsuarioId(Long id, Long usuarioId) {
        Optional<LojaEntity> lojaEntityOptional = this.lojaRepository.findByIdAndUsuarioId(id, usuarioId);
        Loja loja = null;

        if(lojaEntityOptional.isPresent()){
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

    public void atualizasr(Loja loja) {
        LojaEntity lojaCadastrada = this.lojaRepository.getById(loja.getId());

        lojaCadastrada.setDescricao(loja.getDescricao());
        lojaCadastrada.setNome(loja.getNome());
        lojaCadastrada.setEmail(loja.getEmail());
        lojaCadastrada.setTelefone(loja.getTelefone());

        lojaCadastrada.getEndereco().setCidade(this.cidadeRepository.getById(loja.getEndereco().getCidade().getId()));
        lojaCadastrada.getEndereco().setBairro(loja.getEndereco().getBairro());
        lojaCadastrada.getEndereco().setCep(loja.getEndereco().getCep());
        lojaCadastrada.getEndereco().setNumero(loja.getEndereco().getNumero());
        lojaCadastrada.getEndereco().setComplemento(loja.getEndereco().getComplemento());

        this.enderecoRepository.save(lojaCadastrada.getEndereco());
        this.lojaRepository.save(lojaCadastrada);
    }
}
