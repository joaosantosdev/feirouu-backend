package com.joaosantosdev.feirouu.adapters.in.web.controllers;

import com.joaosantosdev.feirouu.adapters.in.web.dtos.RedefinicaoSenhaDTO;
import com.joaosantosdev.feirouu.adapters.in.web.dtos.UsuarioFormDTO;
import com.joaosantosdev.feirouu.adapters.in.web.dtos.UsuarioInfoDTO;
import com.joaosantosdev.feirouu.application.domains.models.CodigoVerificacao;
import com.joaosantosdev.feirouu.application.domains.models.Loja;
import com.joaosantosdev.feirouu.application.domains.models.Usuario;
import com.joaosantosdev.feirouu.application.domains.ports.in.LojaServicePort;
import com.joaosantosdev.feirouu.commons.mappers.UsuarioMapper;
import com.joaosantosdev.feirouu.application.domains.ports.in.UsuarioServicePort;
import com.joaosantosdev.feirouu.commons.utils.UsuarioUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    private UsuarioUtil usuarioUtil;

    private UsuarioServicePort usuarioServicePort;

    private LojaServicePort lojaServicePort;

    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public UsuarioController(UsuarioUtil usuarioUtil, UsuarioServicePort usuarioServicePort, LojaServicePort lojaServicePort, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.usuarioUtil = usuarioUtil;
        this.usuarioServicePort = usuarioServicePort;
        this.lojaServicePort = lojaServicePort;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @PostMapping
    public ResponseEntity<Void> registrar(@RequestBody UsuarioFormDTO usuarioFormDTO) {
        usuarioFormDTO.setSenha(this.bCryptPasswordEncoder.encode(usuarioFormDTO.getSenha()));
        Long id = usuarioServicePort.salvar(UsuarioMapper.map(usuarioFormDTO), new CodigoVerificacao(usuarioFormDTO.getEmail(),
                usuarioFormDTO.getCodigoVerificacao().toUpperCase()));
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(id).toUri();
        return ResponseEntity.created(uri).build();
    }


    @GetMapping("/autenticado")
    public ResponseEntity<UsuarioInfoDTO> obterUsuarioAutenticado() {
        Long usuarioId = this.usuarioUtil.obterUsuarioLogado().getId();
        Usuario usuario = this.usuarioServicePort.obterPorId(usuarioId);
        Loja loja = this.lojaServicePort.buscarPorUsuarioId(usuario.getId());
        return ResponseEntity.ok(UsuarioMapper.mapUsuarioInfo(usuario, loja));
    }

    @PutMapping("/autenticado")
    public ResponseEntity<Void> atualizarUsuarioAutenticado(@RequestBody UsuarioFormDTO usuarioFormDTO) {
        Usuario usuario = this.usuarioUtil.obterUsuarioLogado();
        CodigoVerificacao codigoVerificacao = null;

        if (usuarioFormDTO.getCodigoVerificacao() != null) {
            codigoVerificacao = new CodigoVerificacao(usuarioFormDTO.getEmail(), usuarioFormDTO.getCodigoVerificacao().toUpperCase());
        }

        usuarioServicePort.atualizar(UsuarioMapper.map(usuario.getId(), usuarioFormDTO), codigoVerificacao);
        return ResponseEntity.noContent().build();
    }


    @PostMapping("/redefinir-senha")
    public ResponseEntity<Void> redefinirSenha(@RequestBody RedefinicaoSenhaDTO redefinicaoSenhaDTO) {
        redefinicaoSenhaDTO.setSenha(this.bCryptPasswordEncoder.encode(redefinicaoSenhaDTO.getSenha()));
        CodigoVerificacao codigoVerificacao = null;

        if (redefinicaoSenhaDTO.getCodigoVerificacao() != null) {
            codigoVerificacao = new CodigoVerificacao(redefinicaoSenhaDTO.getEmail(),
                    redefinicaoSenhaDTO.getCodigoVerificacao().toUpperCase());
        }

        usuarioServicePort.redefinirSenha(UsuarioMapper.map(redefinicaoSenhaDTO), codigoVerificacao);
        return ResponseEntity.noContent().build();
    }

}
