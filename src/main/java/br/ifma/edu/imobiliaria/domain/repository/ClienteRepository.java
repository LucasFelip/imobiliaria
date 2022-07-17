package br.ifma.edu.imobiliaria.domain.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.ifma.edu.imobiliaria.domain.model.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Integer> {
    List<Cliente> findByNomeContaining(String nome);

    Page<Cliente> findByNomeContaining(String nome, Pageable paginacao);

    Optional<Cliente> findByEmail(String email);
}
