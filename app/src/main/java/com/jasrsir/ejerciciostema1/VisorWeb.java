package com.jasrsir.ejerciciostema1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class VisorWeb extends AppCompatActivity {

    WebView paginaweb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visor_web);
        //Asignamos el WebView a lavariable y la asignamos como ciente de web por defecto.
        paginaweb = (WebView) findViewById(R.id.webv);
        paginaweb.setWebViewClient(new WebViewClient());
    }
}
