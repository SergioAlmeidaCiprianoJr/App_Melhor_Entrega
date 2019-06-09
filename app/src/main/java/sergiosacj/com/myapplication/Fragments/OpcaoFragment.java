package sergiosacj.com.myapplication.Fragments;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

import sergiosacj.com.myapplication.Interface.ComunicaFragments;
import sergiosacj.com.myapplication.MelhorEntrega.Carreta;
import sergiosacj.com.myapplication.MelhorEntrega.Carro;
import sergiosacj.com.myapplication.MelhorEntrega.Empresa;
import sergiosacj.com.myapplication.MelhorEntrega.Moto;
import sergiosacj.com.myapplication.MelhorEntrega.Van;
import sergiosacj.com.myapplication.MelhorEntrega.Veiculos;
import sergiosacj.com.myapplication.R;

public class OpcaoFragment extends Fragment {

    private TextView tipoMenorCusto;
    private TextView custoMenorCusto;
    private TextView tempoMenorCusto;
    private TextView custoBeneficioMenorCusto;

    private TextView tipoMenorTempo;
    private TextView custoMenorTempo;
    private TextView tempoMenorTempo;
    private TextView custoBeneficioMenorTempo;

    private TextView tipoMelhorCustoBeneficio;
    private TextView custoMelhorCustoBeneficio;
    private TextView tempoMelhorCustoBeneficio;
    private TextView custoBeneficioMelhorCustoBeneficio;

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
        custoBeneficioMenorCusto = view.findViewById(R.id.custoBeneficioVeiculoMenorCusto);

        tipoMenorTempo = view.findViewById(R.id.tipoVeiculoMenorTempo);
        custoMenorTempo = view.findViewById(R.id.custoVeiculoMenorTempo);
        tempoMenorTempo = view.findViewById(R.id.tempoVeiculoMenorTempo);
        custoBeneficioMenorTempo = view.findViewById(R.id.custoBeneficioVeiculoMenorTempo);

        tipoMelhorCustoBeneficio = view.findViewById(R.id.tipoVeiculoMelhorCustoBeneficio);
        custoMelhorCustoBeneficio = view.findViewById(R.id.custoVeiculoMelhorCustoBeneficio);
        tempoMelhorCustoBeneficio = view.findViewById(R.id.tempoVeiculoMelhorCustoBeneficio);
        custoBeneficioMelhorCustoBeneficio = view.findViewById(R.id.custoBeneficioVeiculoMenorCustoBeneficio);

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

        Double custoCarreta = calculaCarreta.calculaCusto(empresa.getDistancia());
        Double tempoCarreta = calculaCarreta.calculaTempo(empresa.getDistancia());
        custoCarreta = arredondar(custoCarreta);
        tempoCarreta = arredondar(tempoCarreta);

        carreta.add(empresa.getFrota().getVeiculoMenorCusto());
        carreta.add(String.valueOf(custoCarreta));
        carreta.add(String.valueOf(tempoCarreta));
        carreta.add(String.valueOf(custoCarreta / tempoCarreta));

        Double custoCarro = calculaCarro.calculaCusto(empresa.getDistancia());
        Double tempoCarro = calculaCarro.calculaTempo(empresa.getDistancia());
        custoCarro = arredondar(custoCarro);
        tempoCarro = arredondar(tempoCarro);

        carro.add(empresa.getFrota().getVeiculoMenorCusto());
        carro.add(String.valueOf(custoCarro));
        carro.add(String.valueOf(tempoCarro));
        carro.add(String.valueOf(custoCarro / tempoCarro));

        Double custoMoto = calculaMoto.calculaCusto(empresa.getDistancia());
        Double tempoMoto = calculaMoto.calculaTempo(empresa.getDistancia());
        custoMoto = arredondar(custoMoto);
        tempoMoto = arredondar(tempoMoto);

        moto.add(empresa.getFrota().getVeiculoMenorCusto());
        moto.add(String.valueOf(custoMoto));
        moto.add(String.valueOf(tempoMoto));
        moto.add(String.valueOf(custoMoto / tempoMoto));

        Double custoVan = calculaVan.calculaCusto(empresa.getDistancia());
        Double tempoVan = calculaVan.calculaTempo(empresa.getDistancia());
        custoVan = arredondar(custoVan);
        tempoVan = arredondar(tempoVan);

        van.add(empresa.getFrota().getVeiculoMenorCusto());
        van.add(String.valueOf(custoVan));
        van.add(String.valueOf(tempoVan));
        van.add(String.valueOf(custoVan / tempoVan));


        if(empresa.getFrota().getVeiculoMenorCusto().equals("carro")) veiculoMenorCusto(carro);
        else if(empresa.getFrota().getVeiculoMenorCusto().equals("carreta")) veiculoMenorCusto(carreta);
        else if(empresa.getFrota().getVeiculoMenorCusto().equals("moto")) veiculoMenorCusto(moto);
        else if(empresa.getFrota().getVeiculoMenorCusto().equals("van")) veiculoMenorCusto(van);

        if(empresa.getFrota().getVeiculoMenorTempo().equals("carro")) veiculoMenorTempo(carro);
        else if(empresa.getFrota().getVeiculoMenorTempo().equals("carreta")) veiculoMenorTempo(carreta);
        else if(empresa.getFrota().getVeiculoMenorTempo().equals("moto")) veiculoMenorTempo(moto);
        else if(empresa.getFrota().getVeiculoMenorTempo().equals("van")) veiculoMenorTempo(van);

        if(empresa.getFrota().getVeiculoMelhorCustoBeneficio().equals("carro")) veiculoMelhorCustoBeneficio(carro);
        else if(empresa.getFrota().getVeiculoMelhorCustoBeneficio().equals("carreta")) veiculoMelhorCustoBeneficio(carreta);
        else if(empresa.getFrota().getVeiculoMelhorCustoBeneficio().equals("moto")) veiculoMelhorCustoBeneficio(moto);
        else if(empresa.getFrota().getVeiculoMelhorCustoBeneficio().equals("van")) veiculoMelhorCustoBeneficio(van);

    }

    public void veiculoMenorCusto(ArrayList<String> veiculo){

        tipoMenorCusto.setText(veiculo.get(0));
        custoMenorCusto.setText(veiculo.get(1));
        tempoMenorCusto.setText(veiculo.get(2));
        custoBeneficioMenorCusto.setText(veiculo.get(3));

    }

    public void veiculoMenorTempo(ArrayList<String> veiculo){

        tipoMenorTempo.setText(veiculo.get(0));
        custoMenorTempo.setText(veiculo.get(1));
        tempoMenorTempo.setText(veiculo.get(2));
        custoBeneficioMenorTempo.setText(veiculo.get(3));

    }

    public void veiculoMelhorCustoBeneficio(ArrayList<String> veiculo){

        tipoMelhorCustoBeneficio.setText(veiculo.get(0));
        custoMelhorCustoBeneficio.setText(veiculo.get(1));
        tempoMelhorCustoBeneficio.setText(veiculo.get(2));
        custoBeneficioMelhorCustoBeneficio.setText(veiculo.get(3));

    }

    private static double arredondar(double media) {
        return Math.round(media);
    }



}
