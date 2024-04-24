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

    /**
     *
     * @param idMusique
     * @param nom
     * @param lien
     * @param creationDate
     * @param idGenre
     * @param idArtiste
     */
    public Musique(int idMusique, String nom, String lien, Date creationDate, int idGenre, int idArtiste) {
        this.idMusique = idMusique;
        this.nom = nom;
        this.lien = lien;
        this.creationDate = creationDate;
        this.idGenre = idGenre;
        this.idArtiste = idArtiste;
    }

    /**
     *
     * @return l'id de la musique
     */
    public int getIdMusique() {
        return idMusique;
    }

    /**
     *
     * @return le nom de la musique
     */
    public String getNom() {
        return nom;
    }

    /**
     *
     * @return le lien de la musique
     */
    public String getLien() {
        return lien;
    }

    /**
     *
     * @return la date de creation de la musique
     */
    public Date getCreationDate() {
        return creationDate;
    }

    /**
     *
     * @return l'id du genre de la musique
     */
    public int getIdGenre() {
        return idGenre;
    }

    /**
     *
     * @return l'id de l'artiste de la musique
     */
    public int getIdArtiste() {
        return idArtiste;
    }
    
}
