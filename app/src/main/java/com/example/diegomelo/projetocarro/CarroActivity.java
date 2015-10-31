package com.example.diegomelo.projetocarro;


import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

public class CarroActivity extends AppCompatActivity implements ListaCarroFragment.AoClicarNoCarro{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carro);

        ViewPager viewPager = (ViewPager)findViewById(R.id.viewPager);
        viewPager.setAdapter(new PaginasAdapter(getSupportFragmentManager()));

        TabLayout tabLayout = (TabLayout)findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
    }


    @Override
    public void voceClicouNoCarro(Carro c) {
        if (getResources().getBoolean(R.bool.fone)) {
            Intent it = new Intent(this, DetalheCarroActivity.class);
            it.putExtra("carro", c);
            startActivity(it);
        }else{

            DetalheCarroFragment detalheCarroFragment = DetalheCarroFragment.novaInstancia(c);

            getSupportFragmentManager().beginTransaction().replace(R.id.detalhe, detalheCarroFragment).commit();
        }
    }


    private class PaginasAdapter extends FragmentPagerAdapter{

        public PaginasAdapter(FragmentManager fragmentManager){
            super(fragmentManager);
        }

        @Override
        public Fragment getItem(int position) {
            if (position == 0){
                return new ListaCarroFragment();
            }else{
                return new Fragment();
            }
        }

        @Override
        public int getCount() {
            return 2;
        }

        @Override
        public CharSequence getPageTitle(int position) {

            if (position == 0){
                return "Lista de carros";
            }else{
                return "Meus favoritos";
            }

        }
    }
}
