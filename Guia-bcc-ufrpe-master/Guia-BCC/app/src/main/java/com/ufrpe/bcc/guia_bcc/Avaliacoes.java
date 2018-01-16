package com.ufrpe.bcc.guia_bcc;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

import adapters.ListaDeDisciplinasCursadasAdapter;
import beans.DisciplinaCursada;

public class Avaliacoes extends Fragment {

    private static final String nomeInicial ="Avaliacoes";
    SectionsPageAdapter mSpa;


    @Override
    public View onCreateView(LayoutInflater lif, @Nullable final ViewGroup container , @Nullable final Bundle savedInstanceState){
        View  myView = getLayoutInflater(savedInstanceState).inflate(R.layout.tab_avaliacoes,container,false);

        ListView lvListaDisciplinasGeral = (ListView) myView.findViewById(R.id.lvListaDisciplinasGeral);

        ListaDeDisciplinasCursadasAdapter adapterLista = new ListaDeDisciplinasCursadasAdapter(getCursos(),this,savedInstanceState);

        lvListaDisciplinasGeral.setAdapter(adapterLista);


        lvListaDisciplinasGeral.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            // Pra ir pra tela de avaliações
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int posicao, long id) {
                /*
                Intent intent = new Intent(getActivity(), PerguntasAvaliacao.class);
                intent.putExtra("num_pergunta", 1);
                DisciplinaCursada disciplina = (DisciplinaCursada)adapterView.getItemAtPosition(posicao);
                intent.putExtra("avaliado", disciplina.getNomeDisciplina() + "\n" + disciplina.getNomeProfessor());
                getActivity().startActivity(intent);
                */


                // Create fragment and give it an argument specifying the article it should show
                DetalheDisciplinaCursada newFragment = new DetalheDisciplinaCursada();
                Bundle args = new Bundle();
                DisciplinaCursada disciplina = (DisciplinaCursada)adapterView.getItemAtPosition(posicao);
                args.putString("disciplina", disciplina.getNomeDisciplina());
                args.putString("professor", disciplina.getNomeProfessor());
                newFragment.setArguments(args);

                android.support.v4.app.FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();

                // Replace whatever is in the fragment_container view with this fragment,
                // and add the transaction to the back stack so the user can navigate back
                transaction.replace(R.id.tab_avaliacoes, newFragment);
                transaction.addToBackStack(null);

                // Commit the transaction
                transaction.commit();
            }
        });

        return myView;

    }

    ///Método de  testes para retornar disciplina cursadas pelo aluno
    public ArrayList<DisciplinaCursada> getCursos(){
        ArrayList<DisciplinaCursada> lista = new ArrayList<DisciplinaCursada>();
        lista.add(new DisciplinaCursada("Arquitetura e organização de computadores","Andre Aziz",4,"25/11/2017","26/11/2017", false,"2017.2"));
        lista.add(new DisciplinaCursada("Matemática Discreta II","Vanilson Burégio",25,"25/11/2017","26/11/2017", false,"2017.2"));
        lista.add(new DisciplinaCursada("Sistemas Distribuidos","Fernando Aires",15,"25/11/2017","26/11/2017", false,"2017.2"));
        lista.add(new DisciplinaCursada("Algorítmos e estruturas de dados","Luciano",10,"25/11/2017","26/11/2017", false,"2017.2"));
        lista.add(new DisciplinaCursada("Engenharia de software","Marcelo Marinho",35,"25/11/2017","26/11/2017", false,"2017.2"));
        /*
        deixei com que ele tivesse pagando apenas 5 disciplinas
        lista.add(new DisciplinaCursada("Matemática Discreta I","Felipe Cordeiro",40,1.1f));
        lista.add(new DisciplinaCursada("Introdução a programação I","Péricles Miranda",40,10.0f));
        lista.add(new DisciplinaCursada("Introdução a programação II","Leandro Marques",34,9.5f));
        lista.add(new DisciplinaCursada("Redes de computadores","Obionor Nóbrega",50,8.5f));
        lista.add(new DisciplinaCursada("Teoria da computação","Adeniltom Silva",14,4.5f));
        lista.add(new DisciplinaCursada("Projeto e Análise de Algorítmos","Jeane",16,9.5f));
        lista.add(new DisciplinaCursada("Circuitos digitais","Abner",60,8.5f));
        lista.add(new DisciplinaCursada("Iteligência Artificial","Valmir Nogueira",30,4.5f));*/

        return lista;
    }





}
