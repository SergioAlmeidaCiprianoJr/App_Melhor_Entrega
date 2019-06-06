package sergiosacj.com.myapplication.Fragments;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import sergiosacj.com.myapplication.ComunicaFragments;
import sergiosacj.com.myapplication.R;

public class CadastroFragment extends Fragment {

    private Button diminuiCarros, aumentaCarros;
    private Button diminuiCarretas, aumentaCarretas;
    private Button diminuiMotos, aumentaMotos;
    private Button diminuiVans, aumentaVans;
    private Button confirmaAlteracoes;

    private EditText taxaLucro;

    private TextView quantidadeCarros;
    private TextView quantidadeCarretas;
    private TextView quantidadeMotos;
    private TextView quantidadeVans;

    private ComunicaFragments comunicaCadastroFragments;

    /*
    public void recebeDados(Double porcentagemLucro, int numeroCarros, int numeroCarretas, int numeroMotos, int numeroVans){
        taxaLucro.setText(String.valueOf(porcentagemLucro));
        quantidadeCarros.setText(String.valueOf(numeroCarros));
        quantidadeCarretas.setText(String.valueOf(numeroCarretas));
        quantidadeMotos.setText(String.valueOf(numeroMotos));
        quantidadeVans.setText(String.valueOf(numeroVans));
    }*/


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_cadastro, container, false);

        diminuiCarros = view.findViewById(R.id.diminuiCarros);
        aumentaCarros = view.findViewById(R.id.aumentaCarros);
        diminuiCarretas = view.findViewById(R.id.diminuiCarretas);
        aumentaCarretas = view.findViewById(R.id.aumentaCarretas);
        diminuiMotos = view.findViewById(R.id.diminuiMotos);
        aumentaMotos = view.findViewById(R.id.aumentaMotos);
        diminuiVans = view.findViewById(R.id.diminuiVans);
        aumentaVans = view.findViewById(R.id.aummentaVans);
        confirmaAlteracoes = view.findViewById(R.id.buttonConfirmaAlteracoes);

        taxaLucro = view.findViewById(R.id.editTextTaxaLucro);

        quantidadeCarros = view.findViewById(R.id.quantidadeCarro);
        quantidadeCarretas = view.findViewById(R.id.quantidadeCarreta);
        quantidadeMotos = view.findViewById(R.id.quantidadeMoto);
        quantidadeVans = view.findViewById(R.id.quantidadeVan);

        alteraCarros(view);
        alteraCarretas(view);
        alteraMotos(view);
        alteraVans(view);

        confirmaAlteracoes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(verificaTaxaLucro()){
                    comunicaCadastroFragments.atualizaEmpresa(
                            Double.parseDouble(taxaLucro.getText().toString()),
                            Integer.parseInt(quantidadeCarros.getText().toString()),
                            Integer.parseInt(quantidadeCarretas.getText().toString()),
                            Integer.parseInt(quantidadeMotos.getText().toString()),
                            Integer.parseInt(quantidadeVans.getText().toString())
                    );
                }
            }
        });

        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        comunicaCadastroFragments = (ComunicaFragments) context;

    }

    boolean verificaTaxaLucro(){
        if(taxaLucro.getText().toString().isEmpty()){
            return false;
        }
        else return true;
    }

    void alteraCarros(final View view){

        aumentaCarros.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int valorCarros = Integer.parseInt(quantidadeCarros.getText().toString());
                valorCarros++;
                if(valorCarros<0){
                    Toast.makeText(getContext(), getString(R.string.altera_carro), Toast.LENGTH_LONG).show();
                    quantidadeCarros.setText(String.valueOf(0));
                }
                else{
                    quantidadeCarros.setText(String.valueOf(valorCarros));
                }
            }
        });

        diminuiCarros.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int valorCarros = Integer.parseInt(quantidadeCarros.getText().toString());
                valorCarros--;
                if(valorCarros<0){
                    Toast.makeText(getContext(), getString(R.string.altera_carro), Toast.LENGTH_LONG).show();
                    quantidadeCarros.setText(String.valueOf(0));
                }
                else{
                    quantidadeCarros.setText(String.valueOf(valorCarros));
                }
            }
        });
    }

    void alteraCarretas(final View view){

        aumentaCarretas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int valorCarretas = Integer.parseInt(quantidadeCarretas.getText().toString());
                valorCarretas++;
                if(valorCarretas<0){
                    Toast.makeText(getContext(), getString(R.string.altera_carreta), Toast.LENGTH_LONG).show();
                    quantidadeCarretas.setText(String.valueOf(0));
                }
                else{
                    quantidadeCarretas.setText(String.valueOf(valorCarretas));
                }
            }
        });

        diminuiCarretas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int valorCarretas = Integer.parseInt(quantidadeCarretas.getText().toString());
                valorCarretas--;
                if(valorCarretas<0){
                    Toast.makeText(getContext(), getString(R.string.altera_carreta), Toast.LENGTH_LONG).show();
                    quantidadeCarretas.setText(String.valueOf(0));
                }
                else{
                    quantidadeCarretas.setText(String.valueOf(valorCarretas));
                }
            }
        });
    }

    void alteraMotos(final View view){

        aumentaMotos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int valorMotos = Integer.parseInt(quantidadeMotos.getText().toString());
                valorMotos++;
                if(valorMotos<0){
                    Toast.makeText(getContext(), getString(R.string.altera_moto), Toast.LENGTH_LONG).show();
                    quantidadeMotos.setText(String.valueOf(0));
                }
                else{
                    quantidadeMotos.setText(String.valueOf(valorMotos));
                }
            }
        });

        diminuiMotos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int valorMotos = Integer.parseInt(quantidadeMotos.getText().toString());
                valorMotos--;
                if(valorMotos<0){
                    Toast.makeText(getContext(), getString(R.string.altera_moto), Toast.LENGTH_LONG).show();
                    quantidadeMotos.setText(String.valueOf(0));
                }
                else{
                    quantidadeMotos.setText(String.valueOf(valorMotos));
                }
            }
        });
    }

    void alteraVans(final View view){

        aumentaVans.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int valorVans = Integer.parseInt(quantidadeVans.getText().toString());
                valorVans++;
                if(valorVans<0){
                    Toast.makeText(getContext(), getString(R.string.altera_van), Toast.LENGTH_LONG).show();
                    quantidadeVans.setText(String.valueOf(0));
                }
                else{
                    quantidadeVans.setText(String.valueOf(valorVans));
                }
            }
        });

        diminuiVans.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int valorVans = Integer.parseInt(quantidadeVans.getText().toString());
                valorVans--;
                if(valorVans<0){
                    Toast.makeText(getContext(), getString(R.string.altera_van), Toast.LENGTH_LONG).show();
                    quantidadeVans.setText(String.valueOf(0));
                }
                else{
                    quantidadeVans.setText(String.valueOf(valorVans));
                }
            }
        });
    }


}
