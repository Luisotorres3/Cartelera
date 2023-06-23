package com.pdm.cartelera.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.pdm.cartelera.Model.Film;
import com.pdm.cartelera.R;

import java.util.ArrayList;
import java.util.List;

public class FilmDetailsAdapter extends RecyclerView.Adapter<FilmDetailsAdapter.ViewHolder>{

    private List<Film> listaPeliculas;
    private Context context;

    public FilmDetailsAdapter(){

    }

    public FilmDetailsAdapter(Context context) {
        this.context = context;
        this.listaPeliculas = new ArrayList<>();
    }

    public FilmDetailsAdapter(List<Film> listaPeliculas, Context context) {
        this.listaPeliculas = listaPeliculas;
        this.context = context;
    }

    public void add(Film film){
        listaPeliculas.add(film);
        notifyDataSetChanged();
    }
    public void clear(){
        this.listaPeliculas.clear();
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public FilmDetailsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.film_details,parent,false);
        return new FilmDetailsAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FilmDetailsAdapter.ViewHolder holder, int position) {
        Film pelicula= listaPeliculas.get(position);

        holder.nombre.setText(pelicula.getNombre());
        holder.año.setText(pelicula.getAño());
        holder.imagen.setImageResource(pelicula.getImagen());
        holder.sinopsis.setText(pelicula.getSinopsis());
        holder.autor.setText(pelicula.getAutor());
        holder.duracion.setText(String.valueOf(pelicula.getDuracion()));

        holder.linearVerMas.setOnClickListener(v -> {
            holder.sinopsis.setVisibility(View.VISIBLE);
            holder.linearVerMas.setVisibility(View.GONE);
        });

    }

    @Override
    public int getItemCount() {
        return listaPeliculas.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        private TextView nombre, año, autor, sinopsis, duracion;
        private LinearLayout linearVerMas;
        private ImageView imagen;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            nombre=itemView.findViewById(R.id.textNameDetails);
            año=itemView.findViewById(R.id.textAgeDetails);
            imagen=itemView.findViewById(R.id.imageDetails);
            autor= itemView.findViewById(R.id.textAutorDetails);
            duracion=itemView.findViewById(R.id.textDuracionDetails);
            sinopsis=itemView.findViewById(R.id.textSinopsisDetails);
            linearVerMas=itemView.findViewById(R.id.linearVerMas);




        }

    }
}
