package com.ufrpe.bcc.guia_bcc;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;

import com.google.gson.Gson;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import beans.Aluno;
import beans.DadosDoAVA;
import beans.Usuario;


public class MainActivity extends AppCompatActivity {

    EditText edtNomeUsuario;
    EditText edtSenha;
    Button btnEntrar;
    Button btnEsqueciSenha;
    CheckBox cbLembrarUsuario;


    //Atributos nome de usuario e senha salvos pelo usuario
    private String username;
    private String password;

    @Override
    public void onStart(){
        super.onStart();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtNomeUsuario = (EditText) findViewById(R.id.edtNome);
        edtNomeUsuario = (EditText) findViewById(R.id.edtSenha);

        btnEntrar = (Button) findViewById(R.id.btnEntrar);
        btnEsqueciSenha = (Button) findViewById(R.id.btnEsqueciSenha);

        cbLembrarUsuario = (CheckBox) findViewById(R.id.cbLembrarUsuario);

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
                Intent myIntent = new Intent(MainActivity.this,CamposUsuario.class);

                if(edtNomeUsuario.getText().toString() != null && edtSenha.getText().toString()!= null){
                    new ConectarAva(edtNomeUsuario.getText().toString(),edtSenha.getText().toString()).execute();
                }
                startActivity(myIntent);
            }
        });

        /**
         * Setando ouvinte de checkbox caso o usuário opte por salvar o usuario e  a senha
         * */
        cbLembrarUsuario.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(){

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){

                }
                else {

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

    static class ConectarAva extends AsyncTask<Void,Void, Aluno>{

        public static final String URL_TO_CONNECT = "http://ava.ufrpe.br/login/token.php";
        public static final String SERVICE = "moodle_mobile_app";

        private String username;
        private String password;

        public ConectarAva(String username,String password){
            this.setUsername(username);
            this.setPassword(password);
        }

        @Override
        protected Aluno doInBackground(Void... voids) {
            Aluno aluno = null;
            try {
                URL url = new URL(URL_TO_CONNECT);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("POST");
                connection.addRequestProperty("username",username);
                connection.addRequestProperty("password",password);
                connection.addRequestProperty("service",SERVICE);
                connection.connect();

                InputStream is = connection.getInputStream();

                Gson gson = new Gson();

                DadosDoAVA dados = gson.fromJson(new InputStreamReader(is), DadosDoAVA.class);

                if(dados != null){
                    aluno = new Aluno();
                    aluno.setNomeAluno(dados.getFirstaname()+ " " +dados.getLastname());
                    aluno.setUsuario(new Usuario(dados.getUsername(),this.password));
                }
                else {
                    throw new NullPointerException("Dados do ava não foram retornados");
                }

            }
            catch(Exception e){
                e.printStackTrace();
            }
            return aluno;
        }

        @Override
        protected void onPostExecute(Aluno usuario){

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
