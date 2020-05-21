package br.com.forumalura.controller.dto;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonProperty;

import br.com.forumalura.entity.StatusTopico;
import br.com.forumalura.entity.Topico;

public class DetalheTopicoDto {

	@JsonProperty("id")
	private Long id;
	
	@JsonProperty("titulo")
	private String titulo;
	
	@JsonProperty("mensagem")
	private String mensagem;
	
	@JsonProperty("data_criacao")
	private LocalDateTime dataCriacao = LocalDateTime.now();
	
	@JsonProperty("autor")
	private String nomeAutor;
	
	@JsonProperty("status")
	private StatusTopico status;
	
	@JsonProperty("respostas")
	private List<RespostaTopicoDto> respostas;
	
	public Long getId() {
		return id;
	}

	public String getTitulo() {
		return titulo;
	}

	public String getMensagem() {
		return mensagem;
	}

	public LocalDateTime getDataCriacao() {
		return dataCriacao;
	}

	public String getNomeAutor() {
		return nomeAutor;
	}

	public StatusTopico getStatus() {
		return status;
	}

	public List<RespostaTopicoDto> getRespostas() {
		return respostas;
	}
	
	public DetalheTopicoDto(Topico top) {
		this.id = top.getId();
		this.titulo = top.getTitulo();
		this.mensagem = top.getMensagem();
		this.dataCriacao = top.getDataCriacao();
		this.nomeAutor = top.getAutor().getNome();
		this.status = top.getStatus();
		this.respostas = new ArrayList<RespostaTopicoDto>();
		this.respostas.addAll(top.getRespostas().stream().map(RespostaTopicoDto::new).collect(Collectors.toList()));
	}
}
