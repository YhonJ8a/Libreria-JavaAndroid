package com.newpos.libreria;

import static com.newpos.libreria.R.id.imageView3;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.newpos.libreria.Entidades.SharedPreference;
import com.newpos.libreria.adaptadores.ListaLibrosPresAdapter;
import com.newpos.libreria.adaptadores.ListalibrosDisponiblesAdapter;
import com.newpos.libreria.db.DbConeccion;


public class AdminLibrosDisponibles extends AppCompatActivity implements PopupMenu.OnMenuItemClickListener {

    TextView nombre, text ,TextUsuAdmin;
    RecyclerView listaLibrosPrestados;
    DbConeccion dbConeccion;
    ImageButton btnMas, btnAtras;
    Class segVistaAdaptador;
    ImageView perfilImagen;
    String urlImagen = "https://w7.pngwing.com/pngs/550/132/png-transparent-glenn-quagmire-stewie-griffin-peter-griffin-brian-griffin-meg-griffin-others-child-face-hand.png";

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_libros_disponibles);
        getSupportActionBar().hide();


        text = findViewById(R.id.librosDisTitulo);
        text.setText("libros Disponibles");
        TextUsuAdmin = findViewById(R.id.testUsuario);
        TextUsuAdmin.setText("Administrador");
        dbConeccion = new DbConeccion(this);
        nombre = findViewById(R.id.nomUsuario);

        String nomUsuario = dbConeccion.nombreUsuario();
        nombre.setText(nomUsuario);


        perfilImagen = findViewById(imageView3);
        Glide.with(this).load(urlImagen).error(R.drawable.ic_baseline_android_24).into(perfilImagen);

        listaLibrosPrestados = findViewById(R.id.listaLibrosDis);
        listaLibrosPrestados.setLayoutManager(new GridLayoutManager(this, 2));
        //listaLibrosPrestados.setLayoutManager(new LinearLayoutManager(this));

        btnAtras = findViewById(R.id.imageButtonAtras);
        btnMas = findViewById(R.id.imageButtonMas);

        btnAtras.setVisibility(View.INVISIBLE);




        btnMas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                popupMenu(view);
            }
        });


        segVistaAdaptador =AdminActualizarLibro.class;
        ListalibrosDisponiblesAdapter adapter = new ListalibrosDisponiblesAdapter( segVistaAdaptador, dbConeccion.mostrarLibrosDisponibles());
        listaLibrosPrestados.setAdapter(adapter);

    }

    public void popupMenu(View v){
        PopupMenu popupMenu = new PopupMenu(this, v);
        popupMenu.setOnMenuItemClickListener(this );
        popupMenu.inflate(R.menu.menupopup);
        popupMenu.show();
    }

    @Override
    public boolean onMenuItemClick(MenuItem menuItem) {
        switch (menuItem.getItemId()){
            case R.id.prestados:
                return  true;
            case R.id.agregar:
                irAlVistaAgregar();
                return  true;
            case R.id.salir:
                regresarLogin();
                return  true;
            default:
                return false;
        }
    }

    private  void regresarLogin(){
        Intent intent = new Intent(this, LoginSesion.class);
        startActivity(intent);
    }

    private  void irAlVistaPrestados(){
        Intent intent = new Intent(this, LoginSesion.class);
        startActivity(intent);
    }

    private  void irAlVistaAgregar(){
        Intent intent = new Intent(this, AdminAgregarLibro.class);
        startActivity(intent);
    }



}