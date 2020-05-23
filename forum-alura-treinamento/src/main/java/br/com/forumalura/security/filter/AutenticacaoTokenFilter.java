package br.com.forumalura.security.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.annotations.Filter;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import br.com.forumalura.entity.Usuario;
import br.com.forumalura.security.TokenService;

@Filter(name = "AutenticacaoTokenFilter")
public class AutenticacaoTokenFilter extends OncePerRequestFilter {

	private static final String AUTHORIZATION_TYPE_BEARER = "Bearer ";
	private static final String AUTHORIZATION = "Authorization";
	private TokenService tokenService;
	
	
	public AutenticacaoTokenFilter(TokenService tokenService) {
		this.tokenService = tokenService;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		String token = recuperarValidarToken(request);

		boolean tokenValido = tokenService.isTokenValido(token);
		
		if(tokenValido) {
			autenticarUsuario(token);
		}
		
		filterChain.doFilter(request, response);
	}

	
	// autentica o usuário atraves do token jwt
	private void autenticarUsuario(String token) {
		
		Long idUser = tokenService.getIdUsuario(token);
		Usuario user = tokenService.getUsuarioById(idUser);
		
		UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(user, user.getSenha(), user.getAuthorities());
		SecurityContextHolder.getContext().setAuthentication(authentication);
	}

	

	private String recuperarValidarToken(HttpServletRequest request) {
		String token = request.getHeader(AUTHORIZATION);
		if(token == null || token.isEmpty() || !token.startsWith(AUTHORIZATION_TYPE_BEARER)) {
			return null;
		}
		return token.substring(AUTHORIZATION_TYPE_BEARER.length(), token.length());
	}

}
