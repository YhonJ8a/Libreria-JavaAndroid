package com.newpos.libreria.adaptadores;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.newpos.libreria.Entidades.Libros;
import com.newpos.libreria.R;

import java.util.ArrayList;


public class ListaLibrosPresAdapter extends RecyclerView.Adapter<ListaLibrosPresAdapter.LibrosPrestados> {

    ArrayList<Libros> listaLibros;

    public ListaLibrosPresAdapter(ArrayList<Libros> listaLibros) {
        this.listaLibros = listaLibros;
    }

    @NonNull
    @Override
    public LibrosPrestados onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_nuevo_libro_pres,null, false);
        return  new LibrosPrestados(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LibrosPrestados holder, int position) {
        holder.nombre.setText(listaLibros.get(position).getNom_libro());
        holder.autor.setText(listaLibros.get(position).getAutor());
        holder.fecha.setText(listaLibros.get(position).getFecha());
    }

    @Override
    public int getItemCount() {
        return listaLibros.size();
    }

    public class LibrosPrestados extends RecyclerView.ViewHolder {

        TextView nombre , autor, fecha;

        public LibrosPrestados(@NonNull View itemView) {
            super(itemView);

            nombre = itemView.findViewById(R.id.NLPnombre);
            autor = itemView.findViewById(R.id.NLPautor);
            fecha = itemView.findViewById(R.id.NLPfecha);
        }
    }
}
