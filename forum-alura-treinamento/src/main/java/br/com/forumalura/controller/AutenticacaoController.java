package br.com.forumalura.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.forumalura.controller.dto.AutenticacaoLoginFormDto;
import br.com.forumalura.controller.dto.TokenDto;
import br.com.forumalura.security.TokenService;

@RestController
@RequestMapping("/forum/auth")
public class AutenticacaoController {

	private static final String AUTHORIZATION_TYPE = "Bearer";

	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private TokenService tokenService;
	
	@PostMapping()
	public ResponseEntity<TokenDto> autenticar(@RequestBody @Valid AutenticacaoLoginFormDto form){
		try {
			UsernamePasswordAuthenticationToken userAuth = form.converter();
			
			Authentication authentication = authenticationManager.authenticate(userAuth);
			String token = tokenService.gerarToken(authentication);
			
			return ResponseEntity.ok().body(new TokenDto(token, AUTHORIZATION_TYPE, tokenService.tokenExpirationAt()));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
		}		
	}
}
