package com.example.diegomelo.projetocarro;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.diegomelo.projetocarro.model.Carro;
import com.google.gson.Gson;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;
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

        new CarroTask().execute();
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

    class CarroTask extends AsyncTask<Void, Void, Carro[]>{

        @Override
        protected Carro[] doInBackground(Void... params) {

            OkHttpClient client = new OkHttpClient();

            Request request = new Request.Builder()
                    .url("https://dl.dropboxusercontent.com/u/71103581/listaCarros.json")
                    //.url("http://192.166.99.106/carrosJson/listarCarros.php")
                    .build();

            Response response = null;

            try {
                response = client.newCall(request).execute();
                String s =  response.body().string();

                Gson gson = new Gson();
                Carro[] carros = gson.fromJson(s,Carro[].class);
                return carros;
            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(Carro[] carros) {
            super.onPostExecute(carros);

            if (carros != null){
                listaCarros = new ArrayList<Carro>();

                for (Carro carro: carros) {
                    listaCarros.add(carro);
                }

                ArrayAdapter<Carro> carroAdapter = new ArrayAdapter<Carro>(
                        getActivity(),
                        android.R.layout.simple_list_item_1,
                        listaCarros
                );

                setListAdapter(carroAdapter);
            }
        }
    }
}
