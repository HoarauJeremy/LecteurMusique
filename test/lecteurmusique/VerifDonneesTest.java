/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package lecteurmusique;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author jerem
 */
public class VerifDonneesTest {
    
    public VerifDonneesTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of verifEmail method, of class VerifDonnees.
     */
    @Test
    public void testVerifEmailValide() {
        System.out.println("verifEmail Valide");
        String email = "exem-ple.Ex3@example.com";
        boolean expResult = true;
        boolean result = VerifDonnees.verifEmail(email);
        assertEquals(expResult, result);
    }

    /**
     * Test of verifEmail method, of class VerifDonnees.
     */
    @Test
    public void testVerifEmailInvalide() {
        System.out.println("verifEmail Invalide");
        String email = "fedvsvs4=56@kofdpb.c25om";
        boolean expResult = false;
        boolean result = VerifDonnees.verifEmail(email);
        assertEquals(expResult, result);
    }

    /**
     * Test of valid verifNomUtilisateur method, of class VerifDonnees.
     */
    @Test
    public void testVerifNomUtilisateurValide() {
        System.out.println("verifNomUtilisateur Valide");
        String nom = "jean-Pierre";
        boolean expResult = true;
        boolean result = VerifDonnees.verifNomUtilisateur(nom);
        assertEquals(expResult, result);
    }

    /**
     * Test of an invalid verifNomUtilisateur method, of class VerifDonnees.
     */
    @Test
    public void testVerifNomUtilisateurInvalid() {
        System.out.println("verifNomUtilisateur Invalide");
        String nom = "John-Doe35";
        boolean expResult = false;
        boolean result = VerifDonnees.verifNomUtilisateur(nom);
        assertEquals(expResult, result);
    }
    
}
