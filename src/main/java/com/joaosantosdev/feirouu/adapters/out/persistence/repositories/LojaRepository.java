package com.joaosantosdev.feirouu.adapters.out.persistence.repositories;

import com.joaosantosdev.feirouu.adapters.out.persistence.entities.LojaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LojaRepository extends JpaRepository<LojaEntity, Long> {
    LojaEntity findByUsuarioId(Long usuarioId);
    Optional<LojaEntity> findByIdAndUsuarioId(Long id, Long usuarioId);

}
