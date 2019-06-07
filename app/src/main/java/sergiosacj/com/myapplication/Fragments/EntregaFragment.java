package sergiosacj.com.myapplication.Fragments;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import sergiosacj.com.myapplication.Interface.ComunicaFragments;
import sergiosacj.com.myapplication.R;

public class EntregaFragment extends Fragment {

    private EditText pesoCarga;
    private EditText distancia;
    private EditText tempoMaximo;

    private Button realizaEntrega;
    private ComunicaFragments comunicaFragments;

    public EntregaFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_entrega, container, false);

        pesoCarga = view.findViewById(R.id.pesoCarga);
        distancia = view.findViewById(R.id.distancia);
        tempoMaximo = view.findViewById(R.id.tempoMaximo);

        realizaEntrega = view.findViewById(R.id.buttonRealizaEntrega);

        realizaEntrega.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(verificaEntrada()) {
                    comunicaFragments.realizaEntrega(
                            Double.parseDouble(pesoCarga.getText().toString()),
                            Double.parseDouble(distancia.getText().toString()),
                            Double.parseDouble(tempoMaximo.getText().toString())
                    );
                    Toast.makeText(getContext(), "Enviado", Toast.LENGTH_SHORT).show();
                }
                else Toast.makeText(getContext(), "Preencha os campos primeiro", Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        comunicaFragments = (ComunicaFragments) context;

    }

    public boolean verificaEntrada(){
        if(pesoCarga.getText().toString().isEmpty()) return false;
        else if(distancia.getText().toString().isEmpty()) return false;
        else if(tempoMaximo.getText().toString().isEmpty()) return false;
        else return true;
    }

}
