package br.com.forumalura.exception.handler;

import java.time.LocalDateTime;

public class ErroValidacaoCampoExceptionResponse {
	
	private String campo;
	private String erro;
	private String statusCode;
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
