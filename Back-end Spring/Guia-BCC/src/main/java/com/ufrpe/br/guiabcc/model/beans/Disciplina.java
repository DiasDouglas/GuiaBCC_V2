package com.ufrpe.br.guiabcc.model.beans;

import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.Data;

@Data
@Entity
public class Disciplina {
	
	@Id	//@GeneratedValue
	private long id;
    private String nomeDisciplina;
    @OneToMany(mappedBy = "disciplina", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<DisciplinaProfessor> professoresAnteriores;
    private int qtdMediaAlunos;
    private String ultimoSemestre;//ultimo semestre que foi lecionada
    private float avaliacaoGeral;
    private float avaliacaoDificuldade;
    private float avaliacaoClareza;
    private float avaliacaoEsforco;
    private float avaliacaoConteudo;
    private int qtdItens;
    private String ultimaAtt;
    private String urlBancoDa;
    
    public Disciplina() {
    	
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
	public Set<DisciplinaProfessor> getProfessoresAnteriores() {
		return professoresAnteriores;
	}
	public void setProfessoresAnteriores(Set<DisciplinaProfessor> professoresAnteriores) {
		this.professoresAnteriores = professoresAnteriores;
	}
	
	public void addProfessores(List<DisciplinaProfessor> newProfessores) {
		this.professoresAnteriores.addAll(newProfessores);
	}

	public String getUrlBancoDa() {
		return urlBancoDa;
	}

	public void setUrlBancoDa(String urlBancoDa) {
		this.urlBancoDa = urlBancoDa;
	}
    
    
}
