package com.joaosantosdev.feirouu.adapters.out.persistence.mappers;

import com.joaosantosdev.feirouu.adapters.out.persistence.entities.CidadeEntity;
import com.joaosantosdev.feirouu.adapters.out.persistence.entities.EnderecoEntity;
import com.joaosantosdev.feirouu.adapters.out.persistence.entities.EstadoEntity;
import com.joaosantosdev.feirouu.application.domains.models.Cidade;
import com.joaosantosdev.feirouu.application.domains.models.Endereco;
import com.joaosantosdev.feirouu.application.domains.models.Estado;

public class EnderecoEntityMapper {

    public static Estado mapEstado(EstadoEntity estadoEntity){
        return new Estado(estadoEntity.getId(),
                estadoEntity.getNome(),
                estadoEntity.getUf());
    }

    public static Cidade mapCidade(CidadeEntity cidadeEntity){
        return new Cidade(cidadeEntity.getId(),
                cidadeEntity.getNome(),
                mapEstado(cidadeEntity.getEstado()));
    }


    public static Endereco map(EnderecoEntity enderecoEntity) {
        return new Endereco(
                enderecoEntity.getId(),
                enderecoEntity.getNumero(),
                enderecoEntity.getCep(),
                enderecoEntity.getComplemento(),
                enderecoEntity.getBairro(),
                enderecoEntity.getRua(),
                mapCidade(enderecoEntity.getCidade())
        );
    }

    private static CidadeEntity mapCidade(Cidade cidade) {
        if(cidade == null){
            return null;
        }
        return new CidadeEntity(cidade.getId());
    }

    public static EnderecoEntity map(Endereco endereco) {
        if(endereco == null){
            return null;
        }
        return new EnderecoEntity(
                endereco.getId(),
                endereco.getNumero(),
                endereco.getCep(),
                endereco.getComplemento(),
                endereco.getBairro(),
                endereco.getRua(),
                mapCidade(endereco.getCidade())
        );
    }


}
