package br.ifma.edu.imobiliaria.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.ifma.edu.imobiliaria.domain.model.Imovel;
import br.ifma.edu.imobiliaria.domain.repository.imovel.ImovelRepositoryQuery;

public interface ImovelRepository extends JpaRepository<Imovel, Long>, ImovelRepositoryQuery {

}
