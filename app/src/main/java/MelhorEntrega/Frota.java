package MelhorEntrega;

import android.os.Build;
import android.support.annotation.RequiresApi;

import java.util.ArrayList;
import java.util.Collections;

public class Frota {

    private ArrayList<Veiculos> frota;
    private ArrayList<String> veiculosDisponiveis;
    private ArrayList<String> veiculosTamanhoIdeal;
    private String veiculoMenorCusto, veiculoMenorTempo;
    private Double valorMenorCusto, valorMenorTempo;
    private Carreta carreta;
    private Van van;
    private Carro carro;
    private Moto moto;
    private boolean carroVerificado = false, carretaVerificada = false, motoVerificada = false, vanVerificada = false;

    public Frota() {
        this.frota = new ArrayList<>();
        this.veiculosDisponiveis = new ArrayList<>();
        this.veiculosTamanhoIdeal = new ArrayList<>();
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

    public void setVeiculoMenorCusto(String veiculoMenorCusto) {
        this.veiculoMenorCusto = veiculoMenorCusto;
    }

    public String getVeiculoMenorCusto() {
        return veiculoMenorCusto;
    }

    public void setVeiculoMenorTempo(String veiculoMenorTempo) {
        this.veiculoMenorTempo = veiculoMenorTempo;
    }

    public String getVeiculoMenorTempo() {
        return veiculoMenorTempo;
    }

    public Double getValorMenorCusto() {
        return valorMenorCusto;
    }

    public void setValorMenorCusto(Double valorMenorCusto) {
        this.valorMenorCusto = valorMenorCusto;
    }

    public Double getValorMenorTempo() {
        return valorMenorTempo;
    }

    public void setValorMenorTempo(Double valorMenorTempo) {
        this.valorMenorTempo = valorMenorTempo;
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
        if(!jaEstaNaLista) {
            veiculosDisponiveis.add(tipoVeiculo);
            if(tipoVeiculo.equals("carro")) carroVerificado = true;
            else if(tipoVeiculo.equals("carreta")) carretaVerificada = true;
            else if(tipoVeiculo.equals("moto")) motoVerificada = true;
            else vanVerificada = true;
        }
    }

    public void verificaDisponibilidade(){
        veiculosDisponiveis.clear();
        for(int i = 0; i < frota.size(); i++){
            if(frota.get(i).getEstado().equals("disponivel")) {
                adicionaVeiculosDisponiveis(frota.get(i).getTipo());
            }
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public Double verificaMenorCusto(double distancia, double tempoMaximo){


        ArrayList<Double> custo  = new ArrayList<>();
        double custoCarro = carro.calculaCusto(distancia);
        double custoCarreta = carreta.calculaCusto(distancia);
        double custoMoto = moto.calculaCusto(distancia);
        double custoVan = van.calculaCusto(distancia);

        if(carretaVerificada && (carreta.calculaTempo(distancia) <= tempoMaximo)) custo.add(custoCarreta);
        if(carroVerificado && (carro.calculaTempo(distancia) <= tempoMaximo)) custo.add(custoCarro);
        if(motoVerificada && (moto.calculaTempo(distancia) <= tempoMaximo)) custo.add(custoMoto);
        if(vanVerificada && (van.calculaTempo(distancia) <= tempoMaximo)) custo.add(custoVan);

        if(!custo.isEmpty()) {

            Collections.sort(custo);

            if (custo.get(0) == custoCarro) setVeiculoMenorCusto("carro");
            else if (custo.get(0) == custoMoto) setVeiculoMenorCusto("moto");
            else if (custo.get(0) == custoCarreta) setVeiculoMenorCusto("carreta");
            else setVeiculoMenorCusto("van");

            return custo.get(0);

        }
        else return 0.0;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public Double verificaMenorTempo(double distancia, double tempoMaximo){

        ArrayList<Double> tempo  = new ArrayList<>();
        double tempoCarro = carro.calculaTempo(distancia);
        double tempoCarreta = carreta.calculaTempo(distancia);
        double tempoMoto = moto.calculaTempo(distancia);
        double tempoVan = van.calculaTempo(distancia);

        if(tempoCarreta <= tempoMaximo) tempo.add(tempoCarreta);
        if(tempoCarro  <= tempoMaximo) tempo.add(tempoCarro);
        if(tempoMoto  <= tempoMaximo) tempo.add(tempoMoto);
        if(tempoVan <= tempoMaximo) tempo.add(tempoVan);

        if(!tempo.isEmpty()) Collections.sort(tempo);

        if(tempo.isEmpty()) {
            setVeiculoMenorTempo("impossivel");
            return 0.0;
        }

        if(tempo.get(0) == tempoCarro) setVeiculoMenorTempo("carro");
        else if(tempo.get(0) ==  tempoMoto) setVeiculoMenorTempo("moto");
        else if(tempo.get(0) == tempoCarreta) setVeiculoMenorTempo("carreta");
        else setVeiculoMenorTempo("van");

        return tempo.get(0);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void verificaTamanhoIdeal(double carga){
        veiculosTamanhoIdeal.clear();
        if(carga < carro.getCargaSuportada()) veiculosTamanhoIdeal.add("carro");
        if(carga < carreta.getCargaSuportada()) veiculosTamanhoIdeal.add("carreta");
        if(carga < van.getCargaSuportada()) veiculosTamanhoIdeal.add("van");
        if(carga < moto.getCargaSuportada()) veiculosTamanhoIdeal.add("moto");
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void verificaVeiculoIdeal(double distancia, double carga, double tempoMaximo){
        verificaDisponibilidade();
        verificaTamanhoIdeal(carga);
        setValorMenorCusto(verificaMenorCusto(distancia, tempoMaximo));
        setValorMenorTempo(verificaMenorTempo(distancia, tempoMaximo));
    }
}
