package sergiosacj.com.myapplication.MelhorEntrega;

import android.os.Build;
import android.support.annotation.RequiresApi;

import java.util.ArrayList;
import java.util.Collections;

public class Frota {

    private ArrayList<Veiculos> frota;
    private ArrayList<String> veiculosDisponiveis;
    private ArrayList<String> veiculosTamanhoIdeal;
    private String veiculoMenorCusto, veiculoMenorTempo, veiculoMelhorCustoBeneficio;
    private Double valorMenorTempo;
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

    public Double getValorMenorTempo() {
        return valorMenorTempo;
    }

    public void setValorMenorTempo(Double valorMenorTempo) {
        this.valorMenorTempo = valorMenorTempo;
    }

    public String getVeiculoMelhorCustoBeneficio() {
        return veiculoMelhorCustoBeneficio;
    }

    public void setVeiculoMelhorCustoBeneficio(String veiculoMelhorCustoBeneficio) {
        this.veiculoMelhorCustoBeneficio = veiculoMelhorCustoBeneficio;
    }

    public void adicionaFrota(Veiculos veiculo) {
        this.frota.add(veiculo);
    }

    public void removeFrota(Veiculos veiculo) {
        for(int i = 0; i < frota.size(); i++){
            Veiculos veiculoSaindo = frota.get(i);
            if(veiculoSaindo.getTipo().equals(veiculo.getTipo())) {
                frota.remove(i);
                break;
            }
        }
    }

    public Veiculos ficaIndisponivel(String veiculoEscolhido){
        Veiculos veiculo = new Veiculos();
        for(int i = 0; i < frota.size(); i++){
            veiculo = frota.get(i);
            if(veiculo.getTipo().equals(veiculoEscolhido) && veiculo.getEstado().equals("disponivel")) {
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
            else if(tipoVeiculo.equals("van")) vanVerificada = true;
        }
    }

    public void verificaDisponibilidade(){
        veiculosDisponiveis.clear();
        carroVerificado = false;
        carretaVerificada = false;
        motoVerificada = false;
        vanVerificada = false;
        for(int i = 0; i < frota.size(); i++){
            if(frota.get(i).getEstado().equals("disponivel")) {
                adicionaVeiculosDisponiveis(frota.get(i).getTipo());
            }
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void verificaCustoBeneficio(double distancia, double carga, double tempoMaximo){

        this.carreta = new Carreta();
        this.carro = new Carro();
        this.moto = new Moto();
        this.van = new Van();

        carreta.setCargaAtual(carga);
        carro.setCargaAtual(carga);
        moto.setCargaAtual(carga);
        van.setCargaAtual(carga);

        carreta.calculaRendimento();
        carro.calculaRendimento();
        moto.calculaRendimento();;
        van.calculaRendimento();

        ArrayList<Double> custo  = new ArrayList<>();
        ArrayList<Double> custoBeneficio = new ArrayList<>();
        ArrayList<Double> tempo  = new ArrayList<>();
        double custoCarro = carro.calculaCusto(distancia);
        double custoCarreta = carreta.calculaCusto(distancia);
        double custoMoto = moto.calculaCusto(distancia);
        double custoVan = van.calculaCusto(distancia);

        double tempoCarro = carro.calculaTempo(distancia);
        double tempoCarreta = carreta.calculaTempo(distancia);
        double tempoMoto = moto.calculaTempo(distancia);
        double tempoVan = van.calculaTempo(distancia);

        if(carretaVerificada && (tempoCarreta <= tempoMaximo)) {
            custo.add(custoCarreta);
            custoBeneficio.add(custoCarreta/tempoCarreta);
            tempo.add(tempoCarreta);
        }
        if(carroVerificado && (tempoCarro <= tempoMaximo)) {
            custo.add(custoCarro);
            custoBeneficio.add(custoCarro/tempoCarro);
            tempo.add(tempoCarro);
        }
        if(motoVerificada && (tempoMoto <= tempoMaximo)) {
            custo.add(custoMoto);
            custoBeneficio.add(custoMoto/tempoMoto);
            tempo.add(tempoMoto);
        }
        if(vanVerificada && (tempoVan <= tempoMaximo)) {
            custo.add(custoVan);
            custoBeneficio.add(custoVan/tempoVan);
            tempo.add(tempoVan);
        }

        if(tempo.isEmpty()) {
            setValorMenorTempo(-1.0);
        }
        else {

            Collections.sort(custo);
            Collections.sort(custoBeneficio);
            Collections.sort(tempo);

            if (tempo.get(0) == tempoCarro) setVeiculoMenorTempo("carro");
            else if (tempo.get(0) == tempoMoto) setVeiculoMenorTempo("moto");
            else if (tempo.get(0) == tempoCarreta) setVeiculoMenorTempo("carreta");
            else setVeiculoMenorTempo("van");

            if (custo.get(0) == custoCarro) setVeiculoMenorCusto("carro");
            else if (custo.get(0) == custoMoto) setVeiculoMenorCusto("moto");
            else if (custo.get(0) == custoCarreta) setVeiculoMenorCusto("carreta");
            else setVeiculoMenorCusto("van");

            if (custoBeneficio.get(0) == custoCarro) setVeiculoMelhorCustoBeneficio("carro");
            else if (custoBeneficio.get(0) == custoMoto) setVeiculoMelhorCustoBeneficio("moto");
            else if (custoBeneficio.get(0) == custoCarreta)
                setVeiculoMelhorCustoBeneficio("carreta");
            else setVeiculoMelhorCustoBeneficio("van");

            setValorMenorTempo(tempo.get(0));

        }
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void verificaTamanhoIdeal(double carga){

        this.carreta = new Carreta();
        this.carro = new Carro();
        this.moto = new Moto();
        this.van = new Van();

        veiculosTamanhoIdeal.clear();
        if(carga <= carro.getCargaSuportada()) veiculosTamanhoIdeal.add("carro");
        else carroVerificado = false;
        if(carga <= carreta.getCargaSuportada()) veiculosTamanhoIdeal.add("carreta");
        else carretaVerificada = false;
        if(carga <= van.getCargaSuportada()) veiculosTamanhoIdeal.add("van");
        else vanVerificada = false;
        if(carga <= moto.getCargaSuportada()) veiculosTamanhoIdeal.add("moto");
        else motoVerificada = false;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void verificaVeiculoIdeal(double distancia, double carga, double tempoMaximo){
        this.veiculoMelhorCustoBeneficio = "";
        this.veiculoMenorCusto = "";
        this.veiculoMenorTempo = "";
        verificaDisponibilidade();
        verificaTamanhoIdeal(carga);
        verificaCustoBeneficio(distancia, carga, tempoMaximo);
    }
}
