package Frota;

public class Veiculos {

    private String tipo;
    private String estado;
    private String combustivel;
    private double rendimento;
    private double cargaSuportada;
    private double cargaAtual;
    private double velocidadeMedia;
    private double taxaReducaoRendimento;
    private double GASOLINA = 4.4449;
    private double ALCOOL = 3.499;
    private double DIESEL = 3.869;

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getCombustivel() {
        return combustivel;
    }

    public void setCombustivel(String combustivel) {
        this.combustivel = combustivel;
    }

    public double getRendimento() {
        return rendimento;
    }

    public void setRendimento(double rendimento) {
        this.rendimento = rendimento;
    }

    public double getCargaSuportada() {
        return cargaSuportada;
    }

    public void setCargaSuportada(double cargaSuportada) {
        this.cargaSuportada = cargaSuportada;
    }

    public double getCargaAtual() {
        return cargaAtual;
    }

    public void setCargaAtual(double cargaAtual) {
        this.cargaAtual = cargaAtual;
    }

    public double getVelocidadeMedia() {
        return velocidadeMedia;
    }

    public void setVelocidadeMedia(double velocidadeMedia) {
        this.velocidadeMedia = velocidadeMedia;
    }

    public double getTaxaReducaoRendimento() {
        return taxaReducaoRendimento;
    }

    public void setTaxaReducaoRendimento(double taxaReducaoRendimento) {
        this.taxaReducaoRendimento = taxaReducaoRendimento;
    }

    public void calculaRendimento() {
        setRendimento(getRendimento() - getCargaAtual() * getTaxaReducaoRendimento());
    }
}