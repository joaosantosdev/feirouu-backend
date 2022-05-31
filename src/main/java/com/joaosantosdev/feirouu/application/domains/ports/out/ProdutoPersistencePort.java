package com.joaosantosdev.feirouu.application.domains.ports.out;

import com.joaosantosdev.feirouu.application.domains.filtros.ProdutoFiltro;
import com.joaosantosdev.feirouu.application.domains.models.Produto;
import com.joaosantosdev.feirouu.application.utils.Pagina;
import com.joaosantosdev.feirouu.application.utils.Paginacao;

import java.util.List;
import java.util.Optional;

public interface ProdutoPersistencePort {
    Produto cadastrar(Produto produto);
    void atualizar(Produto produto);
    Optional<Produto> buscar(Long id, Long lojaId);
    Pagina<Produto> buscarPaginado(Long lojaId, Paginacao paginacao, ProdutoFiltro produtoFiltro);

    Integer obterQuantidadeDisponivel(Long id);

    void excluir(Long produtoId);

    List<Produto> buscarPorLoja(Long lojaId);
}
