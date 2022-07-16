package br.ifma.edu.imobiliaria.api.controller;

import java.net.URI;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
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

import br.ifma.edu.imobiliaria.domain.model.Imovel;
import br.ifma.edu.imobiliaria.domain.service.ImovelService;
import lombok.AllArgsConstructor;

import org.springframework.data.domain.Sort;

@AllArgsConstructor
@RestController
@RequestMapping("/imoveis")
public class ImovelController {
    private final ImovelService service;

    @GetMapping
    public Iterable<Imovel> lista(Double valorDeAluquelSugerido) {
        if (valorDeAluquelSugerido == null)
            return service.todos();
        else
            return service.buscarPor(valorDeAluquelSugerido);
    }

    @GetMapping("paginacao/{numPagina}/{qtdPagina}")
    public Iterable<Imovel> buscaPaginada(@PathVariable int numPagina,
            @PathVariable int qtdPagina) {
        if (qtdPagina > 10)
            qtdPagina = 10;
        PageRequest page = PageRequest.of(numPagina, qtdPagina);
        return service.buscaPaginada(page);
    }

    @GetMapping("/paginacao")
    public Iterable<Imovel> lista(@RequestParam(required = false) Double valorDeAluquelSugerido,
            @PageableDefault(sort = "valor", direction = Sort.Direction.ASC, page = 0, size = 5) Pageable paginacao) {
        if (valorDeAluquelSugerido == null)
            return service.buscaPaginada(paginacao);
        else
            return service.buscaPor(valorDeAluquelSugerido, paginacao);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Imovel> buscaPor(@PathVariable Integer id) {
        return service.buscaPor(id)
                .map(ResponseEntity::ok) // .map(cliente -> ResponseEntity.ok(cliente))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Imovel> cadastro(@Valid @RequestBody Imovel imovel,
            UriComponentsBuilder builder) {

        final Imovel imovelSalvo = service.salvar(imovel);
        final URI uri = builder
                .path("/imoveis/{id}")
                .buildAndExpand(imovelSalvo.getId()).toUri();

        return ResponseEntity.created(uri).body(imovelSalvo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Imovel> atualiza(@PathVariable Integer id,
            @Valid @RequestBody Imovel imovel) {
        if (service.naoExisteImovelCom(id))
            return ResponseEntity.notFound().build();
        else {
            imovel.setId(id);
            Imovel imovelAtualizado = service.salvar(imovel);
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
