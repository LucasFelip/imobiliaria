package br.ifma.edu.imobiliaria.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.ifma.edu.imobiliaria.domain.model.Imovel;

@Repository
public interface ImovelRepository extends JpaRepository<Imovel, Integer> {
    List<Imovel> findByTipoImovel(String tipoImovel);

    List<Imovel> findByValorDeAluguelSugerido(Double valorDeAluguelSugerido);
}
