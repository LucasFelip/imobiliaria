package br.ifma.edu.imobiliaria.domain.service;

import java.util.Optional;
import java.util.Objects;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import br.ifma.edu.imobiliaria.domain.exception.NegocioException;
import br.ifma.edu.imobiliaria.domain.model.Cliente;
import br.ifma.edu.imobiliaria.domain.repository.ClienteRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ClienteService {

    private final ClienteRepository repository;

    public Iterable<Cliente> todos() {
        return repository.findAll();
    }

    public Optional<Cliente> buscaPor(Integer id) {
        return repository.findById(id);
    }

    public Iterable<Cliente> buscaPor(String nome) {
        return repository.findByNomeContaining(nome);
    }

    @Transactional
    public Cliente salva(Cliente cliente) {
        boolean emailEmUso = (repository
                .findByEmail(cliente.getEmail()))
                .stream()
                .anyMatch(clienteExistente -> !Objects.equals(clienteExistente, cliente));

        if (emailEmUso) {
            throw new NegocioException("Já existe um cliente cadastrado com este e-mail.");
        }
        return repository.save(cliente);
    }

    @Transactional
    public void removePelo(Integer id) {
        repository.deleteById(id);
    }

    public boolean naoExisteCom(Integer id) {
        return !repository.existsById(id);
    }

    public Page<Cliente> buscaPaginada(Pageable page) {
        return repository.findAll(page);
    }

    public Page<Cliente> buscaPor(String nome, Pageable paginacao) {
        return repository.findByNomeContaining(nome, paginacao);
    }
}
