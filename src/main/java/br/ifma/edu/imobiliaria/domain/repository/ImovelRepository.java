package br.ifma.edu.imobiliaria.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.ifma.edu.imobiliaria.domain.model.Imovel;

@Repository
public interface ImovelRepository extends JpaRepository<Imovel, Long> {

    Imovel findById(long id);

    List<Imovel> findByCidadeLikeIgnoreCase(String nome);

    @Query("select i from Imovel i where imobiliaria_id = :id")
    List<Imovel> findByImobiliariaId(@Param("id") Long id);

}
