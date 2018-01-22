package beans;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by Fabio on 24/11/2017.
 * Classe básica que representa as disciplinas
 */

public class Disciplina implements Parcelable{

    private long ID;
    private String nomeDisciplina;
    private ArrayList<Professor> professoresAnteriores;
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

    public Disciplina(long ID, String nomeDisciplina, ArrayList<Professor> professoresAnteriores, int qtdMediaAlunos,
                      String ultimoSemestre, float avaliacaoDificuldade,
                      float avaliacaoClareza, float avaliacaoEsforco, float avaliacaoConteudo,
                      int qtdItens, String ultimaAtt){
        this.setID(ID);
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
        this.setQtdMediaAlunos(qtdMediaAlunos);
    }

    public long getID() {
        return ID;
    }

    public void setID(long ID) {
        this.ID = ID;
    }

    public void setAvaliacaoGeral(float avaliacaoGeral) {
        this.avaliacaoGeral = avaliacaoGeral;
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

    public ArrayList<Professor> getProfessoresAnteriores() {
        return professoresAnteriores;
    }

    public void setProfessoresAnteriores(ArrayList<Professor> professoresAnteriores) {
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

    public Disciplina(Parcel source){
        this.setID(source.readLong());
        this.setNomeDisciplina(source.readString());
        this.setProfessoresAnteriores(source.readArrayList(Professor.class.getClassLoader()));
        this.setQtdMediaAlunos(source.readInt());
        this.setUltimoSemestre(source.readString());
        this.setAvaliacaoGeral(source.readFloat());
        this.setAvaliacaoDificuldade(source.readFloat());
        this.setAvaliacaoClareza(source.readFloat());
        this.setAvaliacaoEsforco(source.readFloat());
        this.setAvaliacaoConteudo(source.readFloat());
        this.setQtdItens(source.readInt());
        this.setUltimaAtt(source.readString());
    }


    @Override
    public void writeToParcel(Parcel dest, int flags) {
         if(this.getID() != 0) {
             dest.writeByte((byte)1);
             dest.writeLong(this.getID());
         }
         else {
             dest.writeByte((byte)0);
         }

        if(this.getNomeDisciplina() != null) {
            dest.writeByte((byte)1);
            dest.writeString(this.getNomeDisciplina());
        }
        else {
            dest.writeByte((byte)0);
        }

        if(this.getProfessoresAnteriores() != null) {
            dest.writeByte((byte)1);
            dest.writeParcelable((Parcelable) this.getProfessoresAnteriores(), flags);
        }
        else{
            dest.writeByte((byte)0);
        }

        if(this.getQtdMediaAlunos() != 0) {
            dest.writeByte((byte)1);
            dest.writeInt(this.getQtdMediaAlunos());
        }
        else{
            dest.writeByte((byte)0);
        }

        if(this.getUltimoSemestre() != null) {
            dest.writeByte((byte)1);
            dest.writeString(this.getUltimoSemestre());
        }
        else{
            dest.writeByte((byte)0);
        }
         if(this.getAvaliacaoGeral() != 0.0) {
             dest.writeByte((byte)1);
             dest.writeFloat(this.getAvaliacaoGeral());
         }
         else {
             dest.writeByte((byte)0);
         }

         if(this.getAvaliacaoDificuldade() != 0.0) {
             dest.writeByte((byte)1);
             dest.writeFloat(this.getAvaliacaoDificuldade());
         }
         else{
             dest.writeByte((byte)0);
         }

         if(this.getAvaliacaoClareza() != 0.0) {
             dest.writeByte((byte)1);
             dest.writeFloat(this.getAvaliacaoClareza());
         }
         else{
             dest.writeByte((byte)0);
         }

        if(this.getAvaliacaoEsforco() != 0.0) {
            dest.writeByte((byte)1);
            dest.writeFloat(this.getAvaliacaoEsforco());
        }
        else{
            dest.writeByte((byte)0);
        }

         if(this.getAvaliacaoConteudo() != 0.0) {
             dest.writeByte((byte)0);
             dest.writeFloat(this.getAvaliacaoConteudo());
         }
         else{
             dest.writeByte((byte)0);
         }

        if(this.getQtdItens() !=0) {
            dest.writeByte((byte)1);
            dest.writeInt(this.getQtdItens());
        }
        else{
            dest.writeByte((byte)0);
        }

        if(this.getUltimaAtt() != null) {
            dest.writeByte((byte)1);
            dest.writeString(this.getUltimaAtt());
        }
        else{
            dest.writeByte((byte) 0);
        }

    }

    public  static final Parcelable.Creator<Disciplina> CREATOR = new Creator<Disciplina>() {
        @Override
        public Disciplina createFromParcel(Parcel source) {
            return new Disciplina(source);
        }

        @Override
        public Disciplina[] newArray(int size) {
            return new Disciplina[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }


}
