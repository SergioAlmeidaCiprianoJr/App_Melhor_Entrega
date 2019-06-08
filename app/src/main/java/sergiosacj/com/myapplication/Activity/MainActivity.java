package sergiosacj.com.myapplication.Activity;

import android.graphics.Color;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

import sergiosacj.com.myapplication.Fragments.OpcaoFragment;
import sergiosacj.com.myapplication.Interface.ComunicaFragments;
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
    private OpcaoFragment opcaoFragment = new OpcaoFragment();

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
        iniciaInstrucoesFragment();

        buttonCadastro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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
                iniciaEntregaFragment();
            }
        });

        buttonInstrucoes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iniciaInstrucoesFragment();
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
        if(!checkEmpty.isEmpty()) {
            empresa.realizaEntrega(pesoCarga, distancia, tempoMaximo);
            enviaDadosOpcoes();
            iniciaOpcaoFragment();
        }
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

    @Override
    public void enviaDadosOpcoes() {
        opcaoFragment.recebeDados(empresa);
    }

    @Override
    public void veiculoEscolhido(String veiculoEscolhido) {
        empresa.confirmaEntrega(veiculoEscolhido);
    }

    public void iniciaInstrucoesFragment(){
        defineCorButton("buttonInstrucoes");
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction().replace(R.id.frameCadastro, instrucoesFragment);
        transaction.commit();
    }

    public void iniciaCadastroFragment(){
        defineCorButton("buttonCadastro");
        enviaDadosCadastro();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction().replace(R.id.frameCadastro, cadastroFragment);
        transaction.commit();
    }

    public void iniciaEntregaFragment(){
        defineCorButton("buttonEntrega");
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction().replace(R.id.frameCadastro, entregaFragment);
        transaction.commit();
    }

    public void iniciaOpcaoFragment(){
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction().replace(R.id.frameCadastro, opcaoFragment);
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
