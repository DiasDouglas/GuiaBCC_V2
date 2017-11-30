package com.ufrpe.bcc.guia_bcc;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import beans.Pergunta;
import beans.Perguntas;

public class PerguntasAvaliacao extends AppCompatActivity {

    private RadioGroup radioGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perguntas_avaliacao);

        Intent anterior = getIntent();

        if(anterior.getStringExtra("tipo").equals("avaliar_disciplina")){
            // Pegando o numero da questão
            final int numQuestao = anterior.getIntExtra("num_pergunta", 0);

            // Pegando o nome da disciplina e do professor
            String aux;
            if((anterior.getStringExtra("disciplina") != null) && (anterior.getStringExtra("professor") != null)){
                aux = anterior.getStringExtra("disciplina") + "\n" + anterior.getStringExtra("professor");
            } else {
                aux = anterior.getStringExtra("disciplinaAvaliada");
            }
            final String tipoAvaliacao = aux;

            // Instanciar perguntas para testes
            final Perguntas listaDePerguntas = new Perguntas();
            listaDePerguntas.setLista_perguntas(criarPerguntasDisciplina());

            if (numQuestao <= listaDePerguntas.getLista_perguntas().size()) {   // Se não chegou ao fim das questões

                // Carregar perguntas
                TextView txt_vw_numPergunta = (TextView) findViewById(R.id.txt_vw_numPergunta);
                TextView txtvw_pergunta = (TextView) findViewById(R.id.txtvw_pergunta);
                TextView txt_vw_tipoAvaliacao = (TextView) findViewById(R.id.txt_vw_tipoAvaliacao);
                Button btn_cancelar = (Button) findViewById(R.id.btn_cancelar);
                Button btn_continuar = (Button) findViewById(R.id.btn_continuar);
                radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
                RadioButton rb_resposta1 = (RadioButton) findViewById(R.id.rb_resposta1);
                RadioButton rb_resposta2 = (RadioButton) findViewById(R.id.rb_resposta2);
                RadioButton rb_resposta3 = (RadioButton) findViewById(R.id.rb_resposta3);
                RadioButton rb_resposta4 = (RadioButton) findViewById(R.id.rb_resposta4);

                txt_vw_numPergunta.setText(numQuestao + ".");
                txtvw_pergunta.setText((CharSequence) listaDePerguntas.procurarPergunta(numQuestao).getQuestao());
                txt_vw_tipoAvaliacao.setText(tipoAvaliacao);
                rb_resposta1.setText((CharSequence) listaDePerguntas.procurarPergunta(numQuestao).getResposta_1());
                rb_resposta2.setText((CharSequence) listaDePerguntas.procurarPergunta(numQuestao).getResposta_2());
                rb_resposta3.setText((CharSequence) listaDePerguntas.procurarPergunta(numQuestao).getResposta_3());
                rb_resposta4.setText((CharSequence) listaDePerguntas.procurarPergunta(numQuestao).getResposta_4());

                btn_continuar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (radioGroup.getCheckedRadioButtonId() != -1) {
                            int radioButtonID = radioGroup.getCheckedRadioButtonId();
                            RadioButton rd = (RadioButton) findViewById(radioButtonID);
                            Intent i = new Intent(PerguntasAvaliacao.this, PerguntasAvaliacao.class);
                            i.putExtra("num_pergunta", numQuestao + 1);
                            i.putExtra("disciplinaAvaliada", tipoAvaliacao);
                            i.putExtra("tipo", "avaliar_disciplina");
                            startActivity(i);
                        } else {
                            Toast alerta = Toast.makeText(PerguntasAvaliacao.this, R.string.nenhuma_opcao_selecionada, Toast.LENGTH_LONG);
                            alerta.show();
                        }
                    }
                });
                btn_cancelar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent i = new Intent(PerguntasAvaliacao.this, CamposUsuario.class);
                        startActivity(i);
                    }
                });
            } else {
                Intent i = new Intent(PerguntasAvaliacao.this, Agradecimento.class);
                startActivity(i);
            }
        } else if(anterior.getStringExtra("tipo").equals("avaliar_professor")) {
            // Pegando o numero da questão
            final int numQuestao = anterior.getIntExtra("num_pergunta", 0);

            // Pegando o nome da disciplina e do professor
            String aux;
            if((anterior.getStringExtra("disciplina") != null) && (anterior.getStringExtra("professor") != null)){
                aux = anterior.getStringExtra("professor") + "\n" + anterior.getStringExtra("disciplina");
            } else {
                aux = anterior.getStringExtra("professorAvaliado");
            }
            final String tipoAvaliacao = aux;

            // Instanciar perguntas para testes
            final Perguntas listaDePerguntas = new Perguntas();
            listaDePerguntas.setLista_perguntas(criarPerguntasProfessor());

            if (numQuestao <= listaDePerguntas.getLista_perguntas().size()) {   // Se não chegou ao fim das questões

                // Carregar perguntas
                TextView txt_vw_numPergunta = (TextView) findViewById(R.id.txt_vw_numPergunta);
                TextView txtvw_pergunta = (TextView) findViewById(R.id.txtvw_pergunta);
                TextView txt_vw_tipoAvaliacao = (TextView) findViewById(R.id.txt_vw_tipoAvaliacao);
                Button btn_cancelar = (Button) findViewById(R.id.btn_cancelar);
                Button btn_continuar = (Button) findViewById(R.id.btn_continuar);
                radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
                RadioButton rb_resposta1 = (RadioButton) findViewById(R.id.rb_resposta1);
                RadioButton rb_resposta2 = (RadioButton) findViewById(R.id.rb_resposta2);
                RadioButton rb_resposta3 = (RadioButton) findViewById(R.id.rb_resposta3);
                RadioButton rb_resposta4 = (RadioButton) findViewById(R.id.rb_resposta4);

                txt_vw_numPergunta.setText(numQuestao + ".");
                txtvw_pergunta.setText((CharSequence) listaDePerguntas.procurarPergunta(numQuestao).getQuestao());
                txt_vw_tipoAvaliacao.setText(tipoAvaliacao);
                rb_resposta1.setText((CharSequence) listaDePerguntas.procurarPergunta(numQuestao).getResposta_1());
                rb_resposta2.setText((CharSequence) listaDePerguntas.procurarPergunta(numQuestao).getResposta_2());
                rb_resposta3.setText((CharSequence) listaDePerguntas.procurarPergunta(numQuestao).getResposta_3());
                rb_resposta4.setText((CharSequence) listaDePerguntas.procurarPergunta(numQuestao).getResposta_4());

                btn_continuar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (radioGroup.getCheckedRadioButtonId() != -1) {
                            int radioButtonID = radioGroup.getCheckedRadioButtonId();
                            RadioButton rd = (RadioButton) findViewById(radioButtonID);
                            Intent i = new Intent(PerguntasAvaliacao.this, PerguntasAvaliacao.class);
                            i.putExtra("num_pergunta", numQuestao + 1);
                            i.putExtra("professorAvaliado", tipoAvaliacao);
                            i.putExtra("tipo", "avaliar_professor");
                            startActivity(i);
                        } else {
                            Toast alerta = Toast.makeText(PerguntasAvaliacao.this, R.string.nenhuma_opcao_selecionada, Toast.LENGTH_LONG);
                            alerta.show();
                        }
                    }
                });
                btn_cancelar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent i = new Intent(PerguntasAvaliacao.this, CamposUsuario.class);
                        startActivity(i);
                    }
                });
            } else {
                Intent i = new Intent(PerguntasAvaliacao.this, Agradecimento.class);
                startActivity(i);
            }
        }
    }

    public ArrayList<Pergunta> criarPerguntasProfessor() {
        //As perguntas serão geradas aqui para fins de testes

        Pergunta p1 = new Pergunta(1, "O professor é de boa? ",
                "Sim.", "Maizoumenos","Err, não....","Noup.");
        Pergunta p2 = new Pergunta(2, "Classifique a metodologia do professor: ",
                "É uma ótima metodologia.","Metodologia boa.", "Ruim.",
                "Inadequada.");
        Pergunta p3 = new Pergunta(3, "O professor sempre chega no horário? ",
                "Sempre.", "Quase sempre.","Quase nunca.","Nunca.");
        Pergunta p4 = new Pergunta(4, "O professor dá pontos? ",
                "Com frequência.", "Sim.","Quase nunca.","Não :(");

        Perguntas listaDePerguntasProfessor = new Perguntas();

        listaDePerguntasProfessor.adicionarPergunta(p1);
        listaDePerguntasProfessor.adicionarPergunta(p2);
        listaDePerguntasProfessor.adicionarPergunta(p3);
        listaDePerguntasProfessor.adicionarPergunta(p4);

        return listaDePerguntasProfessor.getLista_perguntas();
    }

    public ArrayList<Pergunta> criarPerguntasDisciplina(){
        //As perguntas serão geradas aqui para fins de testes

        Pergunta p1 = new Pergunta(1, "Classifique a ementa da disciplina: ",
                "Ótima. A ementa abrange conteúdos relevantes e atualizados.", "Boa",
                "Ruim. A ementa é incompleta e/ou desatualizada.",
                "Péssima.");
        Pergunta p2 = new Pergunta(2, "Classifique os conteúdos abordados na disciplina: ",
                "Ótimos conteúdos, adequados ao curso e ao aprendizado em geral.",
                "Bons conteúdos.", "Ruins. Os conteúdos contribuem pouco ao aprendizado.",
                "Péssimos. Os conteúdos não contribuem em nada com o aprendizado.");
        Pergunta p3 = new Pergunta(3, "Classifique as referências bibliográficas utilizadas na disciplina: ",
                "Ótimas referências bibliográficas.", "Boas referências bibliográficas.",
                "Ruins. As referências não são muito adequadas.",
                "Péssimas. As referências bibliográficas não contribuem em nada com a disciplina.");
        Pergunta p4 = new Pergunta(4, "Classifique os materiais de apoio da disciplina: ",
                "Ótimos materiais de apoio.", "Bons materiais de apoio.",
                "Ruins. Os materiais de apoio contribuem pouco para o aprendizado.",
                "Péssimos. Os materiais de apoio não contribuem de forma alguma com a disciplina.");

        Perguntas listaDePerguntasDisciplina = new Perguntas();

        listaDePerguntasDisciplina.adicionarPergunta(p1);
        listaDePerguntasDisciplina.adicionarPergunta(p2);
        listaDePerguntasDisciplina.adicionarPergunta(p3);
        listaDePerguntasDisciplina.adicionarPergunta(p4);

        return listaDePerguntasDisciplina.getLista_perguntas();
    }

}
