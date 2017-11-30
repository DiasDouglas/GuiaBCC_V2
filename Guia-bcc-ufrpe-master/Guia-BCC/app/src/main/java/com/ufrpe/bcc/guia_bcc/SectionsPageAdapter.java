package com.ufrpe.bcc.guia_bcc;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ismael on 06/11/2017.
 */

public class SectionsPageAdapter extends FragmentPagerAdapter {

    private final List<Fragment> listaDeFragmentos = new ArrayList<Fragment>();
    private final List<String> titulosFragmentos= new ArrayList<String>();

    public SectionsPageAdapter(FragmentManager fm) {
        super(fm);
    }

    public void adicionarFragmento(Fragment novoFragmento,String tituloFramento){
           if(novoFragmento != null && tituloFramento != null){
               this.listaDeFragmentos.add(novoFragmento);
               this.titulosFragmentos.add(tituloFramento);
           }
    }

    @Override
    public CharSequence getPageTitle(int position){
        return this.titulosFragmentos.get(position);
    }

    @Override
    public Fragment getItem(int position) {
        return listaDeFragmentos.get(position);
    }

    @Override
    public int getCount() {
        return listaDeFragmentos.size();
    }
}
