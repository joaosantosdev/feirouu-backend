package com.joaosantosdev.feirouu.adapters.out.persistence.services;

import com.joaosantosdev.feirouu.adapters.out.persistence.entities.CidadeEntity;
import com.joaosantosdev.feirouu.adapters.out.persistence.entities.EstadoEntity;
import com.joaosantosdev.feirouu.adapters.out.persistence.mappers.EnderecoEntityMapper;
import com.joaosantosdev.feirouu.adapters.out.persistence.repositories.CidadeRepository;
import com.joaosantosdev.feirouu.adapters.out.persistence.repositories.EstadoRepository;
import com.joaosantosdev.feirouu.application.domains.models.Cidade;
import com.joaosantosdev.feirouu.application.domains.models.Estado;
import com.joaosantosdev.feirouu.application.domains.ports.out.EnderecoPersistencePort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EnderecoPersistence implements EnderecoPersistencePort {

    private EstadoRepository estadoRepository;
    private CidadeRepository cidadeRepository;

    public EnderecoPersistence(EstadoRepository estadoRepository, CidadeRepository cidadeRepository) {
        this.estadoRepository = estadoRepository;
        this.cidadeRepository = cidadeRepository;
    }

    @Override
    public List<Estado> obterEstados() {
        List<EstadoEntity> estadoEntities = this.estadoRepository.findAll();

        return estadoEntities.stream().map(estado -> new Estado(estado.getId(),
                estado.getNome(),
                estado.getUf())).collect(Collectors.toList());
    }

    @Override
    public List<Cidade> obterCidadesPorEstado(Integer id) {
        List<CidadeEntity> cidadesEntities = this.cidadeRepository.findByEstadoId(id);

        return cidadesEntities.stream().map(cidade -> new Cidade(cidade.getId(),
                cidade.getNome(),
                EnderecoEntityMapper.mapEstado(cidade.getEstado()))).collect(Collectors.toList());
    }
}
