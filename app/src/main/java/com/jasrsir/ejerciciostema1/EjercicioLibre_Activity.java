package com.jasrsir.ejerciciostema1;

import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

public class EjercicioLibre_Activity extends AppCompatActivity {

    //region Variables
    private int puntosJUno;
    private int puntosJbanca;
    private int[] baraja;
    private Jugador computer;
    private Jugador jugad1;
    private ImageView comp1;
    private ImageView comp2;
    private ImageView comp3;
    private ImageView comp4;
    private ImageView jug1;
    private ImageView jug2;
    private ImageView jug3;
    private ImageView jug4;
    private Button start;
    private Button otra;
    private Button plantar;
    private TextView puntosJ;
    private TextView puntosC;
    private Random rnd;
    private double puntosCPartida;
    private double puntosJpartida;
    private int turno;

    //endregion

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ejercicio_libre);
        inicializar();
        plantar.setEnabled(false);
        otra.setEnabled(false);

    }

    public void barajaOnClick(View v) {
        switch (v.getId()) {
            case R.id.btnStart:
                start();
                plantar.setEnabled(true);
                otra.setEnabled(true);
                start.setEnabled(false);
                echarInicial();
                break;

            case R.id.btnPlantar:
                plantar.setEnabled(false);
                otra.setEnabled(false);
                turnoPc();
                start.setEnabled(true);
                turno++;
                break;
            case R.id.btnOtra:
                echarCarta();
                turnoPc();
                turno++;
                break;
        }
    }

    private void turnoPc() {
        if (puntosCPartida < 5) {
            echarCartaC();

            if (puntosCPartida > 7.5) {

                AlertDialog.Builder popup = new AlertDialog.Builder(EjercicioLibre_Activity.this);
                popup.setTitle("HAS GANADO");
                popup.setMessage("Has ganado, tu contrincante se ha pasado");
                popup.setPositiveButton("Yo sabia que era el mejor!", null);
                popup.show();
                plantar.setEnabled(false);
                otra.setEnabled(false);
                start.setEnabled(true);
            } else if (puntosCPartida > puntosJpartida) {
                AlertDialog.Builder popup = new AlertDialog.Builder(EjercicioLibre_Activity.this);
                popup.setTitle("Ha ganado el adversario");
                popup.setMessage("Has perdido, tu contrincante tiene más puntos");
                popup.setPositiveButton("Yo sabia que era el peor!", null);
                popup.show();
                plantar.setEnabled(false);
                otra.setEnabled(false);
                start.setEnabled(true);
            }else if (puntosCPartida < puntosJpartida) {
                AlertDialog.Builder popup = new AlertDialog.Builder(EjercicioLibre_Activity.this);
                popup.setTitle("Has ganado");
                popup.setMessage("tienes más puntos que tu adversario");
                popup.setPositiveButton("Yo sabia que era el mejor!", null);
                popup.show();
                plantar.setEnabled(false);
                otra.setEnabled(false);
                start.setEnabled(true);
            }

        }
    }

    private void echarCartaC() {
        int cartaC = rnd.nextInt(baraja.length);
        if (turno == 1)
            comp2.setImageResource(baraja[cartaC]);
        if (turno == 2)
            comp3.setImageResource(baraja[cartaC]);
        if (turno == 3) {
            comp4.setImageResource(baraja[cartaC]);
        }
        puntosCPartida += sacarValor(cartaC);




    }

    //metodo que echa una carta al jugador
    private void echarCarta() {
        int cartaJ = rnd.nextInt(baraja.length);
        if (turno == 1)
            jug2.setImageResource(baraja[cartaJ]);
        if (turno == 2)
            jug3.setImageResource(baraja[cartaJ]);
        if (turno == 3)
            jug4.setImageResource(baraja[cartaJ]);

        puntosJpartida += sacarValor(cartaJ);

        if (puntosJpartida > 7.5) {
            plantar.setEnabled(false);
            otra.setEnabled(false);
            AlertDialog.Builder popup = new AlertDialog.Builder(EjercicioLibre_Activity.this);
            popup.setTitle("Te has pasado");
            popup.setMessage("Si te sirve de consuelo.. has perdido");
            popup.setPositiveButton("bueno... la próxima será.", null);
            popup.show();
            start.setEnabled(true);
        } else if (puntosJpartida == 7.5) {
            plantar.setEnabled(false);
            otra.setEnabled(false);
            AlertDialog.Builder popup = new AlertDialog.Builder(EjercicioLibre_Activity.this);
            popup.setTitle("¡¡7 y medio!!");
            popup.setMessage("Has ganado por goleadaaa");
            popup.setPositiveButton("Perfect", null);
            popup.show();
            start.setEnabled(true);
        }
    }

    //método que simula la primera tirada
    private void echarInicial() {
        int cartaP = rnd.nextInt(baraja.length);
        comp1.setImageResource(baraja[cartaP]);
        puntosCPartida = sacarValor(cartaP);
        int cartaJ = rnd.nextInt(baraja.length);
        jug1.setImageResource(baraja[cartaJ]);
        puntosJpartida = sacarValor(cartaJ);
        turno++;
    }

    //Método que inicializa las variables
    private void inicializar() {
        comp1 = (ImageView) findViewById(R.id.imvC1);
        comp2 = (ImageView) findViewById(R.id.imvC2);
        comp3 = (ImageView) findViewById(R.id.imvC3);
        comp4 = (ImageView) findViewById(R.id.imvC4);
        jug1 = (ImageView) findViewById(R.id.imvJ1);
        jug2 = (ImageView) findViewById(R.id.imvJ2);
        jug3 = (ImageView) findViewById(R.id.imvJ3);
        jug4 = (ImageView) findViewById(R.id.imvJ4);
        start = (Button) findViewById(R.id.btnStart);
        otra = (Button) findViewById(R.id.btnOtra);
        plantar = (Button) findViewById(R.id.btnPlantar);
        puntosC = (TextView) findViewById(R.id.txvPunCom);
        puntosJ = (TextView) findViewById(R.id.txvPunJug);


    }



    //Metodo que inicia la baraja, jugadores y demás variables
    private void start() {
        rnd = new Random();
        baraja = new int[] {R.drawable.b1, R.drawable.b2, R.drawable.b3, R.drawable.b4, R.drawable.b5, R.drawable.b6,
                R.drawable.b7, R.drawable.b10, R.drawable.b11, R.drawable.b12,
                R.drawable.c1, R.drawable.c2, R.drawable.c3, R.drawable.c4, R.drawable.c5, R.drawable.c6,
                R.drawable.c7, R.drawable.c10, R.drawable.c11, R.drawable.c12,
                R.drawable.o1, R.drawable.o2, R.drawable.o3, R.drawable.o4, R.drawable.o5, R.drawable.o6,
                R.drawable.o7, R.drawable.o10, R.drawable.o11, R.drawable.o12,
                R.drawable.e1, R.drawable.e2, R.drawable.e3, R.drawable.e4, R.drawable.e5, R.drawable.e6,
                R.drawable.e7, R.drawable.e10, R.drawable.e11, R.drawable.e12};
        if (jugad1 == null && computer == null) {
            jugad1 = new Jugador();
            computer = new Jugador();
        }
        comp1.setImageResource(R.drawable.cardback);
        comp2.setImageResource(R.drawable.cardback);
        comp3.setImageResource(R.drawable.cardback);
        comp4.setImageResource(R.drawable.cardback);
        jug1.setImageResource(R.drawable.cardback);
        jug2.setImageResource(R.drawable.cardback);
        jug3.setImageResource(R.drawable.cardback);
        jug4.setImageResource(R.drawable.cardback);
        puntosCPartida = 0;
        puntosJpartida = 0;
        turno = 0;
    }

    //Metodo que reinicia la partida
    private void fin() {
        baraja = null;
        start();


    }

    //Clase Jugador para los 2 jugadores
    private class Jugador {
        protected int ganadas;

        public Jugador() {
            ganadas = 0;
        }

    }

    private double sacarValor(int carta){

        double valor = 0;

        if (carta == baraja[0] || carta == baraja[10] || carta == baraja[20] || carta == baraja[30])
            valor = 1;
        else if (carta == baraja[1] || carta == baraja[11] || carta == baraja[21] || carta == baraja[31])
            valor = 2;
        else if (carta == baraja[2] || carta == baraja[12] || carta == baraja[22] || carta == baraja[32])
            valor = 3;
        else if (carta == baraja[3] || carta == baraja[13] || carta == baraja[23] || carta == baraja[33])
            valor = 4;
        else if (carta == baraja[4] || carta == baraja[14] || carta == baraja[24] || carta == baraja[34])
            valor = 5;
        else if (carta == baraja[5] || carta == baraja[15] || carta == baraja[25] || carta == baraja[35])
            valor = 6;
        else if (carta == baraja[6] || carta == baraja[16] || carta == baraja[26] || carta == baraja[36])
            valor = 7;
        else if (carta == baraja[7] || carta == baraja[17] || carta == baraja[27] || carta == baraja[37])
            valor = 0.5;
        else if (carta == baraja[8] || carta == baraja[18] || carta == baraja[28] || carta == baraja[38])
            valor = 0.5;
        else if (carta == baraja[9] || carta == baraja[19] || carta == baraja[29] || carta == baraja[39])
            valor = 0.5;

        return valor;
    }
}
