package br.ifma.edu.imobiliaria.api.controller;

import br.ifma.edu.imobiliaria.domain.model.Imovel;
import br.ifma.edu.imobiliaria.domain.service.ImovelService;
import lombok.AllArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.net.URI;
import java.util.Optional;

import javax.validation.Valid;

@AllArgsConstructor
@RestController
@RequestMapping(value = "/imovel")
public class ImovelController {
    private final ImovelService service;

    @GetMapping
    public List<Imovel> listar() {
        return (List<Imovel>) service.todos();
    }

    @GetMapping("{id}")
    public Optional<Imovel> listaImovelPorId(@PathVariable(value = "id") long id) {
        return service.buscaPor(id);
    }

    @GetMapping(path = "/imobiliaria/{id}")
    public List<Imovel> listaPorImobiliaria(@PathVariable("id") long id) {
        return service.buscaPorImobiliaria(id);
    }

    @GetMapping(path = "/cidade/{cidade}")
    public List<Imovel> listaPorCidade(@RequestParam(name = "cidade") String cidade) {
        return service.buscaPor("%" + cidade + "%");
    }

    @PostMapping
    public ResponseEntity<Imovel> salvaImovel(@RequestBody Imovel imovel, UriComponentsBuilder builder) {
        final Imovel imovelSalvo = service.salva(imovel);
        final URI uri = builder
                .path("/imovel/{id}")
                .buildAndExpand(imovelSalvo.getId()).toUri();
        return ResponseEntity.created(uri).body(imovelSalvo);
    }

    @PutMapping("{id}")
    public ResponseEntity<Imovel> atualiza(@PathVariable Long id,
            @Valid @RequestBody Imovel imovel) {
        if (service.naoExisteCom(id)) {
            return ResponseEntity.notFound().build();
        } else {
            imovel.setId(id);
            Imovel imovelAtualizado = service.salva(imovel);
            return ResponseEntity.ok(imovelAtualizado);
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> remover(@PathVariable Long id) {
        Optional<Imovel> optional = service.buscaPor(id);

        if (optional.isPresent()) {
            service.removePelo(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
