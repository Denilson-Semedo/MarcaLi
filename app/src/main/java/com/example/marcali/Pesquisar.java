package com.example.marcali;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class Pesquisar extends AppCompatActivity {

    RecyclerView recView;
    DatabaseReference reference;
    PesquisarAdapter pesquisarAdapter;
    ArrayList<Estabelecimento> list;

    EditText pesquisar;
    ImageButton pesquisarBtn;
    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pesquisar);

        recView = findViewById(R.id.recView);

        reference = FirebaseDatabase.getInstance().getReference("estabelecimentos");
        recView.setHasFixedSize(true);
        recView.setLayoutManager(new LinearLayoutManager(this));

        list = new ArrayList<>();
        pesquisarAdapter = new PesquisarAdapter(this,list);
        recView.setAdapter(pesquisarAdapter);

        pesquisar = findViewById(R.id.pesquisar);
        pesquisarBtn = findViewById(R.id.pesquisarBtn);

        bottomNavigationView = findViewById(R.id.bottom_navigation);

        bottomNavigationView.setSelectedItemId(R.id.search);

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                    Estabelecimento estabelecimento = dataSnapshot.getValue(Estabelecimento.class);
                    list.add(estabelecimento);
                }
                pesquisarAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {

            }
        });

        pesquisarBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //pesquisarFirebase();
            }
        });



        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull @NotNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.search:
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
                        startActivity(new Intent(getApplicationContext()
                                ,Perfil.class));
                        overridePendingTransition(0,0);
                        return true;
                }
                return false;
            }
        });
    }



    /*public void pesquisarFirebase(){
        PesquisarAdapter<Estabelecimento, Estabelecimento.ViewHolder>
    }*/
}