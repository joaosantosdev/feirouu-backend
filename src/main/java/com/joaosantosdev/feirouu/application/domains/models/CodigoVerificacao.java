package com.joaosantosdev.feirouu.application.domains.models;


import com.joaosantosdev.feirouu.application.exceptions.NegocioException;
import com.joaosantosdev.feirouu.application.domains.ports.out.CodigoVerificacaoPersistencePort;
import com.joaosantosdev.feirouu.application.domains.ports.out.EnvioCodigoVerificacaoServicePort;

import java.time.LocalDateTime;
import java.util.Random;

public class CodigoVerificacao {
    private Long id;
    private String email;
    private String codigo;
    private Long timestapCriacao;

    public CodigoVerificacao(String email){
        this.email = email;
        this.codigo = this.gerarCodigoVerificacao();
    }

    public CodigoVerificacao(String email, String codigo){
        this.email = email;
        this.codigo = codigo;
    }

    public CodigoVerificacao(Long id, String email, String codigo, Long timestapCriacao) {
        this.id = id;
        this.email = email;
        this.codigo = codigo;
        this.timestapCriacao = timestapCriacao;
    }

    private String gerarCodigoVerificacao(){
        Random random = new Random();
        int ULTIMA_LETRA_ALFABETO_MAISCULO = 95;
        int PRIMEIRA_LETRA_ALFABETO_MAISCULO = 65;
        int i = random.nextInt(ULTIMA_LETRA_ALFABETO_MAISCULO - PRIMEIRA_LETRA_ALFABETO_MAISCULO + 1) + PRIMEIRA_LETRA_ALFABETO_MAISCULO;
        StringBuilder codigo = new StringBuilder(String.valueOf((char) i));
        for(i = 1; i <= 6;i++){
            codigo.append(random.nextInt(9-1+1)+1);
        }
        return codigo.toString();
    }

    public void enviar(CodigoVerificacaoPersistencePort codigoVerificacaoPersistencePort, EnvioCodigoVerificacaoServicePort emailServicePort){
        CodigoVerificacao ultimoCodigo = codigoVerificacaoPersistencePort.obterUltimoCodigoVerificacao(this.getEmail());
        long DEZ_MINUTOS = 600000 ;
        long UM_MINUTO = 60000;
        if(ultimoCodigo != null && (System.currentTimeMillis() <= (ultimoCodigo.getTimestapCriacao()  + DEZ_MINUTOS) - UM_MINUTO)){
            this.timestapCriacao = ultimoCodigo.getTimestapCriacao();
            this.email = ultimoCodigo.getEmail();
        }else{
            if(!emailServicePort.enviar(this)){
                throw new NegocioException(400, "Erro ao enviar codigo de verificação");
            }
            this.timestapCriacao = System.currentTimeMillis();
            codigoVerificacaoPersistencePort.salvar(this);
        }

        this.codigo = null;
    }

    public void validar(CodigoVerificacaoPersistencePort codigoVerificacaoPersistencePort){
        CodigoVerificacao codigoVerificacao = codigoVerificacaoPersistencePort.obterCodigoVerificacaoValido(this);
        if(codigoVerificacao == null || codigoVerificacao.getTimestapCriacao() > System.currentTimeMillis()){
            throw new NegocioException(400, "Código de verificação inválido");
        }
    }

    public Long getId() {
        return id;
    }

    public String getCodigo() {
        return codigo;
    }

    public Long getTimestapCriacao() {
        return timestapCriacao;
    }

    public String getEmail() {
        return email;
    }
}
