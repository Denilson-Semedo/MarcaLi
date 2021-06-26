package com.example.marcali;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskExecutors;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

import static android.content.ContentValues.TAG;

public class Verificar extends AppCompatActivity {

    Button verificar;
    EditText primeiro, segundo, terceiro, quarto;
    ProgressBar progress_bar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verificar);

        verificar = findViewById(R.id.verificar);
        progress_bar = findViewById(R.id.progress_bar);
        primeiro = findViewById(R.id.primeiro);
        segundo = findViewById(R.id.segundo);
        terceiro = findViewById(R.id.terceiro);
        quarto = findViewById(R.id.quarto);

        String numeroTelefone = getIntent().getStringExtra("telefone");

        //enviarCodigo(numeroTelefone);
    }

    /*private void enviarCodigo(String numeroTelefone) {
        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                "+238"+numeroTelefone, 60, TimeUnit.SECONDS, this,
                new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                    @Override
                    public void onVerificationCompleted(PhoneAuthCredential phoneAuthCredential) {
                        signInUser(phoneAuthCredential);
                    }

                    @Override
                    public void onVerificationFailed(FirebaseException e) {
                        Log.d(TAG, "onVerificationFailed:"+e.getLocalizedMessage());
                    }

                    @Override
                    public void onCodeSent(final String verificationId, PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                        super.onCodeSent(verificationId, forceResendingToken);
                        //
                        Dialog dialog = new Dialog(this);
                        dialog.setContentView(R.layout.verify_popup);

                        final EditText etVerifyCode = dialog.findViewById(R.id.etVerifyCode);
                        Button btnVerifyCode = dialog.findViewById(R.id.btnVerifyOTP);
                        btnVerifyCode.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                String verificationCode = etVerifyCode.getText().toString();
                                if(verificationId.isEmpty()) return;
                                //create a credential
                                PhoneAuthCredential credential=PhoneAuthProvider.getCredential(verificationId,verificationCode);
                                signInUser(credential);
                            }
                        });

                        dialog.show();
                    }
                });
    }*/

}