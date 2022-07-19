package br.ifma.edu.imobiliaria.api.dto.request;

import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class UsuarioRequest {
    @NotBlank
    private String email;

    @NotBlank
    private String senha;

    @NotBlank
    private String papel;
}
