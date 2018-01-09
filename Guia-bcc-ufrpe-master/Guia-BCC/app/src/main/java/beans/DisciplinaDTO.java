package beans;

/**
 * Created by Fabio on 13/12/2017.
 */

public class DisciplinaDTO {
    private long ID;
    private String nome;
    private float avaliacaoGeral;
    private int qtdItens;
    private String ultimaAtt;

    public DisciplinaDTO() {
    }

    public DisciplinaDTO(long ID, String nome, float avaliacaoGeral, int qtdItens, String ultimaAtt) {
        this.setID(ID);
        this.setNome(nome);
        this.setAvaliacaoGeral(avaliacaoGeral);
        this.setQtdItens(qtdItens);
        this.setUltimaAtt(ultimaAtt);
    }

    public long getID() {
        return ID;
    }

    public void setID(long ID) {
        this.ID = ID;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public float getAvaliacaoGeral() {
        return avaliacaoGeral;
    }

    public void setAvaliacaoGeral(float avaliacaoGeral) {
        this.avaliacaoGeral = avaliacaoGeral;
    }

    public int getQtdItens() {
        return qtdItens;
    }

    public void setQtdItens(int qtdItens) {
        this.qtdItens = qtdItens;
    }

    public String getUltimaAtt() {
        return ultimaAtt;
    }

    public void setUltimaAtt(String ultimaAtt) {
        this.ultimaAtt = ultimaAtt;
    }
}
