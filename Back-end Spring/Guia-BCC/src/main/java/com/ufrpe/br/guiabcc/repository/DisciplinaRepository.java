package com.ufrpe.br.guiabcc.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ufrpe.br.guiabcc.model.beans.Disciplina;

public interface DisciplinaRepository extends JpaRepository<Disciplina, Long> {
	public List<Disciplina> findAllByOrderByNomeDisciplina();
}
