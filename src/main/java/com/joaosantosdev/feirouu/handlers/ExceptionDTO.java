package com.joaosantosdev.feirouu.handlers;

import lombok.Getter;

@Getter
public class ExceptionDTO {
    private String tipo;
    private Integer codigoHTTP;
    private String mensagem;
    private Long timestamp;

    public ExceptionDTO(String tipo, Integer codigoHTTP, String mensagem, Long timestamp) {
        this.tipo = tipo;
        this.codigoHTTP = codigoHTTP;
        this.mensagem = mensagem;
        this.timestamp = timestamp;
    }
}
