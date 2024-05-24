package com.example.lecteurmusique.Models;

public class Genre {
    public int idGenre;
    public String nom;

    public Genre(int idGenre, String nom) {
        this.idGenre = idGenre;
        this.nom = nom;
    }

    public int getIdGenre() {
        return idGenre;
    }

    public String getNom() {
        return nom;
    }
}
