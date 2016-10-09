package com.jasrsir.ejerciciostema1;

import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class EjercicioLibre_Activity extends AppCompatActivity {

    //region Variables
    private int[] baraja;
    private Jugador computer;
    private Jugador jugador;
    private ImageView comp1;
    private ImageView comp2;
    private ImageView comp3;
    private ImageView jug1;
    private ImageView jug2;
    private ImageView jug3;
    private Button start;
    private Button otra;
    private Button plantar;
    private TextView puntosJ;
    private TextView puntosC;
    private Random rndJ;
    private Random rndG;
    private int turno;
    private Thread parar;
    private long sleep = 100;
    //endregion

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ejercicio_libre);
        inicializar();
        plantar.setEnabled(false);
        otra.setEnabled(false);

    }

    //método Onclick para botones
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
                jugador.plantarse = true;
                turnoPc();
                start.setEnabled(true);
                comprobarGanador();
                break;
            case R.id.btnOtra:
                echarCarta();
                turnoPc();
                comprobarGanador();
                turno++;
                break;
        }
    }

    //Método que inicializa las variables
    private void inicializar() {
        comp1 = (ImageView) findViewById(R.id.imvC1);
        comp2 = (ImageView) findViewById(R.id.imvC2);
        comp3 = (ImageView) findViewById(R.id.imvC3);
        jug1 = (ImageView) findViewById(R.id.imvJ1);
        jug2 = (ImageView) findViewById(R.id.imvJ2);
        jug3 = (ImageView) findViewById(R.id.imvJ3);
        start = (Button) findViewById(R.id.btnStart);
        otra = (Button) findViewById(R.id.btnOtra);
        plantar = (Button) findViewById(R.id.btnPlantar);
        puntosC = (TextView) findViewById(R.id.txvPunCom);
        puntosJ = (TextView) findViewById(R.id.txvPunJug);


    }

    //Metodo que inicia la baraja, jugadores y demás variables
    private void start() {
        rndJ = new Random();
        parar = new Thread();
        baraja = null;
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
            jugador.plantarse = false;
            computer.plantarse = false;
            puntosC.setText(String.valueOf(computer.ganadas));
            puntosJ.setText(String.valueOf(jugador.ganadas));
        }

        comp1.setImageResource(R.drawable.blaanco);
        comp2.setImageResource(R.drawable.blaanco);
        comp3.setImageResource(R.drawable.blaanco);
        jug1.setImageResource(R.drawable.blaanco);
        jug2.setImageResource(R.drawable.blaanco);
        jug3.setImageResource(R.drawable.blaanco);
        try {
            parar.sleep(sleep);
        } catch (InterruptedException e) {
        }
        rndG = new Random();
        turno = 0;
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

    //Metodo que reinicia la partida
    private void fin() {
        puntosJ.setText(String.valueOf(jugador.ganadas));
        puntosC.setText(String.valueOf(computer.ganadas));
        plantar.setEnabled(false);
        otra.setEnabled(false);
        start.setEnabled(true);
    }

    //Método que saca una carta al Computer, y tirar si se ha plantado el jugador
    private void echarCartaC() {
        int cartaC = rndG.nextInt(baraja.length);
        if (turno == 1)
            comp2.setImageResource(baraja[cartaC]);
        if (turno == 2)
            comp3.setImageResource(baraja[cartaC]);

        computer.puntos += sacarValor(cartaC);
        if (jugador.plantarse && turno < 2 && computer.puntos < 7.5) {
            turno++;
            turnoPc();
        }
    }

    //metodo que echa una carta al jugador
    private void echarCarta() {
        int cartaJ = rndJ.nextInt(baraja.length);
        if (turno == 1)
            jug2.setImageResource(baraja[cartaJ]);
        if (turno == 2) {
            jug3.setImageResource(baraja[cartaJ]);
            otra.setEnabled(false);
            plantar.setEnabled(false);
        }

        jugador.puntos += sacarValor(cartaJ);

    }

    //metodo que compueba el ganador de la partida
    private void comprobarGanador() {
        if (computer.puntos > 7.5 && jugador.puntos > 7.5) {
            AlertDialog.Builder popup = new AlertDialog.Builder(EjercicioLibre_Activity.this);
            popup.setTitle("¡EMPATE");
            popup.setMessage("Os habéis pasado");
            popup.setPositiveButton("Trauma!!", null);
            popup.show();
            fin();
        } else if (jugador.puntos > 7.5) {
            AlertDialog.Builder popup = new AlertDialog.Builder(EjercicioLibre_Activity.this);
            popup.setTitle("Te has pasado");
            popup.setMessage("Si te sirve de consuelo.. has perdido");
            popup.setPositiveButton("bueno... la próxima será.", null);
            popup.show();
            computer.ganadas++;
            fin();
        } else if (computer.puntos > 7.5) {
            AlertDialog.Builder popup = new AlertDialog.Builder(EjercicioLibre_Activity.this);
            popup.setTitle("HAS GANADO");
            popup.setMessage("Has ganado, tu contrincante se ha pasado");
            popup.setPositiveButton("Yo sabia que era el mejor!", null);
            popup.show();
            jugador.ganadas++;
            plantar.setEnabled(false);
            otra.setEnabled(false);
            start.setEnabled(true);
            fin();
        } else if (jugador.puntos == 7.5) {
            AlertDialog.Builder popup = new AlertDialog.Builder(EjercicioLibre_Activity.this);
            popup.setTitle("¡¡7 y medio!!");
            popup.setMessage("Has ganado por goleadaaa");
            popup.setPositiveButton("Perfect", null);
            popup.show();
            jugador.ganadas++;
            fin();
        } else if (computer.puntos == 7.5) {
            AlertDialog.Builder popup = new AlertDialog.Builder(EjercicioLibre_Activity.this);
            popup.setTitle("¡¡7 y medio para el computer!!");
            popup.setMessage("Has perdido por goleadaaa");
            popup.setPositiveButton("Trauma!!", null);
            popup.show();
            computer.ganadas++;
            fin();
        }else if (jugador.puntos < computer.puntos && turno == 2) {
            AlertDialog.Builder popup = new AlertDialog.Builder(EjercicioLibre_Activity.this);
            popup.setTitle("Perdedor");
            popup.setMessage("has perdido");
            popup.setPositiveButton("Perfect :')", null);
            popup.show();
            computer.ganadas++;
            fin();
        }else if (jugador.puntos > computer.puntos  && turno == 2) {
            AlertDialog.Builder popup = new AlertDialog.Builder(EjercicioLibre_Activity.this);
            popup.setTitle("Ganador");
            popup.setMessage("+1 de destreza");
            popup.setPositiveButton("Perfect ^^", null);
            popup.show();
            jugador.ganadas++;
            fin();
        }else if (jugador.puntos > computer.puntos  && jugador.plantarse && computer.plantarse) {
            AlertDialog.Builder popup = new AlertDialog.Builder(EjercicioLibre_Activity.this);
            popup.setTitle("el movil se ha plantado");
            popup.setMessage("+1 de destreza");
            popup.setPositiveButton("Perfect ^^", null);
            popup.show();
            jugador.ganadas++;
            fin();
        }else if (jugador.puntos < computer.puntos && jugador.plantarse && computer.plantarse) {
            AlertDialog.Builder popup = new AlertDialog.Builder(EjercicioLibre_Activity.this);
            popup.setTitle("El móvil te ha ganado");
            popup.setMessage("os habéis plantado y has perdido");
            popup.setPositiveButton("Perfect :')", null);
            popup.show();
            computer.ganadas++;
            fin();
        } else if (computer.puntos == jugador.puntos  && (turno == 2 || (jugador.plantarse && computer.plantarse))) {
            AlertDialog.Builder popup = new AlertDialog.Builder(EjercicioLibre_Activity.this);
            popup.setTitle("Empate técnico");
            popup.setMessage("Habéis Empatado");
            popup.setPositiveButton("Best IA Ever", null);
            popup.show();
            plantar.setEnabled(false);
            otra.setEnabled(false);
            start.setEnabled(true);
            jugador.ganadas++;
            computer.ganadas++;
            fin();
        }
    }

    //Realiza el turno de computer
    private void turnoPc() {
        if (jugador.plantarse && jugador.puntos < computer.puntos)
            computer.plantarse = true;
        else if (computer.puntos < 6 && !computer.plantarse)
            echarCartaC();
        else if (!computer.plantarse)
            computer.plantarse = true;

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
}
