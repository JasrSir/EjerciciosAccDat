package com.jasrsir.ejerciciostema1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

public class MainConversorMoneda extends AppCompatActivity {

    //Creacion de variables necesarias
    private EditText dolares;
    private EditText euros;
    private RadioButton dolarEuro;
    private RadioButton euroDolar;
    private Button convertir;
    private Conversiones conversion;
    private EditText valorCambio;
    private Toast notificacion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_conversor_moneda);
        //inicializamos las variables con sus componentes correspondientes.
        dolares = (EditText) findViewById(R.id.txtDolar);
        euros = (EditText) findViewById(R.id.txtEuro);
        dolarEuro = (RadioButton) findViewById(R.id.rbtnDoltoEur);
        euroDolar = (RadioButton) findViewById(R.id.rbtnEuroDolar);
        convertir = (Button) findViewById(R.id.button);

        conversion = new Conversiones();
    }

    //Método OnClick para botones
    public void getOnClick(View v) {

        try {
            valorCambio = (EditText) findViewById(R.id.edtCambio);
            if (v == convertir){
                if (dolarEuro.isChecked()){
                    String valor = dolares.getText().toString();
                    if (valor.isEmpty() || valor == ".")
                        valor = "0";
                    euros.setText(conversion.convertirAEuros(valor));
                }
                else {
                    String valor = euros.getText().toString();
                    if (valor.isEmpty() || valor == ".")
                        valor = "0";
                    dolares.setText(conversion.convertirADolares(valor));
                }
            }
        } catch (NumberFormatException exception){
            notificacion = Toast.makeText(this, "Error al convertir el número", Toast.LENGTH_LONG);
            notificacion.show();

        }

    }

    /**
     * Clase destinada a las conversiones
     * @author Juan Antonio Suarez Rosa.
     */
    public class Conversiones {

        //Constructor vacío
        public Conversiones(){
        }
        /**
         * Método que convierte una cantidad de euros a dolares
         * @param cantidad cantidad introducida en String
         * @return la cantidad convertida en string
         */
        public String convertirADolares(String cantidad) {
            double valor = Double.parseDouble(cantidad) * Double.parseDouble(valorCambio.getText().toString());
            return String.format("%.2f", valor);
        }

        /**
         * Método que convierte una cantidad de dólares a euros
         * @param cantidad cantidad introducida en String
         * @return la cantidad convertida en string
         */
        public String convertirAEuros(String cantidad) {
            double valor = Double.parseDouble(cantidad) * Double.parseDouble(valorCambio.getText().toString());
            return String.format("%.2f", valor);
        }
    }
}
