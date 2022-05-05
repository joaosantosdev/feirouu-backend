package com.joaosantosdev.feirouu.application.domains.services;


import com.joaosantosdev.feirouu.application.domains.models.CodigoVerificacao;
import com.joaosantosdev.feirouu.application.domains.ports.in.CodigoVerificacaoServicePort;
import com.joaosantosdev.feirouu.application.domains.ports.out.CodigoVerificacaoPersistencePort;
import com.joaosantosdev.feirouu.application.domains.ports.out.EnvioCodigoVerificacaoServicePort;
import com.joaosantosdev.feirouu.application.domains.ports.out.UsuarioPersistencePort;
import com.joaosantosdev.feirouu.application.exceptions.NegocioException;

public class CodigoVerificacaoService implements CodigoVerificacaoServicePort {

    private CodigoVerificacaoPersistencePort codigoVerificacaoPersistencePort;
    private EnvioCodigoVerificacaoServicePort envioCodigoVerificacaoServicePort;
    private UsuarioPersistencePort usuarioPersistencePort;

    public CodigoVerificacaoService(CodigoVerificacaoPersistencePort codigoVerificacaoPersistencePort, EnvioCodigoVerificacaoServicePort envioCodigoVerificacaoServicePort, UsuarioPersistencePort usuarioPersistencePort) {
        this.codigoVerificacaoPersistencePort = codigoVerificacaoPersistencePort;
        this.envioCodigoVerificacaoServicePort = envioCodigoVerificacaoServicePort;
        this.usuarioPersistencePort = usuarioPersistencePort;
    }

    @Override
    public CodigoVerificacao enviar(String email) {
        if(this.usuarioPersistencePort.verificarEmail(email)){
            throw new NegocioException(400, "Email já possui cadastro");
        }
        CodigoVerificacao codigoVerificacao = new CodigoVerificacao(email);
        codigoVerificacao.enviar(codigoVerificacaoPersistencePort, envioCodigoVerificacaoServicePort);
        return codigoVerificacao;
    }

}
