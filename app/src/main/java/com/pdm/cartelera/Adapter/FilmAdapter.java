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

public class FilmAdapter extends RecyclerView.Adapter<FilmAdapter.ViewHolder>{

    private List<Film> listaPeliculas;
    private Context context;

    public FilmAdapter(){

    }

    public FilmAdapter(Context context) {
        this.context = context;
        this.listaPeliculas = new ArrayList<>();
    }

    public FilmAdapter(List<Film> listaPeliculas, Context context) {
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
    public FilmAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.film_estreno,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FilmAdapter.ViewHolder holder, int position) {
        Film pelicula= listaPeliculas.get(position);

        holder.nombre.setText(pelicula.getNombre());
        holder.año.setText(pelicula.getAño());
        holder.imagen.setImageResource(pelicula.getImagen());

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

    @Override
    public int getItemCount() {
        return listaPeliculas.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        private TextView nombre, año;
        private ImageView imagen;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            nombre=itemView.findViewById(R.id.nameFilm);
            año=itemView.findViewById(R.id.ageFilm);
            imagen=itemView.findViewById(R.id.imageFilm);


        }

    }
}
