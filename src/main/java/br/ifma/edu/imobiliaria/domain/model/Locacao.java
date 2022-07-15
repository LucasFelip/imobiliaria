package br.ifma.edu.imobiliaria.domain.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Locacao {
    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL)
    private Imovel imovel;

    @ManyToOne(cascade = CascadeType.ALL)
    private Cliente inquilino;

    private boolean ativo;

    @NotNull
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date dataInicioAluguel;

    @NotNull
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date dataFimAluguel;

    @NotNull
    private Integer diaVencimento;

    @NotNull
    private Double percentualMulta;

    @NotNull
    private Double valorAluguel;

    private String observacao;

    public void receberInquilino(Cliente cliente) {
        this.inquilino = cliente;
    }
}
