package br.ifma.edu.imobiliaria.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.ifma.edu.imobiliaria.domain.model.Imovel;
import br.ifma.edu.imobiliaria.domain.repository.Imovel.ImovelRepositoryQuery;

@Repository
public interface ImovelRepository extends JpaRepository<Imovel, Integer>, ImovelRepositoryQuery {

}
