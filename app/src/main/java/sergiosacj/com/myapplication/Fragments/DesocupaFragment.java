package sergiosacj.com.myapplication.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import sergiosacj.com.myapplication.Adapter.AdapterVeiculosOcupados;
import sergiosacj.com.myapplication.MelhorEntrega.Empresa;
import sergiosacj.com.myapplication.R;

public class DesocupaFragment extends Fragment {

    private RecyclerView recyclerView;
    private Empresa empresa;

    public DesocupaFragment() {
        // Required empty public constructor
    }

    public void recebeDados(Empresa empresa){
        this.empresa = empresa;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_desocupar, container, false);

        recyclerView = view.findViewById(R.id.RecyclerOcupados);
        AdapterVeiculosOcupados adaptador = new AdapterVeiculosOcupados(empresa.getVeiculosRealizandoEntregas());
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.addItemDecoration(new DividerItemDecoration(getContext(), LinearLayout.VERTICAL));
        recyclerView.setAdapter(adaptador);

        return view;
    }

}
