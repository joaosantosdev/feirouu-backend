package com.joaosantosdev.feirouu.commons.mappers;

import com.joaosantosdev.feirouu.adapters.in.web.dtos.LojaDTO;
import com.joaosantosdev.feirouu.application.domains.models.Loja;
import com.joaosantosdev.feirouu.application.domains.models.Usuario;

public class LojaMapper {

    public static Loja map(Usuario usuario, LojaDTO lojaDTO){
        return new Loja(lojaDTO.getId(),
                lojaDTO.getNome(),
                lojaDTO.getDescricao(),
                lojaDTO.getTelefone(),
                lojaDTO.getEmail(),
                EnderecoMapper.map(lojaDTO.getEndereco()),
                usuario);
    }

    public static Loja map(Usuario usuario, Long lojaId){
        return new Loja(lojaId, usuario);
    }

    public static LojaDTO map(Loja loja) {
        return new LojaDTO(
                loja.getId(),
                loja.getNome(),
                loja.getDescricao(),
                loja.getTelefone(),
                loja.getEmail(),
                EnderecoMapper.map(loja.getEndereco())
        );
    }

}
