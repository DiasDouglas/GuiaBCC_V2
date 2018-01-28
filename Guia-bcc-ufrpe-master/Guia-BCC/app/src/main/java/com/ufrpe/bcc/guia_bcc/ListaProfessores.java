package com.ufrpe.bcc.guia_bcc;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ExpandableListView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.net.ConnectException;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import adapters.ListaDeProfessoresAdapter;
import beans.API;
import beans.Disciplina;
import beans.ProfessorAnterior;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Fabio on 13/12/2017.
 */

public class ListaProfessores extends AppCompatActivity {

    public static final String EXTRA_DISCIPLINA_ID = "id_disciplina";
    private long disciplinaID;
    ArrayList<ProfessorAnterior> professores;
    ListaDeProfessoresAdapter adapter;
    ExpandableListView elvProfessores;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_professores);
        Log.i("lista", "lista");
        elvProfessores = (ExpandableListView) findViewById(R.id.elvProfessores);

        disciplinaID = getIntent().getLongExtra(EXTRA_DISCIPLINA_ID, 0);


        new GetProfessores().execute();
    }

    public HashMap<ProfessorAnterior, List<ProfessorAnterior>> arrumarItens(ArrayList<ProfessorAnterior> profs){
        //metodo gambiarral (porque nem so de boas praticas vive um homem) para
        //arrumar os professores da forma necessaria para usar o ExpandableListView
        HashMap<ProfessorAnterior, List<ProfessorAnterior>> listaProfessores = new HashMap<>();
        for (ProfessorAnterior profAux:profs) {
            List<ProfessorAnterior> listaAuxiliar = new ArrayList<ProfessorAnterior>();
            listaAuxiliar.add(profAux);
            listaProfessores.put(profAux,listaAuxiliar);
            // esse foreach percorre todos os professores e pra cada um cria um arraylist com
            // apenas um elemento (ele mesmo) pra que quando eu expanda a lista, eu use os dados dele mesmo
        }
        return listaProfessores;
    }

    private class GetProfessores extends AsyncTask<Void,Void,ArrayList<ProfessorAnterior>> {

        private ProgressDialog dialog;

        @Override
        protected void onPreExecute() {
            dialog = ProgressDialog.show(ListaProfessores.this, "Aviso", "Aguarde enquanto carregamos os dados.");
        }

        @Override
        protected ArrayList<ProfessorAnterior> doInBackground(Void... voids) {
            ArrayList<ProfessorAnterior> profs = null;
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder().url(API.URL_API_GUIA_BCC + "disciplina/" + disciplinaID + "/professores").build();
            try {
                Response response = client.newCall(request).execute();
                String jsonString = response.body().string();

                Gson gson = new Gson();
                profs = gson.fromJson(jsonString, new TypeToken<ArrayList<ProfessorAnterior>>() {
                }.getType());
            } catch (ConnectException e) {
                Log.e("Exeption de conexao", getString(R.string.erroConexao));
            } catch (ProtocolException e) {
                Log.e("Exeption de protocolo", e.getMessage());
            } catch (MalformedURLException e) {
                Log.e("Exeption de URL", e.getMessage());
            } catch (IOException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return profs;
        }

        @Override                   //verificar depis
        protected void onPostExecute(ArrayList<ProfessorAnterior> profs) {
            // cria um adaptador (BaseExpandableListAdapter) com os dados
            adapter = new ListaDeProfessoresAdapter(profs, arrumarItens(profs), ListaProfessores.this);
            // define o apadtador do ExpandableListView
            elvProfessores.setAdapter(adapter);

            dialog.dismiss();
        }
    }
}
