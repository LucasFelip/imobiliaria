package br.ifma.edu.imobiliaria.api.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

import br.ifma.edu.imobiliaria.domain.model.Imovel;
import br.ifma.edu.imobiliaria.domain.service.ImovelService;

@AllArgsConstructor
@RestController
@RequestMapping("/imoveis")
public class ImovelController {
    private final ImovelService service;

    @GetMapping
    public Iterable<Imovel> lista() {
        return service.todos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Imovel> buscaPor(@PathVariable Integer id) {
        return service.buscaPor(id)
                .map(ResponseEntity::ok) // .map(cliente -> ResponseEntity.ok(cliente))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/{tipoImovel}")
    public Iterable<Imovel> buscarPor(@PathVariable String tipoImovel) {
        return service.buscaPor(tipoImovel);
    }

    @GetMapping("/{valorDeAluguelSugerido}")
    public Iterable<Imovel> buscarPor(@PathVariable Double valorDeAluguelSugerido) {
        return service.buscaPor(valorDeAluguelSugerido);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Imovel> atualiza(@PathVariable Integer id,
            @Valid @RequestBody Imovel imovel) {
        if (service.naoExisteCom(id)) {
            return ResponseEntity.notFound().build();
        } else {
            imovel.setId(id);
            Imovel imovelAtualizado = service.salva(imovel);
            return ResponseEntity.ok(imovelAtualizado);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> remover(@PathVariable Integer id) {
        Optional<Imovel> optional = service.buscaPor(id);

        if (optional.isPresent()) {
            service.removePelo(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}