package com.joaosantosdev.feirouu.adapters.out.persistence.mappers;

import com.joaosantosdev.feirouu.adapters.out.persistence.entities.ClienteEntity;
import com.joaosantosdev.feirouu.application.domains.models.Cliente;

public class ClienteEntityMapper {
    public static Cliente map(ClienteEntity cliente) {
        if(cliente == null){
            return null;
        }
        return new Cliente(
                cliente.getCpf(),
                cliente.getNome(),
                cliente.getEmail(),
                cliente.getTelefone()
        );
    }
}
