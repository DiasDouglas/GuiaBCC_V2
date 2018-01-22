package com.ufrpe.bcc.guia_bcc;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;


import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import adapters.ListaDeDisciplinasCursadasAdapter;
import beans.Aluno;
import beans.DadosDoAVA;
import beans.DisciplinaCursada;
import beans.connections.CoursesFromJson;
import beans.connections.CoursesListFromJson;
import beans.connections.Token;

@SuppressLint("ValidFragment")
public class Inicio extends Fragment {

    private static final String nomeInicial ="Inicio";

    private TextView tvNomeUsuario;
    private ImageView ivFotoUsuario;

    private Aluno alunoLogado;
    private DadosDoAVA dadosDoAVA;
    private Token tokenAluno;

    @SuppressLint("ValidFragment")
    public Inicio(Aluno aluno, DadosDoAVA dadosDoAVA,Token token){
        this.setAlunoLogado(aluno);
        this.setDadosDoAVA(dadosDoAVA);
        this.setTokenAluno(token);
    }


    @Override
    public View onCreateView(LayoutInflater lif, @Nullable ViewGroup container , @Nullable Bundle savedInstanceState){
        View  myView = lif.inflate(R.layout.tab_inicio,container,false);

        tvNomeUsuario = (TextView) myView.findViewById(R.id.tvNomeUsuario);
        ivFotoUsuario = (ImageView) myView.findViewById(R.id.ivFotoUsuario);

        //Verificando se o aluno passado como parâmetro veio como nulo
        if(alunoLogado != null && dadosDoAVA != null) {
            //Por enquanto serão utilizadas as disciplinas no arraylist
            alunoLogado.setDisciplinasCursadas(this.getCursos());
            tvNomeUsuario.setText(alunoLogado.getNomeAluno());
        }
        else {
            alunoLogado = new Aluno("Ismael", getCursos());
        }


        ListView listaDeCurso = (ListView) myView.findViewById(R.id.lvCursosTelaInicial);

        ListaDeDisciplinasCursadasAdapter adapter = new ListaDeDisciplinasCursadasAdapter(alunoLogado.getDisciplinasCursadas(),this,savedInstanceState);

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

    public Token getTokenAluno() {
        return tokenAluno;
    }

    public void setTokenAluno(Token tokenAluno) {
        if(tokenAluno != null)
            this.tokenAluno = tokenAluno;
        else
            throw new NullPointerException("Token do aluno, nulo");
    }

    //Método para carregar a foto do usuário

    @Override
    public void onStart(){
        super.onStart();

        if(ivFotoUsuario != null && dadosDoAVA != null){
            //new CarregarFoto(dadosDoAVA.getUserpictureurl()).execute();
            new CarregarCursos().execute();
        }
    }


    /**
     * Esse async task faz uma requisição para
     * */
    private class CarregarFoto extends AsyncTask<Void,Void,Bitmap>{

        private String urlImagem;

        CarregarFoto(String urlImagem){
            this.urlImagem = urlImagem;
        }

        @Override
        protected Bitmap doInBackground(Void... voids) {
            Bitmap retorno = null;

            try{
                URL url = new URL(urlImagem);
                HttpURLConnection connection =(HttpURLConnection) url.openConnection();
                connection.setRequestMethod("POST");
                connection.setRequestProperty("Content-Type","application/x-www-form-urlencoded");
                connection.setDoInput(true);
                connection.connect();

                InputStream is = connection.getInputStream();

                InputStreamReader reader = new InputStreamReader(is);
            }
            catch (Exception e){
                e.printStackTrace();
            }

            return retorno;
        }


        @Override
        protected void onPostExecute(Bitmap retorno){

        }
    }


    private class CarregarCursos extends  AsyncTask<Void,Void,CoursesListFromJson>{

        private static final String URL_DADOS_EXTRAS_ALUNO = "http://ava.ufrpe.br/webservice/rest/server.php?moodlewsrestformat=json";
        private static final String WSFUNCTIONS = "wsfunction=core_user_get_users_by_id";
        private static final String WSTOKEN_PREFIXO="wstoken=";
        private static final String USERIDS_PREFIXO="userids%5B0%5D=";

        @Override
        protected CoursesListFromJson doInBackground(Void... voids) {
            CoursesListFromJson retorno = null;

            try{
                URL url = new URL(URL_DADOS_EXTRAS_ALUNO+"&"+WSFUNCTIONS+"&"+WSTOKEN_PREFIXO+
                        tokenAluno.getToken()+"&"+USERIDS_PREFIXO+dadosDoAVA.getUserid());
                HttpURLConnection connection =(HttpURLConnection) url.openConnection();
                connection.setRequestMethod("POST");
                connection.setRequestProperty("Content-Type","application/json");
                connection.setDoInput(true);
                connection.connect();


                InputStream is = connection.getInputStream();
                InputStreamReader ir = new InputStreamReader(is);
                BufferedReader bf = new BufferedReader(ir);

               String line =  bf.readLine();
               while(bf.readLine() != null) {
                   line+= bf.readLine();
               }

               line = line.substring(1,line.length()-1);
                JsonParser parser = new JsonParser();
                JsonObject obj = parser.parse(line).getAsJsonObject();

                if(obj.get("enrolledcourses") !=  null){

                    retorno = new CoursesListFromJson();

                    for(JsonElement j : obj.get("enrolledcourses").getAsJsonArray()){
                        /*Reutilizando a variável line, pois apartir desse ponto o objeto JSON
                        * já terá sido criado*/
                           line = j.getAsJsonObject().get("id").toString();
                           //Verificando se a disciplina é do ano atual

                    }
                }

                /*
               Gson gson = new Gson();
               Object r = gson.fromJson(new InputStreamReader(is),Object.class);


               if(r!=null){
                   retorno = null;
               }*/


            }
            catch (Exception e){
                e.printStackTrace();
            }


            return retorno;
        }


        @Override
        protected void onPostExecute(CoursesListFromJson cursos){

        }
    }

}
