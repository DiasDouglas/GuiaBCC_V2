package com.ufrpe.br.guiabcc.model.beans;

import java.io.Serializable;
import java.util.Objects;

public class DisciplinaProfessorId implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private long professor;
    private long disciplina;
    private String semestreLecionado;

    public DisciplinaProfessorId() {}

    public DisciplinaProfessorId(Professor professor, Disciplina disciplina, String semestreLecionado) {
        this.professor = professor.getId();
        this.disciplina = disciplina.getId();
        this.semestreLecionado = semestreLecionado;
    }

    @Override
    public boolean equals(Object o) {

        if (o == this) {
            return true;
        }
        if (!(o instanceof DisciplinaProfessor)) {
            return false;
        }
        DisciplinaProfessor disciplinaProfessor = (DisciplinaProfessor) o;
        return Objects.equals(professor, disciplinaProfessor.getProfessor().getId()) &&
               Objects.equals(disciplina, disciplinaProfessor.getDisciplina().getId()) &&
               Objects.equals(semestreLecionado, disciplinaProfessor.getSemestreLecionado());
    }

    @Override
    public int hashCode() {
        return Objects.hash(professor, disciplina, semestreLecionado);
    }
}
