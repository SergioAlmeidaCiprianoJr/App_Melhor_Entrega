package Frota;

public class Carro extends Veiculos {
    public Carro() {
        setTipo("carro");
        setEstado("disponivel");
        setCombustivel("flex");
        setRendimento(50);
        setCarga_atual(0);
        setCarga_suportada(360);
        setVelocidade_media(100);
        setTaxa_reducao_rendimento(0.025);
    }
}
