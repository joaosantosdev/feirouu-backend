package com.joaosantosdev.feirouu.handlers;

public class ImagemException extends RuntimeException{
    private Integer codigoHttp;

    public ImagemException(Integer codigoHttp, String mensagem){
        super(mensagem);
        this.codigoHttp = codigoHttp;
    }

    public Integer getCodigoHttp() {
        return codigoHttp;
    }
}
