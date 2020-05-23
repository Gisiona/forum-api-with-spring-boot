package br.com.forumalura.controller.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TokenDto {

	@JsonProperty("token")
	private String token;
	
	@JsonProperty("type")
	private String tipoAutorizacao;
	
	@JsonProperty("expiration")
	private String expirationToken;
	
	public String getExpirationToken() {
		return expirationToken;
	}

	public void setExpirationToken(String expirationToken) {
		this.expirationToken = expirationToken;
	}

	public TokenDto(String token, String authorizationType, String expirationToken) {
		this.tipoAutorizacao = authorizationType;
		this.token = token;
		this.expirationToken = expirationToken;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getTipoAutorizacao() {
		return tipoAutorizacao;
	}

	public void setTipoAutorizacao(String tipoAutorizacao) {
		this.tipoAutorizacao = tipoAutorizacao;
	}
}
