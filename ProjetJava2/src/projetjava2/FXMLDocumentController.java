/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXML2.java to edit this template
 */
package projetjava2;

import Controller.connexion;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.Button;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import java.sql.Connection; // Importez la classe Connection



import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 *
 * @author LENOVO
 */
public class FXMLDocumentController implements Initializable {
    
    
        @FXML
    private Button btnAjouterArticle;
    @FXML
    private Button btnAffecterArticle;
    @FXML
    private Button btnajoutconf;
    
      @FXML
    private Button btnGererInvites;
     
     @FXML
    private Button BtnAjouterInvite;
    @FXML
private AnchorPane mainAnchorPane;
    
    
    private Connection connection; // La connexion à la base de données

    // Méthode pour définir la connexion depuis le main
    public void setConnection(Connection connection) {
        this.connection = connection;
    }
    
    
    public Connection getConnection()
    {
        return this.connection;
    }

    
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
    
    
    
    @FXML
    void afficherAffecterArticle(ActionEvent event) {
        try {
            // Charger le fichier FXML contenant l'AnchorPane à inclure
            
            FXMLLoader loader = new FXMLLoader(getClass().getResource("affecterArticle.fxml"));
AnchorPane ajoutPane = loader.load();
            
            
      

            // Ajouter l'AnchorPane chargé à votre AnchorPane principal
            mainAnchorPane.getChildren().setAll(ajoutPane);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    
    @FXML
    void afficherAjout(MouseEvent event) {
        try {
            // Charger le fichier FXML contenant l'AnchorPane à inclure
            
            FXMLLoader loader = new FXMLLoader(getClass().getResource("ajouterConf.fxml"));
AnchorPane ajoutPane = loader.load();
            
            
      

            // Ajouter l'AnchorPane chargé à votre AnchorPane principal
            mainAnchorPane.getChildren().setAll(ajoutPane);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    @FXML
    void afficherGestionInvites(ActionEvent event) {
        try {
            // Charger le fichier FXML contenant l'AnchorPane à inclure
            
            FXMLLoader loader = new FXMLLoader(getClass().getResource("gererInvites.fxml"));
AnchorPane ajoutPane = loader.load();
            
            
      

            // Ajouter l'AnchorPane chargé à votre AnchorPane principal
            mainAnchorPane.getChildren().setAll(ajoutPane);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    
    
    @FXML
    void AjouterInvite(ActionEvent event) {
        try {
            // Charger le fichier FXML contenant l'AnchorPane à inclure
            
            FXMLLoader loader = new FXMLLoader(getClass().getResource("ajouterInvitefxml.fxml"));
AnchorPane ajoutPane = loader.load();
            
            
      

            // Ajouter l'AnchorPane chargé à votre AnchorPane principal
            mainAnchorPane.getChildren().setAll(ajoutPane);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    
    
     @FXML
    void testFonction(MouseEvent event) {
          System.out.println("aaaaaaaaaa");
    }
    
     @FXML
    void test(MouseEvent event) {
           btnajoutconf.setText("Ajouter une conférence");
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
