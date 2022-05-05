package com.joaosantosdev.feirouu.security;

import com.joaosantosdev.feirouu.adapters.out.persistence.entities.UsuarioEntity;
import com.joaosantosdev.feirouu.adapters.out.persistence.repositories.UsuarioRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UsuarioDetailsService implements UserDetailsService {
    private UsuarioRepository usuarioEntityRepository;

    public UsuarioDetailsService(UsuarioRepository usuarioRepository){
        this.usuarioEntityRepository = usuarioRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException
    {
        UsuarioEntity usuarioEntity = this.usuarioEntityRepository.findByEmail(email);

        if(usuarioEntity == null){
            throw new UsernameNotFoundException("Usuário não encontrado");
        }

        return new UsuarioDetails(usuarioEntity.getId(),
                usuarioEntity.getEmail(),
                usuarioEntity.getSenha());
    }
}
