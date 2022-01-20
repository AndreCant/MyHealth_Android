package it.mwt.myhealth;

import android.app.Application;

public class MyHealth extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        System.out.println("START APPLICATION");
    }
}
