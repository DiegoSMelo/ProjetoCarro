package com.example.diegomelo.projetocarro;

import android.app.Application;

import com.squareup.otto.Bus;

/**
 * Created by Diego Melo on 18/11/2015.
 */
public class CarroApp extends Application {

    Bus mBus;

    @Override
    public void onCreate() {
        super.onCreate();

        mBus = new Bus();
    }

    public Bus getBus(){
        return this.mBus;
    }
}
