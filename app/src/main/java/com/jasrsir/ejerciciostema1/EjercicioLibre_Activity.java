package com.jasrsir.ejerciciostema1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.Random;

public class EjercicioLibre_Activity extends AppCompatActivity {

    private int puntosJUno;
    private int puntosJbanca;
    private int[] baraja;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ejercicio_libre);
        start();
    }


    private void start() {
        Random rnd = new Random();
        baraja = new int[] {1,2,3};

    }

    private void restart() {

        baraja = null;
    }
}
