package com.joaosantosdev.feirouu.adapters.in.web.controllers;

import com.joaosantosdev.feirouu.application.domains.models.Cidade;
import com.joaosantosdev.feirouu.application.domains.models.Estado;
import com.joaosantosdev.feirouu.application.domains.ports.in.EnderecoServicePort;
import lombok.Getter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class EnderecoController {

    private EnderecoServicePort enderecoServicePort;

    public EnderecoController(EnderecoServicePort enderecoServicePort) {
        this.enderecoServicePort = enderecoServicePort;
    }

    @GetMapping("/estados")
    public ResponseEntity<List<Estado>> obterEstados(){
        return ResponseEntity.ok(this.enderecoServicePort.obterEstados());
    }

    @GetMapping("/estados/{id}/cidades")
    public ResponseEntity<List<Cidade>> obterCidadesPorEstado(@PathVariable Integer id){
        return ResponseEntity.ok(this.enderecoServicePort.obterCidadesPorEstado(id));
    }
}
