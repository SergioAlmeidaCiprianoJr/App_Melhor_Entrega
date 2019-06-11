package sergiosacj.com.myapplication.Interface;

public interface ComunicaFragments {

    void atualizaEmpresa(Double porcentagemLucro, int numeroCarros, int numeroCarretas, int numeroMotos, int numeroVans);

    void realizaEntrega(double pesoCarga, double distancia, double tempoMaximo);

    void enviaDadosCadastro();

    void enviaDadosOpcoes();

    void enviaDadosDesocupa();

    void escolheVeiculo(String veiculoEscolhido);

    void retiraVeiculo(String veiculoRetirado, int posicao);
}
