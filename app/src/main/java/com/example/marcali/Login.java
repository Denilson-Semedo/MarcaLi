package com.example.marcali;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class Login extends AppCompatActivity {

    //variaveis
    TextInputLayout userName, pass;
    Button cadastro, login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        userName  = findViewById(R.id.username);
        pass  = findViewById(R.id.password);
        login  = findViewById(R.id.login);
        cadastro = findViewById(R.id.cadastre);

        cadastro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switchActivities();
            }
        });

    }

    private void switchActivities() {
        Intent switchActivityIntent = new Intent(this, SingUp1.class);
        startActivity(switchActivityIntent);
    }

    private Boolean validarPassword(){
        String val = pass.getEditText().getText().toString();

        if(val.isEmpty()){
            pass.setError("O campo não pode estar Vazio!");
            return false;
        } else {
            pass.setError(null);
            return true;
        }
    }

    private Boolean validarUserName(){
        String val = userName.getEditText().getText().toString();

        if(val.isEmpty()){
            userName.setError("O campo não pode estar Vazio!");
            return false;
        } else {
            userName.setError(null);
            userName.setErrorEnabled(false);
            return true;
        }
    }

    public void entrar(View view){
        if (!validarUserName() | !validarPassword()) {
            return;
        } else {
            verificarUser();
        }
    }

    private void verificarUser() {
        String userNameInput = userName.getEditText().getText().toString().trim();
        String passwordInput = pass.getEditText().getText().toString().trim();

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("users");

        Query validarUser = reference.orderByChild("username").equalTo(userNameInput);

        validarUser.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                if (dataSnapshot.exists()){

                    userName.setError(null);
                    userName.setErrorEnabled(false);

                    String passwordd = dataSnapshot.child(userNameInput).child("password").getValue(String.class);

                    if (passwordd.equals(passwordInput)) {

                        String BD_nome = dataSnapshot.child(userNameInput).child("nome").getValue(String.class);
                        String BD_username = dataSnapshot.child(userNameInput).child("username").getValue(String.class);
                        String BD_email = dataSnapshot.child(userNameInput).child("email").getValue(String.class);
                        String BD_telefone = dataSnapshot.child(userNameInput).child("telefone").getValue(String.class);
                        String BD_morada = dataSnapshot.child(userNameInput).child("morada").getValue(String.class);
                        String BD_password = dataSnapshot.child(userNameInput).child("password").getValue(String.class);

                        Intent intent = new Intent(getApplicationContext(),Perfil.class);

                        intent.putExtra("nome", BD_nome);
                        intent.putExtra("username", BD_username);
                        intent.putExtra("email", BD_email);
                        intent.putExtra("telefone", BD_telefone);
                        intent.putExtra("morada", BD_morada);
                        intent.putExtra("password", BD_password);

                        startActivity(intent);

                    } else {
                        pass.setError("Password Incorreto!!");
                        pass.requestFocus();
                    }
                } else {
                    userName.setError("Utilizador não encontrado!!");
                    userName.requestFocus();
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }

            /*@Override
            public void onCancelled(@NonNull @org.jetbrains.annotations.NotNull DatabaseError error) {

            }*/
        });
    }
}