package com.ufrpe.bcc.guia_bcc;

import android.os.AsyncTask;
import android.support.design.widget.TabLayout;
/*
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
*/
import android.support.v7.app.AppCompatActivity;
/*
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
*/
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.util.Log;

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
import java.util.concurrent.ExecutionException;

import beans.Aluno;
import beans.DadosDoAVA;
import beans.Disciplina;
import beans.DisciplinaCursada;
import beans.DisciplinaDTO;
import beans.connections.Token;
/*
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.widget.TableLayout;
import android.widget.TextView;
*/

public class CamposUsuario extends AppCompatActivity {


    SectionsPageAdapter mSpa;
    //Dados retornados do JSON da requisição
    private Aluno alunoLogado;
    private DadosDoAVA dadosDoAVA;
    private Token tokenAluno;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_campos_usuario);

        alunoLogado  = (Aluno) getIntent().getSerializableExtra("aluno_logado");
        dadosDoAVA = (DadosDoAVA) getIntent().getSerializableExtra("dados_ava");
        tokenAluno = (Token) getIntent().getSerializableExtra("token_logado");


        //Verifica se a instancia de aluno logado na intent é nula
        if(alunoLogado != null && dadosDoAVA != null && tokenAluno!=null) {
            ViewPager myViewPager = (ViewPager) findViewById(R.id.container);
            this.setupViewPager(myViewPager);

            TabLayout mTabLayout = (TabLayout) findViewById(R.id.tabs);
            mTabLayout.setupWithViewPager(myViewPager);
        }
        else {
            throw new NullPointerException("Instancia de aluno veio nula na activity campos usuario");
        }
    }


    //Método para configurar o view pager para adicionar os fragmentos da TabView
    public void setupViewPager(ViewPager vp){
        mSpa = new SectionsPageAdapter(getSupportFragmentManager());
        mSpa.adicionarFragmento(new Inicio(alunoLogado),"Inicio");
        mSpa.adicionarFragmento(new Avaliacoes(alunoLogado.getDisciplinasCursadas()),"Avaliacoes");
        mSpa.adicionarFragmento(new Questoes(),"Questoes");
        vp.setAdapter(mSpa);
    }






}
