/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package lecteurmusique.Model;

import java.util.ArrayList;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

/**
 *
 * @author Jérémy Hoarau
 * Created: 2 mai 2024
 */
public class Lecteur {
    private static Lecteur instance;
    private ArrayList<Musique> playlist;
    private MediaPlayer mediaPlayer;
    private int indexActuel;

    private Lecteur() {
        playlist = new ArrayList<>();
        indexActuel = 0;
    }

    public static Lecteur getInstance() {
        if (instance == null) {
            instance = new Lecteur();
        }
        return instance;
    }

    public void chargerPlaylist(ArrayList<Musique> musiques) {
        playlist.clear();
        playlist.addAll(musiques);
    }

    public void jouer() {
        if (mediaPlayer != null) {
            mediaPlayer.stop();
        }
        Musique musiqueActuelle = playlist.get(indexActuel);
        Media media = new Media(musiqueActuelle.getLien());
        mediaPlayer = new MediaPlayer(media);
        mediaPlayer.play();
    }

    public void pause() {
        if (mediaPlayer != null) {
            mediaPlayer.pause();
        }
    }

    public void suivant() {
        if (mediaPlayer != null) {
            mediaPlayer.stop();
        }
        indexActuel = (indexActuel + 1) % playlist.size();
        jouer();
    }

    public void precedent() {
        if (mediaPlayer != null) {
            mediaPlayer.stop();
        }
        indexActuel = (indexActuel - 1 + playlist.size()) % playlist.size();
        jouer();
    }
    
    public ArrayList<Musique> getPlaylist() {
        return playlist;
    }
}
