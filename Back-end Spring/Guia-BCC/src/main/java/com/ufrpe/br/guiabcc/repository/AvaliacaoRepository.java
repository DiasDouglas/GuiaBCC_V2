package com.ufrpe.br.guiabcc.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ufrpe.br.guiabcc.model.beans.Avaliacao;
import com.ufrpe.br.guiabcc.model.beans.AvaliacaoId;

public interface AvaliacaoRepository extends JpaRepository<Avaliacao, AvaliacaoId> {

}
