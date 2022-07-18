package br.ifma.edu.imobiliaria.domain.service;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import br.ifma.edu.imobiliaria.domain.model.Imobiliaria;
import br.ifma.edu.imobiliaria.domain.repository.ImobiliariaRepository;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class ImobiliariaService {
    private final ImobiliariaRepository repository;

    public Iterable<Imobiliaria> todos() {
        return repository.findAll();
    }

    public Optional<Imobiliaria> buscaPor(Long id) {
        return repository.findById(id);
    }

    @Transactional
    public Imobiliaria salva(Imobiliaria imobiliaria) {
        return repository.save(imobiliaria);
    }

    @Transactional
    public void removePelo(Long id) {
        repository.deleteById(id);
    }

    public boolean naoExisteCom(Long id) {
        return !repository.existsById(id);
    }
}
