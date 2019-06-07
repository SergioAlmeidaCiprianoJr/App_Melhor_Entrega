package sergiosacj.com.myapplication.Fragments;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import sergiosacj.com.myapplication.Interface.ComunicaFragments;
import sergiosacj.com.myapplication.MelhorEntrega.Carreta;
import sergiosacj.com.myapplication.MelhorEntrega.Carro;
import sergiosacj.com.myapplication.MelhorEntrega.Moto;
import sergiosacj.com.myapplication.MelhorEntrega.Van;
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

    private String veiculoMenorCusto;
    private String veiculoMenorTempo;
    private String veiculoMelhorCustoBeneficio;
    private boolean entregaImpossivel;

    private Carreta carreta;
    private Carro carro;
    private Moto moto;
    private Van van;

    public OpcaoFragment() {
        // Required empty public constructor
    }

    public void recebeDados(String veiculoMenorCusto, String veiculoMenorTempo, String veiculoMelhorCustoBeneficio, boolean entregaImpossivel){
        this.veiculoMenorCusto = veiculoMenorCusto;
        this.veiculoMenorTempo = veiculoMenorTempo;
        this.veiculoMelhorCustoBeneficio = veiculoMelhorCustoBeneficio;
        this.entregaImpossivel = entregaImpossivel;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_opcao, container, false);



        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        comunicaFragments = (ComunicaFragments) context;

    }
}
