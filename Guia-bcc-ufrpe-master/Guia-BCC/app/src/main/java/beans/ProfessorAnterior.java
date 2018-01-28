package beans;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Fabio on 08/12/2017.
 * Updated by Ismael on 22/10/2018
 * Adicionado objeto professor para que a classe fique parecida com o objeto JSON
 * retornado pelo endpoint do spring
 */

/*
essa classe fará parte da classe "DisciplinaCursada" que terá um arrayList de
ProfessorAnterior pois armazenará os professores que lecinoaram em cada semestre, junto com sua
avaliação individual
 */
public class ProfessorAnterior implements Parcelable {
    private String nome;
    private String semestreLecionado;
    private int qtdAlunos;
    private int qtdAlunosAvaliaram;
    private float avaliacaoGeral;
    private float avaliacaoDificuldade;
    private float avaliacaoClareza;
    private float avaliacaoEsforco;
    private float avaliacaoConteudo;

    public ProfessorAnterior(String professor, String semestreLecionado, int qtdAlunos, int qtdAlunosAvaliaram,
                             float avaliacaoDificuldade, float avaliacaoClareza,
                             float avaliacaoEsforco, float avaliacaoConteudo) {
        this.setProfessor(professor);
        this.semestreLecionado = semestreLecionado;
        this.qtdAlunos = qtdAlunos;
        this.qtdAlunosAvaliaram = qtdAlunosAvaliaram;
        this.avaliacaoDificuldade = avaliacaoDificuldade;
        this.avaliacaoClareza = avaliacaoClareza;
        this.avaliacaoEsforco = avaliacaoEsforco;
        this.avaliacaoConteudo = avaliacaoConteudo;
        this.calcularAvaliacaoGeral();
    }

    public String getProfessor() {
        return nome;
    }

    public void setProfessor(String professor) {
        this.nome = professor;
    }

    public String getSemestreLecionado() {
        return semestreLecionado;
    }

    public void setSemestreLecionado(String semestreLecionado) {
        this.semestreLecionado = semestreLecionado;
    }

    public int getQtdAlunos() {
        return qtdAlunos;
    }

    public void setQtdAlunos(int qtdAlunos) {
        this.qtdAlunos = qtdAlunos;
    }

    public int getQtdAlunosAvaliaram() {
        return qtdAlunosAvaliaram;
    }

    public void setQtdAlunosAvaliaram(int qtdAlunosAvaliaram) {
        this.qtdAlunosAvaliaram = qtdAlunosAvaliaram;
    }

    public float getAvaliacaoGeral() {
        return avaliacaoGeral;
    }

    public void setAvaliacaoGeral(float avaliacaoGeral) {
        this.avaliacaoGeral = avaliacaoGeral;
    }

    public void calcularAvaliacaoGeral(){
        //fazendo uma media apenas a principio, depois a equacao vai mudar
        this.avaliacaoGeral = (this.avaliacaoClareza+this.avaliacaoConteudo+this.avaliacaoDificuldade+this.avaliacaoEsforco) / 4;
    }

    public float getAvaliacaoDificuldade() {
        return avaliacaoDificuldade;
    }

    public void setAvaliacaoDificuldade(float avaliacaoDificuldade) {
        this.avaliacaoDificuldade = avaliacaoDificuldade;
    }

    public float getAvaliacaoClareza() {
        return avaliacaoClareza;
    }

    public void setAvaliacaoClareza(float avaliacaoClareza) {
        this.avaliacaoClareza = avaliacaoClareza;
    }

    public float getAvaliacaoEsforco() {
        return avaliacaoEsforco;
    }

    public void setAvaliacaoEsforco(float avaliacaoEsforco) {
        this.avaliacaoEsforco = avaliacaoEsforco;
    }

    public float getAvaliacaoConteudo() {
        return avaliacaoConteudo;
    }

    public void setAvaliacaoConteudo(float avaliacaoConteudo) {
        this.avaliacaoConteudo = avaliacaoConteudo;
    }

    // os metodos a seguir sao do Parcelable
    protected ProfessorAnterior(Parcel in) {
        nome = in.readParcelable(Professor.class.getClassLoader());
        semestreLecionado = in.readString();
        if (in.readByte() == 0) {
            qtdAlunos = 0;
        } else {
            qtdAlunos = in.readInt();
        }
        if (in.readByte() == 0) {
            qtdAlunosAvaliaram = 0;
        } else {
            qtdAlunosAvaliaram = in.readInt();
        }
        if (in.readByte() == 0) {
            avaliacaoGeral = 0;
        } else {
            avaliacaoGeral = in.readFloat();
        }
        if (in.readByte() == 0) {
            avaliacaoDificuldade = 0;
        } else {
            avaliacaoDificuldade = in.readFloat();
        }
        if (in.readByte() == 0) {
            avaliacaoClareza = 0;
        } else {
            avaliacaoClareza = in.readFloat();
        }
        if (in.readByte() == 0) {
            avaliacaoEsforco = 0;
        } else {
            avaliacaoEsforco = in.readFloat();
        }
        if (in.readByte() == 0) {
            avaliacaoConteudo = 0;
        } else {
            avaliacaoConteudo = in.readFloat();
        }
        /*
        if (in.readByte() == 0) {
            ID = 0;
        } else {
            ID = in.readLong();
        }*/
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(nome);
        dest.writeString(semestreLecionado);
        if (qtdAlunos == 0) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(qtdAlunos);
        }
        if (qtdAlunosAvaliaram == 0) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(qtdAlunosAvaliaram);
        }
        if (avaliacaoGeral == 0) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeFloat(avaliacaoGeral);
        }
        if (avaliacaoDificuldade == 0) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeFloat(avaliacaoDificuldade);
        }
        if (avaliacaoClareza == 0) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeFloat(avaliacaoClareza);
        }
        if (avaliacaoEsforco == 0) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeFloat(avaliacaoEsforco);
        }
        if (avaliacaoConteudo == 0) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeFloat(avaliacaoConteudo);
        }
        /*if (ID == 0) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeLong(ID);
        }
        */
    }

    public static final Creator<ProfessorAnterior> CREATOR = new Creator<ProfessorAnterior>() {
        @Override
        public ProfessorAnterior createFromParcel(Parcel in) {
            return new ProfessorAnterior(in);
        }

        @Override
        public ProfessorAnterior[] newArray(int size) {
            return new ProfessorAnterior[size];
        }
    };

}
