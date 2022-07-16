package br.ifma.edu.imobiliaria.api.controller;

import java.lang.reflect.Field;
import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.ifma.edu.imobiliaria.domain.model.Imovel;
import br.ifma.edu.imobiliaria.domain.service.ImovelService;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/imoveis")
public class ImovelController {

    private final ImovelService service;

    @GetMapping
    @CacheEvict(value = "listaDeAlugueis", allEntries = true)
    public Page<Imovel> listar(
            @PageableDefault(sort = "id", direction = Sort.Direction.ASC, page = 10, size = 50) Pageable pageable) {
        return this.service.buscaPaginada(pageable);
    }

    @PostMapping
    public Imovel cadastrar(@RequestBody @Valid Imovel imovel) {
        return this.service.salvar(imovel);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletar(@PathVariable Integer id) {
        Optional<Imovel> imovel = this.service.buscaPor(id);
        if (imovel.isPresent()) {
            this.service.removePelo(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Imovel> atualiza(@PathVariable Integer id, Imovel imovel) {
        Optional<Imovel> optional = this.service.buscaPor(id);
        if (optional.isPresent()) {
            imovel.setId(id);
            Imovel imovelAtualizado = this.service.salvar(imovel);
            ResponseEntity.ok(imovelAtualizado);
        }
        return ResponseEntity.notFound().build();
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Imovel> atualizacaoParcial(@PathVariable Integer id,
            @RequestBody Map<String, Object> campos) {
        Optional<Imovel> optional = this.service.buscaPor(id);
        if (optional.isPresent()) {
            Imovel imovelAtual = optional.get();
            this.merge(campos, imovelAtual);
            return this.atualiza(id, imovelAtual);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    private void merge(Map<String, Object> campos, Imovel imovelDestino) {
        ObjectMapper objectMapper = new ObjectMapper();
        Imovel clienteOrigem = objectMapper.convertValue(campos, Imovel.class);

        campos.forEach((nomePropriedade, valorPropriedade) -> {
            Field field = ReflectionUtils.findField(Imovel.class, nomePropriedade);
            field.setAccessible(true);
            Object novoValor = ReflectionUtils.getField(field, clienteOrigem);
            ReflectionUtils.setField(field, imovelDestino, novoValor);
        });
    }

}
