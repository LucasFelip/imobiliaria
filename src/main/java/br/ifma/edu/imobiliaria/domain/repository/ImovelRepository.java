package br.ifma.edu.imobiliaria.domain.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.ifma.edu.imobiliaria.domain.model.Imovel;

@Repository
public interface ImovelRepository extends JpaRepository<Imovel, Integer> {
    List<Imovel> findByValorDeAluquelSugerido(Double valorDeAluquelSugerido);

    Page<Imovel> findByValorDeAluquelSugerido(Double valorDeAluquelSugerido, Pageable paginacao);
}
