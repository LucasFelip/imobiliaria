package br.ifma.edu.imobiliaria.domain.repository;

import br.ifma.edu.imobiliaria.domain.model.Pessoa;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Repository
public interface PessoaRepository extends JpaRepository<Pessoa, Integer> {
    Iterable<Pessoa> findByNomeContaining(String nome);

    Page<Pessoa> findByNomeContaining(String nome, Pageable paginacao);

    Optional<Pessoa> findByEmail(String email);
}
