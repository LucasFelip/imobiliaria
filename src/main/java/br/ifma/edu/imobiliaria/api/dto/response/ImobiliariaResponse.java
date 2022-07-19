package br.ifma.edu.imobiliaria.api.dto.response;

import lombok.Data;

@Data
public class ImobiliariaResponse {
    private Long id;
    private String cnpj;
    private String nome;
    private String descricao;
    private String email;
    private String senha;
}
