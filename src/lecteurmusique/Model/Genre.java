/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package lecteurmusique.Model;

/**
 *
 * @author Jérémy Hoarau
 */
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
