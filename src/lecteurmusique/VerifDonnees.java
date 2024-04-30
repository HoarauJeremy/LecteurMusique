/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lecteurmusique;

import java.util.regex.Pattern;

/**
 * Classe de vérification de données saisie par les utilisateurs
 *
 * @author Jérémy Hoarau
 */
public class VerifDonnees {
    
    /**
     * Verifie si l'addresse mail saisie par l'utilisateur est conforme au <b>Regex</b>.
     *
     * @param email Saisie par l'utilisateur
     * @return <i><b>true</b></i> si l'addresse mail est conforme au <b>ReGex</b>, sinon <i><b>false</b></i>
     */
    public static boolean verifEmail(String email) {
        boolean resultat = true;
        
        if (Pattern.matches("^[\\w\\.=-]+@[\\w\\.-]+\\.[\\w]{2,3}$", email) == false) {
            resultat = false;
        }
        
        return resultat;
    }
    
    /**
     * Verifie si le nom saisie par l'utilisateur est conforme au <b>Regex</b>.
     * 
     * @param nom Saisie par l'utilisateur
     * @return <i><b>true</b></i> si l'addresse mail est conforme au <b>ReGex</b>, sinon <i><b>false</b></i>
     */
    public static boolean verifNomUtilisateur(String nom) {
        boolean resultat = true;
        
        if (Pattern.matches("^[a-zA-Zéèêë\\ -]{2,}$", nom) == false) {
            resultat = false;
        }
        
        return resultat;
    }
    
    public static boolean verifMotDePasse(String motDePasse) {
        boolean resultat = false;
        
        int testLettreMinuscule = 0;
        int testLettreMajuscule = 0;
        int testChiffre = 0;
        int testCaractereSpe = 0;
        
        for (int i = 0; i < motDePasse.length(); i++) {
            char c = motDePasse.charAt(i);
            if (Character.isLowerCase(c)) {
                testLettreMinuscule++;
            } else if (Character.isUpperCase(c)) {
                testLettreMajuscule++;
            } else if (Character.isDigit(c)) {
                testChiffre++;
            } else if (c >= 33 && c <= 46 ||c == 64) {
                testCaractereSpe++;
            }
        }
        
        if (motDePasse.length() >= 12 && testLettreMinuscule >= 1 && testLettreMajuscule >= 1 && testChiffre >= 1 && testCaractereSpe >= 1) {
            resultat = true;
        }
        
        return resultat;
    }
    
}
