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

import beans.DisciplinaDTO;

/**
 * Created by Ismael on 21/11/2017.
 * Adapter para itens de listagem de disciplina
 */

public class ListaDeDisciplinasAdapter extends BaseAdapter {

    private List<DisciplinaDTO> listaDisciplinas;
    private Fragment act;
    private Bundle savedInstanceState;
/*
    //atributos da tela
    @BindView(R.id.nomeDisciplina) TextView nomeDisciplina;
    @BindView(R.id.qtdItens) TextView qtdItens;
    @BindView(R.id.txtQtdItem) TextView txtQtdItens;
    @BindView(R.id.avaliacaoGeral) TextView avaliacaoGeral;
    @BindView(R.id.dataUltimaAtt) TextView dataUltimaAtt;*/

    public ListaDeDisciplinasAdapter(List<DisciplinaDTO> listaDisciplinas, Fragment act, Bundle savedInstanceState ){
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

    @SuppressLint("RestrictedApi")
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        //View myView =  act.getLayoutInflater(savedInstanceState).inflate(R.layout.item_lista_questoes,parent,false);
        View myView;
        ViewHolder holder;
        DisciplinaDTO disc = listaDisciplinas.get(position);

        if(convertView == null){
            myView  = this.act.getLayoutInflater(this.savedInstanceState).inflate(R.layout.item_lista_questoes,parent,false);
            holder = new ViewHolder(myView);
            myView.setTag(holder);

            holder.nomeDisciplina.setText(disc.getNome());
            holder.qtdItens.setText(disc.getQtdItens() + "");
            holder.dataUltimaAtt.setText(disc.getUltimaAtt());
        }
        else {
            myView = convertView;
            holder = (ViewHolder) myView.getTag();
        }

        float avGeral = disc.getAvaliacaoGeral();
        String txtAvGeral;
        if (avGeral > 75) {
            txtAvGeral = "Ótima";
        } else if (avGeral > 50) {
            txtAvGeral = "Boa";
        } else if (avGeral > 25) {
            txtAvGeral = "Regular";
        } else {
            txtAvGeral = "Ruim";
        }
        holder.avaliacaoGeral.setText(txtAvGeral);
        holder.txtQtdItens.setText(disc.getQtdItens() > 1 ? "itens disponíveis" : "item disponível");


        return myView;
    }
}

class ViewHolder{

    TextView nomeDisciplina;
    TextView qtdItens;
    TextView dataUltimaAtt;
    TextView avaliacaoGeral;
    TextView txtQtdItens;

    ViewHolder(View myView){
        nomeDisciplina = (TextView) myView.findViewById(R.id.tvNomeDisciplina);
        qtdItens =  (TextView) myView.findViewById(R.id.qtdItens);
        dataUltimaAtt = (TextView) myView.findViewById(R.id.dataUltimaAtt);
        avaliacaoGeral = (TextView) myView.findViewById(R.id.avaliacaoGeral);
        txtQtdItens = (TextView) myView.findViewById(R.id.txtQtdItem);
    }
}
