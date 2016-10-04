package com.jasrsir.ejerciciostema1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * Ejercicios Tema 1 de Acceso a Datos
 * @author Juan Antonio Suárez Rosa
 * @version 1.0
 */

//todo Activity Principal de los ejercicios.
public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    //Creamos el objeto botón para guardar el boton pulsado
    private Button ej1;
    private Button ej2;
    private Button ej3;
    private Button ej4;
    private Button ej5;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        inicializar();
    }

    //Sobrescribimos el evento onclick
    @Override
    public void onClick(View v) {
        //creamos el intent para a siguiente actividad.
        Intent intent;

        //Hacemos un switch para diferenciar el botón pulsado e iniciar las activity
        switch (v.getId()){
            case R.id.btnEjer1:
                intent = new Intent(MainActivity.this, MainConversorMoneda.class);
                startActivity(intent);
                break;
            case R.id.btnEjer2:
                intent = new Intent(MainActivity.this, MainConversorMedida.class);
                startActivity(intent);
                break;
            case R.id.btnEjer3:
                intent = new Intent(MainActivity.this, MainWeb.class);
                startActivity(intent);
                break;
            case R.id.btnEjer4:
                intent = new Intent(MainActivity.this, MainContaCoffee.class);
                startActivity(intent);
                break;
            case R.id.btnEjer5:
                break;
        }
    }

    //Método que inicializa las variables y las asigna
    private void inicializar(){
        ej1 = (Button) findViewById(R.id.btnEjer1);
        ej2 = (Button) findViewById(R.id.btnEjer2);
        ej3 = (Button) findViewById(R.id.btnEjer3);
        ej4 = (Button) findViewById(R.id.btnEjer4);
        ej5 = (Button) findViewById(R.id.btnEjer5);
        ej1.setOnClickListener(this);
        ej2.setOnClickListener(this);
        ej3.setOnClickListener(this);
        ej4.setOnClickListener(this);
        ej5.setOnClickListener(this);
    }
}
