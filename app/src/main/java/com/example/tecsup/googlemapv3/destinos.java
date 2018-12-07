package com.example.tecsup.googlemapv3;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class destinos extends AppCompatActivity {

    String destino="";
    String latitud="";
    String longitud="";
    String info="";
    TextView lblDestino, lblCoordenadas, lblInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_destinos);

        lblDestino = findViewById(R.id.lblDestino);
        lblCoordenadas = findViewById(R.id.lblCoordenadas);
        lblInfo = findViewById(R.id.lblInfo);

        Bundle recibidos = this.getIntent().getExtras();
        if(recibidos !=null) {
            destino = getIntent().getExtras().getString("destino");
            latitud = getIntent().getExtras().getString("latitud");
            longitud = getIntent().getExtras().getString("longitud");
            info = getIntent().getExtras().getString("info");
        }
        lblDestino.setText(destino);
        lblCoordenadas.setText(latitud + " , " + longitud);
        lblInfo.setText(info);

    }

    public void volverLista(View v){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

}
