/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package projetjava2;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 *
 * @author LENOVO
 */
public class loginSCController implements Initializable{
    
    
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
    if (pseudo.getText().equals("sc") && mdp.getText().equals("sc")) {
        try {
            // Charger le fichier FXML
            FXMLLoader loader = new FXMLLoader(getClass().getResource("interfaceComSc.fxml"));
            Parent root  = loader.load();
             interfaceComScController controller = loader.getController();
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
        Alert alert = new Alert(Alert.AlertType.ERROR);
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
