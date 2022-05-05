package com.joaosantosdev.feirouu.adapters.in.web.controllers;

import com.joaosantosdev.feirouu.application.domains.models.CodigoVerificacao;
import com.joaosantosdev.feirouu.application.domains.ports.in.CodigoVerificacaoServicePort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/codigo-verificacao")
public class CodigoVerificacaoController {

    private CodigoVerificacaoServicePort codigoVerificacaoServicePort;

    public CodigoVerificacaoController(CodigoVerificacaoServicePort codigoVerificacaoServicePort){
        this.codigoVerificacaoServicePort = codigoVerificacaoServicePort;
    }

    @GetMapping("/{email}")
    public ResponseEntity<CodigoVerificacao> enviar(@PathVariable String email){
        CodigoVerificacao codigoVerificacao = this.codigoVerificacaoServicePort.enviar(email);
        return ResponseEntity.ok(codigoVerificacao);
    }
}
