package br.ifma.edu.imobiliaria.domain.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.br.CPF;

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Cliente {
    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @NotNull
    @NotEmpty
    @Length(min = 3, max = 200)
    private String nome;

    @CPF
    private String cpf;

    @NotEmpty
    @Length(min = 8, message = "Deve possuir {min} dígitos no mínimo")
    private String telefone;

    @Email
    @NotEmpty
    private String email;

    public void alugar(Imovel imovel) {

    }

    public void pagarAluguel(Imovel imovel) {

    }

    public void devolverImovel(Imovel imovel) {

    }
}
