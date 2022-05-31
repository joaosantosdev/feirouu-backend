package com.joaosantosdev.feirouu.adapters.out.persistence.services;

import com.joaosantosdev.feirouu.adapters.out.persistence.entities.UsuarioEntity;
import com.joaosantosdev.feirouu.adapters.out.persistence.mappers.UsuarioEntityMapper;
import com.joaosantosdev.feirouu.adapters.out.persistence.repositories.UsuarioRepository;
import com.joaosantosdev.feirouu.application.domains.models.Usuario;
import com.joaosantosdev.feirouu.application.domains.ports.out.UsuarioPersistencePort;
import com.joaosantosdev.feirouu.application.domains.ports.out.UsuarioSenhaServicePort;
import org.hibernate.proxy.HibernateProxy;
import org.hibernate.proxy.LazyInitializer;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class UsuarioPersistence implements UsuarioPersistencePort {
    private UsuarioRepository usuarioRepository;
    private UsuarioSenhaServicePort usuarioSenhaServicePort;

    public UsuarioPersistence(UsuarioRepository usuarioRepository, UsuarioSenhaServicePort usuarioSenhaServicePort){
        this.usuarioRepository = usuarioRepository;
        this.usuarioSenhaServicePort = usuarioSenhaServicePort;
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

    @Override
    @Transactional(readOnly = true)
    public Usuario obterPorId(Long id) {
        UsuarioEntity usuarioEntity = this.usuarioRepository.obterPorId(id);
        return UsuarioEntityMapper.map(usuarioEntity);
    }

    @Override
    @Transactional
    public void atualizar(Usuario usuario) {
        UsuarioEntity usuarioEntity = this.usuarioRepository.obterPorId(usuario.getId());
        usuarioEntity.setNome(usuario.getNome());
        usuarioEntity.setEmail(usuario.getEmail());
        usuarioEntity.setTelefone(usuario.getTelefone());

        if(usuario.getSenha() != null && !usuario.getSenha().trim().equals("")){
            usuarioEntity.setSenha(this.usuarioSenhaServicePort.obterSenhaCriptografada(usuario.getSenha()));
        }

        this.usuarioRepository.save(usuarioEntity);
    }

    @Override
    public void redefinirSenha(Usuario usuario) {
        UsuarioEntity usuarioEntity = this.usuarioRepository.findByEmail(usuario.getEmail());
        usuarioEntity.setSenha(usuario.getSenha());
        this.usuarioRepository.save(usuarioEntity);
    }

}
