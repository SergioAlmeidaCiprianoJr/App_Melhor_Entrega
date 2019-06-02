package MelhorEntrega;

import android.os.Build;
import android.support.annotation.RequiresApi;

import java.util.ArrayList;

public class Empresa {

    private String nome;
    private Frota frota;
    private double porcentagemLucro;
    private boolean entregaImpossivel;
    private ArrayList<Veiculos> veiculosRealizandoEntregas;

    public Empresa(String nome, double porcentagemLucro) {
        this.nome = nome;
        this.porcentagemLucro = porcentagemLucro;
        this.entregaImpossivel = false;
        this.veiculosRealizandoEntregas.clear();
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
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
        frota.verificaVeiculoIdeal(distancia, carga, tempoMaximo);
        if(frota.getVeiculoMenorTempo().equals("impossivel")) entregaImpossivel = true;
        else if(frota.getVeiculosDisponiveis().isEmpty()) entregaImpossivel = true;
        else if(frota.getVeiculosTamanhoIdeal().isEmpty()) entregaImpossivel = true;
    }

    private void confirmaEntrega(int veiculoEscolhido){
        veiculosRealizandoEntregas.add(frota.ficaIndisponivel(veiculoEscolhido));
    }
}
