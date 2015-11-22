package com.example.diegomelo.projetocarro;

import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.View;
import android.widget.ListView;

import com.example.diegomelo.projetocarro.data.CarroDAO;
import com.example.diegomelo.projetocarro.model.Carro;
import com.squareup.otto.Bus;
import com.squareup.otto.Subscribe;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Diego Melo on 17/10/2015.
 */
public class ListaFavoritosCarroFragment extends ListFragment {

    List<Carro> listaCarros;
    CarroAdapter carroAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        listaCarros = new ArrayList<>();

        Bus bus = ((CarroApp)getActivity().getApplication()).getBus();
        bus.register(this);
    }


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        if (listaCarros.isEmpty()) {
            carroAdapter = new CarroAdapter(getActivity(), listaCarros);
            setListAdapter(carroAdapter);

            carregarCarros();
        }
    }

    private void carregarCarros(){
        CarroDAO carroDAO = new CarroDAO(getActivity());
        listaCarros.clear();
        listaCarros.addAll(carroDAO.listar());
        carroAdapter.notifyDataSetChanged();
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);

        Carro carro = listaCarros.get(position);

        if (getActivity() instanceof AoClicarNoCarro){
            ((AoClicarNoCarro)getActivity()).voceClicouNoCarro(carro);
        }
    }

    @Subscribe
    public void listaDeFavoritosAtualizada(Carro carro){
        carregarCarros();
    }

}
