package br.ifma.edu.imobiliaria.api.dto.response;

import lombok.Data;

@Data
public class UsuarioResponse {
    private Long id;
    private String email;
    private String senha;
    private String papel;
}
