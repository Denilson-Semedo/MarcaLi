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

public class ServicoAdapter extends RecyclerView.Adapter<ServicoAdapter.ViewHolder>  {

    Context context;
    ArrayList<Servico> list;

    public ServicoAdapter(Context context, ArrayList<Servico> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @NotNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.servicos,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull ViewHolder holder, int position) {
        Servico servico = list.get(position);
        holder.nome.setText(servico.getNome());
        holder.preco.setText(servico.getPreco());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        TextView nome, preco;

        public ViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);

            nome = itemView.findViewById(R.id.nomeServ);
            preco = itemView.findViewById(R.id.precoServ);
        }
    }


}

