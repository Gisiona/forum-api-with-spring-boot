package br.com.forumalura.controller.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonProperty;

import br.com.forumalura.entity.Curso;
import br.com.forumalura.entity.Topico;
import br.com.forumalura.repository.CursoRepository;

public class TopicoFormDto {

	@NotBlank
	@Length(min = 10)
	@JsonProperty("titulo")
	private String titulo;
	
	
	@NotBlank() 
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
