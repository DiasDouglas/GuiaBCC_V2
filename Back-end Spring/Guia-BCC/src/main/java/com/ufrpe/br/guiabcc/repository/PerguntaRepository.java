package com.ufrpe.br.guiabcc.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ufrpe.br.guiabcc.model.beans.Pergunta;

public interface PerguntaRepository extends JpaRepository<Pergunta, Integer> {

}
