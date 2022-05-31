package com.joaosantosdev.feirouu.application.domains.ports.out;

import com.joaosantosdev.feirouu.application.domains.models.Cliente;
import com.joaosantosdev.feirouu.application.domains.models.Movimentacao;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface MovimentacaoPersistencePort {
    Long registrarEntrada(Movimentacao movimentacao);

    void registrarSaidas(Cliente cliente, List<Movimentacao> movimentacoes);

    Optional<Movimentacao> obterMovimentacaoPorId(Long id);

    void registrarDevolucoes(List<Movimentacao> movimentacoes);

    Integer obterQuantidadeDisponivelDevolucao(Long id);
    Integer obterQuantidadeDisponivelSaidaPorProduto(Long id);

    List<Movimentacao> obterSaidasPorClienteCpfLojaId(String cpf, Long lojaId);

    Boolean produtoTemMovimentacoes(Long produtoId);

    List<Movimentacao> obterEntradasPorProduto(Long produtoId);

    List<Movimentacao> obterRelatorioVendas(Long lojaId, String dataInicial, String dataFinal);
}
