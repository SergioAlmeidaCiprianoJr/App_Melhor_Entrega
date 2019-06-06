package sergiosacj.com.myapplication;

public interface ComunicaFragments {

    void atualizaEmpresa(Double porcentagemLucro, int numeroCarros, int numeroCarretas, int numeroMotos, int numeroVans);

    void realizaEntrega(double pesoCarga, double distancia, double tempoMaximo);

    void enviaDadosCadastro();
}
