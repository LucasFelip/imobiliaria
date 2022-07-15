package br.ifma.edu.imobiliaria.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.ifma.edu.imobiliaria.domain.model.Locacao;

public interface LocacaoRepository extends JpaRepository<Locacao, Long> {

}
