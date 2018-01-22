package adapters;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.ufrpe.bcc.guia_bcc.R;

import java.util.List;

import beans.DisciplinaCursada;
import beans.DisciplinaDTO;

/**
 * Created by Fabio on 24/11/2017.
 * Adapter para itens de listagem de disciplina
 */

public class ListaDeDisciplinasCursadasAdapter extends BaseAdapter {

    private List<DisciplinaDTO> listaDisciplinas;
    private Fragment act;
    private Bundle savedInstanceState;

    public ListaDeDisciplinasCursadasAdapter(List<DisciplinaDTO> listaDisciplinas, Fragment act, Bundle savedInstanceState ){
        this.listaDisciplinas = listaDisciplinas;
        this.savedInstanceState = savedInstanceState;
        this.act = act;
    }

    @Override
    public int getCount() {
        return listaDisciplinas.size();
    }

    @Override
    public Object getItem(int position) {
        return listaDisciplinas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        /*Implementando conceitos aprendidos na aula sobre list views*/
        //Implementacoes quanto a o convertView ainda serão levados em consideração
        //O formato foi diferente pois estou lidando com um  Fragment e não com uma activity
        @SuppressLint("RestrictedApi") View myView =  act.getLayoutInflater(savedInstanceState).inflate(R.layout.item_lista_disciplina,parent,false);

        DisciplinaDTO disc = listaDisciplinas.get(position);

        TextView tvNomeDisciplina = (TextView) myView.findViewById(R.id.tvNomeDisciplina);
        TextView qtdItens = (TextView) myView.findViewById(R.id.tvQtdItens);
        TextView ultimaAtt = (TextView) myView.findViewById(R.id.tvUltimaAtt);
        TextView tvAvaliacaoGeral = (TextView) myView.findViewById(R.id.tvAvaliacaoGeral);

        tvNomeDisciplina.setText(disc.getNome());
        qtdItens.setText(String.valueOf(disc.getQtdItens()));
        ultimaAtt.setText("Ultima atualização: "+disc.getUltimaAtt());
        tvAvaliacaoGeral.setText("Avaliacao geral: "+String.valueOf(disc.getAvaliacaoGeral()));

        return myView;
    }
}

