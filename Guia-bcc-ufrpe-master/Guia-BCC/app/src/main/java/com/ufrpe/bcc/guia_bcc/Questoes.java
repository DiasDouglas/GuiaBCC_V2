package com.ufrpe.bcc.guia_bcc;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

import adapters.ListaDeDisciplinasAdapter;
import beans.Disciplina;
import beans.DisciplinaCursada;
import beans.DisciplinaDTO;
import butterknife.BindView;

public class Questoes extends Fragment {

    private static final String nomeInicial ="Questoes";

    //@BindView(R.id.lvListaDisciplinasBanco)
    //ListView disciplinas;

    @Override
    public View onCreateView(LayoutInflater lif, @Nullable ViewGroup container , @Nullable Bundle savedInstanceState){
        View  myView = getLayoutInflater(savedInstanceState).inflate(R.layout.tab_questoes,container,false);

        ListView disciplinas = (ListView) myView.findViewById(R.id.lvListaDisciplinasBanco);

        ListaDeDisciplinasAdapter adapterLista = new ListaDeDisciplinasAdapter(getDisciplinas(),this,savedInstanceState);

        disciplinas.setAdapter(adapterLista);

        disciplinas.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int posicao, long id) {
                Intent newIntent = new Intent(getActivity(), DetalheDisciplina.class);
                DisciplinaDTO disciplina = (DisciplinaDTO)adapterView.getItemAtPosition(posicao);

                newIntent.putExtra(DetalheDisciplina.EXTRA_ID_DISCIPLINA, disciplina.getID());
                getActivity().startActivity(newIntent);
            }
        });



        return myView;
    }

    ///Método de  testes para retornar todas as disciplinas cadastradas
    public ArrayList<DisciplinaDTO> getDisciplinas(){
        ArrayList<DisciplinaDTO> lista = new ArrayList<DisciplinaDTO>();

        lista.add(new DisciplinaDTO(1,"Arquitetura e organização de computadores",85.5f,8,"24/11/2017"));
        lista.add(new DisciplinaDTO(2,"Matemática Discreta II",45f,1,"20/08/2017"));
        lista.add(new DisciplinaDTO(3,"Sistemas Distribuidos",28f,5,"24/11/2017"));
        lista.add(new DisciplinaDTO(4,"Algorítmos e estruturas de dados",75f,3,"24/11/2017"));
        lista.add(new DisciplinaDTO(5,"Engenharia de software",60f,8,"24/11/2017"));
        lista.add(new DisciplinaDTO(6,"Introdução a programação I",40f,9,"24/11/2017"));
        lista.add(new DisciplinaDTO(7,"Introdução a programação II",55f,4,"24/11/2017"));
        lista.add(new DisciplinaDTO(8,"Redes de computadores",65f,16,"24/11/2017"));
        lista.add(new DisciplinaDTO(9,"Teoria da computação",62f,7,"24/11/2017"));
        lista.add(new DisciplinaDTO(10,"Projeto e Análise de Algorítmos",58f,10,"24/11/2017"));
        lista.add(new DisciplinaDTO(11,"Circuitos digitais",68f,6,"24/11/2017"));
        lista.add(new DisciplinaDTO(12,"Iteligência Artificial",49f,8,"24/11/2017"));

        /*
        lista.add(new Disciplina(1,"Arquitetura e organização de computadores",new ArrayList<String>(){{add("Professor 1");add("Professor 2");add("Professor 3");}},
                15,"2017.1",85.5f,30.9f,90.9f,88.6f,8,"24/11/2017"));
        lista.add(new Disciplina(2,"Matemática Discreta II",new ArrayList<String>(){{add("Professor 1");add("Professor 2");add("Professor 3");}},
                25,"2017.1",45f,48f,73f,69f,1,"20/08/2017"));
        lista.add(new Disciplina(3,"Sistemas Distribuidos",new ArrayList<String>(){{add("Professor 1");add("Professor 2");add("Professor 3");}},
                18,"2017.1",28f,62f,36f,42f,5,"24/11/2017"));
        lista.add(new Disciplina(4,"Algorítmos e estruturas de dados",new ArrayList<String>(){{add("Professor 1");add("Professor 2");add("Professor 3");}},
                40,"2017.1",75f,49f,75f,70f,3,"24/11/2017"));
        lista.add(new Disciplina(5,"Engenharia de software",new ArrayList<String>(){{add("Professor 1");add("Professor 2");add("Professor 3");}},
                35,"2017.1",60f,75f,70f,60f,8,"24/11/2017"));
        lista.add(new Disciplina(6,"Introdução a programação I",new ArrayList<String>(){{add("Professor 1");add("Professor 2");add("Professor 3");}},
                35,"2017.1",40f,74f,58f,49f,9,"24/11/2017"));
        lista.add(new Disciplina(7,"Introdução a programação II",new ArrayList<String>(){{add("Professor 1");add("Professor 2");add("Professor 3");}},
                35,"2017.1",55f,70f,69f,58f,4,"24/11/2017"));
        lista.add(new Disciplina(8,"Redes de computadores",new ArrayList<String>(){{add("Professor 1");add("Professor 2");add("Professor 3");}},
                35,"2017.1",65f,63f,74f,72f,16,"24/11/2017"));
        lista.add(new Disciplina(9,"Teoria da computação",new ArrayList<String>(){{add("Professor 1");add("Professor 2");add("Professor 3");}},
                35,"2017.1",62f,50f,76f,68f,7,"24/11/2017"));
        lista.add(new Disciplina(10,"Projeto e Análise de Algorítmos",new ArrayList<String>(){{add("Professor 1");add("Professor 2");add("Professor 3");}},
                35,"2017.1",58f,20f,66f,52f,10,"24/11/2017"));
        lista.add(new Disciplina(11,"Circuitos digitais",new ArrayList<String>(){{add("Professor 1");add("Professor 2");add("Professor 3");}},
                35,"2017.1",68f,61f,71f,69f,6,"24/11/2017"));
        lista.add(new Disciplina(12,"Iteligência Artificial",new ArrayList<String>(){{add("Professor 1");add("Professor 2");add("Professor 3");}},
                35,"2017.1",49f,55f,50f,53f,8,"24/11/2017"));
        */
        return lista;
    }

}
