package com.joaosantosdev.feirouu.application.domains.ports.out;

import com.joaosantosdev.feirouu.application.domains.models.Cliente;
import com.joaosantosdev.feirouu.application.domains.models.Movimentacao;

import java.util.List;
import java.util.Optional;

public interface MovimentacaoPersistencePort {
    Long registrarEntrada(Movimentacao movimentacao);

    void registrarSaidas(Cliente cliente, List<Movimentacao> movimentacoes);

    Optional<Movimentacao> obterMovimentacaoPorId(Long id);

    void registrarDevolucoes(List<Movimentacao> movimentacoes);

    Integer obterQuantidadeDisponivelDevelocao(Long id);

    List<Movimentacao> obterSaidasPorClienteCpfLojaId(String cpf, Long lojaId);

    Boolean produtoTemMovimentacoes(Long produtoId);
}
