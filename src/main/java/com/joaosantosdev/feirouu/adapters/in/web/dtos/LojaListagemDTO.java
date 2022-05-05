package com.joaosantosdev.feirouu.adapters.in.web.dtos;

import com.joaosantosdev.feirouu.application.domains.models.Loja;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class LojaListagemDTO {

    private List<LojaDTO> data = new ArrayList<>();

    public LojaListagemDTO(LojaDTO data) {
        this.data = List.of(data);
    }
}
