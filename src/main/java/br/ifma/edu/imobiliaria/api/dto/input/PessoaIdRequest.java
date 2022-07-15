package br.ifma.edu.imobiliaria.api.dto.input;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class PessoaIdRequest {
    @NotNull
    private Long id;
}
