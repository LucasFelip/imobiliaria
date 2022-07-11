package br.ifma.edu.imobiliaria.domain.repository;

import br.ifma.edu.imobiliaria.domain.model.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Repository
public class PessoaRepository extends JpaRepository<Pessoa, Integer> {
	List<Pessoa> findByNomeContaining(String nome );

    Optional<Pessoa> findByEmail(String email);
}
