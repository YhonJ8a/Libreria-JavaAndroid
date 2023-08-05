package com.newpos.libreria;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SearchView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.newpos.libreria.Entidades.Libros;
import com.newpos.libreria.Entidades.SharedPreference;
import com.newpos.libreria.db.DbConeccion;


public class UsuPrestarLibro extends AppCompatActivity {

    TextView nombre;
    SearchView buscador;
    TextView text;
    SharedPreference sp;
    TextView  nombreLibro, autorLibro, descripcionlibro;
    //ImageView imagenlibro;
    Button btnPrestar;
    Libros libro;
    int id = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usu_prestar_libro);

        nombre = findViewById(R.id.nomUsuario);
        buscador = findViewById(R.id.buscador);
        text = findViewById(R.id.librosDisTitulo);
        nombreLibro = findViewById(R.id.PLnomLibro);
        autorLibro = findViewById(R.id.PLautor);
        descripcionlibro = findViewById(R.id.PLdescripcion);
        btnPrestar = findViewById(R.id.btnPrestar);

        text.setText("Prestar Libro");
        DbConeccion dbConeccion = new DbConeccion(this);
        String nomUsuario = dbConeccion.nombreUsuario();

        nombre.setText(nomUsuario);
        buscador.setVisibility(View.INVISIBLE);

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
            autorLibro.setText(libro.getAutor());
            descripcionlibro.setText(libro.getDescripcion());
        }

        btnPrestar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //implementar metodo para regresar a libros disponibles

                // metodo para todos los movimientos en la base



            }
        });

    }
}