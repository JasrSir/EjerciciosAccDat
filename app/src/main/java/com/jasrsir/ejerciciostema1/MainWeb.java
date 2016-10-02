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



        botonWeb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (comprobarHttp(textoweb.getText().toString())) {
                    Uri uri = Uri.parse(webCorrecta + textoweb.getText().toString());
                    Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                    startActivity(intent);
                }

                WebView webview = new WebView(MainWeb.this);
                setContentView(webview);



            }
        });
    }

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
