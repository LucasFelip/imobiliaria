package br.ifma.edu.imobiliaria.domain.service;

import br.ifma.edu.imobiliaria.domain.exception.NegocioException;
import br.ifma.edu.imobiliaria.domain.repository.PessoaRepository;
import br.ifma.edu.imobiliaria.domain.model.Pessoa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class PessoaService {
	 private final PessoaRepository repository;
	 
	 @Autowired
	 public PessoaService(PessoaRepository pessoaRepository) {
		 this.repository = pessoaRepository;
	 }
	 
	 public List<Pessoa> todos() {
		 return repository.findAll();
	 }
	 
	 public Optional<Pessoa> buscaPor(Integer id) {
		 return repository.findById(id);
	 }
	 
	 public List<Pessoa> buscaPor(String nome) {
		 return repository.findByNomeContaining(nome );
	 }
	 
	 @Transactional
	 public Pessoa salva(Pessoa pessoa) {
		 boolean emailEmUso = repository
				 .findByEmail(pessoa.getEmail())
				 .stream()
				 .anyMatch(clienteExistente -> !clienteExistente.equals(pessoa));

		 if (emailEmUso) 
			 throw new NegocioException("Já existe uma pessoa cadastrado com este e-mail.");
		 return repository.save(pessoa);
	 }
	 
	 @Transactional
	 public void removePelo(Integer id) {
		 repository.deleteById(id);
	 }
	 
	 public boolean naoExisteClienteCom(Integer id ) {
		 return !repository.existsById(id );	
	 }
}
