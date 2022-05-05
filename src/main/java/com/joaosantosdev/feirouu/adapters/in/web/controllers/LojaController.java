package com.joaosantosdev.feirouu.adapters.in.web.controllers;

import com.joaosantosdev.feirouu.adapters.in.web.dtos.LojaDTO;
import com.joaosantosdev.feirouu.adapters.in.web.dtos.LojaListagemDTO;
import com.joaosantosdev.feirouu.application.domains.models.Loja;
import com.joaosantosdev.feirouu.application.domains.ports.in.LojaServicePort;
import com.joaosantosdev.feirouu.application.domains.ports.in.ProdutoServicePort;
import com.joaosantosdev.feirouu.commons.mappers.LojaMapper;
import com.joaosantosdev.feirouu.commons.utils.UsuarioUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/lojas")
public class LojaController {

    private LojaServicePort lojaServicePort;
    private UsuarioUtil usuarioUtil;

    public LojaController(LojaServicePort lojaServicePort, UsuarioUtil usuarioUtil) {
        this.lojaServicePort = lojaServicePort;
        this.usuarioUtil = usuarioUtil;
    }

    @PostMapping
    public ResponseEntity<Void> cadastrar(@RequestBody LojaDTO lojaDTO) {
        Long id = this.lojaServicePort.cadastrar(LojaMapper.map(this.usuarioUtil.obterUsuarioLogado(), lojaDTO));
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(id).toUri();
        return ResponseEntity.created(uri).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<LojaListagemDTO> buscarPorId(@PathVariable Long id) {
        Long usuarioId = this.usuarioUtil.obterUsuarioLogado().getId();
        Loja loja = this.lojaServicePort.buscarPorIdUsuarioId(id, usuarioId);
        LojaDTO lojaDTO = LojaMapper.map(loja);
        return ResponseEntity.ok().body(new LojaListagemDTO(lojaDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> atualizar(@PathVariable Long id, @RequestBody LojaDTO lojaDTO) {
        Long usuarioId = this.usuarioUtil.obterUsuarioLogado().getId();
        lojaDTO.setId(id);
        Loja loja = LojaMapper.map(this.usuarioUtil.obterUsuarioLogado(), lojaDTO);
        this.lojaServicePort.atualizar(loja);
        return ResponseEntity.noContent().build();
    }

}
