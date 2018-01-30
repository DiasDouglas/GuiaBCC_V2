package com.ufrpe.bcc.guia_bcc;


import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ConnectException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Map;
import java.util.Set;

import beans.API;
import beans.Aluno;
import beans.DadosDoAVA;
import beans.Disciplina;
import beans.DisciplinaCursada;
import beans.DisciplinaDTO;
import beans.URLDropbox;
import beans.connections.Token;
import beans.Usuario;


public class MainActivity extends AppCompatActivity {

    private static final String PREFS_FILE="PREFS_GUIA_BCC";
    private static final String PREFS_USER_NAME="USER_NAME";
    private static final String PREFS_USER_PSW="USER_PSW";
    private static final String PREFS_REMEMBER_USER="REMEMBER_USER";

    EditText edtNomeUsuario;
    EditText edtSenha;
    Button btnEntrar;
    Button btnEsqueciSenha;
    CheckBox cbLembrarUsuario;


    //Atributos nome de usuario e senha salvos pelo usuario
    private String username;
    private String password;
    //Objeto aluno, retornado, caso a entrada dada seja válida
    private static Aluno novoAluno;

    private boolean rememberOption = false;

    private static Token myToken;

    @Override
    public void onStart(){
        super.onStart();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtNomeUsuario = (EditText) findViewById(R.id.edtNome);
        edtSenha = (EditText) findViewById(R.id.edtSenha);

        //Valores padrão
        //edtNomeUsuario.setText("usuario");
        //edtSenha.setText("senha");
        //====================

        btnEntrar = (Button) findViewById(R.id.btnEntrar);
        cbLembrarUsuario = (CheckBox) findViewById(R.id.cbLembrarUsuario);


        //Pegando valores salvos persistidos no shared preferences
        final SharedPreferences preferences = getSharedPreferences(PREFS_FILE,0);
        rememberOption = preferences.getBoolean(PREFS_REMEMBER_USER,false);

        if(rememberOption){
            edtNomeUsuario.setText(preferences.getString(PREFS_USER_NAME,""));
            edtSenha.setText(preferences.getString(PREFS_USER_PSW,""));
            cbLembrarUsuario.setChecked(rememberOption);
        }


        if(savedInstanceState != null){
            /*Os seguintes testes evitam null pointer exception na hora de  setar os textos*/
            if(savedInstanceState.getString("nome") != null)
                edtNomeUsuario.setText(savedInstanceState.getString("nome"));
            if(savedInstanceState.getString("senha") != null)
                edtSenha.setText(savedInstanceState.getString("senha"));
        }

        btnEntrar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){

                if(!edtNomeUsuario.getText().toString().equals("") && !edtSenha.getText().toString().equals("")){
                   new ConectarAva(edtNomeUsuario.getText().toString(),edtSenha.getText().toString(),MainActivity.this).execute();
                }

            }
        });


        cbLembrarUsuario.setOnCheckedChangeListener(new CheckBox.OnCheckedChangeListener(){


            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(!isChecked){
                    rememberOption = false;
                    SharedPreferences.Editor edt = preferences.edit();
                    edt.putBoolean(PREFS_REMEMBER_USER, rememberOption);
                    edt.putString(PREFS_USER_NAME, null);
                    edt.putString(PREFS_USER_PSW, null);
                    edt.commit();
                    Log.d("preferencia removida", preferences.getString(PREFS_USER_NAME, ""));
                }
            }
        });

    }

    //Salvando o estado da aplicacaos
    @Override
    public void onSaveInstanceState(Bundle b){
        super.onSaveInstanceState(b);

        if(edtNomeUsuario != null){
            if(edtNomeUsuario.getText().toString() != null)
                b.putString("nome",edtNomeUsuario.getText().toString());
        }
        if(edtSenha != null){
            if(edtSenha.getText().toString() != null)
                b.putString("senha",edtSenha.getText().toString());
        }

    }

    class ConectarAva extends AsyncTask<Void,Void, Aluno>{

        private static final String SERVICE = "moodle_mobile_app";
        private static final String CORE_SITE_INFO = "core_webservice_get_site_info";
        private DadosDoAVA dadosDoAVA;

        private static final String WSFUNCTIONS = "wsfunction=core_enrol_get_users_courses";
        private static final String WSTOKEN_PREFIXO="wstoken=";
        private static final String USERIDS_PREFIXO="userid=";
        //private  ArrayList<DisciplinaDTO> disciplinasParaAvaliar; B0%5D

        private String username;
        private String password;
        private Context mainActivityContext;
        private ProgressDialog dialog;
        /**
         *Passando como argumentos do construtor usuario e senha para estabelecer conexão e
         *recuperar o token
         * */
        public ConectarAva(String username,String password,Context mainActivityContext){
            this.setUsername(username);
            this.setPassword(password);
            this.setMainActivityContext(mainActivityContext);
        }

        @Override
        protected void onPreExecute(){
            dialog = ProgressDialog.show(mainActivityContext, getString(R.string.avisoDialogoDeProgresso) , getString(R.string.mensagemDialogoDeProgresso));
        }

        @Override
        protected Aluno doInBackground(Void... voids) {
            Aluno aluno = null;
            try {
                //+"?username="+username+"&"+"password="+password+"&service="+SERVICE - - x-www-form-urlencoded
                //URL url = new URL(URL_TOKEN);
                /**
                 * O usuário a senha e o serviço tem de ser passados como parâmetros da URL,
                 *doutra forma não funcionará
                 * */
                URL url = new URL(API.URL_API_AVA_TOKEN+"?username="+username+"&"+"password="+password+"&service="+SERVICE);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("POST");
                connection.addRequestProperty("Content-Type","application/json");
                connection.setDoInput(true);
                connection.setDoOutput(true);
                connection.connect();

                InputStream is = connection.getInputStream();

                Gson gson = new Gson();

                myToken = gson.fromJson(new InputStreamReader(is), Token.class);

                if(  myToken.getToken() != null){
                    aluno = this.gettingDataFromAva(url, connection, gson);
                    /*As disciplinas são pegas a partir de um método diferente, pois elas não vem
                    * junto com o Token do usuário
                    * */
                    ArrayList<DisciplinaDTO> disc = this.gettingStudentDiciplinesFromAva(myToken.getToken());
                    aluno.setDisciplinasCursadas(disc);
                }

                connection.disconnect();
                is.close();

            }
            catch (ConnectException e){
                e.printStackTrace();
                return  this.connectionError(aluno);
             }
            catch (ProtocolException e){
                Log.e("Exeption de protocolo",e.getMessage());
                return  this.connectionError(aluno);
            }
            catch (MalformedURLException e){
                Log.e("Exeption de URL",e.getMessage());
                return this.connectionError(aluno);
            }
            catch(IOException e){
                e.printStackTrace();
                return this.connectionError(aluno);
            }
            catch (Exception e){
               return this.connectionError(aluno);
            }

            return aluno;
        }

        /**O método faz uma requisição para pegar uma lista das disciplinas cursadas pelo usuário em
         * formato de JSON. Onde a requisição é feita a partir de um token passado como parâmetro
         * */
        public ArrayList<DisciplinaDTO> gettingStudentDiciplinesFromAva(String token)throws ConnectException,
                                                                        ProtocolException,IOException{
            ArrayList<DisciplinaDTO> retorno = null;


            URL url = new URL(API.URL_API_AVA_TO_CONNECT+"&"+WSFUNCTIONS+"&"+WSTOKEN_PREFIXO+
                    token+"&"+USERIDS_PREFIXO+dadosDoAVA.getUserid());
            HttpURLConnection connection =(HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type","application/json");
            connection.setDoInput(true);
            connection.setDoOutput(true);
            connection.connect();


            InputStream is = connection.getInputStream();
            InputStreamReader ir = new InputStreamReader(is);
            BufferedReader bf = new BufferedReader(ir);

            //Ao ler o inputstream será retornado um JsonArray
            String line =  bf.readLine();
            while(bf.readLine() != null) {
                line+= bf.readLine();
            }

            /**A chamada a seguir pega a string Json retronada pelo BufferedReader
             * E passa como parâmetro para o método para o gettingDisciplinaFromRequest
             *
             * MOTIVAÇÃO:Optei por pegar as disciplinas do servidor spring num método diferente,
             * pois se ficassse tudo num único método a legibilidade e o raciocínio da lógica ficaria
             * ainda mais difícil de serem entendidos
             * */
            retorno  = this.gettingDisciplinaFromRequest(line);

            connection.disconnect();
            is.close();
            ir.close();
            bf.close();

            return retorno;
        }

        /**O método a seguir pega uma string em Json, o qual representa um JsonArray com todas
         * as disciplinas cursadas pelo estudante, onde é feito um parse da string e tranformada
         * em um objeto JsonArray, então é iterado sobre os itens do array para criação dos objetos
         * disciplina DTO
         * */
        private ArrayList<DisciplinaDTO> gettingDisciplinaFromRequest(String jsonStudent)throws ConnectException,
                                                                                        ProtocolException,IOException{
            ArrayList<DisciplinaDTO> retorno = null;
            InputStream is = null;
            InputStreamReader isr = null;
            BufferedReader buff= null;
            URL url = null;
            HttpURLConnection conn = null;

            String linhaDeValoresLidos = "";

            Gson gson = new Gson();

            JsonParser parser = new JsonParser();
            JsonArray obj = parser.parse(jsonStudent).getAsJsonArray();


            retorno = new ArrayList<DisciplinaDTO>();
            /*GAMBIARRA: contador para pular o primeiro item do JsonArray pois se trata do ID
             da coordenação*/
            boolean pulo = false;
            for(JsonElement j : obj){

               if(pulo) {
                   url = new URL(API.URL_API_GUIA_BCC+"disciplina/" + j.getAsJsonObject().get("id"));
                   conn = (HttpURLConnection) url.openConnection();
                   conn.setRequestMethod("GET");
                   conn.connect();
                   is = conn.getInputStream();
                   isr = new InputStreamReader(is);
                   buff = new BufferedReader(isr);

                   //Pegando o inputstream da API e armazenando da string
                   linhaDeValoresLidos = buff.readLine();
                   while(buff.readLine() != null) {
                       linhaDeValoresLidos+= buff.readLine();
                   }
                   //Dando um parsing da string retornada
                   JsonObject objetoJson = null;
                   if(linhaDeValoresLidos != null)
                       objetoJson = (JsonObject) parser.parse(linhaDeValoresLidos);
                   /**
                    * Separando o valor do semestre a partir do short name
                    * */
                   String semestre  = j.getAsJsonObject().get("shortname").getAsString().substring(0,6);

                   /**
                    * Se o objeto Json vier como nulo então cadastrar
                    * então a disciplina não consta no banco de dados
                    * então ela é cadastrada com valores default
                    */
                   if(objetoJson == null){
                       //Pegando o nome da disciplina para separado por uma substring
                       String nomeCurso = j.getAsJsonObject().get("fullname").getAsString();
                       //Abrindo uma nova conexão a apartir de uma nova URL
                       url = new URL(API.URL_API_GUIA_BCC+"disciplina");
                       HttpURLConnection newConn = (HttpURLConnection) url.openConnection();
                       newConn.setRequestMethod("POST");
                       newConn.setRequestProperty("Content-Type","application/json");
                       newConn.setDoOutput(true);
                       newConn.connect();

                       OutputStream os = newConn.getOutputStream();
                        String urlCurso = "\""+URLDropbox.getUrl(nomeCurso.substring(9,nomeCurso.length()-6))+"\"";
                       os.write(("{\"id\":"+j.getAsJsonObject().get("id").getAsInt()+","+
                                  "\"nomeDisciplina\":\""+nomeCurso.substring(9,nomeCurso.length()-6)+"\","+
                                  "\"professoresAnteriores\":[],"+
                                  "\"qtdMediaAlunos\":"+0+","+
                                  "\"ultimoSemestre\":\""+nomeCurso.substring(0,6)+"\","+
                                  "\"avaliacaoGeral\":"+0.0+","+
                                  "\"avaliacaoDificuldade\":"+0.0+","+
                                  "\"avaliacaoClareza\":"+0.0+","+
                                  "\"avaliacaoEsforco\":"+0.0+","+
                                  "\"avaliacaoConteudo\":"+0.0+","+
                                 "\"qtdItens\":"+0+","+
                                  "\"ultimaAtt\":\""+nomeCurso.substring(0,4)+"\","+
                                  "\"urlBancoDa\":"+urlCurso
                                   +"}").getBytes());

                       //Criando InputStream para pegar a resposta dessa requisição
                       InputStream newIs  = newConn.getInputStream();
                       BufferedReader newBuff = new BufferedReader(new InputStreamReader(newIs));
                       Log.d("Resposta do POST",newBuff.readLine());

                       if(semestre.equals("2017.2")) {
                           DisciplinaDTO disciplinaDTO = new DisciplinaDTO();
                           disciplinaDTO.setID(j.getAsJsonObject().get("id").getAsLong());
                           disciplinaDTO.setNome(nomeCurso.substring(8,nomeCurso.length()-5));
                           Log.d("Classe Disciplina", disciplinaDTO.getNome());
                           retorno.add(disciplinaDTO);
                       }

                       os.flush();
                       os.close();
                       newConn.disconnect();
                   }
                   else {
                       //Setando a url do dropbox na disciplina

                      /**
                        *É pego a partir do short name da disciplina o semestre, e então é verificado
                       * se a disciplina é do semestre atual, caso positivo , é criado um objeto
                       * DisciplinaDTO
                        */
                       String nomeCurso = j.getAsJsonObject().get("fullname").getAsString();
                       nomeCurso = nomeCurso.substring(9,nomeCurso.length()-6);

                       DisciplinaDTO disciplinaDTO = new DisciplinaDTO();

                       if(objetoJson.get("urlBancoDa") != null && !URLDropbox.getUrl(nomeCurso).equals("")){
                           disciplinaDTO.setUrlBancoDa(URLDropbox.getUrl(nomeCurso));
                       }

                        if(semestre.equals("2017.2")) {
                              disciplinaDTO.setID(objetoJson.get("id").getAsLong());
                              disciplinaDTO.setAvaliacaoGeral(objetoJson.get("avaliacaoGeral").getAsFloat());
                              disciplinaDTO.setNome(objetoJson.get("nomeDisciplina").getAsString());
                              disciplinaDTO.setQtdItens(objetoJson.get("qtdItens").getAsInt());
                              disciplinaDTO.setUltimaAtt(objetoJson.get("ultimaAtt").getAsString());
                              Log.d("Classe Disciplina", disciplinaDTO.getNome());
                              retorno.add(disciplinaDTO);
                          }
                   }
               }
               else {
                   pulo = true;
               }
            }

            conn.disconnect();
            is.close();
            isr.close();
            buff.close();


            return retorno;

        }

        /**
         * Após executado o doInBackground o programa verifica se usuário retornado veio nulo ou não
         * se vier nulo, então significa que os  dados fornecidos foram inválidos.
         * Caso contrário, a aplicação efetua o login
         * */
        @Override
        protected void onPostExecute(Aluno usuario){
                if(usuario != null) {
                    if (!usuario.getNomeAluno().equals("ERROR")) {
                        if (cbLembrarUsuario.isChecked()) {
                            rememberOption = true;
                            SharedPreferences preferences = getSharedPreferences(PREFS_FILE, 0);
                            SharedPreferences.Editor edt = preferences.edit();
                            edt.putBoolean(PREFS_REMEMBER_USER, rememberOption);
                            edt.putString(PREFS_USER_NAME, edtNomeUsuario.getText().toString());
                            edt.putString(PREFS_USER_PSW, edtSenha.getText().toString());
                            Log.d("preferencia salva", edtNomeUsuario.getText().toString());
                            edt.commit();
                        } else {
                            rememberOption = false;
                            SharedPreferences preferences = getSharedPreferences(PREFS_FILE, 0);
                            SharedPreferences.Editor edt = preferences.edit();
                            edt.putBoolean(PREFS_REMEMBER_USER, rememberOption);
                            edt.putString(PREFS_USER_NAME, null);
                            edt.putString(PREFS_USER_PSW, null);
                            edt.commit();
                            Log.d("preferencia removida", preferences.getString(PREFS_USER_NAME, ""));
                        }

                        novoAluno = usuario;
                        Intent myIntent = new Intent(MainActivity.this, CamposUsuario.class);
                        myIntent.putExtra("aluno_logado", novoAluno);
                        myIntent.putExtra("dados_ava", dadosDoAVA);
                        //myIntent.putExtra("token_logado",myToken);
                        this.dialog.hide();
                        startActivity(myIntent);
                    }
                    else {
                        this.dialog.hide();
                        Toast alertaConexao = Toast.makeText(mainActivityContext,mainActivityContext.getString(R.string.erroConexao),Toast.LENGTH_SHORT);
                        alertaConexao.show();
                    }
                }
                else{
                    this.dialog.hide();
                    Toast alertaUsuarioInvalido = Toast.makeText(mainActivityContext,mainActivityContext.getString(R.string.dadosUsuarioInvalidos),Toast.LENGTH_SHORT);
                    alertaUsuarioInvalido.show();
                }
        }

        /**
         * Author: Ismael 20/01/2018
         * @param url
         * @param connection
         * @param gson
         * @return Aluno
         * @throws Exception
         * O código a seguir pega os objetos URL http URLConnection e gson
         * para utilizar o token retornado no doInBackground para requisitar os dados
         * de usuario e construir o objeto aluno
         */
        private Aluno gettingDataFromAva(URL url,HttpURLConnection connection, Gson gson)throws ConnectException,MalformedURLException,
                                                                                        ProtocolException,IOException{

              Aluno aluno = null;

              if(myToken.getToken() != null) {
                  url = new URL(API.URL_API_AVA_TO_CONNECT+"&wsfunction="+CORE_SITE_INFO+"&wstoken="+myToken.getToken());
                  connection = (HttpURLConnection) url.openConnection();
                  connection.setRequestMethod("POST");
                  connection.addRequestProperty("Content-Type","application/json");
                  connection.setDoInput(true);
                  connection.connect();


                  InputStream is = connection.getInputStream();

                  //DadosDoAVA dados = gson.fromJson(new InputStreamReader(is),DadosDoAVA.class);

                  dadosDoAVA = gson.fromJson(new InputStreamReader(is),DadosDoAVA.class);

                  if (dadosDoAVA != null) {
                     aluno = new Aluno();
                     aluno.setNomeAluno(dadosDoAVA.getFirstname() + " " + dadosDoAVA.getLastname());
                     aluno.setUsuario(new Usuario(dadosDoAVA.getUsername(), this.password));
                  } else {
                      throw new NullPointerException("Dados do ava não foram retornados");
                  }
              }
              else{
                  throw new NullPointerException("Token está nulo");
              }

            return aluno;
        }

        public String getUsername() {
            return username;
        }

        public Aluno connectionError(Aluno aluno){

            Log.e("Exeption de conexao",getString(R.string.erroConexao));
            aluno = new Aluno();
            aluno.setNomeAluno("ERROR");
            return aluno;
        }

        public void setUsername(String username) {
            if(username != null)
                this.username = username;
            else
                throw new NullPointerException("Nome de usuario nulo");
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            if(username != null)
                this.password = password;
            else
                throw new NullPointerException("Nome de usuario nulo");

        }

        public Context getMainActivityContext() {
            return mainActivityContext;
        }

        public void setMainActivityContext(Context mainActivityContext) {
            this.mainActivityContext = mainActivityContext;
        }
    }

}
