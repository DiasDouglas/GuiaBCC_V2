package com.ufrpe.br.guiabcc.model.beans;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
@Entity
public class Professor {
	@Id
	@GeneratedValue
    private long id;
    private String nome;
    @OneToMany(mappedBy = "professor")
    private Set<DisciplinaProfessor> disciplinasLecionadas;
    
    public Professor() {
    	
    }
    
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
    @JsonIgnore
	public Set<DisciplinaProfessor> getDisciplinasLecionadas() {
		return disciplinasLecionadas;
	}
    @JsonIgnore
	public void setDisciplinasLecionadas(Set<DisciplinaProfessor> disciplinasLecionadas) {
		this.disciplinasLecionadas = disciplinasLecionadas;
	}
	
}
