package br.ifma.edu.imobiliaria.api.controller;

import br.ifma.edu.imobiliaria.domain.model.Pessoa;
import br.ifma.edu.imobiliaria.domain.service.PessoaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/pessoa")
public class PessoaController {
    private final PessoaService service;

    @Autowired
    public PessoaController(PessoaService pessoaService) {
        this.service = pessoaService;
    }

    @GetMapping
    public Iterable<Pessoa> lista(String nome) {
        if (nome == null)
            return service.todos();
        else
            return service.buscaPor(nome);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pessoa> buscaPor(@PathVariable Integer id) {
        return service.buscaPor(id)
                .map(ResponseEntity::ok) // .map(cliente -> ResponseEntity.ok(cliente))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Pessoa> cadastro(@Valid @RequestBody Pessoa pessoa,
            UriComponentsBuilder builder) {
        final Pessoa pessoaSalvo = service.salva(pessoa);
        final URI uri = builder
                .path("/pessoas/{id}")
                .buildAndExpand(pessoaSalvo.getId()).toUri();

        return ResponseEntity.created(uri).body(pessoaSalvo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Pessoa> atualiza(@PathVariable Integer id,
            @Valid @RequestBody Pessoa pessoa) {
        if (service.naoExisteClienteCom(id)) {
            return ResponseEntity.notFound().build();

        } else {
            pessoa.setId(id);
            Pessoa pessoaAtualizado = service.salva(pessoa);
            return ResponseEntity.ok(pessoaAtualizado);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> remover(@PathVariable Integer id) {
        Optional<Pessoa> optional = service.buscaPor(id);

        if (optional.isPresent()) {
            service.removePelo(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
