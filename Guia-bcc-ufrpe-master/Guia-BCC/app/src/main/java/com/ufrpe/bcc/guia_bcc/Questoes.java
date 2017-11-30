package com.ufrpe.bcc.guia_bcc;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;

import adapters.ListaDeDisciplinasAdapter;
import beans.Disciplina;
import butterknife.BindView;

public class Questoes extends Fragment {

    private static final String nomeInicial ="Questoes";

    //@BindView(R.id.lvListaDisciplinasBanco)
    //ListView disciplinas;

    @Override
    public View onCreateView(LayoutInflater lif, @Nullable ViewGroup container , @Nullable Bundle savedInstanceState){
        View  myView = getLayoutInflater(savedInstanceState).inflate(R.layout.tab_questoes,container,false);

        ListView disciplinas = (ListView) myView.findViewById(R.id.lvListaDisciplinasBanco);

        ListaDeDisciplinasAdapter adapterLista = new ListaDeDisciplinasAdapter(getCursos(),this,savedInstanceState);

        disciplinas.setAdapter(adapterLista);

        return myView;
    }

    ///Método de  testes para retornar todas as disciplinas cadastradas
    public ArrayList<Disciplina> getCursos(){
        ArrayList<Disciplina> lista = new ArrayList<Disciplina>();

        lista.add(new Disciplina("Arquitetura e organização de computadores",new ArrayList<String>(){{add("Professor 1");add("Professor 2");add("Professor 3");}},
                15,"2017.1",85.5f,30.9f,90.9f,88.6f,8,"24/11/2017"));
        lista.add(new Disciplina("Matemática Discreta II",new ArrayList<String>(){{add("Professor 1");add("Professor 2");add("Professor 3");}},
                25,"2017.1",45f,48f,73f,69f,1,"20/08/2017"));
        lista.add(new Disciplina("Sistemas Distribuidos",new ArrayList<String>(){{add("Professor 1");add("Professor 2");add("Professor 3");}},
                18,"2017.1",28f,62f,36f,42f,5,"24/11/2017"));
        lista.add(new Disciplina("Algorítmos e estruturas de dados",new ArrayList<String>(){{add("Professor 1");add("Professor 2");add("Professor 3");}},
                40,"2017.1",75f,49f,75f,70f,3,"24/11/2017"));
        lista.add(new Disciplina("Engenharia de software",new ArrayList<String>(){{add("Professor 1");add("Professor 2");add("Professor 3");}},
                35,"2017.1",60f,75f,70f,60f,8,"24/11/2017"));
        lista.add(new Disciplina("Introdução a programação I",new ArrayList<String>(){{add("Professor 1");add("Professor 2");add("Professor 3");}},
                35,"2017.1",40f,74f,58f,49f,9,"24/11/2017"));
        lista.add(new Disciplina("Introdução a programação II",new ArrayList<String>(){{add("Professor 1");add("Professor 2");add("Professor 3");}},
                35,"2017.1",55f,70f,69f,58f,4,"24/11/2017"));
        lista.add(new Disciplina("Redes de computadores",new ArrayList<String>(){{add("Professor 1");add("Professor 2");add("Professor 3");}},
                35,"2017.1",65f,63f,74f,72f,16,"24/11/2017"));
        lista.add(new Disciplina("Teoria da computação",new ArrayList<String>(){{add("Professor 1");add("Professor 2");add("Professor 3");}},
                35,"2017.1",62f,50f,76f,68f,7,"24/11/2017"));
        lista.add(new Disciplina("Projeto e Análise de Algorítmos",new ArrayList<String>(){{add("Professor 1");add("Professor 2");add("Professor 3");}},
                35,"2017.1",58f,20f,66f,52f,10,"24/11/2017"));
        lista.add(new Disciplina("Circuitos digitais",new ArrayList<String>(){{add("Professor 1");add("Professor 2");add("Professor 3");}},
                35,"2017.1",68f,61f,71f,69f,6,"24/11/2017"));
        lista.add(new Disciplina("Iteligência Artificial",new ArrayList<String>(){{add("Professor 1");add("Professor 2");add("Professor 3");}},
                35,"2017.1",49f,55f,50f,53f,8,"24/11/2017"));

        return lista;
    }

}
