package com.ufrpe.bcc.guia_bcc;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

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

import beans.API;
import beans.Disciplina;
import beans.Professor;
import beans.ProfessorAnterior;

/**
 * Created by Fabio on 08/12/2017.
 * Updated by Ismael on 22/01/2018
 * Adicionando async task para pegar adisciplina do servidor
 * Mudando atributo disciplinaId para long
 */

public class DetalheDisciplina extends AppCompatActivity {
    public static final String EXTRA_ID_DISCIPLINA = "id_disciplina";

    private long disciplinaId;
    TextView mNomeDisciplina;
    TextView mQtdMediaAlunos;
    TextView mUltimoSemestre;
    TextView mDificuldade;
    TextView mConteudo;
    TextView mClareza;
    TextView mEsforco;
    Button mBtnProfessores;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhe_disciplina);

        this.mNomeDisciplina = (TextView) findViewById(R.id.tvNomeDisciplina);
        this.mQtdMediaAlunos = (TextView) findViewById(R.id.tvQtdMediaAlunos);
        this.mUltimoSemestre = (TextView) findViewById(R.id.tvUltimoSemestre);
        this.mDificuldade = (TextView) findViewById(R.id.tvDificuldade);
        this.mConteudo = (TextView) findViewById(R.id.tvConteudo);
        this.mClareza = (TextView) findViewById(R.id.tvClareza);
        this.mEsforco = (TextView) findViewById(R.id.tvEsforco);
        this.mBtnProfessores = (Button) findViewById(R.id.btnProfessores);

        this.disciplinaId = getIntent().getLongExtra(EXTRA_ID_DISCIPLINA,0);

        //Executando A
        new AccessDisciplina().execute();

        //int qtd = disciplinaId.getQtdMediaAlunos();


    }

    private class AccessDisciplina extends AsyncTask<Void,Void,Disciplina>{

        @Override
        protected Disciplina doInBackground(Void... voids) {
            Disciplina retorno = null;

            try {
                URL url = new URL(API.URL_API_GUIA_BCC + "disciplina/"+ disciplinaId);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");
                InputStreamReader isr = new InputStreamReader(connection.getInputStream());
                //BufferedReader buff = new BufferedReader(isr);

                Gson gson = new Gson();
                retorno = gson.fromJson(isr,Disciplina.class);


            }
            catch (ConnectException e){
                Log.e("Exeption de conexao",getString(R.string.erroConexao));
            }
            catch (ProtocolException e){
                Log.e("Exeption de protocolo",e.getMessage());
            }
            catch (MalformedURLException e){
                Log.e("Exeption de URL",e.getMessage());
            }
            catch(IOException e){
                e.printStackTrace();
            }
            catch (Exception e){
                e.printStackTrace();
            }



            return retorno;
        }

        @Override                   //verificar depis
        protected void onPostExecute(Disciplina disciplina){
            if(disciplina != null){
                DetalheDisciplina.this.mNomeDisciplina.setText(disciplina.getNomeDisciplina());
                DetalheDisciplina.this.mQtdMediaAlunos.setText(disciplina.getQtdMediaAlunos()+"");
                DetalheDisciplina.this.mUltimoSemestre.setText(disciplina.getUltimoSemestre());
                DetalheDisciplina.this.mDificuldade.setText(disciplina.getAvaliacaoDificuldade()+"");
                DetalheDisciplina.this.mConteudo.setText(disciplina.getAvaliacaoConteudo()+"");
                DetalheDisciplina.this.mClareza.setText(disciplina.getAvaliacaoClareza()+"");
                DetalheDisciplina.this.mEsforco.setText(disciplina.getAvaliacaoEsforco()+"");

                final ArrayList<ProfessorAnterior> lista = disciplina.getProfessoresAnteriores();

                mBtnProfessores.setOnClickListener(new View.OnClickListener(){
                    @Override
                    public void onClick(View v){
                        Intent intent = new Intent(DetalheDisciplina.this,ListaProfessores.class);
                        intent.putExtra(ListaProfessores.EXTRA_PROFESSORES, lista);
                        startActivity(intent);
                    }
                });
            }
        }

    }

    /*
    private Disciplina getDisciplina(long ID){
        Disciplina retorno = null;
        //metodo que vai passar o id que vai vir da tela anterior e vai retornar a disciplinaId
        //depois esses arraylists vão sair e apenas vai fazer uma requisicao ao servidor

        ProfessorAnterior aziz = new ProfessorAnterior(1,"Aziz (tio lu para os intimos)", "2017.1", 5, 3, 90f, 60f, 92f, 85f);
        ProfessorAnterior vanilson = new ProfessorAnterior(2,"Vanilson (vanvan, homao da porra)", "2017.1", 30, 25, 50f, 95f, 80f, 75f);
        ProfessorAnterior andreC = new ProfessorAnterior(3,"Andre Camara (por onde anda?)", "2016.2", 30, 25, 65f, 70f, 80f, 75f);
        ProfessorAnterior obionor = new ProfessorAnterior(4,"Obionor (tio bio, projeto </3)", "2017.1", 40, 30, 62f, 80f, 82f, 78f);
        ProfessorAnterior jeane = new ProfessorAnterior(5,"Jeane (jeje, nao leu cormem :'( )", "2017.1", 15, 10, 70f, 10f, 70f, 70f);
        //cansei de fazer mais professores

        ArrayList<ProfessorAnterior> profs1 = new ArrayList<>();
        ArrayList<ProfessorAnterior> profs2 = new ArrayList<>();
        ArrayList<ProfessorAnterior> profs3 = new ArrayList<>();
        ArrayList<ProfessorAnterior> profs4 = new ArrayList<>();
        //profs1
        profs1.add(aziz);
        profs1.add(vanilson);
        profs1.add(andreC);
        profs1.add(obionor);
        //profs2
        profs2.add(jeane);
        profs2.add(andreC);
        profs2.add(aziz);
        profs2.add(vanilson);
        //profs3
        profs3.add(vanilson);
        profs3.add(obionor);
        //profs4
        profs4.add(aziz);
        profs4.add(jeane);


        ArrayList<Disciplina> lista = new ArrayList<Disciplina>();
        lista.add(new Disciplina(1,"Arquitetura e organização de computadores ",profs1,15,"2017.1",85.5f,
                30.9f,90.9f,88.6f,8,"24/11/2017"));
        lista.add(new Disciplina(2,"Matemática Discreta II",profs2,25,"2017.1",45f,48f,
                73f,69f,1,"20/08/2017"));
        lista.add(new Disciplina(3,"Sistemas Distribuidos",profs3,
                18,"2017.1",28f,62f,36f,42f,5,"24/11/2017"));
        lista.add(new Disciplina(4,"Algorítmos e estruturas de dados",profs4,
                40,"2017.1",75f,49f,75f,70f,3,"24/11/2017"));
        lista.add(new Disciplina(5,"Engenharia de software",profs1,
                35,"2017.1",60f,75f,70f,60f,8,"24/11/2017"));
        lista.add(new Disciplina(6,"Introdução a programação I",profs2,
                35,"2017.1",40f,74f,58f,49f,9,"24/11/2017"));
        lista.add(new Disciplina(7,"Introdução a programação II",profs3,
                35,"2017.1",55f,70f,69f,58f,4,"24/11/2017"));
        lista.add(new Disciplina(8,"Redes de computadores",profs4,
                35,"2017.1",65f,63f,74f,72f,16,"24/11/2017"));
        lista.add(new Disciplina(9,"Teoria da computação",profs1,
                35,"2017.1",62f,50f,76f,68f,7,"24/11/2017"));
        lista.add(new Disciplina(10,"Projeto e Análise de Algorítmos",profs4,
                35,"2017.1",58f,20f,66f,52f,10,"24/11/2017"));
        lista.add(new Disciplina(11,"Circuitos digitais",profs3,
                35,"2017.1",68f,61f,71f,69f,6,"24/11/2017"));
        lista.add(new Disciplina(12,"Iteligência Artificial",profs1,
                35,"2017.1",49f,55f,50f,53f,8,"24/11/2017"));
        for (Disciplina aux:lista) {
            if(aux.getID() == ID){
                retorno = aux;
                break;
            }
        }
        return retorno;
    }*/

}
