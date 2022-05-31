package com.joaosantosdev.feirouu.adapters.out.persistence.repositories;

import com.joaosantosdev.feirouu.adapters.out.persistence.entities.UsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<UsuarioEntity, Long> {
    UsuarioEntity findByEmail(String email);

    @Query(value = "SELECT * FROM usuario WHERE id = :id", nativeQuery = true)
    UsuarioEntity obterPorId(Long id);

}
