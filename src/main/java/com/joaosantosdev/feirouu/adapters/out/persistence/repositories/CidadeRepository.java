package com.joaosantosdev.feirouu.adapters.out.persistence.repositories;

import com.joaosantosdev.feirouu.adapters.out.persistence.entities.CidadeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CidadeRepository extends JpaRepository<CidadeEntity, Integer> {
    List<CidadeEntity> findByEstadoId(Integer id);
}
