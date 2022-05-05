package com.joaosantosdev.feirouu.application.domains.models;

import com.joaosantosdev.feirouu.application.domains.enums.TipoMovimentacao;
import com.joaosantosdev.feirouu.application.domains.ports.in.ProdutoServicePort;
import com.joaosantosdev.feirouu.application.domains.ports.out.MovimentacaoPersistencePort;
import com.joaosantosdev.feirouu.application.exceptions.NegocioException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public class Movimentacao {
    private Long id;
    private Double valorDesconto;
    private Double valorVenda;
    private Double valorCompra;
    private Integer quantidade;
    private LocalDateTime dataHora;
    private Cliente cliente;
    private Produto produto;
    private Movimentacao movimentacaoReferencia;
    private TipoMovimentacao tipoMovimentacao;
    private List<MovimentacaoValorEtiqueta> valoresEtiquetas;

    public Movimentacao(Long id) {
        this.id = id;
    }

    public Movimentacao(Integer quantidade, Movimentacao movimentacaoReferencia) {
        this.quantidade = quantidade;
        this.movimentacaoReferencia = movimentacaoReferencia;
    }

    public Movimentacao(Integer quantidade, Produto produto, List<MovimentacaoValorEtiqueta> valoresEtiquetas) {
        this.quantidade = quantidade;
        this.produto = produto;
        this.valoresEtiquetas = valoresEtiquetas;
    }

    public Movimentacao(Integer quantidade, Double valorDesconto, Produto produto, Cliente cliente) {
        this.quantidade = quantidade;
        this.valorDesconto = valorDesconto;
        this.produto = produto;
        this.cliente = cliente;
    }

    public Movimentacao(Long id, Double valorDesconto, Double valorCompra, Double valorVenda, Integer quantidade, LocalDateTime dataHora, Produto produto, Cliente cliente, TipoMovimentacao tipoMovimentacao) {
        this.id = id;
        this.valorDesconto = valorDesconto;
        this.valorCompra = valorCompra;
        this.valorVenda = valorVenda;
        this.quantidade = quantidade;
        this.dataHora = dataHora;
        this.produto = produto;
        this.cliente = cliente;
        this.tipoMovimentacao = tipoMovimentacao;
    }


    public Long getId() {
        return id;
    }

    public Double getValorDesconto() {
        return valorDesconto;
    }

    public Double getValorVenda() {
        return valorVenda;
    }

    public Double getValorCompra() {
        return valorCompra;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }


    public Cliente getCliente() {
        return cliente;
    }

    public Produto getProduto() {
        return produto;
    }

    public Movimentacao getMovimentacaoReferencia() {
        return movimentacaoReferencia;
    }

    public TipoMovimentacao getTipoMovimentacao() {
        return tipoMovimentacao;
    }

    public List<MovimentacaoValorEtiqueta> getValoresEtiquetas() {
        return valoresEtiquetas;
    }

    public Long registrarEntrada(MovimentacaoPersistencePort movimentacaoPersistencePort, ProdutoServicePort produtoServicePort) {

        this.produto = produtoServicePort.buscar(this.produto.getId(),
                this.produto.getLoja().getId(),
                produto.getLoja().getUsuario().getId());

        this.produto.getChavesEtiquetas().forEach(chave -> {
            boolean mandouChave = false;
            for(MovimentacaoValorEtiqueta valorEtiqueta : this.getValoresEtiquetas()){
                if(valorEtiqueta.getProdutoChaveEtiqueta().equals(chave.getId())){
                    mandouChave = true;
                    break;
                }
            }
            if(!mandouChave){
                throw new NegocioException(400, "As etiquetas não foram preenchidas corretamente.");
            }
        });

        this.tipoMovimentacao = TipoMovimentacao.ENTRADA;
        this.dataHora = LocalDateTime.now();
        return movimentacaoPersistencePort.registrarEntrada(this);
    }

    public void preecherInformacoesSaidas(ProdutoServicePort produtoServicePort){
        this.produto = produtoServicePort.buscar(this.produto.getId(),
                this.produto.getLoja().getId(),
                this.produto.getLoja().getUsuario().getId());
        this.tipoMovimentacao = TipoMovimentacao.SAIDA;
        this.dataHora = LocalDateTime.now();
    }

    public void preecherInformacoesDevolucao(Long lojaId, Usuario usuario, ProdutoServicePort produtoServicePort,
                                             MovimentacaoPersistencePort movimentacaoPersistencePort) {
        Optional<Movimentacao> movimentacaoReferenciaOp = movimentacaoPersistencePort.obterMovimentacaoPorId(this.getMovimentacaoReferencia().getId());
        this.movimentacaoReferencia = movimentacaoReferenciaOp
                .orElseThrow(() -> new NegocioException(400, "Movimento de referencia não encotnado"));

        Integer quantidadeSaidas = movimentacaoPersistencePort.obterQuantidadeDisponivelDevelocao(this.movimentacaoReferencia.getId());

        if(this.quantidade > quantidadeSaidas){
            throw new NegocioException(400, "Quantidade de devolução insuficiente");
        }

        this.produto = movimentacaoReferencia.getProduto();
        this.produto = produtoServicePort.buscar(this.produto.getId(),
                lojaId,
                usuario.getId());
        this.tipoMovimentacao = TipoMovimentacao.DEVOLUCAO;
        this.dataHora = LocalDateTime.now();
    }
}
