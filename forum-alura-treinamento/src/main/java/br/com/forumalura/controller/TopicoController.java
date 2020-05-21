package br.com.forumalura.controller;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.forumalura.controller.dto.DetalheTopicoDto;
import br.com.forumalura.controller.dto.TopicoDto;
import br.com.forumalura.controller.dto.TopicoFormDto;
import br.com.forumalura.entity.Topico;
import br.com.forumalura.repository.CursoRepository;
import br.com.forumalura.repository.TopicoRepository;

@RestController
@RequestMapping("/forum")
public class TopicoController {

	@Autowired
	private TopicoRepository topicoRepository;
	
	@Autowired 
	private CursoRepository cursoRepository;
	
	@GetMapping("/topicos")
	public List<TopicoDto> listaTopicos(@RequestParam(name = "curso") String nomeCurso) {
		if(nomeCurso == null) {
			List<Topico> topicos = topicoRepository.findAll();
			return TopicoDto.converter(topicos);
		}else {
			List<Topico> topicos = topicoRepository.findByCursoNome(nomeCurso);							
			return TopicoDto.converter(topicos);
		}
	}
	
	@PostMapping("/topicos")
	public ResponseEntity<TopicoDto> cadastrar(@RequestBody @Valid TopicoFormDto form, UriComponentsBuilder uriBuilder) {
		Topico topico = form.converter(form, cursoRepository);
		topico = topicoRepository.save(topico);
		URI uri = uriBuilder.path("/topicos/{id}").buildAndExpand(topico.getId()).toUri();
		return ResponseEntity.created(uri).body(new TopicoDto(topico));
	}

	@GetMapping("/topicos/{id}")
	public ResponseEntity<DetalheTopicoDto> detalharTopico(@PathVariable Long id) {
		Topico topico = topicoRepository.getOne(id);
		return ResponseEntity.status(HttpStatus.OK).body(new DetalheTopicoDto(topico));
	}
	
	@PutMapping("/topicos/{id}")
	public ResponseEntity<TopicoDto> atualizarTopico(@PathVariable Long id, @RequestBody @Valid TopicoAtualizarFormDto) {
		Topico topico = topicoRepository.getOne(id);
		return ResponseEntity.status(HttpStatus.OK).body(new DetalheTopicoDto(topico));
	}
}
