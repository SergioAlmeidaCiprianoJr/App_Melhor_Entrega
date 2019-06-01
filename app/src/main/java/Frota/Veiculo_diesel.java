package Frota;

public class Veiculo_diesel extends Veiculos {

    private double rendimentoDiesel;

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
}