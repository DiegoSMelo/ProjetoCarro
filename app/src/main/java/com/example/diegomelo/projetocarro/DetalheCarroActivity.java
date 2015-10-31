package com.example.diegomelo.projetocarro;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class DetalheCarroActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhe_carro);

        if (savedInstanceState == null) {
            Carro carro = (Carro) getIntent().getSerializableExtra("carro");

            DetalheCarroFragment detalheCarroFragment = DetalheCarroFragment.novaInstancia(carro);

            getSupportFragmentManager().beginTransaction().replace(R.id.detalhe, detalheCarroFragment).commit();
        }
    }


}
