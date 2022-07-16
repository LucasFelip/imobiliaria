package br.ifma.edu.imobiliaria.api.controller;

import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.ifma.edu.imobiliaria.domain.model.Locacao;
import br.ifma.edu.imobiliaria.domain.service.LocacaoService;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/locacao")
public class LocacaoController {
    private final LocacaoService locacaoService;

    @GetMapping
    public Page<Locacao> listar(
            @PageableDefault(sort = "id", direction = Sort.Direction.ASC, page = 0, size = 2) Pageable pageable) {
        return this.locacaoService.buscaPaginada(pageable);
    }

    @PostMapping
    public Locacao cadastro(@RequestBody Locacao locacao) {
        return this.locacaoService.salvar(locacao);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Locacao> atualiza(@PathVariable Integer id, Locacao locacao) {
        Optional<Locacao> optional = this.locacaoService.buscaPor(id);
        if (optional.isPresent()) {
            return ResponseEntity.notFound().build();
        } else {
            locacao.setId(id);
            Locacao locacaoAtualizada = this.locacaoService.salvar(locacao);
            return ResponseEntity.ok(locacaoAtualizada);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Locacao> remove(@PathVariable Integer id) {
        Optional<Locacao> optional = this.locacaoService.buscaPor(id);
        if (optional.isPresent()) {
            locacaoService.deletePor(id);
            return ResponseEntity.ok(optional.get());
        }
        return ResponseEntity.notFound().build();
    }
}
