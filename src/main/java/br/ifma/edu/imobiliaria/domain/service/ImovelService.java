package br.ifma.edu.imobiliaria.domain.service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.ifma.edu.imobiliaria.domain.model.Imovel;
import br.ifma.edu.imobiliaria.domain.repository.ImovelRepository;
import br.ifma.edu.imobiliaria.domain.repository.Filter.ImovelFilter;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ImovelService {
    private ImovelRepository imovelRepository;

    @Transactional
    public Imovel salvar(Imovel imovel) {
        return this.imovelRepository.save(imovel);
    }

    @Transactional
    public void deletePor(Integer id) {
        this.imovelRepository.deleteById(id);
    }

    public Optional<Imovel> buscaPor(Integer id) {
        return this.imovelRepository.findById(id);
    }

    public Page<Imovel> buscaCom(Pageable pageable) {
        return this.imovelRepository.findAll(pageable);
    }

    @Transactional(readOnly = true)
    public Page<Imovel> busca(ImovelFilter filtro, Pageable page) {
        return imovelRepository.filtrar(filtro, page);
    }
}
