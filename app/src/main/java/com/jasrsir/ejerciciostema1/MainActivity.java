package com.jasrsir.ejerciciostema1;

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
    private Button boton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    @Override
    public void onClick(View v) {
        //Asignamos el id de la vista(boton) pulsada al objetoboton
        boton = (Button) findViewById(v.getId());
        //Hacemos un switch para diferenciar el botón pulsado
        switch (v.getId()){
            case R.id.btnEjer1:

        }
        boton = null;

    }
}
