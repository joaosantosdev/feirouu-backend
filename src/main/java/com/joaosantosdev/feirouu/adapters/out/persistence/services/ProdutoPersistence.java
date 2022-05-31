package com.joaosantosdev.feirouu.adapters.out.persistence.services;

import com.joaosantosdev.feirouu.adapters.out.persistence.entities.ProdutoEntity;
import com.joaosantosdev.feirouu.adapters.out.persistence.mappers.ProdutoEntityMapper;
import com.joaosantosdev.feirouu.adapters.out.persistence.repositories.LojaRepository;
import com.joaosantosdev.feirouu.adapters.out.persistence.repositories.ProdutoChaveEtiquetaRepository;
import com.joaosantosdev.feirouu.adapters.out.persistence.repositories.ProdutoRepository;
import com.joaosantosdev.feirouu.adapters.out.persistence.repositories.UsuarioRepository;
import com.joaosantosdev.feirouu.application.domains.enums.Status;
import com.joaosantosdev.feirouu.application.domains.filtros.ProdutoFiltro;
import com.joaosantosdev.feirouu.application.domains.models.Loja;
import com.joaosantosdev.feirouu.application.domains.models.Produto;
import com.joaosantosdev.feirouu.application.domains.ports.out.ProdutoPersistencePort;
import com.joaosantosdev.feirouu.application.utils.Pagina;
import com.joaosantosdev.feirouu.application.utils.Paginacao;
import com.joaosantosdev.feirouu.commons.mappers.ProdutoMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProdutoPersistence implements ProdutoPersistencePort {

    private ProdutoRepository produtoRepository;
    private LojaRepository lojaRepository;
    private UsuarioRepository usuarioRepository;
    private ProdutoChaveEtiquetaRepository produtoChaveEtiquetaRepository;

    public ProdutoPersistence(ProdutoRepository produtoRepository, LojaRepository lojaRepository, UsuarioRepository usuarioRepository, ProdutoChaveEtiquetaRepository produtoChaveEtiquetaRepository) {
        this.produtoRepository = produtoRepository;
        this.lojaRepository = lojaRepository;
        this.usuarioRepository = usuarioRepository;
        this.produtoChaveEtiquetaRepository = produtoChaveEtiquetaRepository;
    }

    @Override
    @Transactional
    public Produto cadastrar(Produto produto) {
        ProdutoEntity produtoEntity = ProdutoEntityMapper.map(produto);
        produtoEntity = this.produtoRepository.save(produtoEntity);
        this.produtoChaveEtiquetaRepository.saveAll(produtoEntity.getChavesEtiquetas());
        return ProdutoMapper.map(produtoEntity);
    }

    @Override
    public void atualizar(Produto produto) {
        ProdutoEntity produtoEntity = ProdutoEntityMapper.map(produto);
        this.produtoRepository.save(produtoEntity);
        this.produtoChaveEtiquetaRepository.saveAll(produtoEntity.getChavesEtiquetas());
    }

    @Override
    public Optional<Produto> buscar(Long id, Long lojaId) {
        ProdutoEntity produtoEntity = this.produtoRepository.findByIdAndLojaId(id, lojaId);
        return Optional.ofNullable(ProdutoMapper.map(produtoEntity));
    }

    @Override
    public Pagina<Produto> buscarPaginado(Long lojaId, Paginacao paginacao, ProdutoFiltro produtoFiltro) {
        PageRequest pageRequest = PageRequest.of(paginacao.getPagina(),
                paginacao.getPorPagina(),
                Sort.Direction.valueOf(paginacao.getDirecao()),
                paginacao.getOrdenarPor());

        Page<ProdutoEntity> produtoEntityPage = this.produtoRepository.buscaPaginada(produtoFiltro.getStatus(), lojaId, pageRequest);

        ArrayList<Produto> produtos = (ArrayList<Produto>) produtoEntityPage.getContent().stream()
                .map(ProdutoMapper::map)
                .collect(Collectors.toList());

        return new Pagina<>(produtos,
                produtoEntityPage.getTotalElements(),
                produtoEntityPage.getPageable().getPageSize());
    }

    @Override
    public Integer obterQuantidadeDisponivel(Long id) {
        return this.produtoRepository.obterQuantidadeDisponivel(id);
    }

    @Override
    @Transactional
    public void excluir(Long produtoId) {
        ProdutoEntity produtoEntity = this.produtoRepository.getById(produtoId);
        this.produtoChaveEtiquetaRepository.deleteAll(produtoEntity.getChavesEtiquetas());
        this.produtoRepository.deleteById(produtoId);
    }

    @Override
    public List<Produto> buscarPorLoja(Long lojaId) {
        List<ProdutoEntity> produtosEntities = this.produtoRepository.findByLojaId(lojaId);
        return produtosEntities.stream()
                .map(ProdutoMapper::map)
                .collect(Collectors.toList());
    }
}
