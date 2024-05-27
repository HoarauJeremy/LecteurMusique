package com.example.lecteurmusique.Controllers;

import com.example.lecteurmusique.Models.Musique;
import com.example.lecteurmusique.Models.NombreEcoute;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class Top50Controller implements Initializable {

    @FXML
    private TableView<NombreEcoute> tableuTop50;

    @FXML
    private TableColumn<NombreEcoute, Integer> rang, nombreEcoute;
    @FXML
    private TableColumn<NombreEcoute, String> titre, artiste;

    private ObservableList<NombreEcoute> top50 = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        /*rang.setCellValueFactory(new PropertyValueFactory<>("Rang"));
        titre.setCellValueFactory(new PropertyValueFactory<>("Musique"));
        artiste.setCellValueFactory(new PropertyValueFactory<>("Artiste"));
        nombreEcoute.setCellValueFactory(new PropertyValueFactory<>("Nombre d'ecoute"));*/

        chargerTop50();
    }

    private void chargerTop50() {
        try {
            ResultSet resultSet = NombreEcoute.recupererMusique();


            while (resultSet.next()) System.out.println(resultSet.getString("nom"));

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

}
