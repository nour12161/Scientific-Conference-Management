package projetjava2;

import Controller.connexion; 
import java.io.IOException;

import java.net.URL;
import java.sql.Connection;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
 import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import java.sql.SQLException;
import java.sql.PreparedStatement;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;  
import javafx.stage.Stage;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author LENOVO
 */
public class loginController implements Initializable {
    
    
    private Connection connection; // La connexion à la base de données

    // Méthode pour définir la connexion depuis le main
    public void setConnection(Connection connection) {
        this.connection = connection;
    }
    
    
     @FXML
    private TextField mdp;

    @FXML
    private TextField pseudo;
    
     @FXML
    private Button btnLogin;

   @FXML
void login(ActionEvent event) {
    if (pseudo.getText().equals("admin") && mdp.getText().equals("admin")) {
        try {
            // Charger le fichier FXML
            FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLDocument.fxml"));
            Parent root  = loader.load();
             FXMLDocumentController controller = loader.getController();
            // Configurer la scène avec le BorderPane chargé
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();

            // Fermer la fenêtre de login
            Stage loginStage = (Stage) btnLogin.getScene().getWindow();
            loginStage.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    } else {
        // Afficher une boîte de dialogue d'erreur
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Erreur");
        alert.setHeaderText("Impossible de se connecter");
        alert.setContentText("Pseudo ou mot de passe incorrect");
        alert.showAndWait();
        clearFields();
    }
}


    
    private void clearFields() {
        pseudo.clear();
        mdp.clear();
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
    
    
    
}
