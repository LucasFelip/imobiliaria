package br.ifma.edu.imobiliaria.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.ifma.edu.imobiliaria.domain.model.Aluguel;

@Repository
public interface AluguelRepository extends JpaRepository<Aluguel, Integer> {

}
