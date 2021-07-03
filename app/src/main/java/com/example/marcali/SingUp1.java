package com.example.marcali;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.Toast;

public class SingUp1 extends AppCompatActivity {

    Button continuar;

    RadioButton user;
    RadioButton prop;

    String tipo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sing_up1);

        continuar = findViewById(R.id.continuar);
        user = (RadioButton) findViewById(R.id.userNormal);
        prop = (RadioButton) findViewById(R.id.propietario);

        tipo = "normalUser";


        continuar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { switchActivities(); }
        });
    }

    private void switchActivities() {

        if (!prop.isChecked() && user.isChecked()){
            Intent switchActivityIntent = new Intent(this, SingUpUser.class);
            switchActivityIntent.putExtra("tipo", tipo);
            startActivity(switchActivityIntent);
        }

        if (prop.isChecked() && !user.isChecked()){
            Intent switchActivityIntent = new Intent(this, SingUpEstabelecimento.class);
            switchActivityIntent.putExtra("tipo", tipo);
            startActivity(switchActivityIntent);
        }
    }

    public void onRadioButtonClicked(View view) {
        boolean checked = ((RadioButton) view).isChecked();

        switch(view.getId()) {
            case R.id.userNormal:
                if (checked)
                    msg(this, "Utilizador Normal");
                user.setChecked(true);
                prop.setChecked(false);
                tipo = "normalUser";
                break;
            case R.id.propietario:
                if (checked)
                    msg(this, "Proprietário");
                prop.setChecked(true);
                user.setChecked(false);
                tipo = "proprietário";
                break;
        }
    }

    public void msg(Context c, String s) {
        Toast.makeText(c, s, Toast.LENGTH_SHORT).show();
    }
}
