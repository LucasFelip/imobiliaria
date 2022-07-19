package br.ifma.edu.imobiliaria.api.dto.request;

import java.util.Set;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class ImovelRequest {
    @NotBlank
    private String nome;

    @NotBlank
    private String descricao;

    @NotNull
    private float valor;

    @NotBlank
    private String cep;

    @NotBlank
    private String logradouro;

    @NotNull
    private Integer numero;

    @NotBlank
    private String bairro;

    @NotBlank
    private String cidade;

    @NotBlank
    private String url_foto;

    @NotNull
    private Set<Long> imobiliaria;
}
