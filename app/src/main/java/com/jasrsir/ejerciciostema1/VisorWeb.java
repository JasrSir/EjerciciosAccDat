package com.jasrsir.ejerciciostema1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class VisorWeb extends AppCompatActivity {

    private Intent intent;
    private WebView paginaweb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visor_web);
        //Asignamos el WebView a lavariable y la asignamos como ciente de web por defecto.
        intent = this.getIntent();
        paginaweb = (WebView) findViewById(R.id.webv);
        //Establecer el componente como el visor web
        paginaweb.setWebViewClient(new WebViewClient());
        //Cargamos la url
        paginaweb.loadUrl(intent.getStringExtra("url"));
    }
}
