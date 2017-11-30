package beans;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by Fabio on 24/11/2017.
 * Classe básica que representa as disciplinas
 */

public class Disciplina {

    private String nomeDisciplina;
    private ArrayList<String> professoresAnteriores;
    private int qtdMediaAlunos;
    private String ultimoSemestre;//ultimo semestre que foi lecionada
    private float avaliacaoGeral;
    private float avaliacaoDificuldade;
    private float avaliacaoClareza;
    private float avaliacaoEsforco;
    private float avaliacaoConteudo;
    private int qtdItens;
    private String ultimaAtt;

    public Disciplina(){}

    public Disciplina(String nomeDisciplina, ArrayList<String> professoresAnteriores, int qtdMediaAlunos,
                      String ultimoSemestre, float avaliacaoDificuldade,
                      float avaliacaoClareza, float avaliacaoEsforco, float avaliacaoConteudo,
                      int qtdItens, String ultimaAtt){
        this.setAvaliacaoClareza(avaliacaoClareza);
        this.setAvaliacaoConteudo(avaliacaoConteudo);
        this.setAvaliacaoDificuldade(avaliacaoDificuldade);
        this.setAvaliacaoEsforco(avaliacaoEsforco);
        this.setNomeDisciplina(nomeDisciplina);
        this.setProfessoresAnteriores(professoresAnteriores);
        this.setUltimaAtt(ultimaAtt);
        this.setUltimoSemestre(ultimoSemestre);
        this.setQtdItens(qtdItens);
        this.calcularAvaliacaoGeral();
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

    public ArrayList<String> getProfessoresAnteriores() {
        return professoresAnteriores;
    }

    public void setProfessoresAnteriores(ArrayList<String> professoresAnteriores) {
        if(professoresAnteriores != null)
            this.professoresAnteriores = professoresAnteriores;
        else
            throw new IllegalArgumentException("Nomes dos professores inválidos.");
    }

    public int getQtdMediaAlunos() {
        return qtdMediaAlunos;
    }

    public void setQtdMediaAlunos(int qtdMediaAlunos) {
        if(qtdMediaAlunos >0)
            this.qtdMediaAlunos = qtdMediaAlunos;
        else
            throw new IllegalArgumentException("Quantidade média dos alunos inválida.");
    }

    public String getUltimoSemestre() {
        return ultimoSemestre;
    }

    public void setUltimoSemestre(String ultimoSemestre) {
        if(ultimoSemestre != null)
            this.ultimoSemestre = ultimoSemestre;
        else
            throw new IllegalArgumentException("Último semestre inválido.");
    }

    public float getAvaliacaoGeral() {
        return avaliacaoGeral;
    }

    public void calcularAvaliacaoGeral(){
        this.avaliacaoGeral = (this.avaliacaoClareza + this.avaliacaoConteudo + this.avaliacaoDificuldade + this.avaliacaoEsforco) / 4;
    }

    public float getAvaliacaoDificuldade() {
        return avaliacaoDificuldade;
    }

    public void setAvaliacaoDificuldade(float avaliacaoDificuldade) {
        if(avaliacaoDificuldade >0)
            this.avaliacaoDificuldade = avaliacaoDificuldade;
        else
            throw new IllegalArgumentException("Avaliação da dificuldade inválida.");
    }

    public float getAvaliacaoClareza() {
        return avaliacaoClareza;
    }

    public void setAvaliacaoClareza(float avaliacaoClareza) {
        if(avaliacaoClareza >0)
            this.avaliacaoClareza = avaliacaoClareza;
        else
            throw new IllegalArgumentException("Avaliação da clareza inválida.");
    }

    public float getAvaliacaoEsforco() {
        return avaliacaoEsforco;
    }

    public void setAvaliacaoEsforco(float avaliacaoEsforco) {
        if(avaliacaoEsforco >0)
            this.avaliacaoEsforco = avaliacaoEsforco;
        else
            throw new IllegalArgumentException("Avaliação do esforço inválido.");
    }

    public float getAvaliacaoConteudo() {
        return avaliacaoConteudo;
    }

    public void setAvaliacaoConteudo(float avaliacaoConteudo) {
        if(avaliacaoConteudo >0)
            this.avaliacaoConteudo = avaliacaoConteudo;
        else
            throw new IllegalArgumentException("Avaliação do conteudo inválido.");
    }

    public int getQtdItens() {
        return qtdItens;
    }

    public void setQtdItens(int qtdItens) {
        if(qtdItens >0)
            this.qtdItens = qtdItens;
        else
            throw new IllegalArgumentException("Quantidade de itens inválida.");
    }

    public String getUltimaAtt() {
        return ultimaAtt;
    }

    public void setUltimaAtt(String ultimaAtt) {
        if(ultimaAtt != null)
            this.ultimaAtt = ultimaAtt;
        else
            throw new IllegalArgumentException("Última atualização inválida.");
    }
}
