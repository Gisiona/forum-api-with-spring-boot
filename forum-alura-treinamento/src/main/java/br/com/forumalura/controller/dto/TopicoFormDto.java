package br.com.forumalura.controller.dto;

import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sun.istack.NotNull;

import br.com.forumalura.entity.Curso;
import br.com.forumalura.entity.Topico;
import br.com.forumalura.repository.CursoRepository;

public class TopicoFormDto {

	@NotNull
	@NotEmpty(message = "O campo titulo não pode ser nulo ou vazio") 
	@Length(min = 10, message = "Digito no minimo 5 caracteres")
	@JsonProperty("titulo")
	private String titulo;
	
	@JsonProperty("mensagem")
	private String mensagem;
	
	@JsonProperty("nome_curso")
	private String curso;
	
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

	public String getCurso() {
		return curso;
	}

	public void setCurso(String curso) {
		this.curso = curso;
	}
	
	public Topico converter(TopicoFormDto form, CursoRepository cursoRepository) {
		Curso curso = cursoRepository.findByNome(form.getCurso());
		return new Topico(form.getTitulo(), form.getMensagem(), curso);
	}
	
}
