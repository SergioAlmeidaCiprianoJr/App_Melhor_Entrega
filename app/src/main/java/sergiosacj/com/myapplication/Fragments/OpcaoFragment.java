package sergiosacj.com.myapplication.Fragments;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;

import sergiosacj.com.myapplication.Interface.ComunicaFragments;
import sergiosacj.com.myapplication.MelhorEntrega.Carreta;
import sergiosacj.com.myapplication.MelhorEntrega.Carro;
import sergiosacj.com.myapplication.MelhorEntrega.Empresa;
import sergiosacj.com.myapplication.MelhorEntrega.Moto;
import sergiosacj.com.myapplication.MelhorEntrega.Van;
import sergiosacj.com.myapplication.R;

public class OpcaoFragment extends Fragment {

    private TextView tipoMenorCusto;
    private TextView custoMenorCusto;
    private TextView tempoMenorCusto;
    private TextView custoLucroMenorCusto;

    private TextView tipoMenorTempo;
    private TextView custoMenorTempo;
    private TextView tempoMenorTempo;
    private TextView custoLucroMenorTempo;

    private TextView tipoMelhorCustoBeneficio;
    private TextView custoMelhorCustoBeneficio;
    private TextView tempoMelhorCustoBeneficio;
    private TextView custoLucroMelhorCustoBeneficio;

    private ComunicaFragments comunicaFragments;


    private Button escolheCusto;
    private Button escolheTempo;
    private Button escolheCustoBeneficio;

    private Empresa empresa;

    public OpcaoFragment() {
        // Required empty public constructor
    }

    public void recebeDados(Empresa empresa){
        this.empresa = empresa;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_opcao, container, false);


        tipoMenorCusto = view.findViewById(R.id.tipoVeiculoMenorCusto);
        custoMenorCusto = view.findViewById(R.id.custoVeiculoMenorCusto);
        tempoMenorCusto = view.findViewById(R.id.tempoVeiculoMenorCusto);
        custoLucroMenorCusto = view.findViewById(R.id.custoBeneficioVeiculoMenorCusto);

        tipoMenorTempo = view.findViewById(R.id.tipoVeiculoMenorTempo);
        custoMenorTempo = view.findViewById(R.id.custoVeiculoMenorTempo);
        tempoMenorTempo = view.findViewById(R.id.tempoVeiculoMenorTempo);
        custoLucroMenorTempo = view.findViewById(R.id.custoBeneficioVeiculoMenorTempo);

        tipoMelhorCustoBeneficio = view.findViewById(R.id.tipoVeiculoMelhorCustoBeneficio);
        custoMelhorCustoBeneficio = view.findViewById(R.id.custoVeiculoMelhorCustoBeneficio);
        tempoMelhorCustoBeneficio = view.findViewById(R.id.tempoVeiculoMelhorCustoBeneficio);
        custoLucroMelhorCustoBeneficio = view.findViewById(R.id.custoBeneficioVeiculoMenorCustoBeneficio);

        escolheCusto = view.findViewById(R.id.buttonVeiculoMenorCusto);
        escolheTempo = view.findViewById(R.id.buttonVeiculoMenorTempo);
        escolheCustoBeneficio = view.findViewById(R.id.buttonVeiculoMelhorCustoBeneficio);

        colocaValoresTela();

