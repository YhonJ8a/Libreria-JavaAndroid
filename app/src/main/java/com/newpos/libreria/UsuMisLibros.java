package com.newpos.libreria;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.newpos.libreria.Entidades.Libros;
import com.newpos.libreria.Entidades.SharedPreference;
import com.newpos.libreria.adaptadores.ListaLibrosPresAdapter;
import com.newpos.libreria.db.DbConeccion;

import java.util.ArrayList;


public class UsuMisLibros extends AppCompatActivity {


    ArrayList<Libros> listaLibros;
    TextView nombre , text, TextUsuAdmin;
    RecyclerView listaLibrosPrestados;
    DbConeccion dbConeccion;
    int coduser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usu_mis_libros);

        nombre = findViewById(R.id.nomUsuario);
        text = findViewById(R.id.librosDisTitulo);
        listaLibrosPrestados = findViewById(R.id.listaLibrosP);
        listaLibrosPrestados.setLayoutManager(new LinearLayoutManager(this));
        TextUsuAdmin = findViewById(R.id.testUsuario);
        TextUsuAdmin.setText("Usuario");

        dbConeccion = new DbConeccion(this);

        nombre.setText(dbConeccion.nombreUsuario());
        text.setText("Mis Libros Prestados");
        coduser = dbConeccion.codUsuario();


        ListaLibrosPresAdapter adapter = new ListaLibrosPresAdapter(dbConeccion.mostrarLibrosPrestados(coduser));
        listaLibrosPrestados.setAdapter(adapter);
    }

    private Libros buscar(String busqueda){
        dbConeccion = new DbConeccion(this);
        listaLibros = dbConeccion.mostrarLibrosPrestados(coduser);

        return null;
    }

}