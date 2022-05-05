package com.joaosantosdev.feirouu.adapters.in.web.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioInfoDTO {
    private Long id;
    private String nome;
    private String email;
    private Long lojaId;
}
