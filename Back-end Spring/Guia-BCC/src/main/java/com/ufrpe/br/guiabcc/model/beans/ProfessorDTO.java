package com.ufrpe.br.guiabcc.model.beans;

public class ProfessorDTO {

	private long ID;
	private String nome;
	private String semestreLecionado;
	private int qtdAlunos;
	private int qtdAlunosAvaliaram;
	private float avaliacaoGeral;
	private float avaliacaoDificuldade;
	private float avaliacaoClareza;
	private float avaliacaoEsforco;
	private float avaliacaoConteudo;
	
	public ProfessorDTO() {
		
	}

	public ProfessorDTO(DisciplinaProfessor discProf) {
		this.ID = discProf.getProfessor().getId();
		this.nome = discProf.getProfessor().getNome();
		this.semestreLecionado = discProf.getSemestreLecionado();
		this.qtdAlunos = discProf.getQtdAlunos();
		this.qtdAlunosAvaliaram = discProf.getQtdAlunosAvaliaram();
		this.avaliacaoDificuldade = discProf.getAvaliacaoDificuldade();
		this.avaliacaoClareza = discProf.getAvaliacaoClareza();
		this.avaliacaoEsforco = discProf.getAvaliacaoEsforco();
		this.avaliacaoConteudo = discProf.getAvaliacaoConteudo();
		this.calcularAvaliacaoGeral();
	}

	public long getID() {
		return ID;
	}

	public void setID(long ID) {
		this.ID = ID;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
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

	public void calcularAvaliacaoGeral() {
		// fazendo uma media apenas a principio, depois a equacao vai mudar
		this.avaliacaoGeral = (this.avaliacaoClareza + this.avaliacaoConteudo + this.avaliacaoDificuldade
				+ this.avaliacaoEsforco) / 4;
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
