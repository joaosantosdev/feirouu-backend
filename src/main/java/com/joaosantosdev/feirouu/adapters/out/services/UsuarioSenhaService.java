package com.joaosantosdev.feirouu.adapters.out.services;

import com.joaosantosdev.feirouu.application.domains.ports.out.UsuarioSenhaServicePort;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsuarioSenhaService implements UsuarioSenhaServicePort {
    private BCryptPasswordEncoder encoder;

    private UsuarioSenhaService(BCryptPasswordEncoder encoder){
        this.encoder = encoder;
    }

    @Override
    public String obterSenhaCriptografada(String senha) {
        return encoder.encode(senha);
    }
}
