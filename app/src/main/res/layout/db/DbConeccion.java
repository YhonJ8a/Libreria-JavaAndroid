package db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

import java.util.ArrayList;

import Entidades.Libros;

public class DbConeccion extends DbHelper{
    Context context;

    public DbConeccion(@Nullable Context context) {
        super(context);
        this.context = context;
    }

    public long insertarNuevoLibro(String nombre, String autor, int cantidad, String descripcion, String foto, String url) {

        long id = 0;
        try {
            DbHelper dbHelper = new DbHelper(context);
            SQLiteDatabase db = dbHelper.getWritableDatabase();

            ContentValues values = new ContentValues();
            values.put("nom_libro", nombre);
            values.put("autor", autor);
            values.put("cantidad", cantidad);
            values.put("descripcion", descripcion);
            values.put("foto", foto);
            values.put("url", url);

            id = db.insert(TABLE_LIBRO, null, values);
        } catch (Exception ex){
            ex.toString();
        }
        return id;
    }

    public String validarUsuarios(String correo){

        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        String passUsuario;
        Cursor cursorUsers ;

        cursorUsers= db.rawQuery("select password from "+ TABLE_USUARIOS+" WHERE correo = '"+ correo+"'", null);

        if(cursorUsers.moveToFirst()) {
            passUsuario = cursorUsers.getString(0);
        }else {
            passUsuario = "correo no exite";
        }
        cursorUsers.close();
        return passUsuario;
    }


    public long insertarNuevoUsuario(String nombre, String correo, String telefono, String direccion ,String password) {

        long id = 0;
        try {
            DbHelper dbHelper = new DbHelper(context);
            SQLiteDatabase db = dbHelper.getWritableDatabase();

            ContentValues values = new ContentValues();
            values.put("nombre", nombre);
            values.put("correo", correo);
            values.put("password", password);
            values.put("telefono", telefono);
            values.put("direccion", direccion);

            id = db.insert(TABLE_LIBRO, null, values);
        } catch (Exception ex){
            ex.toString();
        }
        return id;
    }


    public ArrayList<Libros> mostrarLibrosPrestados(int codUsuario){

        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ArrayList<Libros> listaContactos = new ArrayList<>();
        Libros libros = null;
        Cursor cursorContactos = null;

        cursorContactos = db.rawQuery("select "+TABLE_LIBRO+".* , "+TABLE_PRESTAMO+".fecha "+
                "from "+ TABLE_LIBRO + " inner join "+TABLE_PRESTAMO+" on "+TABLE_LIBRO+".cod_libro = "+TABLE_PRESTAMO+".cod_libro "+
                " inner join "+TABLE_USUARIOS+" on "+TABLE_PRESTAMO+".cod_usuario = "+TABLE_USUARIOS+".id_usuario "+
                " WHERE "+TABLE_USUARIOS+".id_usuario = "+codUsuario+"  ", null);

        if(cursorContactos.moveToFirst()){
            do {
                libros = new Libros();
                libros.setNom_libro(cursorContactos.getString(1));
                libros.setAutor(cursorContactos.getString(2));
                libros.setFoto(cursorContactos.getString(5));
                libros.setFecha(cursorContactos.getString(7));
                listaContactos.add(libros);
            }while (cursorContactos.moveToNext());
        }else {
            libros = new Libros();
            libros.setNom_libro("NO HAY LIBROS PRESTADOS");
            listaContactos.add(libros);
        }

        cursorContactos.close();
        return listaContactos;
    }


    public int codUsuario(String correo){

        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        int cod_Usuario = 0;
        Cursor cursorUsers = null;

        cursorUsers= db.rawQuery("select id_usuario from "+ TABLE_USUARIOS+" WHERE correo = '"+ correo+"'", null);

        if(cursorUsers.moveToFirst()) {
            cod_Usuario = cursorUsers.getInt(0);
        }
        cursorUsers.close();
        return cod_Usuario;
    }

    public ArrayList<Libros> mostrarLibrosDisponibles(){

        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ArrayList<Libros> listaContactos = new ArrayList<>();
        Libros libros = null;
        Cursor cursorContactos = null;

        cursorContactos = db.rawQuery("select nom_libro, autor, foto from "+TABLE_LIBRO+" where cantidad > 0 ", null);

        if(cursorContactos.moveToFirst()){
            do {
                libros = new Libros();
                libros.setNom_libro(cursorContactos.getString(0));
                libros.setAutor(cursorContactos.getString(1));
                libros.setFoto(cursorContactos.getString(2));
                listaContactos.add(libros);
            }while (cursorContactos.moveToNext());
        }else {
            libros = new Libros();
            libros.setNom_libro("NO HAY LIBROS DISPONIBLES");
            listaContactos.add(libros);
        }

        cursorContactos.close();
        return listaContactos;
    }

