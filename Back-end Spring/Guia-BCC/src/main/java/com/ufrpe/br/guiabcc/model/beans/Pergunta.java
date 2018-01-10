package com.ufrpe.br.guiabcc.model.beans;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data
public class Pergunta {
	@Id
	@GeneratedValue
	private Integer codigoPergunta;
	private String questao;
	private String resposta1;
	private String resposta2;
	private String resposta3;
	private String resposta4;
	private int tipo;
	private Integer versao;
	
	public Pergunta() {
		
	}
	
	public Pergunta(Integer codigoPergunta, String questao, String resposta1, String resposta2, String resposta3,
			String resposta4, int tipo, Integer versao) {
		super();
		this.codigoPergunta = codigoPergunta;
		this.questao = questao;
		this.resposta1 = resposta1;
		this.resposta2 = resposta2;
		this.resposta3 = resposta3;
		this.resposta4 = resposta4;
		this.tipo = tipo;
		this.versao = versao;
	}
	
	public Integer getCodigoPergunta() {
		return codigoPergunta;
	}
	public void setCodigoPergunta(Integer codigoPergunta) {
		this.codigoPergunta = codigoPergunta;
	}
	public String getQuestao() {
		return questao;
	}
	public void setQuestao(String questao) {
		this.questao = questao;
	}
	public String getResposta1() {
		return resposta1;
	}
	public void setResposta1(String resposta1) {
		this.resposta1 = resposta1;
	}
	public String getResposta2() {
		return resposta2;
	}
	public void setResposta2(String resposta2) {
		this.resposta2 = resposta2;
	}
	public String getResposta3() {
		return resposta3;
	}
	public void setResposta3(String resposta3) {
		this.resposta3 = resposta3;
	}
	public String getResposta4() {
		return resposta4;
	}
	public void setResposta4(String resposta4) {
		this.resposta4 = resposta4;
	}

	public int getTipo() {
		return tipo;
	}

	public void setTipo(int tipo) {
		this.tipo = tipo;
	}

	public Integer getVersao() {
		return versao;
	}

	public void setVersao(Integer versao) {
		this.versao = versao;
	}
	
}
