package com.joaosantosdev.feirouu.application.domains.ports.in;

import com.joaosantosdev.feirouu.application.domains.models.Cliente;
import com.joaosantosdev.feirouu.application.domains.models.Movimentacao;
import com.joaosantosdev.feirouu.application.domains.models.Usuario;

import java.time.LocalDate;
import java.util.List;

public interface MovimentacaoServicePort {
    Long registrarEntrada(Movimentacao movimentacao);

    void registrarSaidas(Cliente cliente, List<Movimentacao> movimentacoes);

    void registrarDevolucoes(Long lojaId, Usuario usuario, List<Movimentacao> mapDevolucoes);

    List<Movimentacao> obterSaidasPorCliente(Long usuarioId, Long lojaId, String cpf);

    Boolean produtoTemMovimentacoes(Long usuarioId, Long lojaId, Long produtoId);

    List<Movimentacao> obterEntradas(Long usuarioId, Long lojaId, Long produtoId);

    Integer obterQuantidadeDisponivelSaida(Long id, Long lojaId, Long produtoId);

    List<Movimentacao> obterRelatorioVendas(Long lojaId, Long usuarioId, String incial, String dataFinal);
}
