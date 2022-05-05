package com.joaosantosdev.feirouu.commons.mappers;

import com.joaosantosdev.feirouu.application.utils.Paginacao;

public class PaginacaoMapper {
    public static Paginacao map(Integer pagina, Integer porPagina, String ordenarPor, String direcao) {
        return new Paginacao(pagina, porPagina, ordenarPor, direcao);
    }
}
