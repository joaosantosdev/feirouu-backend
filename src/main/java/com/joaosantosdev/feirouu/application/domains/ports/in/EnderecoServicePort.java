package com.joaosantosdev.feirouu.application.domains.ports.in;

import com.joaosantosdev.feirouu.application.domains.models.Cidade;
import com.joaosantosdev.feirouu.application.domains.models.Estado;

import java.util.List;

public interface EnderecoServicePort {
    List<Estado> obterEstados();

    List<Cidade> obterCidadesPorEstado(Integer id);
}
