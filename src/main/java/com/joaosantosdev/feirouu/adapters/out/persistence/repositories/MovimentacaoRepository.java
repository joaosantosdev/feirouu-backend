package com.joaosantosdev.feirouu.adapters.out.persistence.repositories;

import com.joaosantosdev.feirouu.adapters.out.persistence.entities.MovimentacaoEnitity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MovimentacaoRepository extends JpaRepository<MovimentacaoEnitity, Long> {
    @Query(value = "SELECT m.quantidade - SUM(COALESCE(md.quantidade, 0)) " +
            "FROM movimentacao AS m " +
            "LEFT JOIN movimentacao AS md ON md.movimentacao_referencia_id = m.id " +
            " where m.id = :movimentacaoId GROUP BY m.quantidade", nativeQuery = true)
    Integer obterQuantidadeDisponivelDevolucao(Long movimentacaoId);

    @Query(value = "SELECT m.* FROM movimentacao m " +
            "INNER JOIN produto p ON p.id = m.produto_id " +
            "WHERE m.cliente_id = :cpf AND m.tipo_movimentacao = 2 AND p.loja_id = :lojaId", nativeQuery = true)
    List<MovimentacaoEnitity> obterSaidasPorClienteCpfLojaId(@Param("cpf") String cpf, @Param("lojaId") Long lojaId);

    @Query(value = "SELECT COUNT(*) > 0 FROM movimentacao m WHERE m.produto_id = :produtoId", nativeQuery = true)
    Boolean produtoTemMovimentacoes(Long produtoId);

    @Query(value = "SELECT m.* FROM movimentacao m " +
            "LEFT JOIN movimentacao AS ms ON ms.movimentacao_referencia_id = m.id AND ms.tipo_movimentacao = 2 " +
            "LEFT JOIN movimentacao AS md ON md.movimentacao_referencia_id = ms.id AND md.tipo_movimentacao = 3 " +
            "WHERE  m.tipo_movimentacao = 1 AND m.produto_id = :produtoId AND ms.id IS NULL AND md.id IS NULL", nativeQuery = true)
    List<MovimentacaoEnitity> obterEntradasDisponiveisPorProduto(@Param("produtoId") Long produtoId);

    @Query(value = "SELECT SUM(CASE WHEN m.tipo_movimentacao = 1 THEN m.quantidade " +
            "WHEN m.tipo_movimentacao = 2 THEN -m.quantidade " +
            "WHEN m.tipo_movimentacao = 3 THEN m.quantidade ELSE 0 END)" +
            "FROM movimentacao m where m.produto_id = :produtoId GROUP BY produto_id", nativeQuery = true)
    Integer obterQuantidadeDisponivelSaida(@Param("produtoId") Long id);


    @Query(value = "SELECT m.*, p.* FROM movimentacao m " +
            "INNER JOIN produto p ON p.id = m.produto_id " +
            "LEFT JOIN movimentacao AS md ON md.movimentacao_referencia_id = m.id AND md.tipo_movimentacao = 3 " +
            "WHERE md.id IS NULL AND m.tipo_movimentacao = 2 AND p.loja_id = :lojaId AND date(m.data_hora) " +
            "BETWEEN TO_DATE(:dataInicial, 'YYYY-MM-DD') AND TO_DATE(:dataFinal, 'YYYY-MM-DD')", nativeQuery = true)
    List<MovimentacaoEnitity> obterRelatorioVendas(@Param("lojaId") Long lojaId, @Param("dataInicial") String dataInicial, @Param("dataFinal") String dataFinal);
}

