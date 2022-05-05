package com.joaosantosdev.feirouu.adapters.in.web.dtos;

import com.joaosantosdev.feirouu.application.domains.models.Cliente;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ListaSaidaDTO {
    private List<MovimentacaoSaidaDTO> saidas;
    private ClienteDTO cliente;
}
