package sergiosacj.com.myapplication.Activity;

import android.graphics.Color;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;

import sergiosacj.com.myapplication.ComunicaFragments;
import sergiosacj.com.myapplication.Fragments.CadastroFragment;
import sergiosacj.com.myapplication.Fragments.DesocupaFragment;
import sergiosacj.com.myapplication.Fragments.EntregaFragment;
import sergiosacj.com.myapplication.Fragments.InstrucoesFragment;
import sergiosacj.com.myapplication.MelhorEntrega.Empresa;
import sergiosacj.com.myapplication.MelhorEntrega.Veiculos;
import sergiosacj.com.myapplication.R;

public class MainActivity extends AppCompatActivity implements ComunicaFragments {

    private CadastroFragment cadastroFragment = new CadastroFragment();
    private DesocupaFragment desocupaFragment = new DesocupaFragment();
    private EntregaFragment entregaFragment = new EntregaFragment();
    private InstrucoesFragment instrucoesFragment = new InstrucoesFragment();

    private Empresa empresa = new Empresa();

    private Button buttonCadastro;
    private Button buttonDesocupa;
    private Button buttonEntrega;
    private Button buttonInstrucoes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().setElevation(0);

        buttonCadastro = findViewById(R.id.buttonCadastro);
        buttonDesocupa = findViewById(R.id.buttonDesocupar);
        buttonEntrega = findViewById(R.id.buttonEntregar);
        buttonInstrucoes = findViewById(R.id.buttonInstrucoes);

        buttonCadastro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                defineCorButton("buttonCadastro");
                iniciaCadastroFragment();
            }
        });

        buttonDesocupa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                defineCorButton("buttonDesocupa");
            }
        });

        buttonEntrega.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                defineCorButton("buttonEntrega");
                iniciandoEntregaFragment();
            }
        });

        buttonInstrucoes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                defineCorButton("buttonInstrucoes");
            }
        });
    }


    @Override
    public void atualizaEmpresa(Double porcentagemLucro, int numeroCarros, int numeroCarretas, int numeroMotos, int numeroVans) {

        empresa.atualizaEmpresa(porcentagemLucro, numeroCarros, numeroCarretas, numeroMotos, numeroVans);

    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void realizaEntrega(double pesoCarga, double distancia, double tempoMaximo) {
        ArrayList<Veiculos> checkEmpty = empresa.getFrota().getFrota();
        if(!checkEmpty.isEmpty()) empresa.realizaEntrega(pesoCarga, distancia, tempoMaximo);
        else Toast.makeText(getApplicationContext(), "Para realizar entregas realize cadastro", Toast.LENGTH_LONG);
    }

    @Override
    public void enviaDadosCadastro() {
        cadastroFragment.recebeDados(
                empresa.getPorcentagemLucro(),
                empresa.getNumeroCarros(),
                empresa.getNumeroCarretas(),
                empresa.getNumeroMotos(),
                empresa.getNumeroVans()
        );
    }

    public void iniciaCadastroFragment(){
        enviaDadosCadastro();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction().replace(R.id.frameCadastro, cadastroFragment);
        transaction.commit();
    }

    public void iniciandoEntregaFragment(){
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction().replace(R.id.frameCadastro, entregaFragment);
        transaction.commit();
    }

    public void defineCorButton(String button){

        if(button.equals("buttonCadastro")) buttonCadastro.setTextColor(Color.parseColor("#eead2d"));
        else buttonCadastro.setTextColor(Color.parseColor("#FFFFFF"));

        if(button.equals("buttonDesocupa")) buttonDesocupa.setTextColor(Color.parseColor("#eead2d"));
        else buttonDesocupa.setTextColor(Color.parseColor("#FFFFFF"));

        if(button.equals("buttonEntrega")) buttonEntrega.setTextColor(Color.parseColor("#eead2d"));
        else buttonEntrega.setTextColor(Color.parseColor("#FFFFFF"));

        if(button.equals("buttonInstrucoes")) buttonInstrucoes.setTextColor(Color.parseColor("#eead2d"));
        else buttonInstrucoes.setTextColor(Color.parseColor("#FFFFFF"));

    }
}
