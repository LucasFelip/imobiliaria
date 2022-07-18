package br.ifma.edu.imobiliaria.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.ifma.edu.imobiliaria.domain.model.Imobiliaria;

@Repository
public interface ImobiliariaRepository extends JpaRepository<Imobiliaria, Long> {

    Imobiliaria findById(long id);

}
