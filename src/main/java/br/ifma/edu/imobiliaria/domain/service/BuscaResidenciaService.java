package br.ifma.edu.imobiliaria.domain.service;

import br.ifma.edu.imobiliaria.domain.exception.EntidadeNaoEncontradaException;
import br.ifma.edu.imobiliaria.domain.model.Residencia;
import br.ifma.edu.imobiliaria.domain.repository.ResidenciaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.DoubleStream;

@Service
public class BuscaResidenciaService {
    @Autowired
    private ResidenciaRepository residenciaRepository;

    public Residencia buscar(Integer id) {
        return residenciaRepository.findById(id)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Residencia não encontrada"));
    }

    public List<Residencia> todas() {
        return residenciaRepository.findAll();
    }

    public Optional<Residencia> buscaPor(Integer id) {
        return residenciaRepository.findById(id);
    }
}
