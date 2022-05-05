package com.joaosantosdev.feirouu.commons.mappers;

import com.joaosantosdev.feirouu.adapters.in.web.dtos.ClienteDTO;
import com.joaosantosdev.feirouu.application.domains.models.Cliente;

public class ClienteMapper {
    public static Cliente map(ClienteDTO clienteDTO){
        return new Cliente(clienteDTO.getCpf(),
                clienteDTO.getNome(),
                clienteDTO.getEmail(),
                clienteDTO.getTelefone());
    }
}
