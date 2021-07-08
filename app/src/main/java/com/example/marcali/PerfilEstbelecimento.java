package com.example.marcali;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class PerfilEstbelecimento extends AppCompatActivity {

    Dialog addServ;
    EditText nome, preco;
    Button add;
    ImageButton adicionar;

    FirebaseDatabase rootNode;
    DatabaseReference reference;

    public String SHARED_PREFS = "sharedPrefs";
    public String USER = "user";

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

    public String getUser() {
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        return sharedPreferences.getString(USER,"");
    }

    public void msg(Context c, String s) {
        Toast.makeText(c, s, Toast.LENGTH_SHORT).show();
    }

    public void addServico(){

        rootNode = FirebaseDatabase.getInstance();
        reference = rootNode.getReference("servicos");

        addServ.setContentView(R.layout.add_servico);

        add = addServ.findViewById(R.id.adicionar);
        preco = (EditText) addServ.findViewById(R.id.preco);
        nome = (EditText) addServ.findViewById(R.id.nome);

        addServ.show();

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nomeServ = nome.getText().toString();
                String precoServ = preco.getText().toString();
                String userServ = getUser();

                Servico servico = new Servico( nomeServ, userServ, precoServ);
                reference.child(nomeServ+userServ).setValue(servico);

                msg(getApplicationContext(), "Servico adicionado com sucesso!");
            }
        });

    }
}