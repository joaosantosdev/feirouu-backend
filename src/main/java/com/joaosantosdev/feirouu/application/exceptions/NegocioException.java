package com.joaosantosdev.feirouu.application.exceptions;

public class NegocioException extends RuntimeException{
    private Integer codigoHttp;
    public NegocioException(Integer codigoHttp, String mensagem){
        super(mensagem);
        this.codigoHttp = codigoHttp;
    }

    public Integer getCodigoHttp() {
        return codigoHttp;
    }
}
