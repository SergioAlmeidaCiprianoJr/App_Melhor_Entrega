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

    private TextView tipoMenorMelhorCustoBeneficio;
    private TextView custoMenorMelhorCustoBeneficio;
    private TextView tempoMenorMelhorCustoBeneficio;
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
        custoBeneficioMenorTempo = view.findViewById(R.id.custoBeneficioMenorTempo);

        tipoMenorMelhorCustoBeneficio = view.findViewById(R.id.tipoVeiculoMelhorCustoBeneficio);
        custoMenorMelhorCustoBeneficio = view.findViewById(R.id.custoVeiculoMelhorCustoBeneficio);
        tempoMenorMelhorCustoBeneficio = view.findViewById(R.id.tempoVeiculoMelhorCustoBeneficio);
        custoBeneficioMelhorCustoBeneficio = view.findViewById(R.id.custoBeneficioVeiculoMenorCustoBeneficio);

        escolheCusto = view.findViewById(R.id.buttonVeiculoMenorCusto);
        escolheTempo = view.findViewById(R.id.buttonVeiculoMenorTempo);
        escolheCustoBeneficio = view.findViewById(R.id.buttonVeiculoMelhorCustoBeneficio);



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

        carreta.add(empresa.getFrota().getVeiculoMenorCusto());
        carreta.add(String.valueOf(custoCarreta));
        carreta.add(String.valueOf(tempoCarreta));
        carreta.add(String.valueOf(custoCarreta/tempoCarreta));


    }


}
