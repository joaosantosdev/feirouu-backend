package com.joaosantosdev.feirouu.application.domains.services;

import com.joaosantosdev.feirouu.application.domains.models.CodigoVerificacao;
import com.joaosantosdev.feirouu.application.domains.models.Usuario;
import com.joaosantosdev.feirouu.application.domains.ports.in.UsuarioServicePort;
import com.joaosantosdev.feirouu.application.domains.ports.out.CodigoVerificacaoPersistencePort;
import com.joaosantosdev.feirouu.application.domains.ports.out.EnvioCodigoVerificacaoServicePort;
import com.joaosantosdev.feirouu.application.domains.ports.out.UsuarioPersistencePort;
import com.joaosantosdev.feirouu.application.domains.ports.out.UsuarioSenhaServicePort;

public class UsuarioService implements UsuarioServicePort {

    private UsuarioPersistencePort usuarioPersistencePort;
    private CodigoVerificacaoPersistencePort codigoVerificacaoPersistencePort;

    public UsuarioService(UsuarioPersistencePort usuarioPersistencePort,
                          CodigoVerificacaoPersistencePort codigoVerificacaoPersistencePort) {
        this.usuarioPersistencePort = usuarioPersistencePort;
        this.codigoVerificacaoPersistencePort = codigoVerificacaoPersistencePort;
    }

    @Override
    public Long salvar(Usuario usuario, CodigoVerificacao codigoVerificacao) {
        return usuario.cadastrar(usuarioPersistencePort,
                codigoVerificacaoPersistencePort,
                codigoVerificacao);
    }

    @Override
    public Usuario obterPorId(Long id) {
        return this.usuarioPersistencePort.obterPorId(id);
    }

    @Override
    public void atualizar(Usuario usuario, CodigoVerificacao codigoVerificacao) {
        usuario.atualizar(usuarioPersistencePort,
                codigoVerificacaoPersistencePort,
                codigoVerificacao
        );
    }

    @Override
    public void redefinirSenha(Usuario usuario, CodigoVerificacao codigoVerificacao) {
        usuario.redefinirSenha(usuarioPersistencePort,
                codigoVerificacaoPersistencePort,
                codigoVerificacao
        );
    }
}
