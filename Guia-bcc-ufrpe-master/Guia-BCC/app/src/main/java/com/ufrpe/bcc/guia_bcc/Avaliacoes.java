package com.ufrpe.bcc.guia_bcc;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ConnectException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;

import adapters.ListaDeDisciplinasCursadasAdapter;
import beans.Disciplina;
import beans.DisciplinaDTO;

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

                DisciplinaDTO disciplina = (DisciplinaDTO)adapterView.getItemAtPosition(posicao);
                new ConectarDisciplinaProfessor(disciplina).execute();

            }
        });

        return myView;

    }

    private class ConectarDisciplinaProfessor extends AsyncTask<Void,Void, Disciplina>{

        private static final String URL_SPRING_REQUEST = "http://192.168.15.12:3080/guiabcc/disciplina/";
        private static final String URL_SPRING_REQUEST_POSTFIX = "/professores";

        private DisciplinaDTO disciplina;

        ConectarDisciplinaProfessor(DisciplinaDTO id){
            this.setDisciplinaDTO(id);
        }

        @Override
        protected Disciplina doInBackground(Void... voids) {
            Disciplina retorno = null;
            try{
                URL url = new URL(URL_SPRING_REQUEST+this.disciplina.getID());
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");
                InputStreamReader isr = new InputStreamReader(connection.getInputStream());
                BufferedReader buffLeitor = new BufferedReader(isr);

                Gson gson = new Gson();

                retorno = gson.fromJson(isr,Disciplina.class);

            }
            catch (ConnectException e){
                Log.e("Erro ao conectar:",getString(R.string.erroConexao));
            }
            catch (MalformedURLException e){
                Log.e("Erro de URL:",e.getMessage());

            }
            catch (ProtocolException e){
                Log.e("Erro de Protocolo:",e.getMessage());
            }
            catch (IOException e){
                Log.e("Erro de IO:",e.getMessage());
            }


            return retorno;
        }

        //Após executada e requisição, cria um novo fragment
        @Override
        protected  void onPostExecute(Disciplina disciplina){
            // Create fragment and give it an argument specifying the article it should show
            DetalheDisciplinaCursada newFragment = new DetalheDisciplinaCursada();
            Bundle args = new Bundle();

            args.putString("disciplina_nome", disciplina.getNomeDisciplina());
            args.putParcelable("professor_disciplina", disciplina.getProfessoresAnteriores().get(disciplina.getProfessoresAnteriores().size()-1));
            args.putParcelable("disciplina",disciplina);
            newFragment.setArguments(args);

            android.support.v4.app.FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();

            // Replace whatever is in the fragment_container view with this fragment,
            // and add the transaction to the back stack so the user can navigate back
            transaction.replace(R.id.tab_avaliacoes, newFragment);
            transaction.addToBackStack(null);

            // Commit the transaction
            transaction.commit();
        }

        ///Getters and setters

        public DisciplinaDTO getDisciplina() {
            return disciplina;
        }

        public void setDisciplinaDTO(DisciplinaDTO id) {
            if(id != null)
                this.disciplina = id;
            else
                throw new NullPointerException("Id nulo");
        }
    }
}
