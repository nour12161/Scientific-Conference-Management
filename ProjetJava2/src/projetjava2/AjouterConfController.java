package projetjava2;
import Controller.connexion; 

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
import java.sql.DriverManager;



public class AjouterConfController implements Initializable {

    
    private Connection connection;

    // Méthode pour définir la connexion
    public void setConnection(Connection connection) {
        this.connection = connection;
    }
    
    public Connection getConnection()
    {
        return this.connection;
    }
    
    @FXML
    private TextField titreField;

    @FXML
    private TextField lieuField;

    @FXML
    private TextField presidentField;

    @FXML
    private DatePicker dateDebutPicker;

    @FXML
    private DatePicker dateFinPicker;

    @FXML
    private DatePicker dateLimiteInscriptionPicker;

    @FXML
    private DatePicker dateLimiteSoumissionPicker;

    @FXML
    private ListView<String> conferenciersInvitesList;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

   

@FXML
private void enregistrerConference() {
    // Récupérer les valeurs des champs
    String titre = titreField.getText();
    String lieu = lieuField.getText();
    String dateDebut = dateDebutPicker.getValue() != null ? dateDebutPicker.getValue().toString() : null;
    String dateFin = dateFinPicker.getValue() != null ? dateFinPicker.getValue().toString() : null;
    String dateLimiteInscription = dateLimiteInscriptionPicker.getValue() != null ? dateLimiteInscriptionPicker.getValue().toString() : null;
    String dateLimiteSoumission = dateLimiteSoumissionPicker.getValue() != null ? dateLimiteSoumissionPicker.getValue().toString() : null;

    // Vérifier les champs requis
    if (titre.isEmpty() || lieu.isEmpty() || dateDebut == null || dateFin == null || dateLimiteInscription == null || dateLimiteSoumission == null) {
        // Afficher une boîte de dialogue d'erreur
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Erreur");
        alert.setHeaderText("Champs Obligatoires Manquants");
        alert.setContentText("Veuillez remplir tous les champs obligatoires.");

        // Afficher la boîte de dialogue et attendre la réponse de l'utilisateur
        alert.showAndWait();
        return; // Arrêter l'exécution si des champs requis sont manquants
    }

    // Enregistrer la conférence dans la base de données
    try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/projetpoo3", "root", "")) {
        String query = "INSERT INTO conference (titre, lieu, date_debut, date_fin, date_limite_inscription, date_limite_soumission) "
                     + "VALUES (?, ?, ?, ?, ?, ?)";

        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, titre);
        statement.setString(2, lieu);
        statement.setString(3, dateDebut);
        statement.setString(4, dateFin);
        statement.setString(5, dateLimiteInscription);
        statement.setString(6, dateLimiteSoumission);

        int rowsInserted = statement.executeUpdate();
        if (rowsInserted > 0) {
            // Afficher un message de succès
            Alert successAlert = new Alert(AlertType.INFORMATION);
            successAlert.setTitle("Succès");
            successAlert.setHeaderText(null);
            successAlert.setContentText("Conférence enregistrée avec succès.");
            successAlert.showAndWait();

            // Effacer les champs après l'enregistrement
            clearFields();
        }
    } catch (SQLException e) {
        System.out.println("Erreur lors de l'enregistrement de la conférence : " + e.getMessage());
    }
}

private void clearFields() {
    titreField.clear();
    lieuField.clear();
    dateDebutPicker.setValue(null);
    dateFinPicker.setValue(null);
    dateLimiteInscriptionPicker.setValue(null);
    dateLimiteSoumissionPicker.setValue(null);
}



    
}
