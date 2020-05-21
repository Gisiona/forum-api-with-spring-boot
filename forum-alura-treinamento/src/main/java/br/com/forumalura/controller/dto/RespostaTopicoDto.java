package br.com.forumalura.controller.dto;

import java.time.LocalDateTime;

import br.com.forumalura.entity.RespostaTopico;

public class RespostaTopicoDto {

	private Long id;	
	private String mensagem;
	private String nomeAutor;
	private LocalDateTime dataCriacao;
	
	public Long getId() {
		return id;
	}

	public String getMensagem() {
		return mensagem;
	}

	public String getNomeAutor() {
		return nomeAutor;
	}

	public LocalDateTime getDataCriacao() {
		return dataCriacao;
	}
	
	public RespostaTopicoDto(RespostaTopico resposta) {
		this.id = resposta.getId();
		this.mensagem = resposta.getMensagem();
		this.nomeAutor = resposta.getAutor().getNome();
		this.dataCriacao = resposta.getDataCriacao();
	}
}
