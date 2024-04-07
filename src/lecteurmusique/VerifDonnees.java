/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lecteurmusique;

import java.util.regex.Pattern;

/**
 *
 * @author Jérémy Hoarau
 */
public class VerifDonnees {
    
    /**
     *
     * @param email
     * @return
     */
    public static boolean verifEmail(String email) {
        boolean resultat = true;
        
        if (Pattern.matches("^[\\w\\.=-]+@[\\w\\.-]+\\.[\\w]{2,3}$", email) == false) {
            resultat = false;
        }
        
        return resultat;
    }
    
    /**
     *
     * @param nom
     * @return
     */
    public static boolean verifNomUtilisateur(String nom) {
        boolean resultat = true;
        
        if (Pattern.matches("^[a-zA-Zéèêë\\ -]{2,}$", nom) == false) {
            resultat = false;
        }
        
        return resultat;
    }
    
}
