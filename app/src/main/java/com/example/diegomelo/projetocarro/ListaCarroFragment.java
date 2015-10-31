package com.example.diegomelo.projetocarro;

import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Diego Melo on 17/10/2015.
 */
public class ListaCarroFragment extends ListFragment {

    List<Carro> listaCarros;

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        listaCarros = new ArrayList<Carro>();
        listaCarros.add(new Carro("Siena", "Fiat", "2004", "1.8"));
        listaCarros.add(new Carro("March", "Nissan", "2015", "1.6"));
        listaCarros.add(new Carro("Santana", "Volkswagen", "1996", "2.0"));

        ArrayAdapter<Carro> carroAdapter = new ArrayAdapter<Carro>(
                getActivity(),
                android.R.layout.simple_list_item_1,
                listaCarros
        );

        setListAdapter(carroAdapter);
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);

        Carro carro = listaCarros.get(position);

        if (getActivity() instanceof AoClicarNoCarro){
            ((AoClicarNoCarro)getActivity()).voceClicouNoCarro(carro);
        }
    }

    interface AoClicarNoCarro{
        void voceClicouNoCarro(Carro c);
    }
}
