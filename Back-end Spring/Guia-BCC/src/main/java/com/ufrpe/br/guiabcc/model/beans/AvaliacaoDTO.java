package com.ufrpe.br.guiabcc.model.beans;

public class AvaliacaoDTO {
	
	private long disciplina;
	private String cpfAluno;
	private long professor;
	private String semestre;
	private int etapa;
	private int pergunta;
	private int resposta;
	
	public AvaliacaoDTO() {
		
	}
	
	public AvaliacaoDTO(long disciplina, String cpfAluno, long professor, String semestre, int etapa, int pergunta,
			int resposta) {
		super();
		this.disciplina = disciplina;
		this.cpfAluno = cpfAluno;
		this.professor = professor;
		this.semestre = semestre;
		this.etapa = etapa;
		this.pergunta = pergunta;
		this.resposta = resposta;
	}

	public long getDisciplina() {
		return disciplina;
	}

	public void setDisciplina(long disciplina) {
		this.disciplina = disciplina;
	}

	public String getCpfAluno() {
		return cpfAluno;
	}

	public void setCpfAluno(String cpfAluno) {
		this.cpfAluno = cpfAluno;
	}

	public long getProfessor() {
		return professor;
	}

	public void setProfessor(long professor) {
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

	public int getPergunta() {
		return pergunta;
	}

	public void setPergunta(int pergunta) {
		this.pergunta = pergunta;
	}

	public int getResposta() {
		return resposta;
	}

	public void setResposta(int resposta) {
		this.resposta = resposta;
	}
}
