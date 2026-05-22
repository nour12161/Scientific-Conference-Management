package projetjava2;

import Controller.connexion; 

import java.sql.Connection;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;  
import javafx.stage.Stage;

public class ProjetJava2 extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        // Établir la connexion à la base de données en utilisant la classe connexion
        connexion connexion1 = new connexion();
        Connection connexion = connexion1.obtenirConnexion();
        // Charger le fichier FXML
        FXMLLoader loader = new FXMLLoader(getClass().getResource("loginAdmin.fxml"));
        Parent root = loader.load();

        // Passer la connexion à votre contrôleur FXML si nécessaire
        loginController controller = loader.getController();
        controller.setConnection(connexion); // Exemple : méthode setter dans votre contrôleur
        // Configurer la scène  
        Scene scene = new Scene(root);

        // Définir la scène principale
        stage.setScene(scene);
        stage.show();    
    }
    public static void main(String[] args) {
        launch(args);
    }
}
    