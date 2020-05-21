package br.com.forumalura.exception.handler;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ErroValidacaoCampoExceptionResponse {
	@JsonProperty("campo")
	private String campo;
	
	@JsonProperty("mensagem")
	private String erro;
	
	@JsonProperty("status")
	private String statusCode;
	
	@JsonProperty("timestamp")
	private LocalDateTime data;
	

	public ErroValidacaoCampoExceptionResponse(String campo, String msg, String status) {
		this.campo = campo;
		this.erro = msg;
		this.statusCode = status;
		this.data = LocalDateTime.now();
	}
	
	public String getCampo() {
		return campo;
	}
	
	public String getErro() {
		return erro;
	}	
	
	public LocalDateTime getData() {
		return data;
	}
	
	public String getStatusCode() {
		return statusCode;
	}
}
