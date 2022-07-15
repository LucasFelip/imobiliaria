package br.ifma.edu.imobiliaria.api.exceptionhandler;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class ErroDeValidacao {

	private LocalDateTime dataHora;
	private String titulo;
	private List<Erro> erros = new ArrayList<>();

	public ErroDeValidacao(LocalDateTime dataHora, String titulo) {
		this.dataHora = dataHora;
		this.titulo = titulo;
	}

	public void adiciona(Erro erro) {
		erros.add(erro);
	}

	public LocalDateTime getDataHora() {
		return dataHora;
	}

	public String getTitulo() {
		return titulo;
	}

	public List<Erro> getErros() {
		return erros;
	}
}
