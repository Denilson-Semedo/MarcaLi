package com.example.marcali;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.textfield.TextInputLayout;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.io.OutputStream;


public class Perfil extends AppCompatActivity {

    ImageView perfilPic;
    TextView field_user;
    TextInputLayout field_nome, field_email, field_telefone, field_morada, field_password;

    static final int FROM_CAMERA_CODE = 1, FROM_GALLERY_CODE = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);

        //hooks
        field_nome = findViewById(R.id.nome);
        field_email = findViewById(R.id.email);
        field_telefone = findViewById(R.id.telefone);
        field_morada = findViewById(R.id.morada);
        field_password = findViewById(R.id.password);
        field_user = findViewById(R.id.user);


        perfilPic = findViewById(R.id.perfilPic);

        //Initialize ANDA ASSIGN VARIABLES
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);

        //set home selected
        bottomNavigationView.setSelectedItemId(R.id.perfil);

        //perform itemSelectedListener
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull @NotNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.search:
                        startActivity(new Intent(getApplicationContext()
                                ,Pesquisar.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.home:
                        startActivity(new Intent(getApplicationContext()
                                ,Principal.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.agendamento:
                        startActivity(new Intent(getApplicationContext()
                                ,Agendamentos.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.perfil:
                        return true;
                }
                return false;
            }
        });

        prencherDados();

    }

    public void changePhoto(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(Perfil.this);
        builder.setTitle("Carregar Fotografia de:");
        builder.setItems(R.array.origens, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (which == 0){

                    Intent fromCamera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    try {
                        if (fromCamera.resolveActivity(getPackageManager()) != null) {
                            startActivityForResult(fromCamera, FROM_CAMERA_CODE);
                        }
                    } catch (ActivityNotFoundException e) {
                        msg(Perfil.this, e.getMessage());
                    }

                }else if(which == 1){

                    Intent fromGallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    try {
                        startActivityForResult(fromGallery, FROM_GALLERY_CODE);
                    } catch (ActivityNotFoundException e) {
                        msg(Perfil.this, e.getMessage());
                    }

                }
            }
        });

        builder.create();
        builder.show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if( requestCode == FROM_CAMERA_CODE && resultCode == RESULT_OK){

            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            perfilPic.setImageBitmap(imageBitmap);

        }else if(requestCode == FROM_GALLERY_CODE && resultCode == RESULT_OK){

            Uri imageFromGal = data.getData();

            try {
                Bitmap imageBitmap = MediaStore.Images.Media.getBitmap(getApplicationContext().getContentResolver(), imageFromGal);
                perfilPic.setImageBitmap(imageBitmap);

            }catch(IOException e){
                msg(Perfil.this, e.getMessage());
            }

        }
    }

    public void prencherDados(){
        Intent intent = getIntent();
        String user_username = intent.getStringExtra("username");
        String user_nome = intent.getStringExtra("nome");
        String user_email = intent.getStringExtra("email");
        String user_telefone = intent.getStringExtra("telefone");
        String user_morada = intent.getStringExtra("morada");
        String user_password = intent.getStringExtra("password");

        field_email.getEditText().setText(user_email);
        field_nome.getEditText().setText(user_nome);
        field_telefone.getEditText().setText("+238 " + user_telefone);
        field_morada.getEditText().setText(user_morada);
        field_password.getEditText().setText(user_password);
        field_user.setText(user_username);
    }

    public void msg(Context c, String s) {
        Toast.makeText(c, s, Toast.LENGTH_SHORT).show();
    }
}