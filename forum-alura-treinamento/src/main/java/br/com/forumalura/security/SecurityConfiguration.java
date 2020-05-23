package br.com.forumalura.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import br.com.forumalura.security.filter.AutenticacaoTokenFilter;

@EnableWebSecurity
@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Autowired
	private AutenticacaoService autenticacaoService;
	
	@Autowired
	private TokenService tokenService;
	
	@Override
	@Bean
	protected AuthenticationManager authenticationManager() throws Exception {		
		return super.authenticationManager();
	}	
	
	// Confgigurações de autenticacao
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(autenticacaoService)
			.passwordEncoder(new BCryptPasswordEncoder());
	}
	
	
	// Confgigurações de autorizacao
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
		.antMatchers(HttpMethod.GET, "/forum/topicos").permitAll()
		.antMatchers(HttpMethod.GET, "/forum/topicos/*").permitAll()
		.antMatchers(HttpMethod.POST, "/forum/auth").permitAll()
		.antMatchers(HttpMethod.GET, "/actuator/**").permitAll()
		
		// qualquer outra requisição precisa esta autenticado
		.anyRequest().authenticated()
		
		// desabilita o ataque via csrf
		.and().csrf().disable()		
		
		// habilita o tipo de autenticação stateless, ou seja não guarda sessão
		.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
		
		// registra o filtro para o spring ter conhecimento e executar
		.and().addFilterAfter(new AutenticacaoTokenFilter(tokenService), UsernamePasswordAuthenticationFilter.class);
		//.and().formLogin();
		
	}
	
	
	// Confgigurações de recursos estaticos (ex: css, html, js ... etc..)
	@Override
	public void configure(WebSecurity web) throws Exception {
		super.configure(web);
	}

	
}
