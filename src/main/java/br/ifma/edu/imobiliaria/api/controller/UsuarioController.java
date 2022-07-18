package br.ifma.edu.imobiliaria.api.controller;

import br.ifma.edu.imobiliaria.domain.model.Usuario;
import br.ifma.edu.imobiliaria.domain.service.UsuarioService;
import lombok.AllArgsConstructor;

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

import java.util.List;
import java.net.URI;
import java.util.Optional;

import javax.validation.Valid;

@AllArgsConstructor
@RestController
@RequestMapping(value = "/usuario")
public class UsuarioController {
    private final UsuarioService service;

    @GetMapping
    public List<Usuario> listar() {
        return (List<Usuario>) service.todos();
    }

    @GetMapping("{id}")
    public Optional<Usuario> listaUsuarioPorId(@PathVariable(value = "id") long id) {
        return service.buscaPor(id);
    }

    /*
     * @GetMapping("{email}")
     * public Usuario buscarUserPorEmail(String email) {
     * return service.buscaPor(email);
     * }
     */

    @GetMapping("paginacao/{numPagina}/{qtdPagina}")
    public Iterable<Usuario> buscaPaginada(@PathVariable int numPagina,
            @PathVariable int qtdPagina) {
        if (qtdPagina > 10)
            qtdPagina = 10;
        PageRequest page = PageRequest.of(numPagina, qtdPagina);
        return service.buscaPaginada(page);
    }

    @PostMapping
    public ResponseEntity<Usuario> salvaUsuario(@RequestBody Usuario usuario, UriComponentsBuilder builder) {
        final Usuario usuarioSalvo = service.salva(usuario);
        final URI uri = builder
                .path("/usuario/{id}")
                .buildAndExpand(usuarioSalvo.getId()).toUri();
        return ResponseEntity.created(uri).body(usuarioSalvo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Usuario> atualiza(@PathVariable Long id,
            @Valid @RequestBody Usuario usuario) {
        if (service.naoExisteCom(id)) {
            return ResponseEntity.notFound().build();
        } else {
            usuario.setId(id);
            Usuario usuarioAtualizado = service.salva(usuario);
            return ResponseEntity.ok(usuarioAtualizado);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> remover(@PathVariable Long id) {
        Optional<Usuario> optional = service.buscaPor(id);

        if (optional.isPresent()) {
            service.removePelo(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

}
