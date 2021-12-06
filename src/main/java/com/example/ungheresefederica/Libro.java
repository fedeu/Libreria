package com.example.ungheresefederica;

import android.graphics.drawable.Drawable;

public class Libro {

    //variabili d'istanza
    private String titolo, autore;
    private Drawable copertina;

    //costruttori
    public Libro(){}

    public Libro(String titolo, String autore, Drawable copertina) {
        this.titolo = titolo;
        this.autore = autore;
        this.copertina = copertina;
    }

    //get
    public String getTitolo() {
        return titolo;
    }
    public String getAutore() {
        return autore;
    }
}
