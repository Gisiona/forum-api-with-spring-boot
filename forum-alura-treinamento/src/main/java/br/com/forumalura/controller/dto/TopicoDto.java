package br.com.forumalura.controller.dto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;

import br.com.forumalura.entity.Topico;

public class TopicoDto {

	private Long id;
	private String titulo;
	private String mensagem;
	private LocalDateTime dataCriacao = LocalDateTime.now();
	
	public TopicoDto(Topico topico) {
		this.id = topico.getId();
		this.titulo = topico.getTitulo();
		this.mensagem = topico.getMensagem();
		this.dataCriacao = topico.getDataCriacao();
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public String getMensagem() {
		return mensagem;
	}
	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}
	public LocalDateTime getDataCriacao() {
		return dataCriacao;
	}
	public void setDataCriacao(LocalDateTime dataCriacao) {
		this.dataCriacao = dataCriacao;
	}

	public static Page<TopicoDto> converter(Page<Topico> topicos) {
		// Retorna lista
		// return topicos.stream().map(TopicoDto::new).collect(Collectors.toList());
		
		// Retorna Page
		return topicos.map(TopicoDto::new);
	}

	public static TopicoDto converterToTopico(Topico topico) {
		return new TopicoDto(topico);
	}
	
}
