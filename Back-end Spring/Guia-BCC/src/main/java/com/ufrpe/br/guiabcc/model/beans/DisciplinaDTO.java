package com.ufrpe.br.guiabcc.model.beans;

public class DisciplinaDTO {
	
	private long id;
    private String nomeDisciplina;
    private int qtdMediaAlunos;
    private String ultimoSemestre;
    private float avaliacaoGeral;
    private float avaliacaoDificuldade;
    private float avaliacaoClareza;
    private float avaliacaoEsforco;
    private float avaliacaoConteudo;
    private int qtdItens;
    private String ultimaAtt;
    
    public DisciplinaDTO() {
    	
    }
    
    //esse construtor vou usar no DAO disciplina, para ficar mais enxuto lá e não ter que fazer vários setters
    public DisciplinaDTO(Disciplina disc) {
    	this.id = disc.getId();
    	this.nomeDisciplina = disc.getNomeDisciplina();
    	this.qtdMediaAlunos = disc.getQtdMediaAlunos();
    	this.ultimoSemestre = disc.getUltimoSemestre();
    	this.avaliacaoGeral = disc.getAvaliacaoGeral();
    	this.avaliacaoDificuldade = disc.getAvaliacaoDificuldade();
    	this.avaliacaoEsforco = disc.getAvaliacaoEsforco();
    	this.avaliacaoConteudo = disc.getAvaliacaoConteudo();
    	this.qtdItens = disc.getQtdItens();
    	this.ultimaAtt = disc.getUltimaAtt();
    }
    
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getNomeDisciplina() {
		return nomeDisciplina;
	}
	public void setNomeDisciplina(String nomeDisciplina) {
		this.nomeDisciplina = nomeDisciplina;
	}
	public int getQtdMediaAlunos() {
		return qtdMediaAlunos;
	}
	public void setQtdMediaAlunos(int qtdMediaAlunos) {
		this.qtdMediaAlunos = qtdMediaAlunos;
	}
	public String getUltimoSemestre() {
		return ultimoSemestre;
	}
	public void setUltimoSemestre(String ultimoSemestre) {
		this.ultimoSemestre = ultimoSemestre;
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
	public int getQtdItens() {
		return qtdItens;
	}
	public void setQtdItens(int qtdItens) {
		this.qtdItens = qtdItens;
	}
	public String getUltimaAtt() {
		return ultimaAtt;
	}
	public void setUltimaAtt(String ultimaAtt) {
		this.ultimaAtt = ultimaAtt;
	}
}
