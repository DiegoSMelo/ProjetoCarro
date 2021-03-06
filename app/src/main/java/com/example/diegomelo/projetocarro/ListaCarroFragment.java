package com.example.diegomelo.projetocarro;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.example.diegomelo.projetocarro.model.Carro;
import com.google.gson.Gson;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Diego Melo on 17/10/2015.
 */
public class ListaCarroFragment extends ListFragment implements SwipeRefreshLayout.OnRefreshListener {

    List<Carro> listaCarros;
    CarroTask carroTask;
    CarroAdapter carroAdapter;
    SwipeRefreshLayout swipe;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        listaCarros = new ArrayList<>();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_lista_carro, null);
        swipe = (SwipeRefreshLayout)view.findViewById(R.id.swipe);
        swipe.setOnRefreshListener(this);
        return view;
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

        ConnectivityManager connectivityManager = (ConnectivityManager)getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();

        if (networkInfo != null && networkInfo.isConnected()){

            swipe.setRefreshing(true);

            carroTask = new CarroTask();
            carroTask.execute();

        }else{
            swipe.setRefreshing(false);
            Toast.makeText(getActivity(), R.string.semConexao, Toast.LENGTH_LONG).show();

        }
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);

        Carro carro = listaCarros.get(position);

        exibirItem(carro);

    }

    private void exibirItem(Carro carro){
        if (getActivity() instanceof AoClicarNoCarro){
            ((AoClicarNoCarro)getActivity()).voceClicouNoCarro(carro);
        }
    }

    @Override
    public void onRefresh() {

        carregarCarros();

    }





    class CarroTask extends AsyncTask<Void, Void, Carro[]>{

        @Override
        protected Carro[] doInBackground(Void... params) {

            OkHttpClient client = new OkHttpClient();

            Request request = new Request.Builder()
                    .url("https://dl.dropboxusercontent.com/u/71103581/listaCarros%20(1).json")
                // .url("https://dl.dropboxusercontent.com/u/71103581/listaCarros.json")
                 //   .url("http://192.166.99.106/carrosJson/listarCarros.php")
                    .build();

            Response response = null;

            try {

                Thread.sleep(5000);

                response = client.newCall(request).execute();
                String stringJson =  response.body().string();

                Gson gson = new Gson();
                Carro[] carros = gson.fromJson(stringJson,Carro[].class);

                return carros;

            } catch (Throwable e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(Carro[] carros) {
            super.onPostExecute(carros);

            if (carros != null){
                listaCarros.clear();

                for (Carro carro: carros) {
                    listaCarros.add(carro);
                }

                carroAdapter.notifyDataSetChanged();

                if (getResources().getBoolean(R.bool.tablet)) {
                    exibirItem(listaCarros.get(0));
                }

            }else{
                Toast.makeText(getActivity(), R.string.msg_ao_baixar, Toast.LENGTH_LONG).show();
            }
            swipe.setRefreshing(false);
        }
    }
}
