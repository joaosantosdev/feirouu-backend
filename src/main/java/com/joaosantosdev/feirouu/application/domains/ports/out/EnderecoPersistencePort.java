package com.joaosantosdev.feirouu.application.domains.ports.out;

import com.joaosantosdev.feirouu.application.domains.models.Cidade;
import com.joaosantosdev.feirouu.application.domains.models.Estado;

import java.util.List;

public interface EnderecoPersistencePort {
    List<Estado> obterEstados();

    List<Cidade> obterCidadesPorEstado(Integer id);
}
