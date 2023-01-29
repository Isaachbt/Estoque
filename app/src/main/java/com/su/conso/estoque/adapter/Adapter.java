package com.su.conso.estoque.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.su.conso.estoque.R;
import com.su.conso.estoque.bancoDados.DadosDAO;
import com.su.conso.estoque.config.ConvertValor;
import com.su.conso.estoque.model.DadosProdutos;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.MyViewHolder>{

    private List<DadosProdutos> dados;

    public Adapter(List<DadosProdutos> dados) {
        this.dados = dados;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemLista = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_recycler,parent,false);
        return new MyViewHolder(itemLista);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        DadosProdutos produtos = dados.get(position);

        ConvertValor convert = new ConvertValor();
        holder.nome.setText(produtos.getNome());
        holder.valorUni.setText(convert.convert(String.valueOf(produtos.getValor_uni())));
        holder.valorTotal.setText(convert.convert(String.valueOf(produtos.getValor_Total())));
        holder.quantidade.setText(String.valueOf(produtos.getQuantindade_P()));
    }

    @Override
    public int getItemCount() {
        return dados.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        private TextView nome,valorUni,valorTotal,quantidade;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            nome = itemView.findViewById(R.id.txt_nome_adapter);
            valorUni = itemView.findViewById(R.id.txt_valor_un_adapter);
            valorTotal = itemView.findViewById(R.id.txt_valorTotal_adapter);
            quantidade = itemView.findViewById(R.id.quantidade_adapter);
        }
    }
}
