package com.joaosantosdev.feirouu.application.domains.enums;

import com.joaosantosdev.feirouu.application.exceptions.NegocioException;

public enum Status {
    ATIVO(1),
    INATIVO(2);

    private Integer id;

    Status(Integer id) {
        this.id = id;
    }


    public Integer value(){
        return this.id;
    }

    public static Status of(Integer id){
        try{
            Status status = Status.values()[id-1];
            if(status == null){
                throw new NegocioException(400, "Status inválido");
            }
            return status;
        }catch (Exception e){
            throw new NegocioException(400, "Status inválido");
        }
    }
}
