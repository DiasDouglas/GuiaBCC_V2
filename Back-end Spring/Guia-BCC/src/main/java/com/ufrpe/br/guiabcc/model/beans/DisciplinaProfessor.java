package com.ufrpe.br.guiabcc.model.beans;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "disciplina_professor")
@IdClass(DisciplinaProfessorId.class)
public class DisciplinaProfessor implements Serializable {
	
	private static final long serialVersionUID = 1L;
	@Id
	@ManyToOne
	@JoinColumn(name = "disciplina_id")
	private Disciplina disciplina;
	@Id
	@ManyToOne
	@JoinColumn(name = "professor_id")
	private Professor professor;
	@Id
    private String semestreLecionado;
    private int qtdAlunos;
    private int qtdAlunosAvaliaram;
    private float avaliacaoGeral;
    private float avaliacaoDificuldade;
    private float avaliacaoClareza;
    private float avaliacaoEsforco;
    private float avaliacaoConteudo;
    
    public DisciplinaProfessor() {
    	
    }

    public DisciplinaProfessor(ProfessorDTO discProf, Disciplina disc, Professor prof) {
    	this.disciplina = disc;
    	this.professor = prof;
    	this.semestreLecionado = discProf.getSemestreLecionado();
    	this.qtdAlunos = discProf.getQtdAlunos();
    	this.qtdAlunosAvaliaram = discProf.getQtdAlunosAvaliaram();
    	this.avaliacaoClareza = discProf.getAvaliacaoClareza();
    	this.avaliacaoConteudo = discProf.getAvaliacaoConteudo();
    	this.avaliacaoDificuldade = discProf.getAvaliacaoDificuldade();
    	this.avaliacaoEsforco = discProf.getAvaliacaoEsforco();
    	this.avaliacaoGeral = discProf.getAvaliacaoGeral();
    }
    @JsonIgnore
	public Disciplina getDisciplina() {
		return disciplina;
	}
    @JsonIgnore
	public void setDisciplina(Disciplina disciplina) {
		this.disciplina = disciplina;
	}
	public Professor getProfessor() {
		return professor;
	}
	public void setProfessor(Professor professor) {
		this.professor = professor;
	}
	public String getSemestreLecionado() {
		return semestreLecionado;
	}
	public void setSemestreLecionado(String semestreLecionado) {
		this.semestreLecionado = semestreLecionado;
	}
	public int getQtdAlunos() {
		return qtdAlunos;
	}
	public void setQtdAlunos(int qtdAlunos) {
		this.qtdAlunos = qtdAlunos;
	}
	public int getQtdAlunosAvaliaram() {
		return qtdAlunosAvaliaram;
	}
	public void setQtdAlunosAvaliaram(int qtdAlunosAvaliaram) {
		this.qtdAlunosAvaliaram = qtdAlunosAvaliaram;
	}
	public float getAvaliacaoGeral() {
		return avaliacaoGeral;
	}
	public void setAvaliacaoGeral(float avaliacaoGeral) {
		this.avaliacaoGeral = avaliacaoGeral;
	}
	public float getAvaliacaoDificuldade() {
		return avaliacaoDificuldade;
	}
	public void setAvaliacaoDificuldade(float avaliacaoDificuldade) {
		this.avaliacaoDificuldade = avaliacaoDificuldade;
	}
	public float getAvaliacaoClareza() {
		return avaliacaoClareza;
	}
	public void setAvaliacaoClareza(float avaliacaoClareza) {
		this.avaliacaoClareza = avaliacaoClareza;
	}
	public float getAvaliacaoEsforco() {
		return avaliacaoEsforco;
	}
	public void setAvaliacaoEsforco(float avaliacaoEsforco) {
		this.avaliacaoEsforco = avaliacaoEsforco;
	}
	public float getAvaliacaoConteudo() {
		return avaliacaoConteudo;
	}
	public void setAvaliacaoConteudo(float avaliacaoConteudo) {
		this.avaliacaoConteudo = avaliacaoConteudo;
	}

}
