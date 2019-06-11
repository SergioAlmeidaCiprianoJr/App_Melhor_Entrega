package sergiosacj.com.myapplication.Adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import java.util.ArrayList;

import sergiosacj.com.myapplication.MelhorEntrega.Veiculos;
import sergiosacj.com.myapplication.R;

public class AdapterVeiculosOcupados extends RecyclerView.Adapter<AdapterVeiculosOcupados.MyViewHolder> {

    private ArrayList<Veiculos> veiculosRealizandoEntregas;

    public AdapterVeiculosOcupados(ArrayList<Veiculos> veiculosRealizandoEntregas) {
        this.veiculosRealizandoEntregas = veiculosRealizandoEntregas;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View itemLista = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.adapter_veiculos_ocupados, viewGroup, false);

        return new MyViewHolder(itemLista);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {

        myViewHolder.nomeVeiculo.setText(veiculosRealizandoEntregas.get(i).getTipo());
        myViewHolder.cargaAtualVeiculo.setText("Carga Atual:  " + String.valueOf(veiculosRealizandoEntregas.get(i).getCargaAtual()));
        myViewHolder.tempoEntregaAtual.setText("Tempo Entrega Atual:  " + String.valueOf(veiculosRealizandoEntregas.get(i).getTempoEntregaAtual()));
        myViewHolder.distanciaEntregaAtual.setText("Distância Entrega Atual:  " + String.valueOf(veiculosRealizandoEntregas.get(i).getDistanciaEntregaAtual()));

    }

    @Override
    public int getItemCount() {
        return veiculosRealizandoEntregas.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView nomeVeiculo;
        TextView cargaAtualVeiculo;
        TextView tempoEntregaAtual;
        TextView distanciaEntregaAtual;

        public MyViewHolder(@NonNull View itemLista) {
            super(itemLista);

            nomeVeiculo = itemLista.findViewById(R.id.textViewVeiculo);
            cargaAtualVeiculo = itemLista.findViewById(R.id.textViewCargaAtual);
            tempoEntregaAtual = itemLista.findViewById(R.id.textViewTempoEntrega);
            distanciaEntregaAtual = itemLista.findViewById(R.id.textViewDistanciaEntrega);

        }
    }

}
