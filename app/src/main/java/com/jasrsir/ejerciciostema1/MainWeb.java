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
    }

    //Sobreescribimos el OnClick para el unico boton
    public void getOnClick(View v) {{
            String web = textoweb.getText().toString();
            //Si está mal formado la url se modifica basicamente
            if (!comprobarHttp(web)) {
                web = webCorrecta.concat(web);
            }
            //Creamos el bundle, le introducimos la url y la mandamos a la otra actí¡ivity
            Bundle bundle = new Bundle();
            bundle.putString("url",web);
            Intent intent = new Intent(MainWeb.this, VisorWeb.class);
            intent.putExtras(bundle);

            startActivity(intent);
        }

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
        return correcto;
    }
}