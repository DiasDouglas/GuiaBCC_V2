package com.ufrpe.bcc.guia_bcc;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import java.util.ArrayList;

import adapters.ListaDeDisciplinasCursadasAdapter;
import beans.Aluno;
import beans.DadosDoAVA;
import beans.DisciplinaCursada;
import beans.DisciplinaDTO;
import beans.connections.Token;

@SuppressLint("ValidFragment")
public class Inicio extends Fragment {

    private static final String nomeInicial ="Inicio";

    private TextView tvNomeUsuario;
    private ImageView ivFotoUsuario;

    private Aluno alunoLogado;
    private DadosDoAVA dadosDoAVA;
  //  private Token tokenAluno;
    private ArrayList<DisciplinaDTO> disciplinaDTOS;

    @SuppressLint("ValidFragment")
    public Inicio(Aluno aluno){
        this.setAlunoLogado(aluno);
        /*this.setDadosDoAVA(dadosDoAVA);
        this.setTokenAluno(token);
        this.setDisciplinaDTOS(disciplinaDTOS);*/

    }


    @Override
    public View onCreateView(LayoutInflater lif, @Nullable ViewGroup container , @Nullable Bundle savedInstanceState){
        View  myView = lif.inflate(R.layout.tab_inicio,container,false);

        tvNomeUsuario = (TextView) myView.findViewById(R.id.tvNomeUsuario);
        ivFotoUsuario = (ImageView) myView.findViewById(R.id.ivFotoUsuario);

        //Verificando se o aluno passado como parâmetro veio como nulo
        if(alunoLogado != null ) {
            //Por enquanto serão utilizadas as disciplinas no arraylist
            //alunoLogado.setDisciplinasCursadas(this.getCursos());
            tvNomeUsuario.setText(alunoLogado.getNomeAluno());
        }
        else {
            //alunoLogado = new Aluno("Ismael", getCursos());
        }


        ListView listaDeCurso = (ListView) myView.findViewById(R.id.lvCursosTelaInicial);

        ListaDeDisciplinasCursadasAdapter adapter = new ListaDeDisciplinasCursadasAdapter(alunoLogado.getDisciplinasCursadas(),this,savedInstanceState);

        listaDeCurso.setAdapter(adapter);

        return myView;
    }

    public Aluno getAlunoLogado() {
        return alunoLogado;
    }

    public void setAlunoLogado(Aluno alunoLogado) {
        if(alunoLogado != null)
            this.alunoLogado = alunoLogado;
        else
            throw new NullPointerException("Aluno logado nulo");
    }

    public DadosDoAVA getLinkImagem() {
        return dadosDoAVA;
    }

    public void setDadosDoAVA(DadosDoAVA dadosDoAVA) {
        if(dadosDoAVA != null)
            this.dadosDoAVA = dadosDoAVA;
        else
            throw new NullPointerException("Aluno logado nulo");
    }

    /*
    public Token getTokenAluno() {
        return tokenAluno;
    }

    public void setTokenAluno(Token tokenAluno) {
        if(tokenAluno != null)
            this.tokenAluno = tokenAluno;
        else
            throw new NullPointerException("Token do aluno, nulo");
    }
    */
    public ArrayList<DisciplinaDTO> getDisciplinaDTOS() {
        return disciplinaDTOS;
    }

    public void setDisciplinaDTOS(ArrayList<DisciplinaDTO> disciplinaDTOS) {
        this.disciplinaDTOS = disciplinaDTOS;
    }

    //Método para carregar a foto do usuário

    @Override
    public void onStart(){
        super.onStart();

        if(ivFotoUsuario != null && dadosDoAVA != null){
            //new CarregarFoto(dadosDoAVA.getUserpictureurl()).execute();

        }
    }



}
