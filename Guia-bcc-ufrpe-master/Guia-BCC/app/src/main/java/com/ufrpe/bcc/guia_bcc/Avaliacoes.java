package com.ufrpe.bcc.guia_bcc;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
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
import beans.DisciplinaDTO;
import beans.Professor;

@SuppressLint("ValidFragment")
public class Avaliacoes extends Fragment {

    private static final String nomeInicial ="Avaliacoes";
    SectionsPageAdapter mSpa;
    private ArrayList<DisciplinaDTO> disciplinaDTOS;

    @SuppressLint("ValidFragment")
    public  Avaliacoes(ArrayList<DisciplinaDTO> disciplinaDTOS){
        this.disciplinaDTOS = disciplinaDTOS;
    }

    @Override
    public View onCreateView(LayoutInflater lif, @Nullable final ViewGroup container , @Nullable final Bundle savedInstanceState){
        View  myView = lif.inflate(R.layout.tab_avaliacoes,container,false);

        ListView lvListaDisciplinasGeral = (ListView) myView.findViewById(R.id.lvListaDisciplinasGeral);

        ListaDeDisciplinasCursadasAdapter adapterLista = new ListaDeDisciplinasCursadasAdapter(this.disciplinaDTOS,this,savedInstanceState);

        lvListaDisciplinasGeral.setAdapter(adapterLista);


        lvListaDisciplinasGeral.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            // Pra ir pra tela de avaliações
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int posicao, long id) {



                // Create fragment and give it an argument specifying the article it should show
                DetalheDisciplinaCursada newFragment = new DetalheDisciplinaCursada();
                Bundle args = new Bundle();
                DisciplinaDTO disciplina = (DisciplinaDTO)adapterView.getItemAtPosition(posicao);
                args.putString("disciplina_nome", disciplina.getNome());
                args.putLong("disciplina_id", disciplina.getID());
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

    private class ConectarDisciplinaProfessor extends AsyncTask<Void,Void, Professor>{

        private static final String URL_SPRING_REQUEST = "http://192.168.15.12:3080/guiabcc/disciplina/";
        private static final String URL_SPRING_REQUEST_POSTFIX = "/professores";

        private String id;

        ConectarDisciplinaProfessor(String id){
            this.setId(id);
        }

        @Override
        protected Professor doInBackground(Void... voids) {
            return null;
        }

        @Override
        protected  void onPostExecute(Professor proff){

        }

        ///Getters and setters

        public String getId() {
            return id;
        }

        public void setId(String id) {
            if(id != null)
                this.id = id;
            else
                throw new NullPointerException("Id nulo");
        }
    }
}
