package br.com.forumalura.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.forumalura.controller.dto.TopicoDto;
import br.com.forumalura.entity.Topico;
import br.com.forumalura.repository.TopicoRepository;

@RestController
@RequestMapping("/forum")
public class TopicoController {

	@Autowired
	private TopicoRepository topicoRepository;
	
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
	
}
