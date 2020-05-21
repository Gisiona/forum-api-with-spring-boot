package br.com.forumalura.controller.dto;

import javax.validation.constraints.NotBlank;

import br.com.forumalura.entity.Topico;

public class TopicoAtualizarFormDto {
	
	@NotBlank
	private String titulo;
	
	@NotBlank
	private String mensagem;
	
	public TopicoAtualizarFormDto() {}
	
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

	
	public TopicoAtualizarFormDto(Topico topico){
		topico.setTitulo(titulo);
		topico.setMensagem(mensagem);
	}
}
