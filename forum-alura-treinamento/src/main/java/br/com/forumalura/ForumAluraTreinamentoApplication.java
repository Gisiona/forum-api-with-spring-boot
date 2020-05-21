package br.com.forumalura;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.web.config.EnableSpringDataWebSupport;

@SpringBootApplication
@EnableSpringDataWebSupport // habilita o modulo de paginacao default
@EnableCaching // habilitado o modulo de cache in memory
public class ForumAluraTreinamentoApplication {

	public static void main(String[] args) {
		SpringApplication.run(ForumAluraTreinamentoApplication.class, args);
	}

}
