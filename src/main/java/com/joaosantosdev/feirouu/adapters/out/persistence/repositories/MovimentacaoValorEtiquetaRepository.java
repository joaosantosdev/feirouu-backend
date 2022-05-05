package com.joaosantosdev.feirouu.adapters.out.persistence.repositories;

import com.joaosantosdev.feirouu.adapters.out.persistence.entities.MovimentacaoValorEtiquetaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovimentacaoValorEtiquetaRepository extends JpaRepository<MovimentacaoValorEtiquetaEntity, Long> {
}
