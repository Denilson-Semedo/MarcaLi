package com.example.marcali;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.material.textfield.TextInputLayout;
//import com.google.firebase.database.DatabaseReference;
//import com.google.firebase.database.FirebaseDatabase;

public class SingUpUser extends AppCompatActivity {

    //variaveis
    TextInputLayout name, email, userName, pass, pass2;
    Button registrar;

    //FirebaseDatabase rootNode;
    //DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sing_up_user);

        //hooks to all xml elements in SignUpUser.xml
        name  = findViewById(R.id.nome);
        email  = findViewById(R.id.email);
        userName  = findViewById(R.id.username);
        pass  = findViewById(R.id.password);
        pass2  = findViewById(R.id.password2);
        registrar  = findViewById(R.id.registrar);

        //Save data in firebase on button click
        registrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //rootNode = FirebaseDatabase.getInstance();
                //reference = rootNode.getReference("users");

                //get all the values
                String nome = name.getEditText().getText().toString();
                String username = userName.getEditText().getText().toString();
                String mail = email.getEditText().getText().toString();
                String password = pass2.getEditText().getText().toString();
                String tipo = getIntent().getExtras().getString("tipo");


                UserHelper helper = new UserHelper(tipo, nome, username, mail, password);

               // reference.child(username).setValue(helper);
                validarNome();
                validarUserName();
                validarEmail();
                validarPassword();
                validarPassword2();

            }
        });
    }

    private Boolean validarNome(){
        String val = name.getEditText().getText().toString();

        if(val.isEmpty()){
            name.setError("O campo não pode estar Vazio!");
            return false;
        } else {
            name.setError(null);
            name.setErrorEnabled(false);
            return true;
        }
    }

    private Boolean validarUserName(){
        String val = userName.getEditText().getText().toString();
        String space = "(?=\\S+$)";

        if(val.isEmpty()){
            userName.setError("O campo não pode estar Vazio!");
            return false;
        } else if (val.length() >= 15) {
            userName.setError("O nome utilizador tem de ser mais curto!");
            return false;
        } else if (!val.matches(space)) {
            userName.setError("Não é permitido espaços neste campo!");
            return false;
        } else {
            userName.setError(null);
            userName.setErrorEnabled(false);
            return true;
        }
    }

    private Boolean validarEmail(){
        String val = email.getEditText().getText().toString();
        String campoEmail = "[a-zA-Z0-9._-]@+[a-z]+\\.+[a-z]+";

        if(val.isEmpty()){
            email.setError("O campo não pode estar Vazio!");
            return false;
        } else if (!val.matches(campoEmail)) {
            email.setError("Endereço email invalido!");
            return false;
        } else {
            email.setError(null);
            return true;
        }
    }

    private Boolean validarPassword(){
        String val = pass.getEditText().getText().toString();
        String campoPassword = "^" +
                "(?=.*[0-9])" +
                "(?=.*[a-z])" +
                "(?=.*[A-Z])" +
                "(?=.*[a-zA-Z])" +
                "(?=.*[@#$%^&+=])" +
                "(?=\\S+$)" +
                ".{4,}" +
                "$";

        if(val.isEmpty()){
            pass.setError("O campo não pode estar Vazio!");
            return false;
        } else if (!val.matches(campoPassword)) {
            pass.setError("Password fraco!!");
            return false;
        } else {
            pass.setError(null);
            return true;
        }
    }

    private Boolean validarPassword2(){
        String val = pass2.getEditText().getText().toString();

        if(val.isEmpty()){
            pass2.setError("Por favor confirmar Password!");
            return false;
        } else {
            pass2.setError(null);
            return true;
        }
    }

}