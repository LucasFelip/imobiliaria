package br.ifma.edu.imobiliaria.domain.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Imovel {
    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @NotNull
    @NotEmpty
    private String tipoImovel;

    @NotNull
    @NotEmpty
    private String endereco;

    @NotNull
    @NotEmpty
    private String Cep;

    private Integer dormitorio;

    private Integer banheiro;

    private Integer suites;

    private Integer metragem;

    @NotNull
    @NotEmpty
    private Double valorDeAluguelSugerido;

    private String observacao;

    public void reajusteDeAluguel(Double novoValorDeAluguel) {
        this.valorDeAluguelSugerido = novoValorDeAluguel;
    }
}
