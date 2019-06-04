package MelhorEntrega;

import android.os.Build;
import android.support.annotation.RequiresApi;

import java.util.ArrayList;
import java.util.List;

public class Empresa {

    private Frota frota;
    private double porcentagemLucro;
    private boolean entregaImpossivelTempo;
    private boolean entregaImpossivelDisponbilidade;
    private boolean entregaImpossivelTamanho;
    private List<Veiculos> veiculosRealizandoEntregas;

    public Empresa(String nome, double porcentagemLucro) {
        this.entregaImpossivelTempo = false;
        this.entregaImpossivelDisponbilidade = false;
        this.entregaImpossivelTamanho = false;
        this.porcentagemLucro = porcentagemLucro;
        this.veiculosRealizandoEntregas = new ArrayList<>();
    }

    public Frota getFrota() {
        return frota;
    }

    public void adicionaFrota(Veiculos veiculo){
        this.frota.adicionaFrota(veiculo);
    }

    public void removeFrota(Veiculos veiculo){
        this.frota.removeFrota(veiculo);
    }

    public double getPorcentagemLucro() {
        return porcentagemLucro;
    }

    public void setPorcentagemLucro(double porcentagemLucro) {
        this.porcentagemLucro = porcentagemLucro;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void realizaEntrega(double carga, double distancia, double tempoMaximo){
        this.entregaImpossivelTempo = false;
        this.entregaImpossivelDisponbilidade = false;
        this.entregaImpossivelTamanho = false;
        frota.verificaVeiculoIdeal(distancia, carga, tempoMaximo);
        if(frota.getVeiculosDisponiveis().isEmpty()) entregaImpossivelDisponbilidade = true;
        else if(frota.getVeiculoMenorTempo().equals("impossivel")) entregaImpossivelTempo = true;
        else if(frota.getVeiculosTamanhoIdeal().isEmpty()) entregaImpossivelTamanho = true;
    }

    private void confirmaEntrega(int veiculoEscolhido){
        veiculosRealizandoEntregas.add(frota.ficaIndisponivel(veiculoEscolhido));
    }
}
