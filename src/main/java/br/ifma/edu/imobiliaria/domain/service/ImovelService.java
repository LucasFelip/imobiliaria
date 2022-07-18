package br.ifma.edu.imobiliaria.domain.service;

import java.util.Optional;

import javax.transaction.Transactional;
import org.springframework.stereotype.Service;

import br.ifma.edu.imobiliaria.domain.model.Imovel;
import br.ifma.edu.imobiliaria.domain.repository.ImovelRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ImovelService {
    private final ImovelRepository repository;

    public Iterable<Imovel> todos() {
        return repository.findAll();
    }

    public Optional<Imovel> buscaPor(Integer id) {
        return repository.findById(id);
    }

    public Iterable<Imovel> buscaPor(String tipoImovel) {
        return repository.findByTipoImovel(tipoImovel);
    }

    public Iterable<Imovel> buscaPor(Double valorDeAluguelSugerido) {
        return repository.findByValorDeAluguelSugerido(valorDeAluguelSugerido);
    }

    @Transactional
    public Imovel salva(Imovel imovel) {
        return repository.save(imovel);
    }

    @Transactional
    public void removePelo(Integer id) {
        repository.deleteById(id);
    }

    public boolean naoExisteCom(Integer id) {
        return !repository.existsById(id);
    }
}
