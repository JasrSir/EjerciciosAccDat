package com.jasrsir.ejerciciostema1;

import android.media.MediaPlayer;
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
import static com.jasrsir.ejerciciostema1.R.id.btnResetCofee;
import static com.jasrsir.ejerciciostema1.R.id.btnStart;
import static com.jasrsir.ejerciciostema1.R.id.swhPatras;


public class MainContaCoffee extends AppCompatActivity {

    //Variables de clase
    private Button minMas;
    private Button minMenos;
    private Button start;
    private int numCafe;
    private int minutos;
    private int segundos;
    private int minutosAtras;
    private TextView tiempo;
    private TextView cafeses;
    private Switch haciatras;
    private ContadorDown contaTempo;
    private MediaPlayer sonido;
  //  MediaPlayer mp = MediaPlayer.create(this, R.raw.a);
   // mp.start();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_conta_coffee);
        inicializar();
    }

    //método que inicializa los componentes
    private void inicializar() {
        start = (Button) findViewById(btnStart);
        minMas = (Button) findViewById(btn1mas);
        minMenos = (Button) findViewById(btn1menos);
        tiempo = (TextView) findViewById(R.id.txvTiempoRestante);
        cafeses = (TextView) findViewById(R.id.txvNumCafe);
        haciatras = (Switch) findViewById(R.id.swhPatras);
        numCafe = 0;
        minutos = 5;
        sonido = MediaPlayer.create(MainContaCoffee.this,R.raw.descanso);
        actualizar();
    }

    public void getOnClick(View v) {
        switch (v.getId()) {
            case btnStart:
                if (haciatras.isChecked()) {
                    contaTempo = new ContadorDown(minutos * 60000, 1000);
                    contaTempo.start();
                    inicio();
                } else {
                    contaTempo = new ContadorDown(minutos * 60000, 1000);
                    contaTempo.start();
                    minutosAtras = minutos;
                    minutos = 0;
                    inicio();
                }

                break;
            case btn1mas:
                if (minutos == 30) {
                    AlertDialog.Builder popup = new AlertDialog.Builder(MainContaCoffee.this);
                    popup.setTitle("Aviso por Exceso de Tiempo");
                    popup.setMessage("Se te va a enfriar el café con el rato que te vas a tirar");
                    popup.setPositiveButton("bueno...", null);
                    popup.show();
                } else {
                    minutos++;
                }
                break;
            case btn1menos:
                if (minutos == 01) {
                    AlertDialog.Builder popup = new AlertDialog.Builder(MainContaCoffee.this);
                    popup.setTitle("Falta de tiempo");
                    popup.setMessage("No tienes tiempo, no vas a poder tomarte un café");
                    popup.setPositiveButton("Es cierto...", null);
                    popup.show();
                } else
                    minutos--;
                break;
            case btnResetCofee:
                numCafe = 0;
        }
        actualizar();
    }

    //Método que actualiza los minutos y segundos y cafés
    private void actualizar() {
        tiempo.setText(String.format("%02d", minutos) + " : " + String.format("%02d", segundos));
        cafeses.setText(String.format("%02d", numCafe));
    }

    /**
     * Clase que implementa CountDOwnTimer para simular un temporizador
     * La implemento en esta activity por ahorrar más clases
     */
    private class ContadorDown extends CountDownTimer {

        //Constructor por defecto
        public ContadorDown(long tiempoTotalMilis, long intervaloMili) {
            super(tiempoTotalMilis, intervaloMili);
        }

        /**
         * Método sobreescrito para el OnTick
         * @param miliSegFinal milisegundos hasta el fin de la cuenta
         */
        @Override
        public void onTick(long miliSegFinal) {
                tickTiempo();
                actualizar();
        }

        /**
         * Método sobreescrito para el final de la cuenta atrás
         */
        @Override
        public void onFinish() {
            if (numCafe <= 9) {
                AlertDialog.Builder popup = new AlertDialog.Builder(MainContaCoffee.this);
                popup.setTitle("Fin del Tiempo");
                popup.setMessage("Ala, ya te has puesto hasta arriba de café, Vayase a currar no máaas!");
                popup.setPositiveButton("Sí, ya me he chutado cafeína :)", null);
                sonido.start();
                popup.show();
            } else
            fin();
            actualizar();

        }
    }
        //Método que cambia propiedades al iniciar el contador
        private void inicio() {
            minMas.setEnabled(false);
            minMenos.setEnabled(false);
            haciatras.setEnabled(false);
            start.setText("En proceso de encafeinarte");
            start.setEnabled(false);
        }

        //Método que cambia el tiempo restante con cada tick
        private void tickTiempo() {
            //Si el switch está activado cuenta hacia atrás, sino hacia delante
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
        }

        //Método que cambia propiedades al iniciar el contador
        private void fin() {
            numCafe += 1;
            if (numCafe == 10) {
                AlertDialog.Builder popup = new AlertDialog.Builder(MainContaCoffee.this);
                popup.setTitle("NO más cafés");
                popup.setMessage("Creo que ya llevas suficientes cafés por hoy");
                popup.setPositiveButton("Ya no tomo más, en serio...", null);
                popup.show();
            }

            segundos = 0;
            minutos = 5;
            minMas.setEnabled(true);
            minMenos.setEnabled(true);
            haciatras.setEnabled(true);
            start.setText("Empezar cuenta atrás para encafeinarse");
            start.setEnabled(true);
            contaTempo = null;
        }
    }


