package sergiosacj.com.myapplication.Activity;

import android.content.SharedPreferences;
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
import java.util.Objects;

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

    private static final String ARQUIVO_PREFERENCIAS = "ArquivoPreferencias";

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
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
        validaArquivo();

        buttonCadastro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iniciaCadastroFragment();
            }
        });

        buttonDesocupa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iniciaDesocupaFragment();
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

        SharedPreferences preferences = getSharedPreferences(ARQUIVO_PREFERENCIAS, 0);
        SharedPreferences.Editor editor = preferences.edit();

        editor.putString("porcentagemLucro", String.valueOf(porcentagemLucro));
        editor.putInt("numeroCarretas", numeroCarretas);
        editor.putInt("numeroCarros", numeroCarros);
        editor.putInt("numeroMotos", numeroMotos);
        editor.putInt("numeroVans", numeroVans);
        editor.apply();
        editor.commit();

        iniciaEntregaFragment();

    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void realizaEntrega(double pesoCarga, double distancia, double tempoMaximo) {
        ArrayList<Veiculos> checkEmpty = empresa.getFrota().getFrota();
        if(!checkEmpty.isEmpty()) {
            empresa.realizaEntrega(pesoCarga, distancia, tempoMaximo);
            if(!empresa.entregaImpossivel()){
                iniciaOpcaoFragment();
            }
        }
        else Toast.makeText(this, "Sem veiculos", Toast.LENGTH_LONG).show();
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
    public void enviaDadosDesocupa() {
        desocupaFragment.recebeDados(empresa);
    }

    @Override
    public void escolheVeiculo(String veiculoEscolhido) {
        empresa.confirmaEntrega(veiculoEscolhido);
        iniciaDesocupaFragment();
    }

    @Override
    public void retiraVeiculo(Veiculos veiculoRetirado, int posicao) {
        empresa.removeEntrega(veiculoRetirado, posicao);
        iniciaDesocupaFragment();
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
        enviaDadosOpcoes();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction().replace(R.id.frameCadastro, opcaoFragment);
        transaction.commit();
    }

    public void iniciaDesocupaFragment(){
        defineCorButton("buttonDesocupa");
        enviaDadosDesocupa();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction().replace(R.id.frameCadastro, desocupaFragment);
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

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public void validaArquivo(){

        double porcentagemLucro = 0;
        int numeroCarretas = 0, numeroCarros = 0, numeroMotos = 0, numeroVans = 0;

        SharedPreferences preferences = getSharedPreferences(ARQUIVO_PREFERENCIAS, 0);
        if(preferences.contains("porcentagemLucro")){

            porcentagemLucro = Double.parseDouble(Objects.requireNonNull(preferences.getString("porcentagemLucro", "")));

        }

        numeroCarretas = preferences.getInt("numeroCarretas", 0);

        numeroCarros = preferences.getInt("numeroCarros", 0);

        numeroMotos = preferences.getInt("numeroMotos", 0);

        numeroVans = preferences.getInt("numeroVans", 0);


        empresa.atualizaEmpresa(porcentagemLucro, numeroCarros, numeroCarretas, numeroMotos, numeroVans);

    }
}
