package com.example.diegomelo.projetocarro;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.diegomelo.projetocarro.data.CarroDAO;
import com.example.diegomelo.projetocarro.model.Carro;

/**
 * Created by Diego Melo on 17/10/2015.
 */
public class DetalheCarroFragment extends Fragment {

    Carro carro;

    public static DetalheCarroFragment novaInstancia(Carro c){
        Bundle bundle = new Bundle();
        bundle.putSerializable("carro", c);

        DetalheCarroFragment detalheCarroFragment = new DetalheCarroFragment();
        detalheCarroFragment.setArguments(bundle);

        return detalheCarroFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        carro = (Carro) getArguments().getSerializable("carro");

        View layout = inflater.inflate(R.layout.fragment_detalhe_carro, null);

        TextView txtModelo = (TextView) layout.findViewById(R.id.txtModelo);
        TextView txtFabricante = (TextView) layout.findViewById(R.id.txtFabricante);
        TextView txtAnoMotor = (TextView) layout.findViewById(R.id.txtAnoMotor);

        txtModelo.setText(carro.modelo);
        txtFabricante.setText(carro.fabricante);
        txtAnoMotor.setText(carro.motor + " - " + carro.ano);

        return layout;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_detalhe_carro, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == R.id.carro_favorito){
            CarroDAO carroDAO = new CarroDAO(getActivity());

            if (carroDAO.isFavorito(carro)){
                carroDAO.excluir(carro);
            }else{
                carroDAO.inserir(carro);
            }
        }

        return super.onOptionsItemSelected(item);
    }
}
