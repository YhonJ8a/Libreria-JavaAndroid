package com.newpos.libreria;

import static com.newpos.libreria.R.id.imageView3;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.newpos.libreria.Entidades.Libros;
import com.newpos.libreria.db.DbConeccion;

public class AdminActualizarLibro extends AppCompatActivity {
    DbConeccion dbConeccion;

    TextView nombreAdmin, text ,TextAdmin;
    ImageButton btnMas, btnAtras;
    ImageView perfilImagen;
    SearchView buscador;
    EditText nombreLibro, Autor, cantidad, url, imagen, descripcion;
    String nombreL, autorL, urlL, imagenL, descripcionL;
    int cantidadL;
    Libros libro;
    Button actualizar;
    String urlImagen = "https://w7.pngwing.com/pngs/550/132/png-transparent-glenn-quagmire-stewie-griffin-peter-griffin-brian-griffin-meg-griffin-others-child-face-hand.png";
    int id = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_actualizar_libro);
        getSupportActionBar().hide();

        nombreAdmin = findViewById(R.id.nomUsuario);
        text = findViewById(R.id.librosDisTitulo);
        text.setText("Actualizar Libro");
        TextAdmin = findViewById(R.id.testUsuario);
        TextAdmin.setText("Administrador");

        libro = new Libros();
        dbConeccion = new DbConeccion(this);
        nombreLibro = findViewById(R.id.AALnombre);
        Autor = findViewById(R.id.AAlautor);
        cantidad = findViewById(R.id.AAlcantidad);
        url = findViewById(R.id.AALurl);
        imagen = findViewById(R.id.AAlimagen);
        descripcion = findViewById(R.id.AAldescripcion);
        actualizar = findViewById(R.id.BtnActualizar);


        buscador =findViewById(R.id.buscador);
        buscador.setVisibility(View.INVISIBLE);
        btnAtras = findViewById(R.id.imageButtonAtras);
        btnMas = findViewById(R.id.imageButtonMas);
        btnMas.setVisibility(View.INVISIBLE);


        perfilImagen = findViewById(imageView3);
        Glide.with(this).load(urlImagen).error(R.drawable.ic_baseline_android_24).into(perfilImagen);


        if(savedInstanceState== null){
            Bundle extras =getIntent().getExtras();
            if(extras == null){
                id = Integer.parseInt(null);
            }else{
                id = extras.getInt("ID");
            }
        }else{
            id = (int) savedInstanceState.getSerializable("ID");
        }

       libro = dbConeccion.verLibro(id);
        if(!libro.getNom_libro().equals("id erroneo")){
            nombreLibro.setText(libro.getNom_libro());
            Autor.setText(libro.getAutor());
            cantidad.setText(String.valueOf(libro.getCantidad()));
            url.setText(libro.getUrl());
            imagen.setText(libro.getFoto());
            descripcion.setText(libro.getDescripcion());
        }

        actualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nombreL = nombreLibro.getText().toString();
                autorL = Autor.getText().toString();
                cantidadL = Integer.parseInt(cantidad.getText().toString());
                urlL = url.getText().toString();
                imagenL = imagen.getText().toString();
                descripcionL = descripcion.getText().toString();

                boolean respuesta = dbConeccion.editarLibro(id, nombreL, autorL, cantidadL, urlL, imagenL, descripcionL);
                if (respuesta){
                    Toast.makeText(AdminActualizarLibro.this, "CORRECTO", Toast.LENGTH_SHORT).show();
                    regresar();
                }else{
                    Toast.makeText(AdminActualizarLibro.this, "ERROR AL EDITAR", Toast.LENGTH_LONG).show();
                }
            }
        });

        btnAtras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                regresar();
            }
        });
    }

    private  void regresar(){
        Intent intent = new Intent(this, AdminLibrosDisponibles.class);
        startActivity(intent);
    }

}