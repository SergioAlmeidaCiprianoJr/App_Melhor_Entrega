package sergiosacj.com.myapplication.MelhorEntrega;

public class Carro extends Veiculo_flex {

    public Carro() {
        setTipo("carro");
        setEstado("disponivel");
        setCargaAtual(0);
        setCargaSuportada(360);
        setVelocidadeMedia(100);
        setTaxaReducaoRendimentoAlcool(0.0231);
        setTaxaReducaoRendimentoGasolina(0.025);
        setRendimentoAlcool(12);
        setRendimentoGasolina(14);
    }

}
