package com.example.marcali;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.dynamic.IFragmentWrapper;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.jetbrains.annotations.NotNull;

import java.time.LocalDate;
import java.time.Year;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

@RequiresApi(api = Build.VERSION_CODES.O)
public class PerfilEstbelecimento extends AppCompatActivity{

    Dialog addServ;
    EditText nome, preco, coment;
    TextView userN;
    Button add, comentar;
    ImageButton adicionarServ, adicionarComent;
    RatingBar ratingBar;

    RecyclerView servView, comentView;
    ServicoAdapter servicoAdapter;
    ComentarioAdapter comentarioAdapter;
    ArrayList<Servico> servList;
    ArrayList<Comentario> comentList;
    FirebaseDatabase rootNode;
    DatabaseReference referenceServ, referenceComent, reference;

    public String SHARED_PREFS = "sharedPrefs";
    public String USER = "user";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil_estbelecimento);

        servView = findViewById(R.id.ServicoView);
        comentView = findViewById(R.id.comentVieww);

        referenceServ = FirebaseDatabase.getInstance().getReference("servicos");
        referenceComent = FirebaseDatabase.getInstance().getReference("comentarios");
        servView.setHasFixedSize(true);
        comentView.setHasFixedSize(true);
        servView.setLayoutManager(new LinearLayoutManager(this));
        comentView.setLayoutManager(new LinearLayoutManager(this));

        servList = new ArrayList<>();
        comentList = new ArrayList<>();
        servicoAdapter = new ServicoAdapter(this,servList);
        comentarioAdapter = new ComentarioAdapter(this,comentList);
        servView.setAdapter(servicoAdapter);
        comentView.setAdapter(comentarioAdapter);

        referenceServ.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                    Servico servico = dataSnapshot.getValue(Servico.class);
                    servList.add(servico);
                }
                servicoAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {

            }
        });

        referenceComent.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                    Comentario comentario = dataSnapshot.getValue(Comentario.class);
                    comentList.add(comentario);
                }
                servicoAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {

            }
        });

        /*###POPULACIONANDO A LISTA DE COMENTÁRIOS###

        comentView = findViewById(R.id.comentVieww);
        reference = FirebaseDatabase.getInstance().getReference("comentarios");
        comentView.setHasFixedSize(true);
        comentView.setLayoutManager(new LinearLayoutManager(this));

        comentarios = new ArrayList<>();
        comentarioAdapter = new ComentarioAdapter(this,comentarios);
        comentView.setAdapter(comentarioAdapter);

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                    Comentario comentario = dataSnapshot.getValue(Comentario.class);
                    comentarios.add(comentario);
                }
                comentarioAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {

            }
        }); */

        /*###POPULACIONANDO A LISTA DE COMENTÁRIOS###*/

        //reference = FirebaseDatabase.getInstance().getReference("servicos");
        //servView.setHasFixedSize(true);


        //servicos = new ArrayList<>();



        /*reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                    Servico servico = dataSnapshot.getValue(Servico.class);
                    servicos.add(servico);
                }
                //servicoAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });*/

        addServ = new Dialog(this);

        adicionarServ = findViewById(R.id.adicionarServico);
        adicionarComent = findViewById(R.id.adicionarComentario);

        adicionarServ.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addServico();
            }
        });

        adicionarComent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(
                        PerfilEstbelecimento.this, R.style.BottomSheetDialogTheme
                );
                View bottomSheetView = LayoutInflater.from(getApplicationContext())
                        .inflate(
                                R.layout.comentario_bottom_sheet,
                                (LinearLayout)findViewById(R.id.comentarioContainer)
                        );


                bottomSheetDialog.setContentView(bottomSheetView);
                bottomSheetDialog.show();
                comentar = bottomSheetView.findViewById(R.id.comentar);

                comentar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        coment = bottomSheetView.findViewById(R.id.comentario);
                        userN = bottomSheetView.findViewById(R.id.user);
                        ratingBar = bottomSheetView.findViewById(R.id.ratingBar);

                        float rating = ratingBar.getRating();
                        String comentario = coment.getText().toString();
                        String utilizador = userN.getText().toString();
                        fazerComentario(comentario, utilizador, rating);
                    }
                });
            }
        });

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
    }

    private void PopulacionarServicos() {
       //*
    }

    public String getUser() {
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        return sharedPreferences.getString(USER,"");
    }

    public void msg(Context c, String s) {
        Toast.makeText(c, s, Toast.LENGTH_SHORT).show();
    }

    @SuppressLint("WrongViewCast")
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

    public void fazerComentario(String comentario, String utilizador, float rating) {

        rootNode = FirebaseDatabase.getInstance();
        reference = rootNode.getReference("comentarios");

        String estabelecimento = "sesar"; //getIntent().getStringExtra("estabelecimento");

        Comentario comentario1 = new Comentario(utilizador, estabelecimento, comentario, rating);
        reference.child(estabelecimento).setValue(comentario1);
        msg(this, "Comentário feito:" + rating);
    }
}