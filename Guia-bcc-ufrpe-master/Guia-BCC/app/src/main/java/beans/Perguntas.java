package beans;

import java.util.ArrayList;

/**
 * Created by Douglas on 24/11/2017.
 */

public class Perguntas {
    private ArrayList<Pergunta> lista_perguntas;

    public Perguntas(){
        this.lista_perguntas = new ArrayList<>();
    }

    public ArrayList<Pergunta> getLista_perguntas() {
        return lista_perguntas;
    }

    public void setLista_perguntas(ArrayList<Pergunta> lista_perguntas) {
        this.lista_perguntas = lista_perguntas;
    }
    
    public void adicionarPergunta(Pergunta pergunta){
        this.lista_perguntas.add(pergunta);
    }

    public Pergunta procurarPergunta(int codigo){
        Pergunta retorno = null;
        for (Pergunta p: this.lista_perguntas) {
            if(p.getCodigo_pergunta() == codigo){
                retorno = p;
            }
        }
        return retorno;
    }
    
    public void removerPergunta(int codigo){
        for (Pergunta p: this.lista_perguntas) {
            if(p.getCodigo_pergunta() == codigo){
                this.lista_perguntas.remove(p);
            }
        }
    }
}
