package com.joaosantosdev.feirouu.adapters.out.persistence.repositories;

import com.joaosantosdev.feirouu.adapters.out.persistence.entities.ProdutoEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public interface ProdutoRepository extends JpaRepository<ProdutoEntity, Long> {
    ProdutoEntity findByIdAndLojaId(Long id, Long lojaId);

    @Query(value = "SELECT SUM(CASE WHEN m.tipo_movimentacao = 1 THEN m.quantidade " +
            "WHEN m.tipo_movimentacao = 2 THEN -m.quantidade " +
            "WHEN m.tipo_movimentacao = 3 THEN m.quantidade ELSE 0 END)" +
            "FROM movimentacao m where m.produto_id = :produtoId GROUP BY produto_id", nativeQuery = true)
    Integer obterQuantidadeDisponivel(Long produtoId);

    @Query("SELECT prod FROM ProdutoEntity prod INNER JOIN prod.loja loja WHERE prod.status in :status AND loja.id = :lojaId")
    Page<ProdutoEntity> buscaPaginada(@Param("status") ArrayList<Integer> status, @Param("lojaId") Long lojaId, Pageable pageRequest);

    List<ProdutoEntity> findByLojaId(Long lojaId);
}
