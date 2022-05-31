package com.joaosantosdev.feirouu.adapters.in.web.controllers;

import com.joaosantosdev.feirouu.application.domains.models.CodigoVerificacao;
import com.joaosantosdev.feirouu.application.domains.ports.in.CodigoVerificacaoServicePort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/codigo-verificacao")
public class CodigoVerificacaoController {

    private CodigoVerificacaoServicePort codigoVerificacaoServicePort;

    public CodigoVerificacaoController(CodigoVerificacaoServicePort codigoVerificacaoServicePort){
        this.codigoVerificacaoServicePort = codigoVerificacaoServicePort;
    }

    @GetMapping("/{email}")
    public ResponseEntity<CodigoVerificacao> enviar(@PathVariable String email, @RequestParam Boolean redefinicao){
        CodigoVerificacao codigoVerificacao = this.codigoVerificacaoServicePort.enviar(email, redefinicao);
        return ResponseEntity.ok(codigoVerificacao);
    }
}
