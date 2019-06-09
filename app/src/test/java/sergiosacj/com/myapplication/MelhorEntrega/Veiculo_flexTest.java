package sergiosacj.com.myapplication.MelhorEntrega;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class Veiculo_flexTest {

    private double rendimentoAlcool;
    private double rendimentoGasolina;
    private double taxaReducaoRendimentoAlcool;
    private double taxaReducaoRendimentoGasolina;
    private double carga;
    private double porcentagemLucro;
    private double distancia;
    private Veiculo_flex carro;

    @Before
    public void BeforeTest() {
        rendimentoAlcool = 12.0;
        rendimentoGasolina = 14.0;
        taxaReducaoRendimentoAlcool = 0.0231;
        taxaReducaoRendimentoGasolina = 0.025;
        carga = 300.0;
        porcentagemLucro = 10.0;
        distancia = 60.0;
    }

    @Test
    public void calculaRendimentoGasolina() {
        double esperado = rendimentoGasolina - carga*taxaReducaoRendimentoGasolina;
        carro = new Carro();
        carro.setCargaAtual(300);
        carro.calculaRendimentoGasolina();
        assertEquals(esperado, carro.getRendimentoGasolina(), 0);
    }

    @Test
    public void calculaRendimentoAlcool() {
        double esperado = rendimentoAlcool - carga*taxaReducaoRendimentoAlcool;
        carro = new Carro();
        carro.setCargaAtual(300);
        carro.calculaRendimentoAlcool();
        assertEquals(esperado, carro.getRendimentoAlcool(), 0);
    }

    @Test
    public void calculaCusto() {
        carro = new Carro();
        carro.setCargaAtual(300);
        carro.calculaRendimento();
        rendimentoGasolina = rendimentoGasolina - carga*taxaReducaoRendimentoGasolina;
        double esperado = distancia*4.4449/rendimentoGasolina;
        assertEquals(esperado, carro.calculaCusto(distancia), 0);
    }

    @Test
    public void calculaCustoGasolina() {
        carro = new Carro();
        carro.setCargaAtual(300.0);
        carro.calculaRendimento();
        rendimentoGasolina -= carga*taxaReducaoRendimentoGasolina;
        double esperado = distancia*4.4449/rendimentoGasolina;
        assertEquals(esperado, carro.calculaCustoGasolina(distancia), 0);
    }

    @Test
    public void calculaCustoAlcool() {
        carro = new Carro();
        carro.setCargaAtual(300.0);
        carro.calculaRendimento();
        rendimentoAlcool -= carga*taxaReducaoRendimentoAlcool;
        double esperado = distancia*3.499/rendimentoAlcool;
        assertEquals(esperado, carro.calculaCustoGasolina(distancia), 0);
    }

    @Test
    public void calculaLucro() {
        carro = new Carro();
        carro.setCargaAtual(300.0);
        carro.calculaRendimento();
        rendimentoGasolina = rendimentoGasolina - carga*taxaReducaoRendimentoGasolina;
        double esperado = distancia*4.4449/rendimentoGasolina;
        assertEquals(esperado, carro.calculaLucro(porcentagemLucro, distancia), 0);
    }
}