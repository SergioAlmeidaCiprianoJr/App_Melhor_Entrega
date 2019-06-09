package sergiosacj.com.myapplication.MelhorEntrega;

public class Veiculo_diesel extends Veiculos {

    private double rendimentoDiesel;

    public Veiculo_diesel(){
        setCombustivel("diesel");
    }

    public double getRendimentoDiesel() {
        return rendimentoDiesel;
    }

    public void setRendimentoDiesel(double rendimentoDiesel) {
        this.rendimentoDiesel = rendimentoDiesel;
    }

    @Override
    public void calculaRendimento() {
        super.calculaRendimento();
        setRendimentoDiesel(getRendimentoDiesel() - getCargaAtual()*getTaxaReducaoRendimento());
    }

    @Override
    public double calculaCusto(double distancia) {
        super.calculaCusto(distancia);
        calculaRendimento();
        return (distancia/getRendimentoDiesel())*DIESEL;
    }

    @Override
    public double calculaLucro(double porcentagemLucro, double distancia) {
        super.calculaLucro(porcentagemLucro, distancia);
        double custo = calculaCusto(distancia);
        return custo+custo*porcentagemLucro/100;
    }
}