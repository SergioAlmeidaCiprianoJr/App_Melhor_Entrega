package Frota;

public class Veiculo_flex extends Veiculos {

    private double rendimentoAlcool;
    private double rendimentoGasolina;
    private double taxaReducaoRendimentoAlcool;
    private double taxaReducaoRendimentoGasolina;
    private String combustivelMelhor;

    public Veiculo_flex(){
        setCombustivel("flex");
    }

    public double getRendimentoAlcool() {
        return rendimentoAlcool;
    }

    public void setRendimentoAlcool(double rendimentoAlcool) {
        this.rendimentoAlcool = rendimentoAlcool;
    }

    public double getRendimentoGasolina() {
        return rendimentoGasolina;
    }

    public void setRendimentoGasolina(double rendimentoGasolina) {
        this.rendimentoGasolina = rendimentoGasolina;
    }

    public double getTaxaReducaoRendimentoAlcool() {
        return taxaReducaoRendimentoAlcool;
    }

    public void setTaxaReducaoRendimentoAlcool(double taxaReducaoRendimentoAlcool) {
        this.taxaReducaoRendimentoAlcool = taxaReducaoRendimentoAlcool;
    }

    public double getTaxaReducaoRendimentoGasolina() {
        return taxaReducaoRendimentoGasolina;
    }

    public void setTaxaReducaoRendimentoGasolina(double taxaReducaoRendimentoGasolina) {
        this.taxaReducaoRendimentoGasolina = taxaReducaoRendimentoGasolina;
    }

    @Override
    public void calculaRendimento() {
        super.calculaRendimento();
        calculaRendimentoGasolina();
        calculaRendimentoAlcool();
    }

    public void calculaRendimentoGasolina(){
        setRendimentoGasolina(getRendimentoGasolina() - getCargaAtual()*getTaxaReducaoRendimentoGasolina());
    }

    public void calculaRendimentoAlcool(){
        setRendimentoAlcool(getRendimentoAlcool() - getCargaAtual()*getTaxaReducaoRendimentoAlcool());
    }

    @Override
    public double calculaCusto(double distancia) {
        super.calculaCusto(distancia);
        double custoGasolina = calculaCustoGasolina(distancia), custoAlccol = calculaCustoAlcool(distancia);
        if (custoAlccol>custoGasolina){
            combustivelMelhor = "gasolina";
            return custoGasolina;
        }
        else{
            combustivelMelhor = "alcool";
            return custoAlccol;
        }
    }

    public double calculaCustoGasolina(double distancia) {
        distancia *= 2;
        return (distancia/getRendimentoGasolina())*GASOLINA;
    }

    public double calculaCustoAlcool(double distancia) {
        distancia *= 2;
        return (distancia/getRendimentoAlcool())*ALCOOL;
    }
}
