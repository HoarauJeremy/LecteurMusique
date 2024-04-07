/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package lecteurmusique.Model;

import java.util.Date;

/**
 *
 * @author Jérémy Hoarau
 */
public class Musique {

    public int idMusique;
    public String nom, lien;
    public Date creationDate;
    public int idGenre, idArtiste;

    public Musique() {
    }

    public Musique(int idMusique, String nom, String lien, Date creationDate, int idGenre, int idArtiste) {
        this.idMusique = idMusique;
        this.nom = nom;
        this.lien = lien;
        this.creationDate = creationDate;
        this.idGenre = idGenre;
        this.idArtiste = idArtiste;
    }

    public int getIdMusique() {
        return idMusique;
    }

    public String getNom() {
        return nom;
    }

    public String getLien() {
        return lien;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public int getIdGenre() {
        return idGenre;
    }

    public int getIdArtiste() {
        return idArtiste;
    }
    
}
