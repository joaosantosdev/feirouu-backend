package com.joaosantosdev.feirouu.handlers;

import com.joaosantosdev.feirouu.application.exceptions.NegocioException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionsHandler {

    @ExceptionHandler(NegocioException.class)
    public ResponseEntity<ExceptionDTO> negocioException(NegocioException negocioException){
        return ResponseEntity.status(negocioException.getCodigoHttp()).body(new ExceptionDTO(
                "NEGOCIO",
                negocioException.getCodigoHttp(),
                negocioException.getMessage(),
                System.currentTimeMillis()
        ));
    }

    @ExceptionHandler(ImagemException.class)
    public ResponseEntity<ExceptionDTO> imagemException(ImagemException imagemException){
        return ResponseEntity.status(imagemException.getCodigoHttp()).body(new ExceptionDTO(
                "IMAGEM",
                imagemException.getCodigoHttp(),
                imagemException.getMessage(),
                System.currentTimeMillis()
        ));
    }

}
