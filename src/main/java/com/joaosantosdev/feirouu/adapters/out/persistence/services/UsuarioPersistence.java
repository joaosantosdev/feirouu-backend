package com.joaosantosdev.feirouu.adapters.out.persistence.services;

import com.joaosantosdev.feirouu.adapters.out.persistence.entities.UsuarioEntity;
import com.joaosantosdev.feirouu.adapters.out.persistence.mappers.UsuarioEntityMapper;
import com.joaosantosdev.feirouu.adapters.out.persistence.repositories.UsuarioRepository;
import com.joaosantosdev.feirouu.application.domains.models.Usuario;
import com.joaosantosdev.feirouu.application.domains.ports.out.UsuarioPersistencePort;
import org.springframework.stereotype.Service;

@Service
public class UsuarioPersistence implements UsuarioPersistencePort {
    private UsuarioRepository usuarioRepository;

    public UsuarioPersistence(UsuarioRepository usuarioRepository){
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public Long salvar(Usuario usuario) {
        UsuarioEntity entity = this.usuarioRepository.save(UsuarioEntityMapper.map(usuario));
        return entity.getId();
    }

    @Override
    public Boolean verificarEmail(String email) {
        return this.usuarioRepository.findByEmail(email) != null;
    }

}
