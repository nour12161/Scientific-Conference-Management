package projetjava2;


import Controller.connexion; 
import java.io.IOException;
import projetjava2.models.Personne;
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
import java.util.ArrayList;
import java.util.List;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.collections.ObservableList;
import javafx.collections.FXCollections;
import java.sql.ResultSet;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.collections.ObservableList;
import javafx.collections.FXCollections;
import java.sql.DriverManager;
import java.sql.Statement;

public class ajouterInviteController implements Initializable {

    @FXML
    private TextField nomField;

    @FXML
    private TextField emailField;

    @FXML
    private TextField institutionField;

    @FXML
    private Button btnEnregistrerInvite;

    @FXML
    void enregistrerInvite(ActionEvent event) {
        String nom = nomField.getText();
        String email = emailField.getText();
        String institution = institutionField.getText();
        if (nom.isEmpty() || email.isEmpty() || institution.isEmpty()) {
            // Afficher une boîte de dialogue d'erreur
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText("Champs obligatoires manquants");
            alert.setContentText("Veuillez remplir tous les champs.");
            alert.showAndWait();
        } else {
            // Tous les champs sont remplis, insérer dans la table conferencierInvite
            try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/projetpoo3", "root", "")) {
                String query = "INSERT INTO conferencierinvite (nom, email, institution) VALUES (?, ?, ?)";
                try (PreparedStatement statement = connection.prepareStatement(query)) {
                    statement.setString(1, nom);
                    statement.setString(2, email);
                    statement.setString(3, institution);

                    int rowsInserted = statement.executeUpdate();
                    if (rowsInserted > 0) {
                        // Afficher un message de succès
                        Alert successAlert = new Alert(AlertType.INFORMATION);
                        successAlert.setTitle("Succès");
                        successAlert.setHeaderText(null);
                        successAlert.setContentText("Invité enregistré avec succès.");
                        successAlert.showAndWait();

                        // Effacer les champs après l'enregistrement
                        clearFields();
                    }
                }
            } catch (SQLException e) {
                System.out.println("Erreur lors de l'enregistrement de l'invité : " + e.getMessage());
            }
        }
    }

    private void clearFields() {
        nomField.clear();
        emailField.clear();
        institutionField.clear();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
}
