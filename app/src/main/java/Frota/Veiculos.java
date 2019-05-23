package Frota;

public class Veiculos {

    private String tipo;
    private String estado;
    private String combustivel;
    private double rendimentoDiesel;
    private double rendimentoGasolina;
    private double rendimentoAlcool;
    private double carga_suportada;
    private double carga_atual;
    private double velocidade_media;
    private double taxa_reducao_rendimento;
    private double precoGasolina = 4.449;
    private double precoAlcool = 3.499;
    private double precoDiesel = 3.869;

    public Veiculos() {}

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

    public double getRendimentoDiesel() {
        return rendimentoDiesel;
    }

    public void setRendimentoDiesel(double rendimentoDiesel) {
        this.rendimentoDiesel = rendimentoDiesel;
    }

    public double getRendimentoGasolina() {
        return rendimentoGasolina;
    }

    public void setRendimentoGasolina(double rendimentoGasolina) {
        this.rendimentoGasolina = rendimentoGasolina;
    }

    public double getRendimentoAlcool() {
        return rendimentoAlcool;
    }

    public void setRendimentoAlcool(double rendimentoAlcool) {
        this.rendimentoAlcool = rendimentoAlcool;
    }

    public double getCarga_suportada() {
        return carga_suportada;
    }

    public void setCarga_suportada(double carga_suportada) {
        this.carga_suportada = carga_suportada;
    }

    public double getCarga_atual() {
        return carga_atual;
    }

    public void setCarga_atual(double carga_atual) {
        this.carga_atual = carga_atual;
    }

    public double getVelocidade_media() {
        return velocidade_media;
    }

    public void setVelocidade_media(double velocidade_media) {
        this.velocidade_media = velocidade_media;
    }

    public double getTaxa_reducao_rendimento() {
        return taxa_reducao_rendimento;
    }

    public void setTaxa_reducao_rendimento(double taxa_reducao_rendimento) {
        this.taxa_reducao_rendimento = taxa_reducao_rendimento;
    }

    public void calcula_rendimento() {
        if(combustivel.equals("diesel")){
            setRendimentoDiesel(getRendimentoDiesel() - getCarga_atual()*getTaxa_reducao_rendimento());
        }
        else if(combustivel.equals("gasolina")){
            setRendimentoGasolina(getRendimentoGasolina() - getCarga_atual()*getTaxa_reducao_rendimento());
        }
        else if(combustivel.equals("alcool")){
            setRendimentoAlcool(getRendimentoAlcool() - getCarga_suportada()*getTaxa_reducao_rendimento());
        }
        else if(combustivel.equals("flex")){
            setRendimentoGasolina(getRendimentoGasolina() - getCarga_atual()*getTaxa_reducao_rendimento());
            setRendimentoAlcool(getRendimentoAlcool() - getCarga_suportada()*getTaxa_reducao_rendimento());
        }
    }
}

