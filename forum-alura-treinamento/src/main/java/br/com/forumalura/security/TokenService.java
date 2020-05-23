package br.com.forumalura.security;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import br.com.forumalura.entity.Usuario;
import br.com.forumalura.repository.UsuarioRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class TokenService {
	
	@Value("${forum.jwt.token.expiration}")
	private String secret;

	@Value("${forum.jwt.token.expiration}")
	private String expiration;
	
	@Value("${forum.jwt.token.client_id}")
	private String client;
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	public String gerarToken(Authentication authentication) {
		Usuario user = (Usuario) authentication.getPrincipal();
		
		Date date = new Date();
		Date dataExpToken = new Date(date.getTime() + Long.valueOf(expiration));
		
		String token = Jwts
						.builder()
						.setIssuedAt(date)
						.setSubject(user.getId().toString())
						.setIssuedAt(date)
						.setExpiration(dataExpToken)
						.signWith(SignatureAlgorithm.HS256, secret)
						.compact();
		
		return token;
	}

	public String tokenExpirationAt() {
		return new Date(Long.valueOf(expiration)).toString();
	}

	public boolean isTokenValido(String token) {
		try {
			Jwts.parser().setSigningKey(secret).parseClaimsJws(token);
			return true;
		} catch (Exception e) {
			return false;
		}	
	}
	
	public Usuario getUsuarioById(Long id) {
		Optional<Usuario> usuario = usuarioRepository.findById(id);
		if(usuario.isPresent()) {
			return usuario.get();
		}
		return null;
	}

	public Long getIdUsuario(String token) {
		Claims clains = Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
		
		String idUser = clains.getSubject();
		if(idUser != null) {
			return Long.valueOf(idUser);
		}
		return null;
	}
}
