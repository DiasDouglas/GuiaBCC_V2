package com.ufrpe.bcc.guia_bcc;


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
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ConnectException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Map;
import java.util.Set;

import beans.Aluno;
import beans.DadosDoAVA;
import beans.Disciplina;
import beans.DisciplinaCursada;
import beans.DisciplinaDTO;
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


        btnEntrar = (Button) findViewById(R.id.btnEntrar);
        cbLembrarUsuario = (CheckBox) findViewById(R.id.cbLembrarUsuario);


        //Pegando valores salvos persistidos no shared preferences
        SharedPreferences preferences = getSharedPreferences(PREFS_FILE,0);
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
                   new ConectarAva(edtNomeUsuario.getText().toString(),edtSenha.getText().toString()).execute();
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

        private static final String URL_TOKEN = "http://ava.ufrpe.br/login/token.php";
        private static final String URL_TO_CONNECT = "http://ava.ufrpe.br/webservice/rest/server.php?moodlewsrestformat=json";
        private static final String SERVICE = "moodle_mobile_app";
        private static final String CORE_SITE_INFO = "core_webservice_get_site_info";
        private DadosDoAVA dadosDoAVA;


        private static final String URL_DADOS_EXTRAS_ALUNO = "http://ava.ufrpe.br/webservice/rest/server.php?moodlewsrestformat=json";
        private static final String URL_SPRING_REQUEST = "http://IP.DA.MAQUINA.AQUI:PORTA/guiabcc/disciplinaDto/";
        private static final String WSFUNCTIONS = "wsfunction=core_user_get_users_by_id";
        private static final String WSTOKEN_PREFIXO="wstoken=";
        private static final String USERIDS_PREFIXO="userids%5B0%5D=";
        //private  ArrayList<DisciplinaDTO> disciplinasParaAvaliar;

        private String username;
        private String password;

        /**
         *Passando como argumentos do construtor usuario e senha para estabelecer conexão e
         *recuperar o token
         * */
        public ConectarAva(String username,String password){
            this.setUsername(username);
            this.setPassword(password);
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
                URL url = new URL(URL_TOKEN+"?username="+username+"&"+"password="+password+"&service="+SERVICE);
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
                    ArrayList<DisciplinaDTO> disc = this.gettingStudentDiciplinesFromAva();
                    aluno.setDisciplinasCursadas(disc);
                }
                else{
                    Toast dadosInvalidos = Toast.makeText(MainActivity.this,getString(R.string.dadosUsuarioInvalidos),Toast.LENGTH_SHORT);
                    dadosInvalidos.show();
                }

                connection.disconnect();
                is.close();

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

            return aluno;
        }

        public ArrayList<DisciplinaDTO> gettingStudentDiciplinesFromAva()throws Exception{
            ArrayList<DisciplinaDTO> retorno = null;


            URL url = new URL(URL_DADOS_EXTRAS_ALUNO+"&"+WSFUNCTIONS+"&"+WSTOKEN_PREFIXO+
                    myToken.getToken()+"&"+USERIDS_PREFIXO+dadosDoAVA.getUserid());
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

            retorno  = this.gettingDisciplinaFromRequest(line);

            connection.disconnect();
            is.close();
            ir.close();
            bf.close();

            return retorno;
        }


        private ArrayList<DisciplinaDTO> gettingDisciplinaFromRequest(String jsonStudent)throws IOException{

            ArrayList<DisciplinaDTO> retorno = null;
            InputStream is = null;
            InputStreamReader isr = null;
            BufferedReader buff= null;
            URL url = null;
            HttpURLConnection conn = null;

            Gson gson = new Gson();

            jsonStudent = jsonStudent.substring(1,jsonStudent.length()-1);
            JsonParser parser = new JsonParser();
            JsonObject obj = parser.parse(jsonStudent).getAsJsonObject();

            if(obj.get("enrolledcourses") !=  null){

                retorno = new ArrayList<DisciplinaDTO>();

                for(JsonElement j : obj.get("enrolledcourses").getAsJsonArray()){
                        /*Reutilizando a variável line, pois apartir desse ponto o objeto JSON
                        * já terá sido criado*/
                    jsonStudent = j.getAsJsonObject().get("fullname").toString();
                    Log.d("String da linha",jsonStudent.substring(1,7));
                    if(jsonStudent.substring(1,7).equals("2017.2")) {
                        try {
                            url = new URL(URL_SPRING_REQUEST + j.getAsJsonObject().get("id"));
                            conn = (HttpURLConnection) url.openConnection();
                            conn.setRequestMethod("GET");
                            conn.connect();
                            is = conn.getInputStream();
                            isr = new InputStreamReader(is);
                            buff = new BufferedReader(isr);

                            jsonStudent = buff.readLine();


                            if (jsonStudent != null) {
                                JsonObject objeto =  parser.parse(jsonStudent).getAsJsonObject();

                                DisciplinaDTO disciplinaDTO = new DisciplinaDTO();
                                disciplinaDTO.setID(objeto.get("id").getAsLong());
                                disciplinaDTO.setAvaliacaoGeral(objeto.get("avaliacaoGeral").getAsFloat());
                                disciplinaDTO.setNome(objeto.get("nomeDisciplina").getAsString());
                                disciplinaDTO.setQtdItens(objeto.get("qtdItens").getAsInt());
                                disciplinaDTO.setUltimaAtt(objeto.get("ultimaAtt").getAsString());
                                Log.d("Classe Disciplina",disciplinaDTO.getNome());

                                retorno.add(disciplinaDTO);
                            }

                        }catch(ConnectException e ){
                            Log.e("Erro na conexao",e.getMessage());
                        }
                        catch (MalformedURLException e){
                            Log.e("Erro na URL",e.getMessage());
                        }
                        catch(ProtocolException e){
                            Log.e("Erro de protocolo",e.getMessage());
                        }
                        catch (IOException e){
                            Log.e("Erro de IO",e.getMessage());
                        }

                    }

                }

                conn.disconnect();
                is.close();
                isr.close();
                buff.close();
            }

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

                   if(cbLembrarUsuario.isChecked()){
                       rememberOption = true;
                       SharedPreferences preferences = getSharedPreferences(PREFS_FILE, 0);
                       SharedPreferences.Editor edt = preferences.edit();
                       edt.putBoolean(PREFS_REMEMBER_USER, rememberOption);
                       edt.putString(PREFS_USER_NAME, edtNomeUsuario.getText().toString());
                       edt.putString(PREFS_USER_PSW, edtSenha.getText().toString());
                       Log.d("preferencia salva",edtNomeUsuario.getText().toString());
                       edt.commit();
                   }
                   else{
                       rememberOption = false;
                       SharedPreferences preferences = getSharedPreferences(PREFS_FILE,0);
                       SharedPreferences.Editor edt = preferences.edit();
                       edt.putBoolean(PREFS_REMEMBER_USER,rememberOption);
                       edt.putString(PREFS_USER_NAME,null);
                       edt.putString(PREFS_USER_PSW,null);
                       edt.commit();
                       Log.d("preferencia removida",preferences.getString(PREFS_USER_NAME,""));
                   }

                   novoAluno = usuario;
                   Intent myIntent = new Intent(MainActivity.this, CamposUsuario.class);
                   myIntent.putExtra("aluno_logado", novoAluno);
                   myIntent.putExtra("dados_ava",dadosDoAVA);
                  //myIntent.putExtra("token_logado",myToken);
                   startActivity(myIntent);
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
                  url = new URL(URL_TO_CONNECT+"&wsfunction="+CORE_SITE_INFO+"&wstoken="+myToken.getToken());
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
    }

}