        escolheCusto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                comunicaFragments.veiculoEscolhido(tipoMenorCusto.getText().toString());
            }
        });

        escolheTempo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                comunicaFragments.veiculoEscolhido(tempoMenorTempo.getText().toString());
            }
        });

        escolheCustoBeneficio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                comunicaFragments.veiculoEscolhido(tipoMelhorCustoBeneficio.getText().toString());
            }
        });

        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        comunicaFragments = (ComunicaFragments) context;

    }

    public void colocaValoresTela(){

        ArrayList<String> carreta = new ArrayList<>();
        ArrayList<String> carro = new ArrayList<>();
        ArrayList<String> moto = new ArrayList<>();
        ArrayList<String> van = new ArrayList<>();

        Carreta calculaCarreta = new Carreta();
        Carro calculaCarro = new Carro();
        Moto calculaMoto = new Moto();
        Van calculaVan = new Van();

        calculaCarreta.setCargaAtual(empresa.getCarga());
        calculaCarro.setCargaAtual(empresa.getCarga());
        calculaMoto.setCargaAtual(empresa.getCarga());
        calculaVan.setCargaAtual(empresa.getCarga());

        carreta.add(empresa.getFrota().getVeiculoMenorCusto());
        carreta.add(empresa.getFrota().getVeiculoMenorTempo());
        carreta.add(empresa.getFrota().getVeiculoMelhorCustoBeneficio());
        carreta.add(String.valueOf(arredondar(calculaCarreta.calculaCusto(empresa.getDistancia()))));
        carreta.add(String.valueOf(arredondar(calculaCarreta.calculaTempo(empresa.getDistancia()))));
        carreta.add(String.valueOf(arredondar(calculaCarreta.calculaLucro(empresa.getPorcentagemLucro(), empresa.getDistancia()))));

        carro.add(empresa.getFrota().getVeiculoMenorCusto());
        carro.add(empresa.getFrota().getVeiculoMenorTempo());
        carro.add(empresa.getFrota().getVeiculoMelhorCustoBeneficio());
        carro.add(String.valueOf(arredondar(calculaCarro.calculaCusto(empresa.getDistancia()))));
        carro.add(String.valueOf(arredondar(calculaCarro.calculaTempo(empresa.getDistancia()))));
        carro.add(String.valueOf(arredondar(calculaCarro.calculaLucro(empresa.getPorcentagemLucro(), empresa.getDistancia()))));

        moto.add(empresa.getFrota().getVeiculoMenorCusto());
        moto.add(empresa.getFrota().getVeiculoMenorTempo());
        moto.add(empresa.getFrota().getVeiculoMelhorCustoBeneficio());
        moto.add(String.valueOf(arredondar(calculaMoto.calculaCusto(empresa.getDistancia()))));
        moto.add(String.valueOf(arredondar(calculaMoto.calculaTempo(empresa.getDistancia()))));
        moto.add(String.valueOf(arredondar(calculaMoto.calculaLucro(empresa.getPorcentagemLucro(), empresa.getDistancia()))));

        van.add(empresa.getFrota().getVeiculoMenorCusto());
        van.add(empresa.getFrota().getVeiculoMenorTempo());
        van.add(empresa.getFrota().getVeiculoMelhorCustoBeneficio());
        van.add(String.valueOf(arredondar(calculaVan.calculaCusto(empresa.getDistancia()))));
        van.add(String.valueOf(arredondar(calculaVan.calculaTempo(empresa.getDistancia()))));
        van.add(String.valueOf(arredondar(calculaVan.calculaLucro(empresa.getPorcentagemLucro(), empresa.getDistancia()))));


        if(empresa.getFrota().getVeiculoMenorCusto().equals("carro")) veiculoMenorCusto(carro, 0);
        else if(empresa.getFrota().getVeiculoMenorCusto().equals("carreta")) veiculoMenorCusto(carreta, 0);
        else if(empresa.getFrota().getVeiculoMenorCusto().equals("moto")) veiculoMenorCusto(moto, 0);
        else if(empresa.getFrota().getVeiculoMenorCusto().equals("van")) veiculoMenorCusto(van, 0);

        if(empresa.getFrota().getVeiculoMenorTempo().equals("carro")) veiculoMenorTempo(carro, 1);
        else if(empresa.getFrota().getVeiculoMenorTempo().equals("carreta")) veiculoMenorTempo(carreta, 1);
        else if(empresa.getFrota().getVeiculoMenorTempo().equals("moto")) veiculoMenorTempo(moto, 1);
        else if(empresa.getFrota().getVeiculoMenorTempo().equals("van")) veiculoMenorTempo(van, 1);

        if(empresa.getFrota().getVeiculoMelhorCustoBeneficio().equals("carro")) veiculoMelhorCustoBeneficio(carro, 2);
        else if(empresa.getFrota().getVeiculoMelhorCustoBeneficio().equals("carreta")) veiculoMelhorCustoBeneficio(carreta, 2);
        else if(empresa.getFrota().getVeiculoMelhorCustoBeneficio().equals("moto")) veiculoMelhorCustoBeneficio(moto, 2);
        else if(empresa.getFrota().getVeiculoMelhorCustoBeneficio().equals("van")) veiculoMelhorCustoBeneficio(van, 2);

    }

    public void veiculoMenorCusto(ArrayList<String> veiculo, int posicao){

        tipoMenorCusto.setText(veiculo.get(posicao));
        custoMenorCusto.setText(veiculo.get(3));
        tempoMenorCusto.setText(veiculo.get(4));
        custoLucroMenorCusto.setText(veiculo.get(5));

    }

    public void veiculoMenorTempo(ArrayList<String> veiculo, int posicao){

        tipoMenorTempo.setText(veiculo.get(posicao));
        custoMenorTempo.setText(veiculo.get(3));
        tempoMenorTempo.setText(veiculo.get(4));
        custoLucroMenorTempo.setText(veiculo.get(5));

    }

    public void veiculoMelhorCustoBeneficio(ArrayList<String> veiculo, int posicao){

        tipoMelhorCustoBeneficio.setText(veiculo.get(posicao));
        custoMelhorCustoBeneficio.setText(veiculo.get(3));
        tempoMelhorCustoBeneficio.setText(veiculo.get(4));
        custoLucroMelhorCustoBeneficio.setText(veiculo.get(5));

    }

    private static String arredondar(double media) {
        NumberFormat formatter = new DecimalFormat("0.##");
        return formatter.format(media);
    }



}
