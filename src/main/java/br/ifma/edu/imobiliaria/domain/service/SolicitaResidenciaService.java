package br.ifma.edu.imobiliaria.domain.service;

import br.ifma.edu.imobiliaria.domain.exception.EntidadeNaoEncontradaException;
import br.ifma.edu.imobiliaria.domain.exception.NegocioException;
import br.ifma.edu.imobiliaria.domain.model.Pessoa;
import br.ifma.edu.imobiliaria.domain.model.Residencia;
import br.ifma.edu.imobiliaria.domain.model.StatusResidencia;
import br.ifma.edu.imobiliaria.domain.repository.ResidenciaRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@AllArgsConstructor
@Service
public class SolicitaResidenciaService {
    private final PessoaService pessoaService;
    private final ResidenciaRepository residenciaRepository;

    @Transactional
    public Residencia solicitar(Residencia residencia) {
        Pessoa pessoa = pessoaService
                .buscaPor(residencia.getPessoa().getId())
                .orElseThrow(() -> new NegocioException("Pessoa não cadastrado"));

        residencia.setPessoa(pessoa);
        residencia.setStatus(StatusResidencia.PENDENTE);

        return residenciaRepository.save(residencia);
    }
}
