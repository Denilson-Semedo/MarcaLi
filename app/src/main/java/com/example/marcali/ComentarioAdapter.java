package com.example.marcali;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class ComentarioAdapter extends RecyclerView.Adapter<ComentarioAdapter.ViewHolder>  {

    Context context;
    ArrayList<Comentario> list;

    public ComentarioAdapter(Context context, ArrayList<Comentario> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @NotNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.comentarios,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull ViewHolder holder, int position) {
        Comentario comentario = list.get(position);
        holder.user.setText(comentario.getUser());
        holder.texto.setText(comentario.getTexto());
        holder.rating.setNumStars(Math.round(comentario.getRating()));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        TextView user, texto;
        RatingBar rating;

        public ViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);

            user = itemView.findViewById(R.id.userNom);
            texto = itemView.findViewById(R.id.textoComent);
            rating= itemView.findViewById(R.id.ratings);
        }
    }

}