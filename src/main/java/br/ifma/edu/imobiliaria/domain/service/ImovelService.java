package br.ifma.edu.imobiliaria.domain.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import br.ifma.edu.imobiliaria.domain.model.Imovel;
import br.ifma.edu.imobiliaria.domain.repository.ImovelRepository;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class ImovelService {
    private final ImovelRepository repository;

    public Iterable<Imovel> todos() {
        return repository.findAll();
    }

    public Optional<Imovel> buscaPor(Long id) {
        return repository.findById(id);
    }

    public List<Imovel> buscaPor(String nome) {
        return repository.findByCidadeLikeIgnoreCase(nome);
    }

    public List<Imovel> buscaPorImobiliaria(Long id) {
        return repository.findByImobiliariaId(id);
    }

    @Transactional
    public Imovel salva(Imovel imovel) {
        return repository.save(imovel);
    }

    @Transactional
    public void removePelo(Long id) {
        repository.deleteById(id);
    }

    public boolean naoExisteCom(Long id) {
        return !repository.existsById(id);
    }
}
