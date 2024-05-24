package com.example.lecteurmusique;

import java.util.regex.Pattern;

public class VerifierDonnees {
    /**
     * Verifie si l'addresse mail saisie par l'utilisateur est conforme au <b>Regex</b>.
     *
     * @param courriel Saisie par l'utilisateur
     * @return <i><b>true</b></i> si l'addresse mail est conforme au <b>ReGex</b>, sinon <i><b>false</b></i>
     */
    public static boolean verifierEmail(String courriel) {
        boolean resultat = true;

        if (Pattern.matches("^[\\w\\.=-]+@[\\w\\.-]+\\.[\\w]{2,3}$", courriel) == false) {
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
    public static boolean verifierNomUtilisateur(String nom) {
        boolean resultat = true;

        if (Pattern.matches("^[a-zA-Zéèêë\\ -]{2,}$", nom) == false) {
            resultat = false;
        }

        return resultat;
    }

    /**
     *
     * @param motDePasse
     * @return
     */
    public static boolean verifierMotDePasse(String motDePasse) {
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
