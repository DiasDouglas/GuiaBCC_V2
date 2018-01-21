package com.ufrpe.bcc.guia_bcc;

import android.support.design.widget.TabLayout;
/*
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
*/
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
/*
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
*/
import android.support.v4.view.ViewPager;
import android.os.Bundle;

import beans.Aluno;
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
    private Aluno alunoLogado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_campos_usuario);

        alunoLogado  = (Aluno) getIntent().getSerializableExtra("aluno_logado");
        //Verifica se a instancia de aluno logado na intent é nula
        if(alunoLogado != null) {
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
        mSpa.adicionarFragmento(new Avaliacoes(),"Avaliacoes");
        mSpa.adicionarFragmento(new Questoes(),"Questoes");
        vp.setAdapter(mSpa);
    }

}
