package sergiosacj.com.myapplication.MelhorEntrega;

import android.os.Build;
import android.support.annotation.RequiresApi;

import java.util.ArrayList;

public class Empresa {

    private Frota frota;
    private int numeroCarros;
    private int numeroCarretas;
    private int numeroMotos;
    private int numeroVans;
    private double porcentagemLucro;
    private boolean entregaImpossivelTempo;
    private boolean entregaImpossivelDisponbilidade;
    private boolean entregaImpossivelTamanho;
    private ArrayList<Veiculos> veiculosRealizandoEntregas;

    public Empresa() {
        this.entregaImpossivelTempo = false;
        this.entregaImpossivelDisponbilidade = false;
        this.entregaImpossivelTamanho = false;
        this.veiculosRealizandoEntregas = new ArrayList<>();
        this.frota = new Frota();
    }

    public void atualizaEmpresa(double porcentagemLucro, int numeroCarros, int numeroCarretas, int numeroMotos, int numeroVans){
        this.porcentagemLucro = porcentagemLucro;
        Carro carro = new Carro();
        Carreta carreta = new Carreta();
        Moto moto = new Moto();
        Van van = new Van();

        if(numeroCarros > this.numeroCarros) {
            adicionaFrota(carro, numeroCarros);
            this.numeroCarros = numeroCarros;
        }
        else {
            removeFrota(carro, numeroCarros);
            this.numeroCarros -= numeroCarros;
        }

        if(numeroCarretas > this.numeroCarretas) {
            adicionaFrota(carreta, numeroCarretas);
            this.numeroCarretas = numeroCarretas;
        }
        else {
            removeFrota(carreta, numeroCarretas);
            this.numeroCarretas -= numeroCarretas;
        }

        if(numeroMotos > this.numeroMotos) {
            adicionaFrota(moto, numeroMotos);
            this.numeroMotos = numeroMotos;
        }
        else {
            removeFrota(moto, numeroMotos);
            this.numeroMotos -= numeroMotos;
        }

        if(numeroVans > this.numeroVans) {
            adicionaFrota(van, numeroVans);
            this.numeroVans = numeroVans;
        }
        else {
            removeFrota(van, numeroVans);
            this.numeroVans -= numeroVans;
        }

    }

    public Frota getFrota() {
        return frota;
    }

    public void adicionaFrota(Veiculos veiculo, int numeroVeiculos){

        int veiculosParaAdicionar;

        if(veiculo.getTipo().equals("carro")) veiculosParaAdicionar = this.numeroCarros - numeroVeiculos;
        else if(veiculo.getTipo().equals("carreta")) veiculosParaAdicionar = this.numeroCarretas - numeroVeiculos;
        else if(veiculo.getTipo().equals("moto")) veiculosParaAdicionar = this.numeroMotos - numeroVeiculos;
        else veiculosParaAdicionar = this.numeroVans - numeroVeiculos;

        while(veiculosParaAdicionar!=0){
            this.frota.adicionaFrota(veiculo);
            veiculosParaAdicionar--;
        }

    }

    public void removeFrota(Veiculos veiculo, int numeroVeiculos){

        while(numeroVeiculos!=0){
            this.frota.removeFrota(veiculo);
            numeroVeiculos--;
        }

    }

    public double getPorcentagemLucro() {
        return porcentagemLucro;
    }

    public void setPorcentagemLucro(double porcentagemLucro) {
        this.porcentagemLucro = porcentagemLucro;
    }

    public int getNumeroCarros() {
        return numeroCarros;
    }

    public int getNumeroCarretas() {
        return numeroCarretas;
    }

    public int getNumeroMotos() {
        return numeroMotos;
    }

    public int getNumeroVans() {
        return numeroVans;
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