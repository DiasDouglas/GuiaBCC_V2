package beans;

/**
 * Created by Douglas on 24/11/2017.
 */

public class Pergunta {
    private int codigo_pergunta;
    private String questao;
    private String Resposta_1;
    private String Resposta_2;
    private String Resposta_3;
    private String Resposta_4;

    public Pergunta(int codigo_pergunta, String questao, String Resposta_1, String Resposta_2, String Resposta_3,
                    String Resposta_4){
        this.codigo_pergunta = codigo_pergunta;
        this.questao = questao;
        this.Resposta_1 = Resposta_1;
        this.Resposta_2 = Resposta_2;
        this.Resposta_3 = Resposta_3;
        this.Resposta_4 = Resposta_4;
    }

    public int getCodigo_pergunta() {
        return codigo_pergunta;
    }

    public void setCodigo_pergunta(int codigo_pergunta) {
        this.codigo_pergunta = codigo_pergunta;
    }

    public String getQuestao() {
        return questao;
    }

    public void setQuestao(String questao) {
        this.questao = questao;
    }

    public String getResposta_1() {
        return Resposta_1;
    }

    public void setResposta_1(String resposta_1) {
        Resposta_1 = resposta_1;
    }

    public String getResposta_2() {
        return Resposta_2;
    }

    public void setResposta_2(String resposta_2) {
        Resposta_2 = resposta_2;
    }

    public String getResposta_3() {
        return Resposta_3;
    }

    public void setResposta_3(String resposta_3) {
        Resposta_3 = resposta_3;
    }

    public String getResposta_4() {
        return Resposta_4;
    }

    public void setResposta_4(String resposta_4) {
        Resposta_4 = resposta_4;
    }

    public void atualizarPergunta(int codigo_pergunta, String questao, String Resposta_1,
                                  String Resposta_2, String Resposta_3, String Resposta_4){
        this.codigo_pergunta = codigo_pergunta;
        this.questao = questao;
        this.Resposta_1 = Resposta_1;
        this.Resposta_2 = Resposta_2;
        this.Resposta_3 = Resposta_3;
        this.Resposta_4 = Resposta_4;
    }

    public boolean equals(Pergunta b){
        boolean retorno = false;
        if(b != null && this.codigo_pergunta == b.codigo_pergunta){
            retorno = true;
        }
        return retorno;
    }

}
