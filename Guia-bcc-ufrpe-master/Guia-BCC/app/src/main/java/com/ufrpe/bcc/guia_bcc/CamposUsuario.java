package com.ufrpe.bcc.guia_bcc;

import android.content.Context;
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

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import beans.Aluno;
import beans.DadosDoAVA;
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

    private static final String STUDENT_FILE ="objetos_aluno";
    private static final String DATA_FROM_AVA_FILE ="objetos_dadosDoAVA";

    SectionsPageAdapter mSpa;
    //Dados retornados do JSON da requisição
    private Aluno alunoLogado;
    private DadosDoAVA dadosDoAVA;
    //private Token tokenAluno;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_campos_usuario);

        //Primeiramente é verificado se o aluno está salvo no arquivo
        alunoLogado = this.getStudentFromFile();
        dadosDoAVA = this.getStudentDataFromFile();

        if(alunoLogado == null && dadosDoAVA == null) {
            alunoLogado = (Aluno) getIntent().getSerializableExtra("aluno_logado");
            dadosDoAVA = (DadosDoAVA) getIntent().getSerializableExtra("dados_ava");
            this.saveExtrasIntoFiles(alunoLogado,dadosDoAVA);
        }

        //tokenAluno = (Token) getIntent().getSerializableExtra("token_logado");


        //Verifica se a instancia de aluno logado na intent é nula
        if(alunoLogado != null && dadosDoAVA != null /*&& tokenAluno!=null*/) {
            ViewPager myViewPager = (ViewPager) findViewById(R.id.container);
            this.setupViewPager(myViewPager);

            TabLayout mTabLayout = (TabLayout) findViewById(R.id.tabs);
            mTabLayout.setupWithViewPager(myViewPager);
        }
        else {
            throw new NullPointerException("Instancia de aluno veio nula na activity campos usuario");
        }
    }


    /**
     * No método on stop os obejtos aluno logado e dados do AVa são salvos num arquivo para leitura
     * posterior
     * */
    public void saveExtrasIntoFiles(Aluno alunoLogado, DadosDoAVA dadosDoAVA){
        super.onStop();

        try {

            FileOutputStream fosAluno = openFileOutput(STUDENT_FILE,Context.MODE_PRIVATE);
            FileOutputStream fosDadosDoAVA = openFileOutput(DATA_FROM_AVA_FILE,Context.MODE_PRIVATE);

            ObjectOutputStream osAluno = new ObjectOutputStream(fosAluno);
            ObjectOutputStream osDadosDoAVA = new ObjectOutputStream(fosDadosDoAVA);
            if(alunoLogado != null && dadosDoAVA != null){
                osAluno.writeObject(alunoLogado);
                osDadosDoAVA.writeObject(dadosDoAVA);
            }
            else{
                throw new NullPointerException("Objeto DadosDoAVA ou Aluno nulos");
            }

            osAluno.close();
            osDadosDoAVA.close();
            fosAluno.close();
            fosDadosDoAVA.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        catch (IOException e){
            e.printStackTrace();
        }

    }

    /**
     * O método getStudentFromFile() lê um estudante do arquivo e o retorna
     * */
    private Aluno getStudentFromFile(){
        Aluno retorno = null;
        try {

            FileInputStream fisAluno = openFileInput(STUDENT_FILE);
            ObjectInputStream osAluno = new ObjectInputStream(fisAluno);

            retorno = (Aluno) osAluno.readObject();

            osAluno.close();
            fisAluno.close();


        } catch (FileNotFoundException e) {
            e.printStackTrace();
            Log.d("Arquivo não encontrado",e.getMessage());
        }
        catch (IOException e){
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return retorno;
    }

    /**
     * O método getStudentDataFromFile() lê um Dados do ava do arquivo e o retorna
     * */
    private DadosDoAVA getStudentDataFromFile(){
        DadosDoAVA retorno = null;
        try {

            FileInputStream fisDadosDoAVA = openFileInput(DATA_FROM_AVA_FILE);
            ObjectInputStream osDadosDoAVA = new ObjectInputStream(fisDadosDoAVA);

            retorno = (DadosDoAVA) osDadosDoAVA.readObject();

            osDadosDoAVA.close();
            fisDadosDoAVA.close();


        } catch (FileNotFoundException e) {
            e.printStackTrace();
            Log.d("Arquivo não encontrado",e.getMessage());
        }
        catch (IOException e){
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return retorno;
    }

    /**
     * Caso o usuário opte por voltar para a tela de login, os dados do arquivo são
     * deletados
     * */
    @Override
    public void onBackPressed(){
        super.onBackPressed();

            deleteFile(STUDENT_FILE);
            deleteFile(DATA_FROM_AVA_FILE);
            //osAluno.writeObject(null);
            //osDadosDoAVA.writeObject(null);

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
