package com.ufrpe.br.guiabcc.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ufrpe.br.guiabcc.model.beans.Avaliacao;
import com.ufrpe.br.guiabcc.model.beans.AvaliacaoDTO;
import com.ufrpe.br.guiabcc.repository.AvaliacaoRepository;
import com.ufrpe.br.guiabcc.repository.DisciplinaRepository;
import com.ufrpe.br.guiabcc.repository.PerguntaRepository;
import com.ufrpe.br.guiabcc.repository.ProfessorRepository;

@RestController
@RequestMapping("guiabcc/avaliacao")
public class AvaliacaoController {

	@Autowired
	private DisciplinaRepository disciplinas;

	@Autowired
	private PerguntaRepository perguntas;
	
	@Autowired
	private ProfessorRepository professores;
	
	@Autowired
	private AvaliacaoRepository avaliacoes;
	
	@PostMapping
	public Avaliacao saveAvaliacao(@RequestBody AvaliacaoDTO avaliacaoAux) {
		Avaliacao avaliacao = new Avaliacao();
		avaliacao.setCpfAluno(avaliacaoAux.getCpfAluno());
		avaliacao.setEtapa(avaliacaoAux.getEtapa());
		avaliacao.setResposta(avaliacaoAux.getResposta());
		avaliacao.setSemestre(avaliacaoAux.getSemestre());
		avaliacao.setDisciplina(disciplinas.findOne(avaliacaoAux.getDisciplina()));
		avaliacao.setPergunta(perguntas.findOne(avaliacaoAux.getPergunta()));
		avaliacao.setProfessor(professores.findOne(avaliacaoAux.getProfessor()));
		
		return avaliacoes.save(avaliacao);		
	}
	
	@GetMapping
	public List<Avaliacao> getAvaliacoes() {
		return avaliacoes.findAll();
	}

		
}
