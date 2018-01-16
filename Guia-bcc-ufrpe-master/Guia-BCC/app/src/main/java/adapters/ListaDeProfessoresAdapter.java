package adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.ufrpe.bcc.guia_bcc.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import beans.Professor;

/**
 * Created by Fabio on 08/12/2017.
 */

public class ListaDeProfessoresAdapter extends BaseExpandableListAdapter {

    private ArrayList<Professor> listaProfessores;
    private HashMap<Professor, List<Professor>> listaDetalhesProfessores;
    private Context context;

    public ListaDeProfessoresAdapter(ArrayList<Professor> listaProfessores, HashMap<Professor, List<Professor>> listaDetalhesProfessores, Context context) {
        this.listaProfessores = listaProfessores;
        this.listaDetalhesProfessores = listaDetalhesProfessores;
        this.context = context;
    }

    @Override
    public int getGroupCount() {
        // retorna a quantidade de grupos
        return this.listaProfessores.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        // retorna a quantidade de itens de um grupo,
        // como todos os grupos apenas terá um item, retorna sempre 1
        return 1;
    }

    @Override
    public Object getGroup(int groupPosition) {
        // retorna um grupo
        return listaProfessores.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        // retorna um item do grupo
        return listaDetalhesProfessores.get(getGroup(groupPosition)).get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        // retorna o id do grupo, nesse caso, o id do professor
        //return listaProfessores.get(groupPosition).getID();
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        // retorna o id do item do grupo, que também será o id do professor
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        // retorna se os ids são específicos (únicos para cada
        // grupo ou item) ou relativos
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        // cria os itens principais (grupos)
        View myView;
        ViewHolderListaProfessor holder;
        Professor prof = listaProfessores.get(groupPosition);

        if(convertView == null){
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(
                    Context.LAYOUT_INFLATER_SERVICE);
            myView = layoutInflater.inflate(R.layout.item_lista_professor, null);
            holder = new ViewHolderListaProfessor(myView);
            myView.setTag(holder);

            holder.mNomeProfessor.setText(prof.getNome());
            holder.mSemestre.setText(prof.getSemestreLecionado());
        }
        else {
            myView = convertView;
            holder = (ViewHolderListaProfessor) myView.getTag();
        }
        return myView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        // cria os subitens (itens dos grupos)
        View myView;
        ViewHolderListaProfessor holder;
        Professor prof = listaProfessores.get(groupPosition);

        if(convertView == null){
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(
                    Context.LAYOUT_INFLATER_SERVICE);
            myView = layoutInflater.inflate(R.layout.item_lista_professor_detalhe, null);
            holder = new ViewHolderListaProfessor(myView);
            myView.setTag(holder);
        }
        else {
            myView = convertView;
            holder = (ViewHolderListaProfessor) myView.getTag();
        }

        holder.mQtdTotalAlunos.setText(prof.getQtdAlunos()+"");
        holder.mQtdAlunosAvaliaram.setText(prof.getQtdAlunosAvaliaram()+"");
        holder.mDificuldadeProfessor.setText(prof.getAvaliacaoDificuldade()+"");
        holder.mConteudoProfessor.setText(prof.getAvaliacaoConteudo()+"");
        holder.mClarezaProfessor.setText(prof.getAvaliacaoClareza()+"");
        holder.mEsforcoProfessor.setText(prof.getAvaliacaoEsforco()+"");

        return myView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        // retorna se o subitem (item do grupo) é selecionável
        return false;
    }
}

class ViewHolderListaProfessor{

    TextView mNomeProfessor;
    TextView mSemestre;
    TextView mQtdTotalAlunos;
    TextView mQtdAlunosAvaliaram;
    TextView mDificuldadeProfessor;
    TextView mConteudoProfessor;
    TextView mClarezaProfessor;
    TextView mEsforcoProfessor;

    ViewHolderListaProfessor(View myView){
        mNomeProfessor = (TextView) myView.findViewById(R.id.tvNomeProf);
        mSemestre =  (TextView) myView.findViewById(R.id.tvSemestre);
        this.mQtdTotalAlunos = (TextView) myView.findViewById(R.id.tvQtdTotalAlunos);
        this.mQtdAlunosAvaliaram = (TextView) myView.findViewById(R.id.tvQtdAlunosAvaliaram);
        this.mDificuldadeProfessor = (TextView) myView.findViewById(R.id.tvDificuldadeProfessor);
        this.mConteudoProfessor = (TextView) myView.findViewById(R.id.tvConteudoProfessor);
        this.mClarezaProfessor = (TextView) myView.findViewById(R.id.tvClarezaProfessor);
        this.mEsforcoProfessor = (TextView) myView.findViewById(R.id.tvEsforcoProfessor);
    }
}

