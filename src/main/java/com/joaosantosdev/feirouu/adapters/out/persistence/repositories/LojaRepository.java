package com.joaosantosdev.feirouu.adapters.out.persistence.repositories;

import com.joaosantosdev.feirouu.adapters.out.persistence.entities.LojaEntity;
import com.joaosantosdev.feirouu.application.utils.FiltroLoja;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LojaRepository extends JpaRepository<LojaEntity, Long> {
    LojaEntity findByUsuarioId(Long usuarioId);
    Optional<LojaEntity> findByIdAndUsuarioId(Long id, Long usuarioId);



    @Query(value = "SELECT l.* FROM loja l " +
            "INNER JOIN endereco e ON e.id = l.endereco_id " +
            "INNER JOIN cidade c ON c.id = e.cidade_id " +
            "WHERE " +
            "(:cidadeId = 0 OR c.id = :cidadeId) AND " +
            "(:estadoId = 0 OR c.estado_id = :estadoId) AND " +
            "(:bairro IS NULL OR lower(e.bairro) like %:bairro% ) AND " +
            "(:nome IS NULL OR lower(l.nome) like %:nome%) ", nativeQuery = true)
    List<LojaEntity> obterLojasFiltros(@Param("nome") String nome,
                                       @Param("cidadeId") Integer cidadeId,
                                       @Param("estadoId") Integer estadoId,
                                       @Param("bairro") String bairro);
}
