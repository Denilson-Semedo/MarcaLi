package com.example.marcali;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

public class PerfilEstbelecimento extends AppCompatActivity {

    Dialog addServ;
    EditText nome, preco;
    Button add;
    ImageButton adicionar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil_estbelecimento);

        addServ = new Dialog(this);

        adicionar = findViewById(R.id.adicionar);

        adicionar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addServico();
            }
        });
    }

    public void addServico(){

        addServ.setContentView(R.layout.add_servico);

        add = addServ.findViewById(R.id.adicionar);

        addServ.show();



    }
}