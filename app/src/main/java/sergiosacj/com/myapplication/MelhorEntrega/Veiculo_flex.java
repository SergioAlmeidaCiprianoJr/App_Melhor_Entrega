package sergiosacj.com.myapplication.MelhorEntrega;

public class Veiculo_flex extends Veiculos {

    private double rendimentoAlcool;
    private double rendimentoGasolina;
    private double taxaReducaoRendimentoAlcool;
    private double taxaReducaoRendimentoGasolina;

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
        double custoGasolina = calculaCustoGasolina(distancia), custoAlcool = calculaCustoAlcool(distancia);
        return custoAlcool>custoGasolina ? custoGasolina : custoAlcool;
    }

    public double calculaCustoGasolina(double distancia) {
        return (distancia/getRendimentoGasolina())*GASOLINA;
    }

    public double calculaCustoAlcool(double distancia) {
        return (distancia/getRendimentoAlcool())*ALCOOL;
    }

    @Override
    public double calculaLucro(double porcentagemLucro, double distancia) {
        super.calculaLucro(porcentagemLucro, distancia);
        double custo = calculaCusto(distancia);
        return custo+(custo*porcentagemLucro/100);
    }

}
