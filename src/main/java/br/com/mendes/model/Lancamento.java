package br.com.mendes.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
@Entity
public class Lancamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String cliente;

    @NotNull
    @Column(name = "data_lancamento")
    private LocalDate dataLancamento;

    @NotNull
    private String produto;

    @NotNull
    @Column(name = "unidade_medida")
    private String unidadeMedida;

    @NotNull
    @Column(name = "valor_unitario")
    private BigDecimal valorUnitario;

    @NotNull
    private Integer quantidade;

    @NotNull
    @Column(name = "valor_total")
    private BigDecimal valorTotal;

    @NotNull
    @Column(name = "numero_nota")
    private Integer numeroNota;

    @NotNull
    private String observacao;

}
