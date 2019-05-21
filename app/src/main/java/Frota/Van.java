package Frota;

public class Van extends Veiculos{
    public Van() {
        setTipo("van");
        setEstado("disponivel");
        setCombustivel("diesel");
        setRendimento(10);
        setCarga_atual(0);
        setCarga_suportada(3500);
        setVelocidade_media(80);
        setTaxa_reducao_rendimento(0.001);
    }
}
