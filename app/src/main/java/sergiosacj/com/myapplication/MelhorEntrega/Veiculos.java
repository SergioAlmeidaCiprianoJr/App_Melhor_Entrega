package sergiosacj.com.myapplication.MelhorEntrega;

public class Veiculos {

    private String tipo;
    private String estado;
    private String combustivel;
    private double rendimento;
    private double cargaSuportada;
    private double cargaAtual;
    private double velocidadeMedia;
    private double taxaReducaoRendimento;
    protected final double GASOLINA = 4.4449;
    protected final double ALCOOL = 3.499;
    protected final double DIESEL = 3.869;

    public Veiculos(){
        setEstado("disponivel");
        setCargaAtual(0);
    }

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

    public double calculaTempo(double distancia){
        return distancia/getVelocidadeMedia();
    }

    public double calculaCusto(double distancia){
        double litros = distancia/getRendimento(), COMBUSTIVEL = 1;
        return litros*COMBUSTIVEL;
    }

    public double calculaLucro(double porcentagemLucro, double distancia){
        return calculaCusto(distancia)+(calculaCusto(distancia)*porcentagemLucro/100);
    }
}