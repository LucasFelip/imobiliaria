package br.ifma.edu.imobiliaria.api.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AberturaController {
    @GetMapping
    public String imobiliaria() {
        return "BEM VINDO A API DE IMOBILIARIA                    → Desenvolvido por Lucas e Tadeu";
    }
}
