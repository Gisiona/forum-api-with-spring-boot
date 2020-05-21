package br.com.forumalura.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.forumalura.entity.Curso;

public interface CursoRepository extends JpaRepository<Curso, Long>{

	Curso findByNome(String curso);
}
