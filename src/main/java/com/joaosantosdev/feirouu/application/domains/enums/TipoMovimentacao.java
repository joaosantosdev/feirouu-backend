package com.joaosantosdev.feirouu.application.domains.enums;

import com.joaosantosdev.feirouu.application.exceptions.NegocioException;

public enum TipoMovimentacao {
    ENTRADA(1),
    SAIDA(2),
    DEVOLUCAO(3);

    private Integer id;

    TipoMovimentacao(Integer id) {
        this.id = id;
    }


    public Integer value(){
        return this.id;
    }

    public static TipoMovimentacao of(Integer id){
        try{
            TipoMovimentacao tipoMovimentacao = TipoMovimentacao.values()[id-1];
            if(tipoMovimentacao == null){
                throw new NegocioException(400, "Tipo movimentação inválido");
            }
            return tipoMovimentacao;
        }catch (Exception e){
            throw new NegocioException(400, "Tipo movimentação inválido");
        }
    }
}
