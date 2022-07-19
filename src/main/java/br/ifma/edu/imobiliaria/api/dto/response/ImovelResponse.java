package br.ifma.edu.imobiliaria.api.dto.response;

import java.util.Set;

import lombok.Data;

@Data
public class ImovelResponse {
    private Long id;
    private String nome;
    private String descricao;
    private float valor;
    private String cep;
    private String logradouro;
    private Integer numero;
    private String bairro;
    private String cidade;
    private String url_foto;
    private Set<ImobiliariaResponse> imobiliaria;
}
