package com.ufrpe.bcc.guia_bcc;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import beans.API;
import beans.Pergunta;
import beans.Perguntas;

/**
 * Created by Douglas on 24/11/2017.
 * /**
 * Edited by Douglas on 21/01/2018.
 */


public class PerguntasAvaliacao extends AppCompatActivity {

    private RadioGroup radioGroup;
    static ArrayList<Pergunta> perguntasProf;
    static ArrayList<Pergunta> perguntasDisc;
    final Perguntas listaDePerguntas = new Perguntas();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            new CarregaPerguntas().execute().get();
        } catch (InterruptedException e) {
            e.printStackTrace();

        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
    @Override
    protected void onStart() {
        super.onStart();
        setContentView(R.layout.activity_perguntas_avaliacao);



        Intent anterior = getIntent();

        if(anterior.getStringExtra("tipo").equals("avaliar_disciplina")){
            // Pegando o numero da questão
            final int numQuestao = anterior.getIntExtra("num_pergunta", 0);
            // Pegando o nome da disciplina e do professor
            String aux;
            if ((anterior.getStringExtra("disciplina") != null) && (anterior.getStringExtra("professor") != null)) {
                aux = anterior.getStringExtra("disciplina") + "\n" + anterior.getStringExtra("professor");
            } else {
                aux = anterior.getStringExtra("disciplinaAvaliada");
            }
            final String tipoAvaliacao = aux;

            // Instanciar perguntas para testes
            listaDePerguntas.setLista_perguntas(perguntasDisc);

            if (numQuestao <= listaDePerguntas.getLista_perguntas().size()) {   // Se não chegou ao fim das questões

                // Carregar perguntas
                TextView txt_vw_numPergunta = (TextView) findViewById(R.id.txt_vw_numPergunta);
                TextView txtvw_pergunta = (TextView) findViewById(R.id.txtvw_pergunta);
                TextView txt_vw_tipoAvaliacao = (TextView) findViewById(R.id.txt_vw_tipoAvaliacao);
                Button btn_cancelar = (Button) findViewById(R.id.btn_cancelar);
                Button btn_continuar = (Button) findViewById(R.id.btn_continuar);
                radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
                RadioButton rb_resposta1 = (RadioButton) findViewById(R.id.rb_resposta1);
                RadioButton rb_resposta2 = (RadioButton) findViewById(R.id.rb_resposta2);
                RadioButton rb_resposta3 = (RadioButton) findViewById(R.id.rb_resposta3);
                RadioButton rb_resposta4 = (RadioButton) findViewById(R.id.rb_resposta4);

                txt_vw_numPergunta.setText(numQuestao + ".");
                txtvw_pergunta.setText((CharSequence) listaDePerguntas.procurarPergunta(numQuestao).getQuestao());
                txt_vw_tipoAvaliacao.setText(tipoAvaliacao);
                rb_resposta1.setText((CharSequence) listaDePerguntas.procurarPergunta(numQuestao).getResposta_1());
                rb_resposta2.setText((CharSequence) listaDePerguntas.procurarPergunta(numQuestao).getResposta_2());
                rb_resposta3.setText((CharSequence) listaDePerguntas.procurarPergunta(numQuestao).getResposta_3());
                rb_resposta4.setText((CharSequence) listaDePerguntas.procurarPergunta(numQuestao).getResposta_4());

                btn_continuar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (radioGroup.getCheckedRadioButtonId() != -1) {
                            int radioButtonID = radioGroup.getCheckedRadioButtonId();
                            RadioButton rd = (RadioButton) findViewById(radioButtonID);
                            Intent i = new Intent(PerguntasAvaliacao.this, PerguntasAvaliacao.class);
                            i.putExtra("num_pergunta", numQuestao + 1);
                            i.putExtra("disciplinaAvaliada", tipoAvaliacao);
                            i.putExtra("tipo", "avaliar_disciplina");
                            startActivity(i);
                        } else {
                            Toast alerta = Toast.makeText(PerguntasAvaliacao.this, R.string.nenhuma_opcao_selecionada, Toast.LENGTH_LONG);
                            alerta.show();
                        }
                    }
                });
                btn_cancelar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent i = new Intent(PerguntasAvaliacao.this, CamposUsuario.class);
                        i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(i);
                    }
                });
            } else {
                Intent i = new Intent(PerguntasAvaliacao.this, Agradecimento.class);
                startActivity(i);
            }
        } else if(anterior.getStringExtra("tipo").equals("avaliar_professor")){
            // Pegando o numero da questão
            final int numQuestao = anterior.getIntExtra("num_pergunta", 0);

            // Pegando o nome da disciplina e do professor
            String aux;
            if ((anterior.getStringExtra("disciplina") != null) && (anterior.getStringExtra("professor") != null)) {
                aux = anterior.getStringExtra("professor") + "\n" + anterior.getStringExtra("disciplina");
            } else {
                aux = anterior.getStringExtra("professorAvaliado");
            }
            final String tipoAvaliacao = aux;

            // Instanciar perguntas para testes
            final Perguntas listaDePerguntas = new Perguntas();
            listaDePerguntas.setLista_perguntas(perguntasProf);

            if (numQuestao <= listaDePerguntas.getLista_perguntas().size()) {   // Se não chegou ao fim das questões

                // Carregar perguntas
                TextView txt_vw_numPergunta = (TextView) findViewById(R.id.txt_vw_numPergunta);
                TextView txtvw_pergunta = (TextView) findViewById(R.id.txtvw_pergunta);
                TextView txt_vw_tipoAvaliacao = (TextView) findViewById(R.id.txt_vw_tipoAvaliacao);
                Button btn_cancelar = (Button) findViewById(R.id.btn_cancelar);
                Button btn_continuar = (Button) findViewById(R.id.btn_continuar);
                radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
                RadioButton rb_resposta1 = (RadioButton) findViewById(R.id.rb_resposta1);
                RadioButton rb_resposta2 = (RadioButton) findViewById(R.id.rb_resposta2);
                RadioButton rb_resposta3 = (RadioButton) findViewById(R.id.rb_resposta3);
                RadioButton rb_resposta4 = (RadioButton) findViewById(R.id.rb_resposta4);

                txt_vw_numPergunta.setText(numQuestao + ".");
                txtvw_pergunta.setText((CharSequence) listaDePerguntas.procurarPergunta(numQuestao).getQuestao());
                txt_vw_tipoAvaliacao.setText(tipoAvaliacao);
                rb_resposta1.setText((CharSequence) listaDePerguntas.procurarPergunta(numQuestao).getResposta_1());
                rb_resposta2.setText((CharSequence) listaDePerguntas.procurarPergunta(numQuestao).getResposta_2());
                rb_resposta3.setText((CharSequence) listaDePerguntas.procurarPergunta(numQuestao).getResposta_3());
                rb_resposta4.setText((CharSequence) listaDePerguntas.procurarPergunta(numQuestao).getResposta_4());

                btn_continuar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (radioGroup.getCheckedRadioButtonId() != -1) {
                            int radioButtonID = radioGroup.getCheckedRadioButtonId();
                            RadioButton rd = (RadioButton) findViewById(radioButtonID);
                            Intent i = new Intent(PerguntasAvaliacao.this, PerguntasAvaliacao.class);
                            i.putExtra("num_pergunta", numQuestao + 1);
                            i.putExtra("professorAvaliado", tipoAvaliacao);
                            i.putExtra("tipo", "avaliar_professor");
                            startActivity(i);
                        } else {
                            Toast alerta = Toast.makeText(PerguntasAvaliacao.this, R.string.nenhuma_opcao_selecionada, Toast.LENGTH_LONG);
                            alerta.show();
                        }
                    }
                });
                btn_cancelar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent i = new Intent(PerguntasAvaliacao.this, CamposUsuario.class);
                        startActivity(i);
                    }
                });
            } else {
                Intent i = new Intent(PerguntasAvaliacao.this, Agradecimento.class);
                startActivity(i);
            }
        }
    }

    static class CarregaPerguntas extends AsyncTask<Void, Void, ArrayList<Pergunta>> {

        @Override
        protected ArrayList<Pergunta> doInBackground(Void... voids) {
            ArrayList<Pergunta> perguntas = new ArrayList<>();
            try {
                URL url = new URL(API.URL_API_GUIA_BCC+"perguntas");
                HttpURLConnection conexao = (HttpURLConnection) url.openConnection();
                conexao.setRequestMethod("GET");
                InputStream fluxoDeDados = conexao.getInputStream();
                Log.i("", fluxoDeDados.toString());
                BufferedReader reader = null;

                try {
                    reader = new BufferedReader(new InputStreamReader(fluxoDeDados));
                    StringBuffer sBuffer = new StringBuffer();
                    String linha = "";
                    while ((linha = reader.readLine()) != null) {
                        sBuffer.append(linha);
                    }

                    JSONArray arrayJson = new JSONArray(sBuffer.toString());
                    for (int i = 0; i < arrayJson.length(); i++) {
                        JSONObject objetoJson = arrayJson.getJSONObject(i);
                        Pergunta pergunta = new Pergunta();
                        pergunta.setCodigo_pergunta(objetoJson.getInt("codigoPergunta"));
                        pergunta.setQuestao(objetoJson.getString("questao"));
                        pergunta.setResposta_1(objetoJson.getString("resposta1"));
                        pergunta.setResposta_2(objetoJson.getString("resposta2"));
                        pergunta.setResposta_3(objetoJson.getString("resposta3"));
                        pergunta.setResposta_4(objetoJson.getString("resposta4"));
                        pergunta.setTipo(objetoJson.getInt("tipo"));
                        pergunta.setVersao(objetoJson.getInt("versao"));
                        perguntas.add(pergunta);
                    }

                    perguntasProf = new ArrayList<>();
                    perguntasDisc = new ArrayList<>();

                    for (Pergunta p : perguntas) {
                        if (p.getTipo() == 0) {
                            perguntasDisc.add(p);
                        } else if (p.getTipo() == 1) {
                            perguntasProf.add(p);
                        }
                        Log.d("Pergunta: ", p.getQuestao());
                    }

                } catch (IOException e) {
                    Log.e("GUIA BCC", "Erro: " + e.getMessage());
                    e.printStackTrace();
                } finally {
                    if (reader != null) {
                        try {
                            reader.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (Exception e) {
                Log.e("GUIA BCC", "Erro: " + e.getMessage());
                e.printStackTrace();
            }
            return perguntas;
        }

    }

}
