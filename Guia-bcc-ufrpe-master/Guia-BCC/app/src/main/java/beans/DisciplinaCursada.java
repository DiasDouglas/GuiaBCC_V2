package beans;

import java.util.ArrayList;

/**
 * Created by Ismael on 21/11/2017.
 * Classe básica que representa as disciplinas cursada pelo aluno
 */

public class DisciplinaCursada {

    private String nomeDisciplina;
    private String nomeProfessor;
    private int qtdAlunos;
    private String inicioPeriodoAvaliacao;
    private String finalPeriodoAvaliacao;
    private boolean avaliacaoAtualRealizada;
    private String semestreAtual;

    public DisciplinaCursada(){}

    public DisciplinaCursada(String nomeDisciplina, String nomeProfessor, int qtdAlunos,
                             String inicioPeriodoAvaliacao, String finalPeriodoAvaliacao,
                             boolean avaliacaoAtualRealizada, String semestreAtual){
        this.setNomeDisciplina(nomeDisciplina);
        this.setAvaliacaoAtualRealizada(avaliacaoAtualRealizada);
        this.setFinalPeriodoAvaliacao(finalPeriodoAvaliacao);
        this.setInicioPeriodoAvaliacao(inicioPeriodoAvaliacao);
        this.setQtdAlunos(qtdAlunos);
        this.setSemestreAtual(semestreAtual);
        this.setNomeProfessor(nomeProfessor);
    }

    public String getNomeDisciplina() {
        return nomeDisciplina;
    }

    public void setNomeDisciplina(String nomeDisciplina) {
        if(nomeDisciplina != null)
            this.nomeDisciplina = nomeDisciplina;
        else
            throw new IllegalArgumentException("Nome da disciplina inválido.");
    }

    public String getNomeProfessor() {
        return nomeProfessor;
    }

    public void setNomeProfessor(String nomeProfessor) {
        if(nomeProfessor != null)
            this.nomeProfessor = nomeProfessor;
        else
            throw new IllegalArgumentException("Nome do professor inválido.");
    }

    public int getQtdAlunos() {
        return qtdAlunos;
    }

    public void setQtdAlunos(int qtdAlunos) {
        if(qtdAlunos > 0)
            this.qtdAlunos = qtdAlunos;
        else
            throw new IllegalArgumentException("Quantidade de alunos inválido.");
    }

    public String getInicioPeriodoAvaliacao() {
        return inicioPeriodoAvaliacao;
    }

    public void setInicioPeriodoAvaliacao(String inicioPeriodoAvaliacao) {
        if(inicioPeriodoAvaliacao != null)
            this.inicioPeriodoAvaliacao = inicioPeriodoAvaliacao;
        else
            throw new IllegalArgumentException("Período inicial de avaliações inválido.");
    }

    public String getFinalPeriodoAvaliacao() {
        return finalPeriodoAvaliacao;
    }

    public void setFinalPeriodoAvaliacao(String finalPeriodoAvaliacao) {
        if(finalPeriodoAvaliacao != null)
            this.finalPeriodoAvaliacao = finalPeriodoAvaliacao;
        else
            throw new IllegalArgumentException("Período final de avaliações inválido.");
    }

    public boolean isAvaliacaoAtualRealizada() {
        return avaliacaoAtualRealizada;
    }

    public void setAvaliacaoAtualRealizada(boolean avaliacaoAtualRealizada) {
        this.avaliacaoAtualRealizada = avaliacaoAtualRealizada;
    }

    public String getSemestreAtual() {
        return semestreAtual;
    }

    public void setSemestreAtual(String semestreAtual) {
        if(semestreAtual != null)
            this.semestreAtual = semestreAtual;
        else
            throw new IllegalArgumentException("Semestre atual inválido.");
    }
}
