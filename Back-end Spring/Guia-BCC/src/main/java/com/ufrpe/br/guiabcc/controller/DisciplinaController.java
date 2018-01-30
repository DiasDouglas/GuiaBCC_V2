package com.ufrpe.br.guiabcc.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ufrpe.br.guiabcc.model.beans.Disciplina;
import com.ufrpe.br.guiabcc.model.beans.DisciplinaDTO;
import com.ufrpe.br.guiabcc.model.beans.DisciplinaProfessor;
import com.ufrpe.br.guiabcc.model.beans.Professor;
import com.ufrpe.br.guiabcc.model.beans.ProfessorDTO;
import com.ufrpe.br.guiabcc.repository.DisciplinaRepository;
import com.ufrpe.br.guiabcc.repository.ProfessorRepository;

@RestController
@RequestMapping("/guiabcc")
public class DisciplinaController {
	
	@Autowired
	private DisciplinaRepository disciplinaRepository;
	@Autowired
	private ProfessorRepository professorRepository;
	
	
	//metodo para salvar uma nova disciplina, podendo passar a lista de professores como null
	//e ela salvará sem professores, a priori (mais indicado a fazer), e então chama o método
	//saveProfessores para salvar os professores dessa disciplina
	@PostMapping(path = "/disciplina")
	public Disciplina saveDisciplina(@RequestBody Disciplina disc) {
		
		// salvar disciplina
		
		for (DisciplinaProfessor aux : disc.getProfessoresAnteriores()) {
			Professor prof = professorRepository.findOne(aux.getProfessor().getId());
			
			if(prof != null) {
				aux.setProfessor(prof);
				aux.setDisciplina(disc);
			}else {
				throw new RuntimeException("Professor nao existente: " + aux.getProfessor());
			}
		}
		Disciplina discSalva = disciplinaRepository.save(disc);
		return discSalva;
	}
	
	//retorna todas as disciplinas, com os professores e as avaliacoes deles
	//(nao é muito recomendado a fazer devido a dificuldade de tratar o json
	//recebido, o mais indicado é usar o metodo listAllDisciplinaDTO, pois
	//o json virá igual ao objeto que estamos utilizando no front-end)
	//, mas é importante para saber como está guardado no banco
	@GetMapping(path = "/disciplina")
	public List<Disciplina> listAllDisciplinas(){
		return disciplinaRepository.findAll();
	}
	
	//pegar uma disciplina atraves do id, indo com seus professores junto,
	//nao recomendado.
	@GetMapping(path = "/disciplina/{id}")
	public Disciplina getDisciplina(@PathVariable Long id) {
		return disciplinaRepository.findOne(id);
	}
	
	//retornando os professores de uma disciplina cujo ID é passado. Vale salientar
	//que o ProfessorDTO, cujo é retornado uma lista deles, está exatamente como
	//estamos utilizando a classe Professor no front-end, sendo esse metodo o mais
	//recomendado para usar, caso queira pegar os professores anteriores de uma disciplina
	@GetMapping(path = "/disciplina/{id}/professores")
	public List<ProfessorDTO> getProfessoresDisciplina(@PathVariable Long id) {
		List<ProfessorDTO> profs = new ArrayList<>();
		Disciplina disc = disciplinaRepository.findOne(id);
		for (DisciplinaProfessor discProf : disc.getProfessoresAnteriores()) {
			profs.add(new ProfessorDTO(discProf));
		}
		return profs;
	}
	
	@PostMapping(path = "/disciplina/{id}/professores")
	public List<DisciplinaProfessor> addProfessoresDisciplina(	@PathVariable Long id, 
														@RequestBody List<ProfessorDTO> professores) {
		Disciplina disc = disciplinaRepository.findOne(id);
		List<DisciplinaProfessor> profsConvertidos = new ArrayList<>();
		for (ProfessorDTO professorAux : professores) {
			Professor profAtual = professorRepository.findOne(professorAux.getID());
			if(profAtual != null) {
				profsConvertidos.add(new DisciplinaProfessor(professorAux, disc, profAtual));
			}else {
				throw new RuntimeException("Professor nao existente: " + professorAux.getID() );
			}
		}
		disc.addProfessores(profsConvertidos);
		disciplinaRepository.save(disc);
		return profsConvertidos;
	}
	
	// abaixo referente a disciplinaDTO
	
	//metodo que retornará apenas os dados referente a disciplina (sem professores)
	//metodo recomendado usar, pois DisciplinaDTO está exatamente igual utilizamos no 
	//front-end
	@GetMapping(path = "/disciplinaDto")
	public List<DisciplinaDTO> listAllDisciplinaDTO(){
		List<Disciplina> discs = disciplinaRepository.findAllByOrderByNomeDisciplina();
		List<DisciplinaDTO> discsDTO = new ArrayList<>();
		for (Disciplina disciplina : discs) {
			discsDTO.add(new DisciplinaDTO(disciplina));
		}
		return discsDTO;
	}
	
	//metodo para retonar uma disciplina pelo ID no formato do front-end
	//recomendado
	@GetMapping(path = "/disciplinaDto/{id}")
	public DisciplinaDTO getDisciplinaDTO(@PathVariable Long id) {
		return new DisciplinaDTO(disciplinaRepository.findOne(id));
	}
	
	// abaixo referente a professores
	
	@GetMapping(path = "/professor")
	public List<Professor> listAllProfessor(){
		return professorRepository.findAll();
	}
	
	@GetMapping(path = "/professor/{id}")
	public Professor getProfessor(@PathVariable Long id) {
		return professorRepository.findOne(id);
	}

	
}
