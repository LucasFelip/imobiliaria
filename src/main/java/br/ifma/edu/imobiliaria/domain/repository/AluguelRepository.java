package br.ifma.edu.imobiliaria.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.ifma.edu.imobiliaria.domain.model.Aluguel;

public interface AluguelRepository extends JpaRepository<Aluguel, Long> {

}
