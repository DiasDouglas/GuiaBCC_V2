package com.ufrpe.bcc.guia_bcc;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import adapters.ListaDeDisciplinasCursadasAdapter;
import beans.Aluno;
import beans.DisciplinaCursada;

public class Inicio extends Fragment {

    private static final String nomeInicial ="Inicio";

    private TextView tvNomeUsuario;


    @Override
    public View onCreateView(LayoutInflater lif, @Nullable ViewGroup container , @Nullable Bundle savedInstanceState){
        View  myView = getLayoutInflater(savedInstanceState).inflate(R.layout.tab_inicio,container,false);


        Aluno aluno = new Aluno("Ismael",getCursos());

        ListView listaDeCurso = (ListView) myView.findViewById(R.id.lvCursosTelaInicial);

        ListaDeDisciplinasCursadasAdapter adapter = new ListaDeDisciplinasCursadasAdapter(aluno.getDisciplinasCursadas(),this,savedInstanceState);

        listaDeCurso.setAdapter(adapter);

        return myView;
    }


    ///Método de  testes para retornar disciplina cursadas pelo aluno
    public ArrayList<DisciplinaCursada> getCursos(){
        ArrayList<DisciplinaCursada> lista = new ArrayList<DisciplinaCursada>();
        //supondo que ele tenha apenas essas aulas hoje
        lista.add(new DisciplinaCursada("Arquitetura e organização de computadores","Andre Aziz",4,"25/11/2017","26/11/2017", false,"2017.2"));
        lista.add(new DisciplinaCursada("Matemática Discreta II","Vanilson Burégio",25,"25/11/2017","26/11/2017", false,"2017.2"));

        return lista;
    }

}
