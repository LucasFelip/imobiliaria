package br.ifma.edu.imobiliaria.domain.service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.ifma.edu.imobiliaria.domain.model.Imovel;
import br.ifma.edu.imobiliaria.domain.repository.ImovelRepository;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class ImovelService {

    private ImovelRepository repository;

    public Iterable<Imovel> todos() {
        return repository.findAll();
    }

    public Optional<Imovel> buscaPor(Integer id) {
        return repository.findById(id);
    }

    public Iterable<Imovel> buscarPor(Double valorDeAluquelSugerido) {
        return repository.findByValorDeAluquelSugerido(valorDeAluquelSugerido);
    }

    public Page<Imovel> buscaPor(Double valorDeAluquelSugerido, Pageable paginacao) {
        return repository.findByValorDeAluquelSugerido(valorDeAluquelSugerido, paginacao);
    }

    public Page<Imovel> buscaPaginada(Pageable page) {
        return repository.findAll(page);
    }

    @Transactional
    public Imovel salvar(Imovel imovel) {
        return this.repository.save(imovel);
    }

    @Transactional
    public void removePelo(Integer id) {
        repository.deleteById(id);
    }

    public boolean naoExisteImovelCom(Integer id) {
        return !repository.existsById(id);
    }
}
