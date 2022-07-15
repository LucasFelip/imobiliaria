package br.ifma.edu.imobiliaria.domain.repository;

import br.ifma.edu.imobiliaria.domain.model.Residencia;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class ResidenciaRepository extends JpaRepository<Residencia, Integer> {

}
