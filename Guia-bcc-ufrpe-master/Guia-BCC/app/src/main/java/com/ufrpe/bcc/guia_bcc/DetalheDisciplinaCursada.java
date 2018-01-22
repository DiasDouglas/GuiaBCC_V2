package com.ufrpe.bcc.guia_bcc;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import beans.DisciplinaCursada;

/**
 * Created by Douglas on 29/11/2017.
 */

public class DetalheDisciplinaCursada extends Fragment {

    private static final String nomeInicial ="Avaliações";
    private  View myView;
    private ViewGroup myViewGroup;
    private Bundle myBundle;


    @Override
    public View onCreateView(LayoutInflater inf, ViewGroup container , Bundle savedInstanceState){
        View  myView = inf.inflate(R.layout.frag_selecionar_avaliacao,container,false);
        final TextView frag_selecionar_avaliacao_nome_disciplina = (TextView) myView.findViewById(R.id.frag_selecionar_avaliacao_nome_disciplina);
        final TextView frag_selecionar_avaliacao_nome_professor = (TextView) myView.findViewById(R.id.frag_selecionar_avaliacao_nome_professor);
        Button btnAvaliarDisciplina = (Button) myView.findViewById(R.id.btnAvaliarDisciplina);
        Button btnAvaliarProfessor = (Button) myView.findViewById(R.id.btnAvaliarProfessor);
        Button frag_selecionar_avaliacao_btnVoltar = (Button) myView.findViewById(R.id.frag_selecionar_avaliacao_btnVoltar);

        frag_selecionar_avaliacao_nome_professor.setText(getArguments().getString("professor_disciplina"));
        frag_selecionar_avaliacao_nome_disciplina.setText(getArguments().getString("disciplina_nome"));

        btnAvaliarDisciplina.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), PerguntasAvaliacao.class);
                intent.putExtra("tipo", "avaliar_disciplina");
                intent.putExtra("num_pergunta", 1);
                intent.putExtra("professor", frag_selecionar_avaliacao_nome_professor.getText());
                intent.putExtra("disciplina", frag_selecionar_avaliacao_nome_disciplina.getText());
                getActivity().startActivity(intent);
            }
        });
        btnAvaliarProfessor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), PerguntasAvaliacao.class);
                intent.putExtra("tipo", "avaliar_professor");
                intent.putExtra("num_pergunta", 1);
                intent.putExtra("professor", frag_selecionar_avaliacao_nome_professor.getText());
                intent.putExtra("disciplina", frag_selecionar_avaliacao_nome_disciplina.getText());
                getActivity().startActivity(intent);
            }
        });
        frag_selecionar_avaliacao_btnVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().onBackPressed();
            }
        });

        return myView;
    }

    @Override
    public void onStart(){
        super.onStart();
    }

}
