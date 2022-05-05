package com.joaosantosdev.feirouu.adapters.in.web.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class ProdutoListagemDTO {

    private List<ProdutoDTO> data = new ArrayList<>();

    public ProdutoListagemDTO(ProdutoDTO data) {
        this.data = List.of(data);
    }
}
