package com.joaosantosdev.feirouu.application.domains.ports.in;

import com.joaosantosdev.feirouu.application.domains.filtros.ProdutoFiltro;
import com.joaosantosdev.feirouu.application.domains.models.Produto;
import com.joaosantosdev.feirouu.application.utils.Pagina;
import com.joaosantosdev.feirouu.application.utils.Paginacao;

import java.util.List;


public interface ProdutoServicePort {
    Produto cadastrar(Produto produto);
    void atualizar(Produto map);
    Produto buscar(Long id, Long lojaId, Long usuarioId);
    Pagina<Produto> buscarPaginado(Long lojaId, Long usuarioId, Paginacao paginacao, ProdutoFiltro produtoFiltro);

    void verificarSeTemQuantidadeDisponivel(Integer quantidade, Long id);

    void excluirProduto(Long produtoId, Long lojaId, Long usuarioId);

    List<Produto> buscarPorLoja(Long lojaId);
}
