package Frota;

public class Moto extends Veiculos {
    public Moto() {
        setTipo("moto");
        setEstado("disponivel");
        setCombustivel("flex");
        setRendimento(50);
        setCarga_atual(0);
        setCarga_suportada(50);
        setVelocidade_media(110);
        setTaxa_reducao_rendimento(0.3);
    }
}
