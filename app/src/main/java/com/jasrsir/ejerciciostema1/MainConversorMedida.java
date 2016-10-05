package com.jasrsir.ejerciciostema1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

public class MainConversorMedida extends AppCompatActivity {

    //Creacion de variables necesarias
    private EditText centimetros;
    private EditText pulgadas;
    private RadioButton cmsInch;
    private RadioButton inchCms;
    private Button pasarMedida;
    private MainConversorMedida.ConverMedida converMedida;
    private EditText valorMedida;
    private Toast notificacionMedida;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_conversor_medida);
        //inicializamos las variables con sus componentes correspondientes.
        centimetros = (EditText) findViewById(R.id.edtCms);
        pulgadas = (EditText) findViewById(R.id.edtInch);
        cmsInch = (RadioButton) findViewById(R.id.rdbCms);
        inchCms = (RadioButton) findViewById(R.id.rdbInch);
        pasarMedida = (Button) findViewById(R.id.btnMedida);

        converMedida = new ConverMedida();
    }



    public void getOnClick(View v) {

        try {
            if (v == pasarMedida){
                if (cmsInch.isChecked()){
                    String valor = centimetros.getText().toString();
                    if (valor.isEmpty() || valor == ".")
                        valor = "0";
                    pulgadas.setText(converMedida.convertirPulgadas(valor));
                }
                else {
                    String valor = pulgadas.getText().toString();
                    if (valor.isEmpty() || valor == ".")
                        valor = "0";
                    centimetros.setText(converMedida.convertirCentimetros(valor));
                }
            }
        } catch (NumberFormatException exception){
            notificacionMedida = Toast.makeText(this, "Error al pasar la medida", Toast.LENGTH_LONG);
            notificacionMedida.show();

        }
    }


    /**
     * Clase destinada a las conversiones de distancia
     * @author Juan Antonio Suarez Rosa.
     */

    public class ConverMedida {

        double pulgCms = 2.54;
        double cmsPulg = 0.39;
        //Constructor vacío
        public ConverMedida(){
        }
        /**
         * Método que convierte una distancia de cms a pulgadas
         * @param cantidad cantidad introducida en String
         * @return la cantidad convertida en string
         */
        public String convertirPulgadas(String cantidad) {
            double valor = Double.parseDouble(cantidad) * cmsPulg;
            return String.format("%.2f", valor);
        }

        /**
         * Método que convierte una distancia de pulgadas a cms
         * @param cantidad cantidad introducida en String
         * @return la cantidad convertida en string
         */
        public String convertirCentimetros(String cantidad) {
            double valor = Double.parseDouble(cantidad) * pulgCms;
            return String.format("%.2f", valor);
        }
    }
}
