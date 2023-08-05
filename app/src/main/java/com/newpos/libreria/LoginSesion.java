package com.newpos.libreria;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.newpos.libreria.Entidades.SharedPreference;
import com.newpos.libreria.db.DbConeccion;


public class LoginSesion extends AppCompatActivity {

    EditText correo, password;
    Button IniciaSesion;
    TextView registrarse;
    String passwUsuario;
    DbConeccion baseUser;
    SharedPreference sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //requestWindowFeature(Window.FEATURE_NO_TITLE);
        //pantalla completa
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_login);

        correo = findViewById(R.id.correoIS);
        password = findViewById(R.id.passwordIS);
        IniciaSesion = findViewById(R.id.iniciaSesion);
        registrarse = findViewById(R.id.registrarse);
        baseUser = new DbConeccion(this);
        sp = new SharedPreference(this);
        //credenciales admin
        String correoAdmin = "yhonj8a@";
        String passwordAdmin = "12345";




        IniciaSesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String correoVal = correo.getText().toString();
                String passwordVal = password.getText().toString();

                if (!(correoVal.equals(""))){
                    if (!(passwordVal.equals(""))){
                        if (correoVal.equals(correoAdmin) && passwordVal.equals(passwordAdmin)){
                            verAdminLibrosDisponibles();
                            sp.setUsuarioActivo(correoVal);
                        }else{
                            passwUsuario = baseUser.validarUsuarios(correoVal);
                            if (!passwUsuario.equals("correo no exite")){
                                if(passwUsuario.equals(passwordVal)){
                                    sp.setUsuarioActivo(correoVal);
                                    verUsuMisLibrosPrestados();

                                }else {
                                    Toast.makeText(LoginSesion.this, "LA CONTRASEÑA ES INCORRECTA...", Toast.LENGTH_LONG).show();
                                }
                            }else{
                                Toast.makeText(LoginSesion.this, "EL CORREO NO ESTA REGISTRADO...", Toast.LENGTH_LONG).show();
                            }
                        }
                    }else {
                        Toast.makeText(LoginSesion.this, "EL CAMPO CONTRASEÑA NO DEBE ESTAR VACIO!", Toast.LENGTH_LONG).show();
                    }
                }else {
                    Toast.makeText(LoginSesion.this, "EL CAMPO CORREO NO DEBE ESTAR VACIO!", Toast.LENGTH_LONG).show();
                }
            }
        });

        registrarse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                verRegistrarse();
            }
        });
    }

    private void  verRegistrarse(){
        Intent intent = new Intent(this, Registrarse.class);
        startActivity(intent);
    }


    private void  verUsuMisLibrosPrestados(){
        Intent intent = new Intent(this, UsuMisLibros.class);
        startActivity(intent);
    }
    private void  verAdminLibrosDisponibles(){
        Intent intent = new Intent(this,AdminLibrosDisponibles.class);
        startActivity(intent);
    }
}