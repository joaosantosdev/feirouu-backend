package com.joaosantosdev.feirouu.commons.mappers;

import com.joaosantosdev.feirouu.adapters.in.web.dtos.EnderecoDTO;
import com.joaosantosdev.feirouu.application.domains.models.Cidade;
import com.joaosantosdev.feirouu.application.domains.models.Endereco;

public class EnderecoMapper {

    public static Endereco map(EnderecoDTO enderecoDTO){
        Cidade cidade = new Cidade(enderecoDTO.getCidadeId());
        return new Endereco(enderecoDTO.getId(),
                            enderecoDTO.getNumero(),
                            enderecoDTO.getCep(),
                            enderecoDTO.getComplemento(),
                            enderecoDTO.getBairro(),
                            enderecoDTO.getRua(),
                            cidade);
    }

    public static EnderecoDTO map(Endereco endereco){
        return new EnderecoDTO(endereco.getId(),
                endereco.getNumero(),
                endereco.getCep(),
                endereco.getComplemento(),
                endereco.getBairro(),
                endereco.getRua(),
                endereco.getCidade().getId(),
                endereco.getCidade().getEstado().getId());
    }
}
