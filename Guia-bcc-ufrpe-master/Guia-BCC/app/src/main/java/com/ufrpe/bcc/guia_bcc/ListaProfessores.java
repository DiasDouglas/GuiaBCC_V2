package com.ufrpe.bcc.guia_bcc;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ExpandableListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import adapters.ListaDeProfessoresAdapter;
import beans.ProfessorAnterior;

/**
 * Created by Fabio on 13/12/2017.
 */

public class ListaProfessores extends AppCompatActivity {

    public static final String EXTRA_PROFESSORES = "professores";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_professores);

        ExpandableListView elvProfessores = (ExpandableListView) findViewById(R.id.elvProfessores);

        ArrayList<ProfessorAnterior> professores = getIntent().getParcelableArrayListExtra(EXTRA_PROFESSORES);
        //ArrayList<ProfessorAnterior> professores = profs1;


        // cria um adaptador (BaseExpandableListAdapter) com os dados
        ListaDeProfessoresAdapter adapter = new ListaDeProfessoresAdapter(professores, this.arrumarItens(professores), this);
        // define o apadtador do ExpandableListView
        elvProfessores.setAdapter(adapter);
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
}
