package br.ifma.edu.imobiliaria.api.controller;

import br.ifma.edu.imobiliaria.domain.model.Imovel;
import br.ifma.edu.imobiliaria.domain.service.ImovelService;
import lombok.AllArgsConstructor;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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

    @GetMapping("paginacao/{numPagina}/{qtdPagina}")
    public Iterable<Imovel> buscaPaginada(@PathVariable int numPagina,
            @PathVariable int qtdPagina) {
        if (qtdPagina > 10)
            qtdPagina = 10;
        PageRequest page = PageRequest.of(numPagina, qtdPagina);
        return service.buscaPaginada(page);

    }

    @GetMapping("/paginacao")
    public Iterable<Imovel> lista(@RequestParam(required = false) String nome,
            @PageableDefault(sort = "nome", direction = Sort.Direction.ASC, page = 0, size = 5) Pageable paginacao) {
        if (nome == null)
            return service.buscaPaginada(paginacao);
        else
            return service.buscaPor(nome, paginacao);
    }

    @PostMapping
    @CacheEvict(value = "listaImovel", allEntries = true)
    public ResponseEntity<Imovel> salvaImovel(@RequestBody Imovel imovel, UriComponentsBuilder builder) {
        final Imovel imovelSalvo = service.salva(imovel);
        final URI uri = builder
                .path("/imovel/{id}")
                .buildAndExpand(imovelSalvo.getId()).toUri();
        return ResponseEntity.created(uri).body(imovelSalvo);
    }

    @PutMapping("{id}")
    @CacheEvict(value = "listaImovel", allEntries = true)
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
    @CacheEvict(value = "listaImovel", allEntries = true)
    public ResponseEntity<?> remover(@PathVariable Long id) {
        Optional<Imovel> optional = service.buscaPor(id);

        if (optional.isPresent()) {
            service.removePelo(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
