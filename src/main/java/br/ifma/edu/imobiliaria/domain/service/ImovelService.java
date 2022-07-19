package br.ifma.edu.imobiliaria.domain.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.ifma.edu.imobiliaria.domain.exception.NegocioException;
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
        if (repository.findByCidadeLikeIgnoreCase(nome) == null)
            throw new NegocioException("Não existe nenhum imovel cadastrado para essa cidade.");
        return repository.findByCidadeLikeIgnoreCase(nome);
    }

    public List<Imovel> buscaPorImobiliaria(Long id) {
        if (repository.findByImobiliariaId(id) == null)
            throw new NegocioException("Não existe nenhuma imobiliaria cadastrada com este id.");
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

    public Page<Imovel> buscaPaginada(Pageable page) {
        return repository.findAll(page);
    }

    public Page<Imovel> buscaPor(String nome, Pageable pageable) {
        return repository.findByCidadeLikeIgnoreCase(nome, pageable);
    }
}
