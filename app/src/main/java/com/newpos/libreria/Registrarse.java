package com.newpos.libreria;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.newpos.libreria.db.DbConeccion;


public class Registrarse extends AppCompatActivity {

    EditText RNombre, RCorreo, RTelefono, RDireccion, RContraseña ;
    Button registrar, regresar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_registrarse);

        RNombre = findViewById(R.id.RName);
        RCorreo = findViewById(R.id.REmail);
        RTelefono = findViewById(R.id.RPhone);
        RDireccion = findViewById(R.id.RDirecct);
        RContraseña = findViewById(R.id.RDPass);
        registrar = findViewById(R.id.buttonregistrar);
        regresar = findViewById(R.id.RRegresar);
        DbConeccion dbConeccion = new DbConeccion(Registrarse.this);

        registrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String nombreStr = RNombre.getText().toString();
                String correoStr = RCorreo.getText().toString();
                String telefonoStr = RTelefono.getText().toString();
                String direccionStr = RDireccion.getText().toString();
                String passwordStr = RContraseña.getText().toString();

                if (nombreStr.equals("")||(correoStr.equals(""))||(telefonoStr.equals(""))||(direccionStr.equals(""))||(passwordStr.equals(""))){
                    Toast.makeText(Registrarse.this, "LOS CAMPOS NO DEBEN ESTRAR VACIOS!", Toast.LENGTH_LONG).show();
                }else{
                    long id = dbConeccion.insertarNuevoUsuario(nombreStr, correoStr, telefonoStr, direccionStr, passwordStr);
                    if(id > 0){
                        Toast.makeText(Registrarse.this, "REGISTRO GUARDADO", Toast.LENGTH_LONG).show();
                        limpiar();
                        regresarLogin();
                    }else{
                        Toast.makeText(Registrarse.this, "ERROR AL GUARDAR REGISTRO", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });

        regresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                regresarLogin();
            }
        });

    }

    private void limpiar(){
        RNombre.setText("");
        RCorreo.setText("");
        RTelefono.setText("");
        RDireccion.setText("");
        RContraseña.setText("");
    }

    private  void regresarLogin(){
        Intent intent = new Intent(this, LoginSesion.class);
        startActivity(intent);

    }
}