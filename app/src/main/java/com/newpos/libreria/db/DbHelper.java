package com.newpos.libreria.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DbHelper extends SQLiteOpenHelper {

    private static  final int DATBASE_VERSION= 1;
    private static  final String DATABASE_NOMBRE = "biblioteca.db" ;
    public static  final String TABLE_LIBRO = "t_libros" ;
    public static  final String TABLE_USUARIOS = "t_usuarios" ;
    public static  final String TABLE_PRESTAMO = "t_prestar" ;

    public DbHelper(@Nullable Context context) {
        super(context, DATABASE_NOMBRE, null,DATBASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE "+ TABLE_LIBRO + "(" +
                "cod_libro INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "nom_libro TEXT NOT NULL," +
                "autor TEXT NOT NULL," +
                "cantidad INTEGER NOT NULL," +
                "descripcion TEXT, " +
                "foto TEXT, " +
                "url TEXT)");


        sqLiteDatabase.execSQL("CREATE TABLE "+ TABLE_USUARIOS + "(" +
                "id_usuario INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "nombre TEXT NOT NULL," +
                "correo TEXT NOT NULL," +
                "password TEXT NOT NULL," +
                "telefono TEXT ," +
                "direccion TEXT )");


        sqLiteDatabase.execSQL("CREATE TABLE "+ TABLE_PRESTAMO + "(" +
                "cod_prestamo INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "cod_usuario TEXT NOT NULL," +
                "cod_libro TEXT NOT NULL," +
                "cantidadPres INTEGER NOT NULL," +
                "fecha DATE NOT NULL )");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        sqLiteDatabase.execSQL("DROP TABLE " + TABLE_LIBRO);
        onCreate(sqLiteDatabase);
    }
}
