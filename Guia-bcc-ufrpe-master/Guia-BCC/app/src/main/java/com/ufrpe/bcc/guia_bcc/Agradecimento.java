package com.ufrpe.bcc.guia_bcc;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class Agradecimento extends AppCompatActivity {
   
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agradecimento);

        Button btn_finalizar = (Button) findViewById(R.id.btn_finalizar_avaliacao);
        btn_finalizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), CamposUsuario.class);
                startActivity(i);
            }
        });
    }
}
