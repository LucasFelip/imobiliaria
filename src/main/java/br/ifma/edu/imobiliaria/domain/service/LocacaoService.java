package br.ifma.edu.imobiliaria.domain.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.ifma.edu.imobiliaria.domain.model.Locacao;
import br.ifma.edu.imobiliaria.domain.repository.LocacaoRepository;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class LocacaoService {

    private LocacaoRepository locacaoRepository;

    @Transactional
    public Locacao salvar(Locacao aluguel) {
        return this.locacaoRepository.save(aluguel);
    }

    @Transactional
    public void deletePor(Integer id) {
        this.locacaoRepository.deleteById(id);
    }

    public Optional<Locacao> buscaPor(Integer id) {
        return this.locacaoRepository.findById(id);
    }

    public Page<Locacao> buscaPaginada(Pageable pageable) {
        return this.locacaoRepository.findAll(pageable);
    }
}
