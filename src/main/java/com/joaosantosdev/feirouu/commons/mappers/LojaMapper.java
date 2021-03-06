package com.joaosantosdev.feirouu.commons.mappers;

import com.joaosantosdev.feirouu.adapters.in.web.dtos.FiltroLojaDTO;
import com.joaosantosdev.feirouu.adapters.in.web.dtos.LojaDTO;
import com.joaosantosdev.feirouu.application.domains.models.Loja;
import com.joaosantosdev.feirouu.application.domains.models.Usuario;
import com.joaosantosdev.feirouu.application.utils.FiltroLoja;

public class LojaMapper {

    public static Loja map(Usuario usuario, LojaDTO lojaDTO) {
        return new Loja(lojaDTO.getId(),
                lojaDTO.getNome(),
                lojaDTO.getDescricao(),
                lojaDTO.getTelefone(),
                lojaDTO.getEmail(),
                EnderecoMapper.map(lojaDTO.getEndereco()),
                usuario);
    }

    public static Loja map(Usuario usuario, Long lojaId) {
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

    public static FiltroLoja mapFiltro(FiltroLojaDTO filtroLojaDTO) {
        return new FiltroLoja(filtroLojaDTO.getNome(),
                filtroLojaDTO.getCidadeId(),
                filtroLojaDTO.getEstadoId(),
                filtroLojaDTO.getBairro());
    }
}
