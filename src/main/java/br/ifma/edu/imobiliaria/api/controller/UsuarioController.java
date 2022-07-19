package br.ifma.edu.imobiliaria.api.controller;

import br.ifma.edu.imobiliaria.api.dto.mapper.UsuarioMapper;
import br.ifma.edu.imobiliaria.api.dto.request.UsuarioRequest;
import br.ifma.edu.imobiliaria.api.dto.response.UsuarioResponse;
import br.ifma.edu.imobiliaria.domain.model.Usuario;
import br.ifma.edu.imobiliaria.domain.service.UsuarioService;
import lombok.AllArgsConstructor;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
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

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

@AllArgsConstructor
@RestController
@RequestMapping(value = "/usuario")
public class UsuarioController {
    private final UsuarioMapper mapper;
    private final UsuarioService service;

    @GetMapping
    public List<UsuarioResponse> listar() {
        List<Usuario> usuario = service.todos();
        List<UsuarioResponse> usuarioResponse = mapper.toResponseList(usuario);
        return usuarioResponse;
    }

    @GetMapping("{id}")
    public Optional<Usuario> listaUsuarioPorId(@RequestParam(value = "id") long id) {
        return service.buscaPor(id);
    }

    @GetMapping("{email}")
    @CacheEvict(value = "listaUsuario")
    public Usuario usuarioPorEmail(@RequestParam(value = "email") String email) {
        return service.buscaPor(email);
    }

    @GetMapping("paginacao/{numPagina}/{qtdPagina}")
    public Iterable<Usuario> buscaPaginada(@PathVariable int numPagina,
            @PathVariable int qtdPagina) {
        if (qtdPagina > 10)
            qtdPagina = 10;
        PageRequest page = PageRequest.of(numPagina, qtdPagina);
        return service.buscaPaginada(page);
    }

    @PostMapping
    @CacheEvict(value = "listaUsuario", allEntries = true)
    public ResponseEntity<UsuarioResponse> salvaUsuario(@RequestBody UsuarioRequest usuarioRequest) {
        Usuario usuarioSalvo = mapper.toEntity(usuarioRequest);
        usuarioSalvo = service.salva(usuarioSalvo);
        UsuarioResponse usuarioResponse = mapper.toResponse(usuarioSalvo);
        return new ResponseEntity<>(usuarioResponse, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @CacheEvict(value = "listaUsuario", allEntries = true)
    public ResponseEntity<UsuarioResponse> atualiza(@PathVariable Long id,
            @Valid @RequestBody UsuarioRequest usuarioRequest) {
        Usuario usuarioSalvo = mapper.toEntity(usuarioRequest);
        usuarioSalvo = service.atualiza(usuarioSalvo, id);
        UsuarioResponse usuarioResponse = mapper.toResponse(usuarioSalvo);
        return new ResponseEntity<>(usuarioResponse, HttpStatus.OK);

    }

    @DeleteMapping("/{id}")
    @CacheEvict(value = "listaUsuario", allEntries = true)
    public ResponseEntity<?> remover(@PathVariable Long id) {
        service.removePelo(id);
        return ResponseEntity.noContent().build();
    }

}
