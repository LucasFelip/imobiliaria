package br.ifma.edu.imobiliaria.domain.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import br.ifma.edu.imobiliaria.domain.exception.NegocioException;

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Residencia {
    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    @ManyToOne
    private Pessoa pessoa;

    @Enumerated(EnumType.STRING)
    private StatusResidencia status;

    @NotBlank
    @Size(max = 100)
    private String endereco;

    @NotBlank
    private int largura;

    @NotBlank
    private int profundidade;

    @NotBlank
    @Size(max = 500)
    private String info;

    public void finalizar() {
        if (naoPodeSerFinalizada()) {
            throw new NegocioException("Aluguel/Compra não pode ser finalizada");
        }
        this.setStatus(StatusResidencia.FINALIZADA);
    }

    public boolean podeSerFinalizada() {
        return StatusResidencia.PENDENTE.equals(getStatus());
    }

    public boolean naoPodeSerFinalizada() {
        return !podeSerFinalizada();
    }

}