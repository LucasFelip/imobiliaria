package br.ifma.edu.imobiliaria.domain.service;

import br.ifma.edu.imobiliaria.domain.exception.NegocioException;
import br.ifma.edu.imobiliaria.domain.repository.PessoaRepository;
import br.ifma.edu.imobiliaria.domain.model.Pessoa;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@AllArgsConstructor
@Service
public class PessoaService {
	private final PessoaRepository repository = null;

	public List<Pessoa> todos() {
		return repository.findAll();
	}

	public Optional<Pessoa> buscaPor(Integer id) {
		return repository.findById(id);
	}

	public Iterable<Pessoa> buscaPor(String nome) {
		return repository.findByNomeContaining(nome);
	}

	@Transactional
	public Pessoa salva(Pessoa pessoa) {
		boolean emailEmUso = repository
				.findByEmail(pessoa.getEmail())
				.stream()
				.anyMatch(pessoaExistente -> !Objects.equals(pessoaExistente, pessoa));
		if (emailEmUso)
			throw new NegocioException("Já existe uma pessoa cadastrado com este e-mail.");
		return repository.save(pessoa);
	}

	@Transactional
	public void removePelo(Integer id) {
		repository.deleteById(id);
	}

	public boolean naoExisteClienteCom(Integer id) {
		return !repository.existsById(id);
	}
	
	public Page<Pessoa> buscaPaginada(Pageable page) {
        return repository.findAll(page );

    }

    public Page<Pessoa> buscaPor(String nome, Pageable paginacao) {
        return repository.findByNomeContaining(nome, paginacao );
    }
}
