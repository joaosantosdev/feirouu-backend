package com.joaosantosdev.feirouu.adapters.out.persistence.entities;

import com.joaosantosdev.feirouu.application.domains.models.Movimentacao;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Table(name = "movimentacao")
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class MovimentacaoEnitity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private Double valorDesconto;

    @Column
    private Double valorVenda;

    @Column
    private Double valorCompra;

    @Column
    private Integer quantidade;

    @Column
    private LocalDateTime dataHora;

    @ManyToOne
    @JoinColumn(name = "cliente_id", nullable = true)
    private ClienteEntity cliente;

    @ManyToOne
    @JoinColumn(name = "produto_id")
    private ProdutoEntity produto;

    @OneToOne
    @JoinColumn(name = "movimentacao_referencia_id", nullable = true)
    private MovimentacaoEnitity movimentacaoReferencia;

    @Column
    private Integer tipoMovimentacao;

    @OneToMany(mappedBy = "movimentacao")
    private List<MovimentacaoValorEtiquetaEntity> valoresEtiquetas;

    public MovimentacaoEnitity(Long id) {
        this.id = id;
    }
}
