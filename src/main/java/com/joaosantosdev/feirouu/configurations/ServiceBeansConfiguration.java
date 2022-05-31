package com.joaosantosdev.feirouu.configurations;

import com.joaosantosdev.feirouu.application.domains.ports.in.*;
import com.joaosantosdev.feirouu.application.domains.ports.out.*;
import com.joaosantosdev.feirouu.application.domains.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class ServiceBeansConfiguration {

    @Autowired
    private LojaPersistencePort lojaPersistencePort;
    @Autowired
    private ProdutoPersistencePort produtoPersistencePort;

    @Autowired
    private MovimentacaoPersistencePort movimentacaoPersistencePort;


    @Autowired
    private UsuarioPersistencePort usuarioPersistencePort;

    @Autowired
    private EnderecoPersistencePort enderecoPersistencePort;

    @Autowired UsuarioSenhaServicePort usuarioSenhaServicePort;

    @Bean
    public CodigoVerificacaoServicePort codigoVerificacaoServicePort(CodigoVerificacaoPersistencePort codigoVerificacaoPersistencePort,
                                                                     EnvioCodigoVerificacaoServicePort envioCodigoVerificacaoServicePort){
        return new CodigoVerificacaoService(codigoVerificacaoPersistencePort, envioCodigoVerificacaoServicePort, this.usuarioPersistencePort);
    }

    @Bean
    public UsuarioServicePort usuarioServicePort(UsuarioPersistencePort usuarioPersistencePort,
                                                 CodigoVerificacaoPersistencePort codigoVerificacaoPersistencePort){
        return new UsuarioService(usuarioPersistencePort, codigoVerificacaoPersistencePort);
    }

    @Bean
    @Primary
    public LojaServicePort lojaServicePort(){
        return new LojaService(this.lojaPersistencePort);
    }

    @Bean
    public ProdutoServicePort produtoServicePort(){
        return new ProdutoService(this.produtoPersistencePort, lojaServicePort(), this.movimentacaoPersistencePort);
    }

    @Bean
    public MovimentacaoServicePort movimentacaoServicePort(){
        return new MovimentacaoService(this.movimentacaoPersistencePort, produtoServicePort(), lojaServicePort());
    }

    @Bean
    public EnderecoServicePort enderecoServicePort(){
        return new EnderecoService(this.enderecoPersistencePort);
    }
}
