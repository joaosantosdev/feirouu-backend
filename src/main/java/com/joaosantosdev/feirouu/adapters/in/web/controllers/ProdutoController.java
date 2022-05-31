package com.joaosantosdev.feirouu.adapters.in.web.controllers;

import com.joaosantosdev.feirouu.adapters.in.web.dtos.ProdutoDTO;
import com.joaosantosdev.feirouu.adapters.in.web.dtos.ProdutoListagemDTO;
import com.joaosantosdev.feirouu.application.domains.filtros.ProdutoFiltro;
import com.joaosantosdev.feirouu.application.domains.models.Loja;
import com.joaosantosdev.feirouu.application.domains.models.Produto;
import com.joaosantosdev.feirouu.application.domains.models.Usuario;
import com.joaosantosdev.feirouu.application.domains.ports.in.LojaServicePort;
import com.joaosantosdev.feirouu.application.domains.ports.in.MovimentacaoServicePort;
import com.joaosantosdev.feirouu.application.domains.ports.in.ProdutoServicePort;
import com.joaosantosdev.feirouu.application.utils.Pagina;
import com.joaosantosdev.feirouu.application.utils.Paginacao;
import com.joaosantosdev.feirouu.commons.mappers.ProdutoMapper;
import com.joaosantosdev.feirouu.commons.utils.UsuarioUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/lojas")
public class ProdutoController {

    private LojaServicePort lojaServicePort;
    private UsuarioUtil usuarioUtil;
    private ProdutoServicePort produtoServicePort;
    private MovimentacaoServicePort movimentacaoServicePort;

    public ProdutoController(LojaServicePort lojaServicePort, UsuarioUtil usuarioUtil, ProdutoServicePort produtoServicePort, MovimentacaoServicePort movimentacaoServicePort) {
        this.lojaServicePort = lojaServicePort;
        this.usuarioUtil = usuarioUtil;
        this.produtoServicePort = produtoServicePort;
        this.movimentacaoServicePort = movimentacaoServicePort;
    }

    @PostMapping("/{lojaId}/produtos")
    public ResponseEntity<ProdutoDTO> cadastrarProduto(@PathVariable Long lojaId, @RequestBody ProdutoDTO produtoDTO) {
        Usuario usuario = this.usuarioUtil.obterUsuarioLogado();

        Produto produto = this.produtoServicePort.cadastrar(ProdutoMapper.map(usuario, lojaId, produtoDTO));

        return ResponseEntity.ok().body(ProdutoMapper.map(produto));
    }

    @PutMapping("/{lojaId}/produtos/{id}")
    public ResponseEntity<Void> atualizarProduto(@PathVariable Long lojaId,
                                                 @PathVariable Long id,
                                                 @RequestBody ProdutoDTO produtoDTO) {
        Usuario usuario = this.usuarioUtil.obterUsuarioLogado();
        produtoDTO.setId(id);
        this.produtoServicePort.atualizar(ProdutoMapper.map(usuario, lojaId, produtoDTO));
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{lojaId}/produtos/{id}")
    public ResponseEntity<ProdutoListagemDTO> buscarProdutoPorId(@PathVariable Long lojaId,
                                                                 @PathVariable Long id) {
        Usuario usuario = this.usuarioUtil.obterUsuarioLogado();
        Produto produto = this.produtoServicePort.buscar(id, lojaId, usuario.getId());
        return ResponseEntity.ok().body(new ProdutoListagemDTO(ProdutoMapper.map(produto)));
    }

    @GetMapping("/{lojaId}/produtos")
    public ResponseEntity<Pagina<ProdutoDTO>> buscarPaginado(@RequestParam(required = false, defaultValue = "0") Integer pagina,
                                                                         @RequestParam(required = false, defaultValue = "24") Integer porPagina,
                                                                         @RequestParam(required = false, defaultValue = "nome") String ordenarPor,
                                                                         @RequestParam(required = false, defaultValue = "ASC") String direcao,
                                                                         @RequestParam(required = false, defaultValue = "") String textoBusca,
                                                                         @RequestParam(required = false) String status,
                                                                         @PathVariable Long lojaId) {

        Usuario usuario = this.usuarioUtil.obterUsuarioLogado();

        ArrayList<Integer> statusInteger = (ArrayList<Integer>) Arrays.stream(status.replaceAll("\"", "").split(",")).map(Integer::valueOf).collect(Collectors.toList());
        Pagina<Produto> produtos = this.produtoServicePort.buscarPaginado(lojaId, usuario.getId(), new Paginacao(pagina,
                porPagina,
                ordenarPor,
                direcao), new ProdutoFiltro(textoBusca, statusInteger));

        ArrayList<ProdutoDTO> produtosDTO = (ArrayList<ProdutoDTO>) produtos.getDados().stream()
                .map(ProdutoMapper::map)
                .collect(Collectors.toList());

        return ResponseEntity.ok().body(new Pagina<>(produtosDTO,
                produtos.getTotalGeral(),
                produtos.getTotal()));
    }

    @GetMapping("/{lojaId}/produtos/{produtoId}/verificar-movimentacoes")
    public ResponseEntity<Boolean> verificarMovimentacoes(@PathVariable Long lojaId, @PathVariable Long produtoId) {
        Usuario usuario = this.usuarioUtil.obterUsuarioLogado();
        return ResponseEntity.ok(this.movimentacaoServicePort.produtoTemMovimentacoes(usuario.getId(), lojaId, produtoId));
    }

    @DeleteMapping("/{lojaId}/produtos/{produtoId}")
    public ResponseEntity<Void> excluirProduto(@PathVariable Long lojaId, @PathVariable Long produtoId) {
        Usuario usuario = this.usuarioUtil.obterUsuarioLogado();
        this.produtoServicePort.excluirProduto(produtoId, lojaId, usuario.getId());
        return ResponseEntity.noContent().build();
    }


    @GetMapping("/{lojaId}/produtos/todos")
    public ResponseEntity<List<ProdutoDTO>> excluirProduto(@PathVariable Long lojaId) {
        List<Produto> produtos = this.produtoServicePort.buscarPorLoja(lojaId);
        return ResponseEntity.ok(produtos.stream().map(item -> {
            ProdutoDTO dto = ProdutoMapper.map(item);
            dto.setValorCompra(null);
            return dto;
        }).collect(Collectors.toList()));
    }
}
