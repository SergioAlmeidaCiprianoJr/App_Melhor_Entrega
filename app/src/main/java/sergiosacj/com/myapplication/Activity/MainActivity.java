package sergiosacj.com.myapplication.Activity;

import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import sergiosacj.com.myapplication.Fragments.CadastroFragment;
import sergiosacj.com.myapplication.Fragments.DesocupaFragment;
import sergiosacj.com.myapplication.Fragments.EntregaFragment;
import sergiosacj.com.myapplication.Fragments.InstrucoesFragment;
import sergiosacj.com.myapplication.MelhorEntrega.Empresa;
import sergiosacj.com.myapplication.R;

public class MainActivity extends AppCompatActivity implements CadastroFragment.CadastroListener{

    private CadastroFragment cadastroFragment;
    private DesocupaFragment desocupaFragment;
    private EntregaFragment entregaFragment;
    private InstrucoesFragment instrucoesFragment;

    private Empresa empresa = new Empresa();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().setElevation(0);

        cadastroFragment = new CadastroFragment();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction().replace(R.id.frameConteudo, cadastroFragment);
        transaction.commit();

    }


    @Override
    public void atualizaEmpresa(Double porcentagemLucro, int numeroCarros, int numeroCarretas, int numeroMotos, int numeroVans) {

        empresa.atualizaEmpresa(porcentagemLucro, numeroCarros, numeroCarretas, numeroMotos, numeroVans);

    }
}
