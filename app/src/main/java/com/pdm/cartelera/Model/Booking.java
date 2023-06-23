package com.pdm.cartelera.Model;

import android.graphics.Bitmap;

import java.io.Serializable;

public class Booking implements Serializable {

    private String email;
    private Bitmap imagenQR;

    public Booking(String email, Bitmap imagenQR) {
        this.email = email;
        this.imagenQR = imagenQR;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Bitmap getImagenQR() {
        return imagenQR;
    }

    public void setImagenQR(Bitmap imagenQR) {
        this.imagenQR = imagenQR;
    }
}
