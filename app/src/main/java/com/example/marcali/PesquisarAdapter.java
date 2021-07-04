package com.example.marcali;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class PesquisarAdapter extends RecyclerView.Adapter<PesquisarAdapter.ViewHolder>  {

    Context context;
    ArrayList<Estabelecimento> list;

    public PesquisarAdapter(Context context, ArrayList<Estabelecimento> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @NotNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.estabelecimentos,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull ViewHolder holder, int position) {
        Estabelecimento estabelecimento = list.get(position);
        holder.nome.setText(estabelecimento.getNome());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        TextView nome;

        public ViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);

            nome = itemView.findViewById(R.id.nomeEstabelecimento);
        }
    }


}
