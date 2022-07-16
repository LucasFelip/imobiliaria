package br.ifma.edu.imobiliaria.api.controller;

import java.net.URI;
import java.util.Optional;

import javax.validation.Valid;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.ifma.edu.imobiliaria.domain.model.Aluguel;
import br.ifma.edu.imobiliaria.domain.service.AluguelService;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/alugueis")
public class AluguelController {
    private final AluguelService service;

    @GetMapping("paginacao/{numPagina}/{qtdPagina}")
    public Iterable<Aluguel> buscaPaginada(@PathVariable int numPagina,
            @PathVariable int qtdPagina) {
        if (qtdPagina > 10)
            qtdPagina = 10;
        PageRequest page = PageRequest.of(numPagina, qtdPagina);
        return service.buscaPaginada(page);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Aluguel> buscaPor(@PathVariable Integer id) {
        return service.buscaPor(id)
                .map(ResponseEntity::ok) // .map(cliente -> ResponseEntity.ok(cliente))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Aluguel> cadastro(@Valid @RequestBody Aluguel aluguel,
            UriComponentsBuilder builder) {

        final Aluguel aluquelSalvo = service.salvar(aluguel);
        final URI uri = builder
                .path("/aluqueis/{id}")
                .buildAndExpand(aluquelSalvo.getId()).toUri();

        return ResponseEntity.created(uri).body(aluquelSalvo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Aluguel> atualiza(@PathVariable Integer id,
            @Valid @RequestBody Aluguel aluguel) {
        if (service.naoExisteAluquelCom(id)) {
            return ResponseEntity.notFound().build();

        } else {
            aluguel.setId(id);
            Aluguel aluquelAtualizado = service.salvar(aluguel);
            return ResponseEntity.ok(aluquelAtualizado);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> remover(@PathVariable Integer id) {

        Optional<Aluguel> optional = service.buscaPor(id);

        if (optional.isPresent()) {
            service.removePelo(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
