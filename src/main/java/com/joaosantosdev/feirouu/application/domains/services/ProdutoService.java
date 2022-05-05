package com.joaosantosdev.feirouu.application.domains.services;

import com.joaosantosdev.feirouu.application.domains.filtros.ProdutoFiltro;
import com.joaosantosdev.feirouu.application.domains.models.Produto;
import com.joaosantosdev.feirouu.application.domains.ports.in.LojaServicePort;
import com.joaosantosdev.feirouu.application.domains.ports.in.ProdutoServicePort;
import com.joaosantosdev.feirouu.application.domains.ports.out.MovimentacaoPersistencePort;
import com.joaosantosdev.feirouu.application.domains.ports.out.ProdutoPersistencePort;
import com.joaosantosdev.feirouu.application.exceptions.NegocioException;
import com.joaosantosdev.feirouu.application.utils.Pagina;
import com.joaosantosdev.feirouu.application.utils.Paginacao;

import java.util.Optional;

public class ProdutoService implements ProdutoServicePort {
    private ProdutoPersistencePort produtoPersistencePort;
    private LojaServicePort lojaServicePort;
    private MovimentacaoPersistencePort movimentacaoPersistencePort;


    public ProdutoService(ProdutoPersistencePort produtoPersistencePort, LojaServicePort lojaServicePort, MovimentacaoPersistencePort movimentacaoPersistencePort) {
        this.produtoPersistencePort = produtoPersistencePort;
        this.lojaServicePort = lojaServicePort;
        this.movimentacaoPersistencePort = movimentacaoPersistencePort;
    }

    @Override
    public Long cadastrar(Produto produto) {
        this.lojaServicePort.buscarPorIdUsuarioId(produto.getLoja().getId(),
                produto.getLoja().getUsuario().getId());
        return produto.cadastrar(this.produtoPersistencePort);
    }

    @Override
    public void atualizar(Produto produto) {
        produto.atualizar(this.produtoPersistencePort);
    }

    @Override
    public Produto buscar(Long id, Long lojaId, Long usuarioId) {
        this.lojaServicePort.buscarPorIdUsuarioId(lojaId, usuarioId);
        Optional<Produto> produtoOptional = this.produtoPersistencePort.buscar(id, lojaId);

        if(produtoOptional.isEmpty()){
            throw new NegocioException(404, "Produto não encontrado");
        }
        return produtoOptional.get();
    }

    @Override
    public Pagina<Produto> buscarPaginado(Long lojaId, Long usuarioId, Paginacao paginacao, ProdutoFiltro produtoFiltro) {
        this.lojaServicePort.buscarPorIdUsuarioId(lojaId, usuarioId);
        Pagina<Produto> produtoPagina = this.produtoPersistencePort.buscarPaginado(lojaId, paginacao, produtoFiltro);
        return produtoPagina;
    }

    @Override
    public void verificarSeTemQuantidadeDisponivel(Integer quantidade, Long id) {
        if(this.produtoPersistencePort.obterQuantidadeDisponivel(id) < quantidade){
            throw new NegocioException(400, "Quantidade indisponivel de produto");
        }
    }

    @Override
    public void excluirProduto(Long produtoId, Long lojaId, Long usuarioId) {
        this.lojaServicePort.buscarPorIdUsuarioId(lojaId, usuarioId);
        this.produtoPersistencePort.buscar(produtoId, lojaId);

        if(this.movimentacaoPersistencePort.produtoTemMovimentacoes(produtoId)){
            throw  new NegocioException(400, "Produto possui movimentações vinculadas e não pode ser excluído.");
        }
        this.produtoPersistencePort.excluir(produtoId);
    }

}
