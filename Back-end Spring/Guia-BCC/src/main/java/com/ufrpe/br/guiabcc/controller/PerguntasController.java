package com.ufrpe.br.guiabcc.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ufrpe.br.guiabcc.model.beans.Pergunta;
import com.ufrpe.br.guiabcc.repository.PerguntaRepository;

@RestController
@RequestMapping("guiabcc/perguntas")
public class PerguntasController {

	@Autowired
	private PerguntaRepository perguntas;
	
	@PostMapping
	public Pergunta save(@RequestBody Pergunta p) {
		return perguntas.save(p);
	}

	@GetMapping
	public List<Pergunta> listAll(){
		return perguntas.findAll();
	}
	
	@GetMapping(path = "/{id}")
	public Pergunta get(@PathVariable Integer id) {
		return perguntas.findOne(id);
	}
}
