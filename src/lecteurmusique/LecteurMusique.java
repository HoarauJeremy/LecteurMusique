/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package lecteurmusique;

/**
 *
 * @author jerem
 */
public class LecteurMusique {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        Connexion cnx = new Connexion();
        cnx.getSon();
    
        Connexion cnx2 = new Connexion("jeremy", "jeremy");
        cnx2.getArtiste();
        
    }
    
}
