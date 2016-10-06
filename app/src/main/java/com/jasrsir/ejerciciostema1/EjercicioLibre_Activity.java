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
    private int[] baraja;
    private Jugador computer;
    private Jugador jugador;
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
    private Random rndJ;
    private Random rndG;
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
                jugador.plantarse = true;
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
        if (computer.puntos < 6.5 && !computer.plantarse) {
            echarCartaC();

            if (computer.puntos > 7.5) {
                AlertDialog.Builder popup = new AlertDialog.Builder(EjercicioLibre_Activity.this);
                popup.setTitle("HAS GANADO");
                popup.setMessage("Has ganado, tu contrincante se ha pasado");
                popup.setPositiveButton("Yo sabia que era el mejor!", null);
                popup.show();
                plantar.setEnabled(false);
                otra.setEnabled(false);
                start.setEnabled(true);
                jugador.ganadas++;
            } else if (computer.puntos > jugador.puntos && jugador.plantarse) {
                AlertDialog.Builder popup = new AlertDialog.Builder(EjercicioLibre_Activity.this);
                popup.setTitle("Ha ganado el adversario");
                popup.setMessage("Has perdido, tu contrincante tiene más puntos");
                popup.setPositiveButton("Yo sabia que era el peor!", null);
                popup.show();
                plantar.setEnabled(false);
                otra.setEnabled(false);
                start.setEnabled(true);
            }else if (computer.puntos < jugador.puntos && jugador.plantarse) {
                turnoPc();
            }

        }else
            computer.plantarse = true;
    }

    //Método que saca una carta al Computer, y tirar si se ha plantado el jugador
    private void echarCartaC() {
        int cartaC = rndG.nextInt(baraja.length);
        if (turno == 1)
            comp2.setImageResource(baraja[cartaC]);
        if (turno == 2)
            comp3.setImageResource(baraja[cartaC]);
        if (turno == 3) {
            comp4.setImageResource(baraja[cartaC]);
        }
        computer.puntos += sacarValor(cartaC);

        if (jugador.plantarse)
            turnoPc();
    }

    //metodo que echa una carta al jugador
    private void echarCarta() {
        int cartaJ = rndJ.nextInt(baraja.length);
        if (turno == 1)
            jug2.setImageResource(baraja[cartaJ]);
        if (turno == 2)
            jug3.setImageResource(baraja[cartaJ]);
        if (turno == 3)
            jug4.setImageResource(baraja[cartaJ]);

        jugador.puntos += sacarValor(cartaJ);

        if (jugador.puntos > 7.5) {
            AlertDialog.Builder popup = new AlertDialog.Builder(EjercicioLibre_Activity.this);
            popup.setTitle("Te has pasado");
            popup.setMessage("Si te sirve de consuelo.. has perdido");
            popup.setPositiveButton("bueno... la próxima será.", null);
            popup.show();
            computer.ganadas++;
            fin();
        } else if (jugador.puntos == 7.5) {
            AlertDialog.Builder popup = new AlertDialog.Builder(EjercicioLibre_Activity.this);
            popup.setTitle("¡¡7 y medio!!");
            popup.setMessage("Has ganado por goleadaaa");
            popup.setPositiveButton("Perfect", null);
            popup.show();
            jugador.ganadas++;
            fin();
        }

    }

    //método que simula la primera tirada
    private void echarInicial() {
        int cartaP = rndG.nextInt(baraja.length);
        comp1.setImageResource(baraja[cartaP]);
        computer.puntos = sacarValor(cartaP);
        int cartaJ = rndJ.nextInt(baraja.length);
        jug1.setImageResource(baraja[cartaJ]);
        jugador.puntos = sacarValor(cartaJ);
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
        rndJ = new Random();
        baraja = new int[] {R.drawable.b1, R.drawable.b2, R.drawable.b3, R.drawable.b4, R.drawable.b5, R.drawable.b6,
                R.drawable.b7, R.drawable.b10, R.drawable.b11, R.drawable.b12,
                R.drawable.c1, R.drawable.c2, R.drawable.c3, R.drawable.c4, R.drawable.c5, R.drawable.c6,
                R.drawable.c7, R.drawable.c10, R.drawable.c11, R.drawable.c12,
                R.drawable.o1, R.drawable.o2, R.drawable.o3, R.drawable.o4, R.drawable.o5, R.drawable.o6,
                R.drawable.o7, R.drawable.o10, R.drawable.o11, R.drawable.o12,
                R.drawable.e1, R.drawable.e2, R.drawable.e3, R.drawable.e4, R.drawable.e5, R.drawable.e6,
                R.drawable.e7, R.drawable.e10, R.drawable.e11, R.drawable.e12};
        if (jugador == null && computer == null) {
            jugador = new Jugador();
            computer = new Jugador();
        } else {
            computer.puntos = 0;
            jugador.puntos = 0;
        }

        comp1.setImageResource(R.drawable.cardback);
        comp2.setImageResource(R.drawable.cardback);
        comp3.setImageResource(R.drawable.cardback);
        comp4.setImageResource(R.drawable.cardback);
        jug1.setImageResource(R.drawable.cardback);
        jug2.setImageResource(R.drawable.cardback);
        jug3.setImageResource(R.drawable.cardback);
        jug4.setImageResource(R.drawable.cardback);
        rndG = new Random();
        turno = 0;
    }

    //Metodo que reinicia la partida
    private void fin() {
        puntosJ.setText(jugador.ganadas);
        puntosC.setText(computer.ganadas);
        plantar.setEnabled(false);
        otra.setEnabled(false);
        start.setEnabled(true);
    }

    //Clase Jugador para los 2 jugadores con un comportamiento
    private class Jugador {
        protected int ganadas;
        protected double puntos;
        protected boolean plantarse;
        public Jugador() {
            ganadas = 0;
            puntos = 0;
            plantarse = false;
        }

    }
    //Método que saca el valor obtenido de la carta.
    private double sacarValor(int carta){

        double valor = 0;

        if (carta == 0 || carta == 10 || carta == 20 || carta == 30)
            valor = 1;
        else if (carta == 1 || carta == 11 || carta == 21 || carta == 31)
            valor = 2;
        else if (carta == 2 || carta == 12 || carta == 22 || carta == 32)
            valor = 3;
        else if (carta == 3 || carta == 13 || carta == 23 || carta == 33)
            valor = 4;
        else if (carta == 4 || carta == 14 || carta == 24 || carta == 34)
            valor = 5;
        else if (carta == 5 || carta == 15 || carta == 25 || carta == 35)
            valor = 6;
        else if (carta == 6 || carta == 16 || carta == 26 || carta == 36)
            valor = 7;
        else
            valor = 0.5;

        return valor;
    }
}
