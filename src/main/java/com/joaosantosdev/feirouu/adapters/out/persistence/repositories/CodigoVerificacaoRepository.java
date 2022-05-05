package com.joaosantosdev.feirouu.adapters.out.persistence.repositories;

import com.joaosantosdev.feirouu.adapters.out.persistence.entities.CodigoVerificacaoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CodigoVerificacaoRepository extends JpaRepository<CodigoVerificacaoEntity, Long> {
    @Query("FROM CodigoVerificacaoEntity c WHERE c.email = :email AND c.codigo = :codigo ORDER BY c.timestampCriacao DESC")
    CodigoVerificacaoEntity obterCodigoVerificacaoValido(@Param("email") String email,
                                                          @Param("codigo") String codigo);

    @Query(value = "SELECT * FROM codigo_verificacao c WHERE c.email = :email ORDER BY timestamp_criacao DESC LIMIT 1", nativeQuery = true)
    CodigoVerificacaoEntity obterUltimoCodigo(String email);
}
