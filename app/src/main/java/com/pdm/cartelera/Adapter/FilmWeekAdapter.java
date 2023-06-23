package com.pdm.cartelera.Adapter;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.pdm.cartelera.FilmDetails;
import com.pdm.cartelera.Model.Film;
import com.pdm.cartelera.R;

import java.util.ArrayList;
import java.util.List;

public class FilmWeekAdapter extends RecyclerView.Adapter<FilmWeekAdapter.ViewHolder>{

    private List<Film> listaPeliculas;
    private Context context;

    private boolean clickable;

    public FilmWeekAdapter(){

    }

    public FilmWeekAdapter(Context context) {
        this.context = context;
        this.listaPeliculas = new ArrayList<>();
        this.clickable=true;
    }

    public FilmWeekAdapter(List<Film> listaPeliculas, Context context) {
        this.listaPeliculas = listaPeliculas;
        this.context = context;
        this.clickable=true;
    }

    public FilmWeekAdapter(Context context, boolean clickable) {
        this.listaPeliculas = new ArrayList<>();
        this.context = context;
        this.clickable = clickable;
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
    public FilmWeekAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.film_week,parent,false);
        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull FilmWeekAdapter.ViewHolder holder, int position) {
        Film pelicula= listaPeliculas.get(position);

        holder.nombre.setText(pelicula.getNombre());
        holder.año.setText(pelicula.getAutor()+" • "+pelicula.getAño());
        holder.imagen.setImageResource(pelicula.getImagen());
        holder.duracion.setText(String.valueOf(pelicula.getDuracion())+" min");

        holder.tipo1.setText(pelicula.getTipo1());
        holder.tipo2.setText(pelicula.getTipo2());


        if(clickable){
            holder.itemView.setOnClickListener(v -> {
                FilmDetails detalleFragment = new FilmDetails();

                if(pelicula!=null){
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("film",pelicula);
                    detalleFragment.setArguments(bundle);
                }

                FragmentManager fragmentManager = ((FragmentActivity) context).getSupportFragmentManager();
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                transaction.replace(R.id.mainFrame, detalleFragment);
                transaction.addToBackStack(null);
                transaction.commit();
            });
        }
    }

    @Override
    public int getItemCount() {
        return listaPeliculas.size();
    }

    public Film getFilm(int position ){
        return listaPeliculas.get(position);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        private TextView nombre, año, duracion,tipo1,tipo2;
        private ImageView imagen;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            nombre=itemView.findViewById(R.id.nameFilmWeek);
            año=itemView.findViewById(R.id.ageFilmWeek);
            imagen=itemView.findViewById(R.id.imageFilmWeek);
            duracion=itemView.findViewById(R.id.durationFilmWeek);
            tipo1=itemView.findViewById(R.id.tipo1);
            tipo2=itemView.findViewById(R.id.tipo2);


        }

    }
}
