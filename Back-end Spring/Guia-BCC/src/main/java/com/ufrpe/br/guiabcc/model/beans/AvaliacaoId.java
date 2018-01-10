package com.ufrpe.br.guiabcc.model.beans;

import java.io.Serializable;
import java.util.Objects;

public class AvaliacaoId implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private long disciplina;
	private String cpfAluno;
	private long professor;
	private String semestre;
	private int etapa;
	private Integer pergunta;
	
	private AvaliacaoId() {
	}

	public AvaliacaoId(Disciplina disciplina, String cpfAluno, Professor professor, String semestre, int etapa, Pergunta pergunta) {
		this.disciplina = disciplina.getId();
		this.cpfAluno = cpfAluno;
		this.professor = professor.getId();
		this.semestre = semestre;
		this.etapa = etapa;
		this.pergunta = pergunta.getCodigoPergunta();
	}

	@Override
    public boolean equals(Object o) {

        if (o == this) {
            return true;
        }
        if (!(o instanceof AvaliacaoId)) {
            return false;
        }
        AvaliacaoId avaliacao = (AvaliacaoId) o;
        return Objects.equals(disciplina, avaliacao.disciplina) &&
               Objects.equals(cpfAluno, avaliacao.cpfAluno) &&
               Objects.equals(semestre, avaliacao.semestre) &&
               Objects.equals(etapa, avaliacao.etapa) &&
               Objects.equals(pergunta, avaliacao.pergunta) &&
               Objects.equals(professor, avaliacao.professor);
    }

    @Override
    public int hashCode() {
        return Objects.hash(disciplina, cpfAluno, semestre, etapa, professor, pergunta);
    }
}
