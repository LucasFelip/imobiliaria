package br.ifma.edu.imobiliaria.api.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.ifma.edu.imobiliaria.domain.model.Imobiliaria;
import br.ifma.edu.imobiliaria.domain.service.ImobiliariaService;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping(value = "/imobiliaria")
public class ImobiliariaController {
    private final ImobiliariaService service;

    @GetMapping
    public List<Imobiliaria> listaImobiliarias() {
        return (List<Imobiliaria>) service.todos();
    }

    @GetMapping("{id}")
    public Optional<Imobiliaria> listaImobiliariaPorId(@PathVariable(value = "id") long id) {
        return service.buscaPor(id);
    }

    @PutMapping("{id}")
    @CacheEvict(value = "listaImovel", allEntries = true)
    public ResponseEntity<Imobiliaria> atualizaImobiliaria(@RequestBody Imobiliaria imobiliaria,
            @PathVariable(value = "id") long id) {
        if (service.naoExisteCom(id)) {
            return ResponseEntity.notFound().build();
        } else {
            imobiliaria.setId(id);
            Imobiliaria imobiliariaAtualizado = service.salva(imobiliaria);
            return ResponseEntity.ok(imobiliariaAtualizado);
        }
    }

    @DeleteMapping("/{id}")
    @CacheEvict(value = "listaImovel", allEntries = true)
    public ResponseEntity<?> remover(@PathVariable Long id) {
        Optional<Imobiliaria> optional = service.buscaPor(id);

        if (optional.isPresent()) {
            service.removePelo(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}