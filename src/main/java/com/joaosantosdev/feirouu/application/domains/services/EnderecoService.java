package com.joaosantosdev.feirouu.application.domains.services;

import com.joaosantosdev.feirouu.application.domains.models.Cidade;
import com.joaosantosdev.feirouu.application.domains.models.Estado;
import com.joaosantosdev.feirouu.application.domains.ports.in.EnderecoServicePort;
import com.joaosantosdev.feirouu.application.domains.ports.out.EnderecoPersistencePort;

import java.util.List;

public class EnderecoService implements EnderecoServicePort {

    private EnderecoPersistencePort enderecoPersistencePort;

    public EnderecoService(EnderecoPersistencePort enderecoPersistencePort) {
        this.enderecoPersistencePort = enderecoPersistencePort;
    }

    @Override
    public List<Estado> obterEstados() {
        return this.enderecoPersistencePort.obterEstados();
    }

    @Override
    public List<Cidade> obterCidadesPorEstado(Integer id) {
        return this.enderecoPersistencePort.obterCidadesPorEstado(id);
    }
}
