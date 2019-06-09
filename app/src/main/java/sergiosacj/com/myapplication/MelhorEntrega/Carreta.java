package sergiosacj.com.myapplication.MelhorEntrega;

public class Carreta extends Veiculo_diesel {

    public Carreta() {
        setTipo("carreta");
        setEstado("disponivel");
        setCombustivel("diesel");
        setRendimentoDiesel(8);
        setCargaAtual(0);
        setCargaSuportada(30000);
        setVelocidadeMedia(60);
        setTaxaReducaoRendimento(0.0002);
    }

}
