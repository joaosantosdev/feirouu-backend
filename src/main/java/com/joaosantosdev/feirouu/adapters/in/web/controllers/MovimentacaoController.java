package com.joaosantosdev.feirouu.adapters.in.web.controllers;

import com.joaosantosdev.feirouu.adapters.in.web.dtos.*;
import com.joaosantosdev.feirouu.application.domains.models.Cliente;
import com.joaosantosdev.feirouu.application.domains.models.Movimentacao;
import com.joaosantosdev.feirouu.application.domains.models.Usuario;
import com.joaosantosdev.feirouu.application.domains.ports.in.MovimentacaoServicePort;
import com.joaosantosdev.feirouu.commons.mappers.ClienteMapper;
import com.joaosantosdev.feirouu.commons.mappers.MovimentacaoMapper;
import com.joaosantosdev.feirouu.commons.utils.UsuarioUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("")
public class MovimentacaoController {


    private MovimentacaoServicePort movimentacaoServicePort;
    private UsuarioUtil usuarioUtil;

    public MovimentacaoController(MovimentacaoServicePort movimentacaoServicePort, UsuarioUtil usuarioUtil) {
        this.movimentacaoServicePort = movimentacaoServicePort;
        this.usuarioUtil = usuarioUtil;
    }

    @PostMapping("/lojas/{lojaId}/produtos/{produtoId}/entradas")
    public ResponseEntity<Void> registrar(@PathVariable Long lojaId,
                                          @PathVariable Long produtoId,
                                          @RequestBody MovimentacaoEntradaDTO movimentacaoEntradaDTO) {

        Usuario usuario = this.usuarioUtil.obterUsuarioLogado();
        Long id = this.movimentacaoServicePort.registrarEntrada(MovimentacaoMapper.mapEntrada(lojaId,
                produtoId,
                usuario,
                movimentacaoEntradaDTO));

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("{id}").buildAndExpand(id).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PostMapping("/lojas/{lojaId}/movimentacoes/saidas")
    public ResponseEntity<Void> registrarSaidas(@PathVariable Long lojaId,
                                                @RequestBody ListaSaidaDTO listaSaidaDTO) {

        Usuario usuario = this.usuarioUtil.obterUsuarioLogado();
        Cliente cliente = ClienteMapper.map(listaSaidaDTO.getCliente());
        this.movimentacaoServicePort.registrarSaidas(cliente, MovimentacaoMapper.mapSaidas(lojaId, usuario, cliente, listaSaidaDTO.getSaidas()));

        return ResponseEntity.noContent().build();
    }

    @PostMapping("/lojas/{lojaId}/movimentacoes/devolucoes")
    public ResponseEntity<Void> registrarDevolucoes(@PathVariable Long lojaId,
                                                    @RequestBody ListaDevolucaoDTO listaDevolucaoDTO) {

        Usuario usuario = this.usuarioUtil.obterUsuarioLogado();
        this.movimentacaoServicePort.registrarDevolucoes(lojaId,
                usuario,
                MovimentacaoMapper.mapDevolucoes(listaDevolucaoDTO.getDevolucoes()));

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/lojas/{lojaId}/clientes/{cpf}/saidas")
    public ResponseEntity<List<ListagemMovimentacaoDTO>> obterSaidas(@PathVariable Long lojaId,
                                                                     @PathVariable String cpf) {

        Usuario usuario = this.usuarioUtil.obterUsuarioLogado();
        List<Movimentacao> movimentacoes = this.movimentacaoServicePort.obterSaidasPorCliente(usuario.getId(), lojaId, cpf);
        List<ListagemMovimentacaoDTO> lista = movimentacoes.stream().map(MovimentacaoMapper::map).collect(Collectors.toList());
        return ResponseEntity.ok(lista);
    }

    @GetMapping("/lojas/{lojaId}/produtos/{produtoId}/entradas")
    public ResponseEntity<List<MovimentacaoEntradaDTO>> obterEntradas(@PathVariable Long lojaId,
                                                                      @PathVariable Long produtoId) {

        Usuario usuario = this.usuarioUtil.obterUsuarioLogado();
        List<Movimentacao> movimentacoes = this.movimentacaoServicePort.obterEntradas(usuario.getId(), lojaId, produtoId);

        return ResponseEntity.ok(MovimentacaoMapper.mapEntradas(movimentacoes));
    }

    @GetMapping("/lojas/{lojaId}/produtos/{produtoId}/quantidade-disponivel-saida")
    public ResponseEntity<Integer> obterQuantidadeDisponivelSaida(@PathVariable Long lojaId,
                                                                  @PathVariable Long produtoId) {

        Usuario usuario = this.usuarioUtil.obterUsuarioLogado();
        Integer quantidade = this.movimentacaoServicePort.obterQuantidadeDisponivelSaida(usuario.getId(), lojaId, produtoId);

        return ResponseEntity.ok(quantidade);
    }

    @GetMapping("/lojas/{lojaId}/relatorio/vendas")
    public ResponseEntity<List<RelatorioDTO>> relatorioVendas(@PathVariable Long lojaId, @RequestParam String dataInicial, @RequestParam String dataFinal) {

        Usuario usuario = this.usuarioUtil.obterUsuarioLogado();
        List<Movimentacao> movimentacoes = this.movimentacaoServicePort.obterRelatorioVendas(lojaId, usuario.getId(),
                dataInicial, dataFinal);

        return ResponseEntity.ok(RelatorioMapper.map(movimentacoes));
    }

}
