package com.jasrsir.ejerciciostema1;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;

public class MainWeb extends AppCompatActivity {

    //Creamos las variables de los componentes
    private Button botonWeb;
    private EditText textoweb;
    private String webCorrecta;
    //Método que crea la activity
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_web);
        //Iniciaizamos las variables
        botonWeb = (Button) findViewById(R.id.btnAccederWeb);
        textoweb = (EditText) findViewById(R.id.edtWeb);
        //Lo asignamos al click

        botonWeb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Si está mal formado la url se modifica basicamente
                if (comprobarHttp(textoweb.getText().toString())) {
                    webCorrecta.concat(textoweb.getText().toString());
                }
                //Creamos el bundle, le introducimos la url y la mandamos a la otra actí¡ivity
               // Bundle bundle = new Bundle();
                // bundle.putString("url",textoweb.getText().toString());
                //Intent intent = new Intent(MainWeb.this, VisorWeb.class);

                //startActivity(intent);
            }
        });
    }

    /**
     * Método que comprueba la url para que sea operativa.
     * @param web string del editText
     * @return true si está bien compuesta y false en otro caso
     */
    private boolean comprobarHttp(String web){
        boolean correcto = true;
        if (web.substring(0, 3) != "http") {
            webCorrecta = "http://";
            correcto = false;
        }
        else if (web.substring(0, 3) != "www."){
            webCorrecta.concat("www.");
            correcto = false;
        }
        return correcto;
    }
}
