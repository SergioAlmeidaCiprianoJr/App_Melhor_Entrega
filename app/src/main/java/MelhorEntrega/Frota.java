package MelhorEntrega;

import android.os.Build;
import android.support.annotation.RequiresApi;

import java.util.ArrayList;
import java.util.Collections;

public class Frota {

    private ArrayList<Veiculos> frota;
    private ArrayList<String> veiculosDisponiveis = null;
    private ArrayList<String> veiculosTamanhoIdeal = null;
    private String veiculoMenorCusto, veiculoMenorTempo;
    private Carreta carreta;
    private Van van;
    private Carro carro;
    private Moto moto;

    public Frota() {
        this.frota.clear();
        this.veiculosDisponiveis.clear();
        this.veiculosTamanhoIdeal.clear();
    }

    public ArrayList<Veiculos> getFrota() {
        return frota;
    }

    public void adicionaFrota(Veiculos veiculo) {
        this.frota.add(veiculo);
    }

    public ArrayList<String> getVeiculosDisponiveis() {
        return veiculosDisponiveis;
    }

    public ArrayList<String> getVeiculosTamanhoIdeal() {
        return veiculosTamanhoIdeal;
    }

    public String getVeiculoMenorCusto() {
        return veiculoMenorCusto;
    }

    public String getVeiculoMenorTempo() {
        return veiculoMenorTempo;
    }

    public boolean removeFrota(Veiculos veiculo) {
        boolean existe = false;
        for(int i = 0; i < frota.size(); i++){
            Veiculos veiculoSaindo = frota.get(i);
            if(veiculoSaindo == veiculo) {
                frota.remove(i);
                existe = true;
                break;
            }
        }
        return existe;
    }

    public Veiculos ficaIndisponivel(int veiculoEscolhido){
        String veiculoIdeal = veiculosDisponiveis.get(veiculoEscolhido);
        Veiculos veiculo = frota.get(0);
        for(int i = 0; i < frota.size(); i++){
            veiculo = frota.get(i);
            if(veiculo.getTipo().equals(veiculoIdeal) && veiculo.getEstado().equals("disponivel")) {
                frota.get(i).setEstado("indisponivel");
                return veiculo;
            }
        }
        return veiculo;
    }

    public void adicionaVeiculosDisponiveis(String tipoVeiculo){
        boolean jaEstaNaLista = false;
        for(int i = 0; i < veiculosDisponiveis.size(); i++){
            String veiculoEntrando = veiculosDisponiveis.get(i);
            if(veiculoEntrando.equals(tipoVeiculo)) {
                jaEstaNaLista = true;
                break;
            }
        }
        if(!jaEstaNaLista) veiculosDisponiveis.add(tipoVeiculo);
    }

    public void verificaDisponibilidade(){
        for(int i = 0; i < frota.size(); i++){
            if(frota.get(i).getEstado().equals("disponivel")) {
                adicionaVeiculosDisponiveis(frota.get(i).getTipo());
            }
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public String verificaMenorCusto(double distancia){

        ArrayList<Double> custo = null;
        double custoCarro = carro.calculaCusto(distancia);
        double custoCarreta = carreta.calculaCusto(distancia);
        double custoMoto = moto.calculaCusto(distancia);
        double custoVan = van.calculaCusto(distancia);

        custo.add(custoCarreta);
        custo.add(custoCarro);
        custo.add(custoMoto);
        custo.add(custoVan);

        Collections.sort(custo);

        if(custo.get(0) == custoCarro) return "carro";
        else if(custo.get(0) ==  custoMoto) return "moto";
        else if(custo.get(0) == custoCarreta) return "carreta";
        else return "van";

    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public String verificaMenorTempo(double distancia, double tempoMaximo){

        ArrayList<Double> tempo = null;
        double tempoCarro = carro.calculaTempo(distancia);
        double tempoCarreta = carreta.calculaTempo(distancia);
        double tempoMoto = moto.calculaTempo(distancia);
        double tempoVan = van.calculaTempo(distancia);

        tempo.add(tempoCarreta);
        tempo.add(tempoCarro);
        tempo.add(tempoMoto);
        tempo.add(tempoVan);

        Collections.sort(tempo);

        if(tempo.get(0) > tempoMaximo) return "impossivel";

        if(tempo.get(0) == tempoCarro) return "carro";
        else if(tempo.get(0) ==  tempoMoto) return "moto";
        else if(tempo.get(0) == tempoCarreta) return "carreta";
        else return "van";
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void verificaTamanhoIdeal(double carga){
        if(carga < carro.getCargaSuportada()) veiculosTamanhoIdeal.add("carro");
        if(carga < carreta.getCargaSuportada()) veiculosTamanhoIdeal.add("carreta");
        if(carga < van.getCargaSuportada()) veiculosTamanhoIdeal.add("van");
        if(carga < moto.getCargaSuportada()) veiculosTamanhoIdeal.add("moto");
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void verificaVeiculoIdeal(double distancia, double carga, double tempoMaximo){
        veiculosDisponiveis.clear();
        veiculosTamanhoIdeal.clear();
        verificaDisponibilidade();
        veiculoMenorCusto = verificaMenorCusto(distancia);
        veiculoMenorTempo = verificaMenorTempo(distancia, tempoMaximo);
        verificaTamanhoIdeal(carga);
    }
}
