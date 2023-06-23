package com.pdm.cartelera.Model;

import android.util.Pair;


import java.util.ArrayList;
import java.util.Date;

public class Hours {

    private String idPelicula;
    private ArrayList<Pair<String, ArrayList<String>>> fechas;

    public Hours(String idPelicula, ArrayList<Pair<String, ArrayList<String>>> fechas) {
        this.idPelicula = idPelicula;
        this.fechas = fechas;
    }

    public ArrayList<String> getHorasDia(String fecha){
        for (Pair<String, ArrayList<String>> pair : fechas) {
            if (pair.first.equals(fecha)) {
                return pair.second;
            }
        }
        return null;
    }



    public String getIdPelicula() {
        return idPelicula;
    }

    public void setIdPelicula(String idPelicula) {
        this.idPelicula = idPelicula;
    }

    public ArrayList<Pair<String, ArrayList<String>>> getFechas() {
        return fechas;
    }

    public void setFechas(ArrayList<Pair<String, ArrayList<String>>> fechas) {
        this.fechas = fechas;
    }
}
