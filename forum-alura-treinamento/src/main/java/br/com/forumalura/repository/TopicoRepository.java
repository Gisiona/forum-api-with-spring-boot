package br.com.forumalura.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.forumalura.entity.Topico;

public interface TopicoRepository extends JpaRepository<Topico, Long>{

	List<Topico> findByCursoNome(String nomeCurso);

}
