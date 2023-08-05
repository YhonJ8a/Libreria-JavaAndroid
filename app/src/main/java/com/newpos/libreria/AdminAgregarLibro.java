package com.newpos.libreria;

import static com.newpos.libreria.R.id.imageView3;

import androidx.appcompat.app.AppCompatActivity;

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

import com.bumptech.glide.Glide;
import com.newpos.libreria.db.DbConeccion;

public class AdminAgregarLibro extends AppCompatActivity {

    TextView text , usuNomrbe ,TextUsuAdmin;
    SearchView buscador;
    DbConeccion dbConeccion;
    ImageButton mas , atras ;
    Button Agregar;
    EditText nombre, autor , cantidad, url, foto, descripcion;
    String nombreL, autorL , urlL, fotoL, descripcionL;
    String cantidadL;
    ImageView perfilImagen;
    String urlImagen = "https://w7.pngwing.com/pngs/550/132/png-transparent-glenn-quagmire-stewie-griffin-peter-griffin-brian-griffin-meg-griffin-others-child-face-hand.png";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_agregar_libro);
        getSupportActionBar().hide();


        text = findViewById(R.id.librosDisTitulo);
        usuNomrbe = findViewById(R.id.nomUsuario);
        buscador = findViewById(R.id.buscador);
        TextUsuAdmin = findViewById(R.id.testUsuario);
        nombre = findViewById(R.id.NLNombre);
        autor = findViewById(R.id.NLAutor);
        cantidad = findViewById(R.id.NLCantidad);
        url = findViewById(R.id.NLURL);
        foto = findViewById(R.id.NLFoto);
        descripcion = findViewById(R.id.NLDescripcion);
        Agregar = findViewById(R.id.NLBtnAgregar);

        TextUsuAdmin.setText("Administrador");
        dbConeccion = new DbConeccion(this);
        String nomUsuario = dbConeccion.nombreUsuario();
        usuNomrbe.setText(nomUsuario);
        mas = findViewById(R.id.imageButtonMas);
        atras = findViewById(R.id.imageButtonAtras);
        text.setText("Agregar Libro");
        usuNomrbe.setText(dbConeccion.nombreUsuario());
        buscador.setVisibility(View.INVISIBLE);
        mas.setVisibility(View.INVISIBLE);


        perfilImagen = findViewById(imageView3);
        Glide.with(this).load(urlImagen).error(R.drawable.ic_baseline_android_24).into(perfilImagen);


        Agregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                nombreL = nombre.getText().toString();
                autorL = autor.getText().toString();
                urlL= url.getText().toString();
                fotoL = foto.getText().toString();
                cantidadL = cantidad.getText().toString();
                descripcionL = descripcion.getText().toString();

                if (!(nombreL.equals("") || autorL.equals("") || urlL.equals("") || fotoL.equals("") || cantidadL.equals("") || descripcionL.equals("") ) ){
                    long id = dbConeccion.insertarNuevoLibro(nombreL, autorL, cantidadL, descripcionL, fotoL, urlL);
                    if(id > 0){
                        Toast.makeText(AdminAgregarLibro.this, "REGISTRO GUARDADO", Toast.LENGTH_LONG).show();
                        limpiar();
                        atras();
                    }else{
                        Toast.makeText(AdminAgregarLibro.this, "ERROR AL GUARDAR REGISTRO", Toast.LENGTH_LONG).show();
                    }
                }else{
                    Toast.makeText(AdminAgregarLibro.this, "LOS CAMPOS NO DEBEN ESTAR VACIOS!", Toast.LENGTH_LONG).show();
                }
            }
        });

        atras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                atras();
            }
        });
    }

    private void limpiar(){
        nombre.setText("");
        autor.setText("");
        cantidad.setText("");
        url.setText("");
        foto.setText("");
        descripcion.setText("");
    }

    private  void atras(){
        Intent intent = new Intent(this, AdminLibrosDisponibles.class);
        startActivity(intent);
    }
}