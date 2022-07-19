package br.ifma.edu.imobiliaria.domain.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "Imovel")
public class Imovel implements Serializable {
    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(max = 100)
    @Column(nullable = false, length = 100)
    private String nome;

    @NotBlank
    @Size(max = 256)
    @Column(nullable = false, length = 256)
    private String descricao;

    @NotNull
    @Column(nullable = false, length = 12)
    private float valor;

    @NotBlank
    @Size(max = 11)
    @Column(nullable = false, length = 11)
    private String cep;

    @NotBlank
    @Size(max = 100)
    @Column(nullable = false, length = 100)
    private String logradouro;

    @NotNull
    @Column(nullable = false)
    private Integer numero;

    @NotBlank
    @Size(max = 100)
    @Column(nullable = false, length = 100)
    private String bairro;

    @NotBlank
    @Size(max = 100)
    @Column(nullable = false, length = 100)
    private String cidade;

    @NotBlank
    @Size(max = 100)
    @Column(nullable = false, length = 100)
    private String url_foto;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "imobiliaria_id")
    private Imobiliaria imobiliaria;
}
