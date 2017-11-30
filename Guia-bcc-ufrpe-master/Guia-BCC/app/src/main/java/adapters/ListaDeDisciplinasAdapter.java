package adapters;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.ufrpe.bcc.guia_bcc.R;

import org.w3c.dom.Text;

import java.util.List;

import beans.Disciplina;
import beans.DisciplinaCursada;
import butterknife.BindView;

/**
 * Created by Ismael on 21/11/2017.
 * Adapter para itens de listagem de disciplina
 */

public class ListaDeDisciplinasAdapter extends BaseAdapter {

    private List<Disciplina> listaDisciplinas;
    private Fragment act;
    private Bundle savedInstanceState;
/*
    //atributos da tela
    @BindView(R.id.nomeDisciplina) TextView nomeDisciplina;
    @BindView(R.id.qtdItens) TextView qtdItens;
    @BindView(R.id.txtQtdItem) TextView txtQtdItens;
    @BindView(R.id.avaliacaoGeral) TextView avaliacaoGeral;
    @BindView(R.id.dataUltimaAtt) TextView dataUltimaAtt;*/

    public ListaDeDisciplinasAdapter(List<Disciplina> listaDisciplinas, Fragment act, Bundle savedInstanceState ){
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

        View myView =  act.getLayoutInflater(savedInstanceState).inflate(R.layout.item_lista_questoes,parent,false);

        Disciplina disc = listaDisciplinas.get(position);

        TextView nomeDisciplina = (TextView) myView.findViewById(R.id.nomeDisciplina);
        TextView qtdItens = (TextView) myView.findViewById(R.id.qtdItens);
        TextView dataUltimaAtt = (TextView) myView.findViewById(R.id.dataUltimaAtt);
        TextView avaliacaoGeral = (TextView) myView.findViewById(R.id.avaliacaoGeral);
        TextView txtQtdItens = (TextView) myView.findViewById(R.id.txtQtdItem);

        nomeDisciplina.setText(disc.getNomeDisciplina());
        qtdItens.setText(disc.getQtdItens()+"");
        dataUltimaAtt.setText(disc.getUltimaAtt());

        float avGeral = disc.getAvaliacaoGeral();
        String txtAvGeral;
        if(avGeral > 75){
            txtAvGeral = "Ótima";
        }else if(avGeral > 50){
            txtAvGeral = "Boa";
        }else if(avGeral > 25){
            txtAvGeral = "Regular";
        }else{
            txtAvGeral = "Ruim";
        }
        avaliacaoGeral.setText(txtAvGeral);
        txtQtdItens.setText(disc.getQtdItens() > 1 ? "itens disponíveis" : "item disponível");

        return myView;
    }
}

