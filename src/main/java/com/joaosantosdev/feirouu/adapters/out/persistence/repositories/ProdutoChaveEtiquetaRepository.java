package com.joaosantosdev.feirouu.adapters.out.persistence.repositories;

import com.joaosantosdev.feirouu.adapters.out.persistence.entities.ProdutoChaveEtiquetaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdutoChaveEtiquetaRepository extends JpaRepository<ProdutoChaveEtiquetaEntity, Long> {
}
