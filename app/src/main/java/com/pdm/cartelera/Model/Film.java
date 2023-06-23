package com.pdm.cartelera.Model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Film implements Serializable {

    private String idFilm;
    private String nombre;
    private String año;

    private String autor;

    private String sinopsis;

    private ArrayList<String> reparto;

    private int duracion;
    private int imagen;

    private String tipo1,tipo2;

    private String urlTrailer,urlFilmaffinity,urlIMDB;

    public Film(){
        this.nombre = "";
        this.año = "";
        this.autor = "";
        this.sinopsis = "";
        this.reparto =new ArrayList<>();
        this.duracion = 0;
        this.imagen = 0;
        this.tipo1 = "";
        this.tipo2 = "";
        this.urlTrailer = "";
        this.urlFilmaffinity = "";
        this.urlIMDB = "";
    }
    public Film(String nombre, String año, int imagen,String id) {
        this.idFilm=id;
        this.nombre = nombre;
        this.año = año;
        this.imagen = imagen;
    }

    public Film(String nombre, String año, String autor, int duracion, int imagen,String id) {
        this.idFilm=id;
        this.nombre = nombre;
        this.año = año;
        this.autor = autor;
        this.duracion = duracion;
        this.imagen = imagen;
    }

    public Film(String nombre, String año, String autor, String sinopsis, ArrayList<String> reparto, int duracion, int imagen, String tipo1, String tipo2, String urlTrailer, String urlFilmaffinity, String urlIMDB,String id) {
        this.nombre = nombre;
        this.año = año;
        this.autor = autor;
        this.sinopsis = sinopsis;
        this.reparto = reparto;
        this.duracion = duracion;
        this.imagen = imagen;
        this.tipo1 = tipo1;
        this.tipo2 = tipo2;
        this.urlTrailer = urlTrailer;
        this.urlFilmaffinity = urlFilmaffinity;
        this.urlIMDB = urlIMDB;
        this.idFilm=id;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public int getDuracion() {
        return duracion;
    }

    public void setDuracion(int duracion) {
        this.duracion = duracion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getAño() {
        return año;
    }

    public void setAño(String año) {
        this.año = año;
    }

    public int getImagen() {
        return imagen;
    }

    public void setImagen(int imagen) {
        this.imagen = imagen;
    }

    public String getSinopsis() {
        return sinopsis;
    }

    public void setSinopsis(String sinopsis) {
        this.sinopsis = sinopsis;
    }

    public ArrayList<String> getReparto() {
        return reparto;
    }

    public void setReparto(ArrayList<String> reparto) {
        this.reparto = reparto;
    }

    public String getTipo1() {
        return tipo1;
    }

    public void setTipo1(String tipo1) {
        this.tipo1 = tipo1;
    }

    public String getTipo2() {
        return tipo2;
    }

    public void setTipo2(String tipo2) {
        this.tipo2 = tipo2;
    }

    public String getUrlTrailer() {
        return urlTrailer;
    }

    public void setUrlTrailer(String urlTrailer) {
        this.urlTrailer = urlTrailer;
    }

    public String getUrlFilmaffinity() {
        return urlFilmaffinity;
    }

    public void setUrlFilmaffinity(String urlFilmaffinity) {
        this.urlFilmaffinity = urlFilmaffinity;
    }

    public String getUrlIMDB() {
        return urlIMDB;
    }

    public void setUrlIMDB(String urlIMDB) {
        this.urlIMDB = urlIMDB;
    }

    public String getIdFilm() {
        return idFilm;
    }
}
