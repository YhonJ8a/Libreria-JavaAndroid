package com.newpos.libreria;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class NuevoLibroPres extends AppCompatActivity {

   TextView txtNombre , txtAutor, txtFecha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nuevo_libro_pres);
        txtNombre = findViewById(R.id.NLPnombre);
        txtAutor = findViewById(R.id.NLPautor);
        txtFecha = findViewById(R.id.NLPfecha);
    }
}