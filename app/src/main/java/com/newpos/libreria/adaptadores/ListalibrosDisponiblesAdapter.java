package com.newpos.libreria.adaptadores;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.ViewTarget;
import com.newpos.libreria.Entidades.Libros;
import com.newpos.libreria.R;
import com.newpos.libreria.UsuPrestarLibro;

import java.util.ArrayList;


public class ListalibrosDisponiblesAdapter extends RecyclerView.Adapter<ListalibrosDisponiblesAdapter.LibrosDisponibles> {

    ArrayList<Libros> listaLibros;
    Object nombreClass;

    public ListalibrosDisponiblesAdapter(Object nombre , ArrayList<Libros> listaLibros) {
        this.listaLibros = listaLibros;
        nombreClass =  nombre;
    }

    @NonNull
    @Override
    public LibrosDisponibles onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_nldisponible,null, false);
        return new LibrosDisponibles(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LibrosDisponibles holder, int position) {
        holder.nombre.setText(listaLibros.get(position).getNom_libro());
        holder.autor.setText(listaLibros.get(position).getAutor());
        holder.fotoling = listaLibros.get(position).getFoto();
    }

    @Override
    public int getItemCount() {
        return listaLibros.size();
    }

    public class LibrosDisponibles extends RecyclerView.ViewHolder {

        TextView nombre , autor;
        String fotoling;
        ImageView foto;

        public LibrosDisponibles(@NonNull View itemView) {
            super(itemView);

            nombre = itemView.findViewById(R.id.NLDnombre);
            autor = itemView.findViewById(R.id.NLDautor);
            foto = itemView.findViewById(R.id.NLDfoto);

            Glide.with(itemView).load(fotoling).error(R.drawable.portada).into(foto);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Context context = view.getContext();
                    Intent intent = new Intent(context, (Class<?>) nombreClass );// UsuPrestarLibro.class
                    intent.putExtra("ID", listaLibros.get(getAdapterPosition()).getCod_libro() );
                    context.startActivity(intent);
                }
            });
        }
    }


}

