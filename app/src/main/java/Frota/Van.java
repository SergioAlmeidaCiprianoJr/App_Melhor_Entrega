package Frota;

public class Van extends Veiculo_diesel {
    public Van() {
        setTipo("van");
        setEstado("disponivel");
        setCombustivel("diesel");
        setCargaAtual(0);
        setCargaSuportada(3500);
        setVelocidadeMedia(80);
        setTaxaReducaoRendimento(0.001);
    }
}
