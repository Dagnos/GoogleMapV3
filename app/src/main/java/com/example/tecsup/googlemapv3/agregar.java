package com.example.tecsup.googlemapv3;


import android.app.DownloadManager;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.AuthFailureError;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.HashMap;
import java.util.Map;

public class agregar extends AppCompatActivity {

    String ip="192.168.8.102";
    EditText txtNombre, txtCategoria,txtContenido;
    Button btnAgregar,btnRetroceder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar);

        txtNombre = findViewById(R.id.txtNombre);
        txtCategoria = findViewById(R.id.txtCategoria);
        txtContenido = findViewById(R.id.txtContenido);
        btnAgregar = findViewById(R.id.btnAgregar);
        btnRetroceder = findViewById(R.id.btnInicio);
    }

    public void Menu(View view){
        Intent volverMenu = new Intent(this, MainActivity.class);
        startActivity(volverMenu);
        finish();
    }
    public void mostrarAlerta(String titulo, String mensaje){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(titulo);
        builder.setMessage(mensaje);
        builder.setPositiveButton("Aceptar", null);
        AlertDialog alerta = builder.create();
        alerta.show();
    }
    public void agregarUsuario(View v){
        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {

                String nombre = txtNombre.getText().toString().trim();
                String categoria = txtCategoria.getText().toString().trim();
                String contenido = txtContenido.getText().toString().trim();
                RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
                String url ="http://"+ip+":8080/CodigoLey/rest/publicaciones/agregar?"
                        + "nombre=" + nombre
                        + "&categoria=" + categoria
                        + "&contenido=" + contenido;
                JsonArrayRequest stringRequest = new JsonArrayRequest(DownloadManager.Request.Method.PUT,url,null,
                        new Response.Listener<JSONArray>() {
                            @Override
                            public void onResponse(JSONArray response) {
                                try {
                                    String valor = response.getJSONObject(0).getString("status");
                                    if(valor.equals("true")){
                                        mostrarAlerta("Estado",
                                                "Se agrego registro correctamente");
                                    }else{
                                        mostrarAlerta("Estado",
                                                "Se produjo un error al intentar guardar la informacion");
                                    }
                                } catch (JSONException e) {
                                    mostrarAlerta("Error",
                                            "Error al realizar realizar la consulta");
                                }
                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        mostrarAlerta("Error de conexion",
                                "Compuebe que el servicio este activo");
                    }
                })
                {    /**
                 * Passing some request headers
                 */
                @Override
                public Map<String, String> getHeaders() throws AuthFailureError {
                    HashMap<String, String> headers = new HashMap<String, String>();
                    headers.put("Content-Type", "application/json; charset=utf-8");
                    return headers;
                }
                };
                queue.add(stringRequest);
            };
        });
    }
}
