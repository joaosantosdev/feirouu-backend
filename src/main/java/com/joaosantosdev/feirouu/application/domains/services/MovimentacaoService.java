package com.joaosantosdev.feirouu.application.domains.services;

import com.joaosantosdev.feirouu.application.domains.models.Cliente;
import com.joaosantosdev.feirouu.application.domains.models.Movimentacao;
import com.joaosantosdev.feirouu.application.domains.models.Usuario;
import com.joaosantosdev.feirouu.application.domains.ports.in.LojaServicePort;
import com.joaosantosdev.feirouu.application.domains.ports.in.MovimentacaoServicePort;
import com.joaosantosdev.feirouu.application.domains.ports.in.ProdutoServicePort;
import com.joaosantosdev.feirouu.application.domains.ports.out.MovimentacaoPersistencePort;

import java.time.LocalDate;
import java.util.List;

public class MovimentacaoService implements MovimentacaoServicePort {

    private MovimentacaoPersistencePort movimentacaoPersistencePort;
    private ProdutoServicePort produtoServicePort;
    private LojaServicePort lojaServicePort;

    public MovimentacaoService(MovimentacaoPersistencePort movimentacaoPersistencePort, ProdutoServicePort produtoServicePort, LojaServicePort lojaServicePort) {
        this.movimentacaoPersistencePort = movimentacaoPersistencePort;
        this.produtoServicePort = produtoServicePort;
        this.lojaServicePort = lojaServicePort;
    }

    @Override
    public Long registrarEntrada(Movimentacao movimentacao) {
        return movimentacao.registrarEntrada(this.movimentacaoPersistencePort, this.produtoServicePort);
    }

    @Override
    public void registrarSaidas(Cliente cliente, List<Movimentacao> movimentacoes) {
        movimentacoes.forEach(item -> {
            item.preecherInformacoesSaidas(this.produtoServicePort);
            this.produtoServicePort.verificarSeTemQuantidadeDisponivel(item.getQuantidade(), item.getProduto().getId());
        });
        this.movimentacaoPersistencePort.registrarSaidas(cliente, movimentacoes);
    }

    @Override
    public void registrarDevolucoes(Long lojaId, Usuario usuario, List<Movimentacao> movimentacoes) {
        movimentacoes.forEach(movimentacao -> {
            movimentacao.preecherInformacoesDevolucao(lojaId,
                    usuario,this.produtoServicePort,
                    this.movimentacaoPersistencePort);
        });
        this.movimentacaoPersistencePort.registrarDevolucoes(movimentacoes);
    }

    @Override
    public List<Movimentacao> obterSaidasPorCliente(Long usuarioId, Long lojaId, String cpf) {
        this.lojaServicePort.buscarPorIdUsuarioId(lojaId, usuarioId);
        return this.movimentacaoPersistencePort.obterSaidasPorClienteCpfLojaId(cpf, lojaId);
    }

    @Override
    public Boolean produtoTemMovimentacoes(Long usuarioId, Long lojaId, Long produtoId) {
        this.lojaServicePort.buscarPorIdUsuarioId(lojaId, usuarioId);
        this.produtoServicePort.buscar(produtoId, lojaId, usuarioId);
        return this.movimentacaoPersistencePort.produtoTemMovimentacoes(produtoId);
    }

    @Override
    public List<Movimentacao> obterEntradas(Long usuarioId, Long lojaId, Long produtoId) {
        this.lojaServicePort.buscarPorIdUsuarioId(lojaId, usuarioId);
        this.produtoServicePort.buscar(produtoId, lojaId, usuarioId);

        return this.movimentacaoPersistencePort.obterEntradasPorProduto(produtoId);
    }

    @Override
    public Integer obterQuantidadeDisponivelSaida(Long usuarioId, Long lojaId, Long produtoId) {
        this.lojaServicePort.buscarPorIdUsuarioId(lojaId, usuarioId);
        this.produtoServicePort.buscar(produtoId, lojaId, usuarioId);
        return this.movimentacaoPersistencePort.obterQuantidadeDisponivelSaidaPorProduto(produtoId);
    }

    @Override
    public List<Movimentacao> obterRelatorioVendas(Long lojaId, Long usuarioId, String dataInicial, String dataFinal) {
        this.lojaServicePort.buscarPorIdUsuarioId(lojaId, usuarioId);

        return this.movimentacaoPersistencePort.obterRelatorioVendas(lojaId, dataInicial, dataFinal);
    }
}
