package br.ifma.edu.imobiliaria.domain.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.ifma.edu.imobiliaria.domain.model.Aluguel;
import br.ifma.edu.imobiliaria.domain.repository.AluguelRepository;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class AluguelService {
    private final AluguelRepository repository;

    @Transactional
    public Aluguel salvar(Aluguel aluguel) {
        return this.repository.save(aluguel);
    }

    public Optional<Aluguel> buscaPor(Integer id) {
        return repository.findById(id);
    }

    public Page<Aluguel> buscaPaginada(Pageable page) {
        return repository.findAll(page);
    }

    public Iterable<Aluguel> todos() {
        return this.repository.findAll();
    }

    @Transactional
    public void removePelo(Integer id) {
        repository.deleteById(id);
    }

    public boolean naoExisteAluquelCom(Integer id) {
        return !repository.existsById(id);
    }
}
