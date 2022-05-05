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
}

