package com.joaosantosdev.feirouu.adapters.in.web.dtos;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.bind.annotation.GetMapping;

@NoArgsConstructor
@Getter
@Setter
public class FIltroProdutoDTO {
    private Integer pagina;
    private Integer porPagina;
    private String ordenarPor;
    private String direcao;

    public FIltroProdutoDTO(Integer pagina, Integer porPagina, String ordenarPor, String direcao) {
        this.pagina = pagina != null ? pagina : 0;
        this.porPagina = porPagina != null ? porPagina : 24;
        this.ordenarPor = ordenarPor != null ? ordenarPor : "nome";
        this.direcao = direcao != null ? direcao : "ASC";
    }


}
