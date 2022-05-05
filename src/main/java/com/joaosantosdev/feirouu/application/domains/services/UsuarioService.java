package com.joaosantosdev.feirouu.application.domains.services;

import com.joaosantosdev.feirouu.application.domains.models.CodigoVerificacao;
import com.joaosantosdev.feirouu.application.domains.models.Usuario;
import com.joaosantosdev.feirouu.application.domains.ports.in.UsuarioServicePort;
import com.joaosantosdev.feirouu.application.domains.ports.out.CodigoVerificacaoPersistencePort;
import com.joaosantosdev.feirouu.application.domains.ports.out.EnvioCodigoVerificacaoServicePort;
import com.joaosantosdev.feirouu.application.domains.ports.out.UsuarioPersistencePort;

public class UsuarioService implements UsuarioServicePort {

    private UsuarioPersistencePort usuarioPersistencePort;
    private CodigoVerificacaoPersistencePort codigoVerificacaoPersistencePort;

    public UsuarioService(UsuarioPersistencePort usuarioPersistencePort, CodigoVerificacaoPersistencePort codigoVerificacaoPersistencePort){
        this.usuarioPersistencePort = usuarioPersistencePort;
        this.codigoVerificacaoPersistencePort = codigoVerificacaoPersistencePort;
    }

    @Override
    public Long salvar(Usuario usuario, CodigoVerificacao codigoVerificacao) {
        return usuario.cadastrar(usuarioPersistencePort,
                codigoVerificacaoPersistencePort,
                codigoVerificacao);
    }
}
