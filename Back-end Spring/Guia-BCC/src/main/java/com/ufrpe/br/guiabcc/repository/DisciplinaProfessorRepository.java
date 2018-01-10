package com.ufrpe.br.guiabcc.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ufrpe.br.guiabcc.model.beans.DisciplinaProfessor;
import com.ufrpe.br.guiabcc.model.beans.DisciplinaProfessorId;

public interface DisciplinaProfessorRepository extends JpaRepository<DisciplinaProfessor, DisciplinaProfessorId> {

}
