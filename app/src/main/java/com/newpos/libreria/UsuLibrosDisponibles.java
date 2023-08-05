package com.newpos.libreria;

import android.os.Bundle;
import android.widget.SearchView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.newpos.libreria.Entidades.SharedPreference;
import com.newpos.libreria.adaptadores.ListalibrosDisponiblesAdapter;
import com.newpos.libreria.db.DbConeccion;


public class UsuLibrosDisponibles extends AppCompatActivity {
    TextView nombre;
    SearchView bucador;
    TextView text;
    RecyclerView listaLibrosDisponibles ;
    SharedPreference sp;
    DbConeccion dbConeccion;
    Object segVistaAdaptador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usu_libros_disponibles);

        nombre = findViewById(R.id.nomUsuario);
        bucador = findViewById(R.id.buscador);
        text = findViewById(R.id.librosDisTitulo);

        text.setText("Libros Disponibles");

        listaLibrosDisponibles = findViewById(R.id.listaLibrosDisponibles);
        listaLibrosDisponibles.setLayoutManager(new LinearLayoutManager(this));
        sp = new SharedPreference(this);

        dbConeccion  = new DbConeccion(this);
        String nomUsuario = dbConeccion.nombreUsuario();

        nombre.setText(nomUsuario);

        segVistaAdaptador =UsuPrestarLibro.class;
        ListalibrosDisponiblesAdapter adapter = new ListalibrosDisponiblesAdapter( segVistaAdaptador ,dbConeccion.mostrarLibrosDisponibles());
    }
}