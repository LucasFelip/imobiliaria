package br.ifma.edu.imobiliaria.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.ifma.edu.imobiliaria.domain.model.Locacao;

@Repository
public interface LocacaoRepository extends JpaRepository<Locacao, Integer> {

}
