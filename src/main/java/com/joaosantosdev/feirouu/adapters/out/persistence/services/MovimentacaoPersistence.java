package com.joaosantosdev.feirouu.adapters.out.persistence.services;

import com.joaosantosdev.feirouu.adapters.out.persistence.entities.ClienteEntity;
import com.joaosantosdev.feirouu.adapters.out.persistence.entities.MovimentacaoEnitity;
import com.joaosantosdev.feirouu.adapters.out.persistence.entities.MovimentacaoValorEtiquetaEntity;
import com.joaosantosdev.feirouu.adapters.out.persistence.entities.ProdutoEntity;
import com.joaosantosdev.feirouu.adapters.out.persistence.mappers.MovimentacaoEntityMapper;
import com.joaosantosdev.feirouu.adapters.out.persistence.repositories.ClienteRepository;
import com.joaosantosdev.feirouu.adapters.out.persistence.repositories.MovimentacaoRepository;
import com.joaosantosdev.feirouu.adapters.out.persistence.repositories.MovimentacaoValorEtiquetaRepository;
import com.joaosantosdev.feirouu.adapters.out.persistence.repositories.ProdutoRepository;
import com.joaosantosdev.feirouu.application.domains.models.Cliente;
import com.joaosantosdev.feirouu.application.domains.models.Movimentacao;
import com.joaosantosdev.feirouu.application.domains.models.Produto;
import com.joaosantosdev.feirouu.application.domains.ports.out.MovimentacaoPersistencePort;
import com.joaosantosdev.feirouu.commons.mappers.MovimentacaoValorEtiquetaMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MovimentacaoPersistence implements MovimentacaoPersistencePort {

    private MovimentacaoRepository movimentacaoRepository;
    private MovimentacaoValorEtiquetaRepository movimentacaoValorEtiquetaRepository;
    private ProdutoRepository produtoRepository;
    private ClienteRepository clienteRepository;

    public MovimentacaoPersistence(MovimentacaoRepository movimentacaoRepository, MovimentacaoValorEtiquetaRepository movimentacaoValorEtiquetaRepository, ProdutoRepository produtoRepository, ClienteRepository clienteRepository) {
        this.movimentacaoRepository = movimentacaoRepository;
        this.movimentacaoValorEtiquetaRepository = movimentacaoValorEtiquetaRepository;
        this.produtoRepository = produtoRepository;
        this.clienteRepository = clienteRepository;
    }

    @Override
    @Transactional
    public Long registrarEntrada(Movimentacao movimentacao) {
        ProdutoEntity produtoEntity = this.produtoRepository.getById(movimentacao.getProduto().getId());
        MovimentacaoEnitity movimentacaoEnitity = new MovimentacaoEnitity();

        movimentacaoEnitity.setDataHora(movimentacao.getDataHora());
        movimentacaoEnitity.setValorVenda(produtoEntity.getValorVenda());
        movimentacaoEnitity.setValorCompra(produtoEntity.getValorCompra());
        movimentacaoEnitity.setProduto(produtoEntity);
        movimentacaoEnitity.setTipoMovimentacao(movimentacao.getTipoMovimentacao().value());
        movimentacaoEnitity.setQuantidade(movimentacao.getQuantidade());

        List<MovimentacaoValorEtiquetaEntity> valoresEtiquetas = MovimentacaoValorEtiquetaMapper.map(movimentacao, movimentacaoEnitity);

        movimentacaoEnitity.setValoresEtiquetas(valoresEtiquetas);
        this.movimentacaoRepository.save(movimentacaoEnitity);
        this.movimentacaoValorEtiquetaRepository.saveAll(movimentacaoEnitity.getValoresEtiquetas());

        return movimentacaoEnitity.getId();
    }

    @Override
    @Transactional
    public void registrarSaidas(Cliente cliente, List<Movimentacao> movimentacoes) {

        Optional<ClienteEntity> clienteEntityOptional = this.clienteRepository.findById(cliente.getCpf());

        ClienteEntity clienteEntity = clienteEntityOptional.orElseGet(() -> {
            ClienteEntity novoCliente = new ClienteEntity();
            novoCliente.setCpf(cliente.getCpf());
            novoCliente.setNome(cliente.getNome());
            return novoCliente;
        });

        clienteEntity.setEmail(cliente.getEmail());
        clienteEntity.setTelefone(cliente.getTelefone());
        this.clienteRepository.save(clienteEntity);

        for (Movimentacao movimentacao : movimentacoes) {

            ProdutoEntity produtoEntity = this.produtoRepository.getById(movimentacao.getProduto().getId());
            MovimentacaoEnitity movimentacaoEnitity = new MovimentacaoEnitity();
            movimentacaoEnitity.setDataHora(movimentacao.getDataHora());
            movimentacaoEnitity.setValorVenda(produtoEntity.getValorVenda());
            movimentacaoEnitity.setValorCompra(produtoEntity.getValorCompra());
            movimentacaoEnitity.setValorDesconto(movimentacao.getValorDesconto());
            movimentacaoEnitity.setProduto(produtoEntity);
            movimentacaoEnitity.setQuantidade(movimentacao.getQuantidade());
            movimentacaoEnitity.setTipoMovimentacao(movimentacao.getTipoMovimentacao().value());
            movimentacaoEnitity.setCliente(clienteEntity);

            this.movimentacaoRepository.save(movimentacaoEnitity);
        }
    }


    @Override
    @Transactional
    public void registrarDevolucoes(List<Movimentacao> movimentacoes) {
        for (Movimentacao movimentacao : movimentacoes) {
            Movimentacao movimentacaoReferencia = movimentacao.getMovimentacaoReferencia();
            Produto produto = movimentacao.getProduto();
            MovimentacaoEnitity movimentacaoEnitity = new MovimentacaoEnitity();
            movimentacaoEnitity.setDataHora(movimentacao.getDataHora());
            movimentacaoEnitity.setValorVenda(movimentacaoReferencia.getValorVenda());
            movimentacaoEnitity.setValorCompra(movimentacaoReferencia.getValorCompra());
            movimentacaoEnitity.setValorDesconto(movimentacaoReferencia.getValorDesconto());
            movimentacaoEnitity.setProduto(new ProdutoEntity(produto.getId()));
            movimentacaoEnitity.setQuantidade(movimentacao.getQuantidade());
            movimentacaoEnitity.setTipoMovimentacao(movimentacao.getTipoMovimentacao().value());
            movimentacaoEnitity.setMovimentacaoReferencia(new MovimentacaoEnitity(movimentacaoReferencia.getId()));
            movimentacaoEnitity.setCliente(new ClienteEntity(movimentacaoReferencia.getCliente().getCpf()));

            this.movimentacaoRepository.save(movimentacaoEnitity);
        }
    }

    @Override
    public Optional<Movimentacao> obterMovimentacaoPorId(Long id) {
        Optional<MovimentacaoEnitity> movimentacaoEnitityOptional = this.movimentacaoRepository.findById(id);


        Movimentacao movimentacao = null;
        if (movimentacaoEnitityOptional.isPresent()) {
            movimentacao = MovimentacaoEntityMapper.map(movimentacaoEnitityOptional.get());
        }
        return Optional.ofNullable(movimentacao);
    }

    @Override
    public Integer obterQuantidadeDisponivelDevolucao(Long id) {
        return this.movimentacaoRepository.obterQuantidadeDisponivelDevolucao(id);
    }

    @Override
    public Integer obterQuantidadeDisponivelSaidaPorProduto(Long id) {
        return this.movimentacaoRepository.obterQuantidadeDisponivelSaida(id);
    }

    @Override
    public List<Movimentacao> obterSaidasPorClienteCpfLojaId(String cpf, Long lojaId) {
        List<MovimentacaoEnitity> movimentacaoEnitities = this.movimentacaoRepository.obterSaidasPorClienteCpfLojaId(cpf, lojaId);

        movimentacaoEnitities = movimentacaoEnitities.stream().map(movimentacao -> {
            Integer quantidade = this.movimentacaoRepository.obterQuantidadeDisponivelDevolucao(movimentacao.getId());
            movimentacao.setQuantidade(quantidade);
            return movimentacao;
        }).filter(movimentacao -> movimentacao.getQuantidade() > 0).collect(Collectors.toList());


        return movimentacaoEnitities.stream()
                .map(MovimentacaoEntityMapper::map)
                .collect(Collectors.toList());
    }

    @Override
    public Boolean produtoTemMovimentacoes(Long produtoId) {
        return this.movimentacaoRepository.produtoTemMovimentacoes(produtoId);
    }

    @Override
    public List<Movimentacao> obterEntradasPorProduto(Long produtoId) {
        List<MovimentacaoEnitity> movimentacaoEnitities = this.movimentacaoRepository.obterEntradasDisponiveisPorProduto(produtoId);
        return movimentacaoEnitities.stream()
                .map(MovimentacaoEntityMapper::map)
                .collect(Collectors.toList()) ;
    }

    @Override
    public List<Movimentacao> obterRelatorioVendas(Long lojaId, String dataInicial, String dataFinal) {

        List<MovimentacaoEnitity> movimentacaoEnitities = this.movimentacaoRepository.obterRelatorioVendas(lojaId, dataInicial, dataFinal);
        return movimentacaoEnitities.stream()
                .map(MovimentacaoEntityMapper::map)
                .collect(Collectors.toList()) ;
    }


}
