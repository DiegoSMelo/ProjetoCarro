package com.example.diegomelo.projetocarro;


import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.example.diegomelo.projetocarro.model.Carro;

public class CarroActivity extends AppCompatActivity implements ListaCarroFragment.AoClicarNoCarro{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carro);

        /*
        Classe responsável por gerenciar a funcionalidade de arrastar o layout pra esquerda e direita.
        Controle de troca de fragments.
         */
        ViewPager viewPager = (ViewPager)findViewById(R.id.viewPager);
        viewPager.setAdapter(new PaginasAdapter(getSupportFragmentManager()));

        /*
          Gerencia os eventos das tabs
          Add uma ViewPager para transmitir eventos para o TabLayout.
        */
        TabLayout tabLayout = (TabLayout)findViewById(R.id.tabs);
       // tabLayout.setTabMode(TabLayout.MODE_FIXED);
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        tabLayout.setupWithViewPager(viewPager);
    }

    /*
    Método executado ao clicar em um carro da lista.
     */
    @Override
    public void voceClicouNoCarro(Carro c) {
        /*
        Verifica se o aparelho é fone ou tablet.
         */
        if (getResources().getBoolean(R.bool.fone)) {
            /*
            Caso seja fone, ele inicia a activity de detalhe, passando com o parâmetro o objeto do carro a ser detalhado.
            Nesse caso, o fragment é implementado em outra activity.
             */
            Intent it = new Intent(this, DetalheCarroActivity.class);
            it.putExtra("carro", c);
            startActivity(it);
        }else{
            /*
            Caso seja tablet, não haverá mudança de activity para implementar a fragment.
             */

            DetalheCarroFragment detalheCarroFragment = DetalheCarroFragment.novaInstancia(c);
            /*
            Substitui o que está sendo exibido no layout com id detalhe pelo fragment que está sendo passado.
             */
            getSupportFragmentManager().beginTransaction().replace(R.id.detalhe, detalheCarroFragment).commit();
        }
    }


    /*
     O PageAdapter é uma classe que serve para preencher páginas dentro de um ViewPager.
     O FragmentPagerAdapter implementa um PageAdapter, representando cada página como um fragment.
     */
    private class PaginasAdapter extends FragmentPagerAdapter{

        public PaginasAdapter(FragmentManager fragmentManager){
            super(fragmentManager);
        }

        /*
        Retorna o Fregment específico para a posição passada como parâmetro.
         */
        @Override
        public Fragment getItem(int position) {
            if (position == 0){
                return new ListaCarroFragment();
            }else{
                return new Fragment();
            }
        }

        /*
        Retorna o número de views disponiveis.
         */
        @Override
        public int getCount() {
            return 2;
        }

        /*
        Método que é chamado pela ViewPager para descrever títulos para as páginas.
         */
        @Override
        public CharSequence getPageTitle(int position) {

            if (position == 0){
                return getBaseContext().getString(R.string.title_listaDeCarros);
            }else{
                return getBaseContext().getString(R.string.title_meusFavoritos);
            }

        }
    }
}
