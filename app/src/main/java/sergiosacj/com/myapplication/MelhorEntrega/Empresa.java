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
    private Double porcentagemLucro;
    private Double cargaEntrega, distanciaEntrega, tempoMaximoEntrega;
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
        this.porcentagemLucro = 0.0;
        this.numeroCarros = 0;
        this.numeroCarretas = 0;
        this.numeroMotos = 0;
        this.numeroVans = 0;
    }

    public void atualizaEmpresa(Double porcentagemLucro, int numeroCarros, int numeroCarretas, int numeroMotos, int numeroVans){
        this.porcentagemLucro = porcentagemLucro;
        Carro carro = new Carro();
        Carreta carreta = new Carreta();
        Moto moto = new Moto();
        Van van = new Van();

        if(numeroCarros > this.numeroCarros) {
            adicionaFrota(carro, numeroCarros);
            this.numeroCarros = numeroCarros;
        }
        else if(numeroCarros < this.numeroCarros){
            removeFrota(carro, this.numeroCarros-numeroCarros);
            this.numeroCarros = numeroCarros;
        }

        if(numeroCarretas > this.numeroCarretas) {
            adicionaFrota(carreta, numeroCarretas);
            this.numeroCarretas = numeroCarretas;
        }
        else if(numeroCarretas < this.numeroCarretas){
            removeFrota(carreta, this.numeroCarretas-numeroCarretas);
            this.numeroCarretas = numeroCarretas;
        }

        if(numeroMotos > this.numeroMotos) {
            adicionaFrota(moto, numeroMotos);
            this.numeroMotos = numeroMotos;
        }
        else if(numeroMotos < this.numeroMotos){
            removeFrota(moto, this.numeroMotos-numeroMotos);
            this.numeroMotos = numeroMotos;
        }

        if(numeroVans > this.numeroVans) {
            adicionaFrota(van, numeroVans);
            this.numeroVans = numeroVans;
        }
        else if(numeroVans < this.numeroVans){
            removeFrota(van, this.numeroVans-numeroVans);
            this.numeroVans = numeroVans;
        }

    }

    public Frota getFrota() {
        return frota;
    }

    public void adicionaFrota(Veiculos veiculo, int numeroVeiculos){

        int veiculosParaAdicionar;

        if(veiculo.getTipo().equals("carro")) veiculosParaAdicionar = numeroVeiculos - this.numeroCarros;
        else if(veiculo.getTipo().equals("carreta")) veiculosParaAdicionar = numeroVeiculos - this.numeroCarretas;
        else if(veiculo.getTipo().equals("moto")) veiculosParaAdicionar = numeroVeiculos - this.numeroMotos;
        else veiculosParaAdicionar = numeroVeiculos - this.numeroVans;

        while(veiculosParaAdicionar>0){
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

    public Double getCarga() {
        return cargaEntrega;
    }

    public Double getDistancia() {
        return distanciaEntrega;
    }

    public Double getTempoMaximo() {
        return tempoMaximoEntrega;
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

    public ArrayList<Veiculos> getVeiculosRealizandoEntregas() {
        return veiculosRealizandoEntregas;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void realizaEntrega(double carga, double distancia, double tempoMaximo){
        this.cargaEntrega = carga;
        this.distanciaEntrega = distancia;
        this.tempoMaximoEntrega = tempoMaximo;
        this.entregaImpossivelTempo = false;
        this.entregaImpossivelDisponbilidade = false;
        this.entregaImpossivelTamanho = false;
        frota.verificaVeiculoIdeal(distancia, carga, tempoMaximo);
        if(frota.getVeiculosDisponiveis().isEmpty()) entregaImpossivelDisponbilidade = true;
        else if(frota.getValorMenorTempo().equals(-1.0)) entregaImpossivelTempo = true;
        else if(frota.getVeiculosTamanhoIdeal().isEmpty()) entregaImpossivelTamanho = true;
    }

    public boolean entregaImpossivel(){
        if(entregaImpossivelDisponbilidade || entregaImpossivelTempo || entregaImpossivelTamanho) return true;
        else return false;
    }

    public void confirmaEntrega(String veiculoEscolhido){
        veiculosRealizandoEntregas.add(frota.ficaIndisponivel(veiculoEscolhido, cargaEntrega, distanciaEntrega, tempoMaximoEntrega));
    }

    public void removeEntrega(Veiculos veiculoRetirado, int posicao){
        veiculosRealizandoEntregas.remove(posicao);
        veiculoRetirado.setCargaAtual(0);
        veiculoRetirado.setDistanciaEntregaAtual(0);
        veiculoRetirado.setTempoEntregaAtual(0);
        frota.ficaDisponivel(veiculoRetirado);
    }
}
