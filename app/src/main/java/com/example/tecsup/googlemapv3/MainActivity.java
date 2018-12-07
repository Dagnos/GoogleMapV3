package com.example.tecsup.googlemapv3;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

    ListView lstDestinos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lstDestinos = findViewById(R.id.lstDestinos);
        String[] values = new String[] { "PLAZA DE ARMAS",
                "CHARACATO",
                "COLCA",
                "YURA",
                "MIRADOR SACHACA"
        };
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, android.R.id.text1,
                values);
        lstDestinos.setAdapter(adapter);
        lstDestinos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                int itemPosition     = i;
                String  itemValue    = (String) lstDestinos.getItemAtPosition(itemPosition);

                Intent intent = new Intent(MainActivity.this, MapsActivity.class);
                intent.putExtra("destino", itemValue.toLowerCase());
                startActivity(intent);
            }
        });
    }

}

