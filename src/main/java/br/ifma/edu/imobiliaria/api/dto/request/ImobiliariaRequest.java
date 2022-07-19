package br.ifma.edu.imobiliaria.api.dto.request;

import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class ImobiliariaRequest {
    @NotBlank
    private String cnpj;

    @NotBlank
    private String nome;

    @NotBlank
    private String descricao;

    @NotBlank
    private String email;

    @NotBlank
    private String senha;
}
