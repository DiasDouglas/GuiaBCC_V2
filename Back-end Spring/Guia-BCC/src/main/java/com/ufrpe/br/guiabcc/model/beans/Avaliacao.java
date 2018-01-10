package com.ufrpe.br.guiabcc.model.beans;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.ManyToOne;


@Entity
@IdClass(AvaliacaoId.class)
public class Avaliacao implements Serializable{
	private static final long serialVersionUID = -4663038133586824442L;
	@Id
	@ManyToOne	
	private Disciplina disciplina;
	@Id
	private String cpfAluno;
	@Id
	@ManyToOne	
	private Professor professor;
	@Id
	private String semestre;
	@Id
	private int etapa;
	@Id
	@ManyToOne	
	private Pergunta pergunta;
	private int resposta;
	
	public Avaliacao() {
		
	}

	public Disciplina getDisciplina() {
		return disciplina;
	}

	public void setDisciplina(Disciplina disciplina) {
		this.disciplina = disciplina;
	}

	public String getCpfAluno() {
		return cpfAluno;
	}

	public void setCpfAluno(String cpfAluno) {
		this.cpfAluno = cpfAluno;
	}

	public Professor getProfessor() {
		return professor;
	}

	public void setProfessor(Professor professor) {
		this.professor = professor;
	}

	public String getSemestre() {
		return semestre;
	}

	public void setSemestre(String semestre) {
		this.semestre = semestre;
	}

	public int getEtapa() {
		return etapa;
	}

	public void setEtapa(int etapa) {
		this.etapa = etapa;
	}

	public Pergunta getPergunta() {
		return pergunta;
	}

	public void setPergunta(Pergunta pergunta) {
		this.pergunta = pergunta;
	}

	public int getResposta() {
		return resposta;
	}

	public void setResposta(int resposta) {
		this.resposta = resposta;
	}
}
