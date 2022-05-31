package com.joaosantosdev.feirouu.application.domains.ports.out;

public interface UsuarioSenhaServicePort {
    String obterSenhaCriptografada(String senha);
}
