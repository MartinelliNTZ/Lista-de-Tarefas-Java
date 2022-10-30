package com.example.listadetarefas.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.listadetarefas.R;
import com.example.listadetarefas.model.Tarefa;

import java.util.List;

public class AtividadeAdapter extends RecyclerView.Adapter<AtividadeAdapter.MyViewHolder> {
    List<Tarefa> lista;

    public AtividadeAdapter(List<Tarefa> l) {
        this.lista = l;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        //linka o layout com classe e envia ela para a classe my view holder
        View itemList = LayoutInflater
                        .from(parent.getContext())
                        .inflate(R.layout.lista_tarefa_layout,parent,false);
        return new MyViewHolder(itemList);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
            Tarefa  tarefa = lista.get(position);
            holder.textTarefa.setText(tarefa.getNomeTarefa());
    }

    @Override
    public int getItemCount() {
        return this.lista.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView textTarefa;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            textTarefa=itemView.findViewById(R.id.textView);

        }
    }
}
