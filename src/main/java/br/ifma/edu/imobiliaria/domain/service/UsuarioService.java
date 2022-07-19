package br.ifma.edu.imobiliaria.domain.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.ifma.edu.imobiliaria.domain.exception.NegocioException;
import br.ifma.edu.imobiliaria.domain.model.Usuario;
import br.ifma.edu.imobiliaria.domain.repository.UsuarioRepository;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class UsuarioService {
    private final UsuarioRepository repository;

    public List<Usuario> todos() {
        return repository.findAll();
    }

    public Optional<Usuario> buscaPor(Long id) {
        return repository.findById(id);
    }

    public Usuario buscaPor(String email) {
        if (repository.findByEmail(email) == null)
            throw new NegocioException("Não existe nenhum cliente cadastrado com este e-mail.");
        return repository.findByEmail(email);
    }

    @Transactional
    public Usuario salva(Usuario usuario) {
        Usuario emailEmUso = repository.findByEmail(usuario.getEmail());
        if (emailEmUso != null)
            throw new NegocioException("Já existe um cliente cadastrado com este e-mail.");
        return repository.save(usuario);
    }

    public Usuario atualiza(Usuario usuario, Long id) {
        if (repository.findById(id) == null)
            throw new NegocioException("Não existe nenhum cliente cadastrado com este id.");
        return salva(usuario);
    }

    @Transactional
    public void removePelo(Long id) {
        if (repository.findById(id) == null)
            throw new NegocioException("Não existe nenhum cliente cadastrado com este id.");
        repository.deleteById(id);
    }

    public boolean naoExisteCom(Long id) {
        return !repository.existsById(id);
    }

    public Page<Usuario> buscaPaginada(Pageable page) {
        return repository.findAll(page);
    }
}
