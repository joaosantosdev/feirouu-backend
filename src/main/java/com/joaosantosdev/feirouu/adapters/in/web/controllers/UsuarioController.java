package com.joaosantosdev.feirouu.adapters.in.web.controllers;

import com.joaosantosdev.feirouu.adapters.in.web.dtos.UsuarioFormDTO;
import com.joaosantosdev.feirouu.adapters.in.web.dtos.UsuarioInfoDTO;
import com.joaosantosdev.feirouu.application.domains.models.CodigoVerificacao;
import com.joaosantosdev.feirouu.application.domains.models.Loja;
import com.joaosantosdev.feirouu.application.domains.models.Usuario;
import com.joaosantosdev.feirouu.application.domains.ports.in.LojaServicePort;
import com.joaosantosdev.feirouu.commons.mappers.UsuarioMapper;
import com.joaosantosdev.feirouu.application.domains.ports.in.UsuarioServicePort;
import com.joaosantosdev.feirouu.commons.utils.UsuarioUtil;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Locale;

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
    public ResponseEntity<Void> registrar(@RequestBody UsuarioFormDTO usuarioFormDTO){
        usuarioFormDTO.setSenha(this.bCryptPasswordEncoder.encode(usuarioFormDTO.getSenha()));
        Long id = usuarioServicePort.salvar(UsuarioMapper.map(usuarioFormDTO), new CodigoVerificacao(usuarioFormDTO.getEmail(),
                usuarioFormDTO.getCodigoVerificacao().toUpperCase()));
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(id).toUri();
        return ResponseEntity.created(uri).build();
    }



    @GetMapping("/autenticado")
    public ResponseEntity<UsuarioInfoDTO> obterUsuarioAutenticado(){
        Usuario usuario = this.usuarioUtil.obterUsuarioLogado();
        Loja loja = this.lojaServicePort.buscarPorUsuarioId(usuario.getId());

        return ResponseEntity.ok(new UsuarioInfoDTO(
                usuario.getId(),
                usuario.getNome(),
                usuario.getEmail(),
                loja != null ? loja.getId() : null
        ));
    }

}