    public String nombreUsuario(String correo){

        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        String nomUsuario = null;
        Cursor cursorUsers = null;

        cursorUsers= db.rawQuery("select nombre from "+ TABLE_USUARIOS+" WHERE correo = '"+ correo+"'", null);

        if(cursorUsers.moveToFirst()) {
            nomUsuario = cursorUsers.getString(0);
        }
        cursorUsers.close();
        return nomUsuario;
    }

    public ArrayList<Libros> buscarLibrosPrestados(int codUsuario, String nombre){

        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ArrayList<Libros> listaContactos = new ArrayList<>();
        Libros libros = null;
        Cursor cursorContactos = null;

        cursorContactos = db.rawQuery("select "+TABLE_LIBRO+".* , "+TABLE_PRESTAMO+".fecha "+
                " from "+ TABLE_LIBRO + " inner join "+TABLE_PRESTAMO+" on "+TABLE_LIBRO+".cod_libro = "+TABLE_PRESTAMO+".cod_libro "+
                " inner join "+TABLE_USUARIOS+" on "+TABLE_PRESTAMO+".cod_usuario = "+TABLE_USUARIOS+".id_usuario "+
                " WHERE "+TABLE_USUARIOS+".id_usuario = "+codUsuario+"  and "+TABLE_LIBRO+".nom_libro like '"+nombre+"'  ", null);

        if(cursorContactos.moveToFirst()){
            do {
                libros = new Libros();
                libros.setNom_libro(cursorContactos.getString(1));
                libros.setAutor(cursorContactos.getString(2));
                libros.setFoto(cursorContactos.getString(5));
                listaContactos.add(libros);
            }while (cursorContactos.moveToNext());
        }else {
            libros = new Libros();
            libros.setNom_libro("NO HAY RESULTADOS");
            listaContactos.add(libros);
        }

        cursorContactos.close();
        return listaContactos;
    }


    public ArrayList<Libros> buscarLibrosDisponibles( String nombre){

        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ArrayList<Libros> listaContactos = new ArrayList<>();
        Libros libros = null;
        Cursor cursorContactos = null;

        cursorContactos = db.rawQuery("select * from "+ TABLE_LIBRO +
                " WHERE nom_libro like '"+nombre+"' ", null);

        if(cursorContactos.moveToFirst()){
            do {
                libros = new Libros();
                libros.setNom_libro(cursorContactos.getString(1));
                libros.setAutor(cursorContactos.getString(2));
                libros.setFoto(cursorContactos.getString(5));
                listaContactos.add(libros);
            }while (cursorContactos.moveToNext());
        }else {
            libros = new Libros();
            libros.setNom_libro("NO HAY RESULTADOS");
            listaContactos.add(libros);
        }

        cursorContactos.close();
        return listaContactos;
    }

    public Libros verLibro(int id){

        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        Cursor cursorUsers ;
        Libros libro = new Libros();

        cursorUsers= db.rawQuery("select * from "+ TABLE_LIBRO+" WHERE cod_libro = '"+ id+"'", null);

        if(cursorUsers.moveToFirst()) {
            libro.setCod_libro(cursorUsers.getInt(0));
            libro.setNom_libro(cursorUsers.getString(1));
            libro.setAutor(cursorUsers.getString(2));
            libro.setCantidad(cursorUsers.getInt(3));
            libro.setDescripcion(cursorUsers.getString(4));
            libro.setFoto(cursorUsers.getString(5));
            libro.setUrl(cursorUsers.getString(6));
        }else {
            libro.setNom_libro("id erroneo");
        }
        cursorUsers.close();
        return libro;
    }

    public long insertarNuevoPrestamo(String cod_usuario, String cod_libro) {

        long id = 0;
        try {
            DbHelper dbHelper = new DbHelper(context);
            SQLiteDatabase db = dbHelper.getWritableDatabase();

            db.rawQuery("INSERT INTO "+TABLE_PRESTAMO+" (cod_usuario, cod_libro, cantidadPres, fecha) VALUES( "+cod_usuario+" , "+cod_libro+" , 1 ,  current_date) " , null);
        } catch (Exception ex){
            ex.toString();
        }
        return id;
    }
}
