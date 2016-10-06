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
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    //Sobrescribimos el evento onclick
    public void getOnClick(View v) {
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
                intent = new Intent(MainActivity.this, EjercicioLibre_Activity.class);
                startActivity(intent);
                break;
        }
    }
}
