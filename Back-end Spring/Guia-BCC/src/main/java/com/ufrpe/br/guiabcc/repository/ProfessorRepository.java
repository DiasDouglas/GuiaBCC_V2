package com.ufrpe.br.guiabcc.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ufrpe.br.guiabcc.model.beans.Professor;

public interface ProfessorRepository extends JpaRepository<Professor, Long> {

}
