package com.jasrsir.ejerciciostema1;

import android.os.CountDownTimer;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;
import android.widget.TextView;


import static com.jasrsir.ejerciciostema1.R.id.btn1mas;
import static com.jasrsir.ejerciciostema1.R.id.btn1menos;
import static com.jasrsir.ejerciciostema1.R.id.btnStart;
import static com.jasrsir.ejerciciostema1.R.id.swhPatras;

public class MainContaCoffee extends AppCompatActivity implements View.OnClickListener {

    private Contador contador;
    private Button minMas;
    private Button minMenos;
    private Button start;
    private int numCafe;
    private int minutos;
    private int segundos;
    private TextView tiempo;
    private TextView cafeses;
    private Switch haciatras;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_conta_coffee);
        inicializar();
    }

    //método que inicializa los componentes
    private void inicializar() {
        start = (Button) findViewById(btnStart);
        minMas = (Button) findViewById(R.id.btn1mas);
        minMenos = (Button) findViewById(R.id.btn1menos);
        tiempo = (TextView) findViewById(R.id.txvTiempoRestante);
        cafeses = (TextView) findViewById(R.id.txvNumCafe);
        haciatras = (Switch) findViewById(R.id.swhPatras);
        start.setOnClickListener(this);
        minMenos.setOnClickListener(this);
        minMas.setOnClickListener(this);
        numCafe = 0;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case btnStart:
                if (minutos != 00) {
                    contador = new Contador(minutos * 60000, 1000);
                    contador.start();
                    minMas.setEnabled(false);
                    minMenos.setEnabled(false);
                    haciatras.setEnabled(false);
                }
                break;
            case btn1mas:
                if (minutos == 59) {
                    AlertDialog.Builder popup = new AlertDialog.Builder(this);
                    popup.setTitle("Aviso por Exceso de Tiempo");
                    popup.setMessage("Se te va a enfriar el café con el rato que te vas a tirar");
                    popup.setPositiveButton("bueno...", null);
                    popup.show();
                } else {
                    minutos++;
                    actualizar();
                }

                break;
            case btn1menos:
                if (minutos == 00) {
                    AlertDialog.Builder popup=new AlertDialog.Builder(this);
                    popup.setTitle("Falta de tiempo");
                    popup.setMessage("No tienes tiempo, no vas a poder tomarte un café");
                    popup.setPositiveButton("Es cierto...", null);
                    popup.show();
                } else {
                    minutos--;
                    actualizar();
                }
                break;
        }
    }

    private void actualizar() {
        tiempo.setText(String.format("%02d", minutos) + ":" + String.format("%02d", segundos));
        cafeses.setText(String.format("%02d", numCafe));
    }

    /**
     * Clase que implementa CountDOwnTimer para simular un temporizador
     */
    private class Contador extends CountDownTimer {


        //Constructor por defecto
        public Contador(long tiempoTotalMilis, long intervaloMili) {
            super(tiempoTotalMilis, intervaloMili);
            if (!haciatras.isChecked())
                segundos = 59;//TODO MIRAR AQUI
        }

        @Override
        public void onTick(long miliSegFinal) {
            if (haciatras.isChecked()) {
                if (segundos == 0) {
                    minutos--;
                    segundos = 59;
                } else
                    segundos--;
            } else {
                if (segundos == 59) {
                    minutos++;
                    segundos = 0;
                } else
                    segundos++;
            }
            actualizar();
        }

        @Override
        public void onFinish() {
            AlertDialog.Builder popup=new AlertDialog.Builder(MainContaCoffee.this);
            popup.setTitle("Fin del Tiempo");
            popup.setMessage("Ala, ya te has puesto hasta arriba de café, Vayase a currar no máaas!");
            popup.setPositiveButton("Sí, ya me he chutado cafeína :)", null);
            numCafe += 1;
            segundos = 0;
            actualizar();
            popup.show();
            minMas.setEnabled(true);
            minMenos.setEnabled(true);

        }
    }
}
