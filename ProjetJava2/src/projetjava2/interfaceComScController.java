/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package projetjava2;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;


/**
 *
 * @author LENOVO
 */
public class interfaceComScController implements Initializable {
    
    
@FXML
private AnchorPane mainAnchorPane;  
    
    @FXML
    void afficherSoumettreArticle(ActionEvent event) {
        try {
            // Charger le fichier FXML contenant l'AnchorPane à inclure
            
            FXMLLoader loader = new FXMLLoader(getClass().getResource("soumettreArticle.fxml"));
AnchorPane ajoutPane = loader.load();
            
            // Ajouter l'AnchorPane chargé à votre AnchorPane principal
            mainAnchorPane.getChildren().setAll(ajoutPane);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
    
}
