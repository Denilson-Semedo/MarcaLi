package com.example.marcali;


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
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;
import java.io.IOException;
import java.io.OutputStream;


public class Perfil extends AppCompatActivity {

    ImageView perfilPic;

    static final int FROM_CAMERA_CODE = 1, FROM_GALLERY_CODE = 2;

    OutputStream outputStream;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);

        perfilPic = findViewById(R.id.perfilPic);
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


    public void msg(Context c, String s) {
        Toast.makeText(c, s, Toast.LENGTH_SHORT).show();
    }
}