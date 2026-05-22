package projetjava2;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import projetjava2.models.Personne;

import java.io.File;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.cell.TextFieldListCell;


public class soumettreArticleController implements Initializable {
    
    
    private Connection connection;
    
    @FXML
    private TextField titre;
    @FXML
    private ListView<String> listeConference;
    
    @FXML
    private Button btnAjouterAuteur;

    @FXML
    private Button btnAjouterPdf;

    @FXML
    private TextArea description;

    @FXML
    private ListView<Personne> listeAuteurs;

    @FXML
    private TextField mailAuteur;

    @FXML
    private TextField nomAuteur;

    @FXML
    private TextField institutAuteur;

    @FXML
    private TextField pathField;

    @FXML
    private Button soumettre;

    private List<String> pdfFiles; // Liste pour stocker les chemins des fichiers PDF sélectionnés

    @FXML
    public void getPdf() {
        pdfFiles = new ArrayList<>(); // Initialiser la liste des fichiers PDF sélectionnés
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choisir des fichiers PDF");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Fichiers PDF", "*.pdf") // Filtre pour les fichiers PDF
        );

        // Ouvrir la boîte de dialogue de sélection de fichiers
        List<File> selectedFiles = fileChooser.showOpenMultipleDialog(new Stage());

        if (selectedFiles != null && !selectedFiles.isEmpty()) {
            for (File selectedFile : selectedFiles) {
                String pdfPath = selectedFile.getAbsolutePath();
                pdfFiles.add(pdfPath); // Ajouter le chemin du fichier PDF à la liste
            }
            // Mettre à jour le champ de texte avec les chemins des fichiers PDF sélectionnés
            StringBuilder paths = new StringBuilder();
            for (String path : pdfFiles) {
                paths.append(path).append("\n");
            }
            pathField.setText(paths.toString());
        }
    }

    @FXML
    public void ajouterAuteur() {
        // Récupérer les valeurs des champs texte
        String nom = nomAuteur.getText();
        String mail = mailAuteur.getText();
        String institut = institutAuteur.getText();
        // Vérifier si les champs sont vides
        if (nom.isEmpty() || mail.isEmpty() || institut.isEmpty()) {
            // Afficher un message d'erreur si l'un des champs est vide
            System.out.println("Veuillez remplir tous les champs.");
            return; // Arrêter l'exécution de la méthode
        }

        // Construire une représentation de l'auteur avec les valeurs des champs
        Personne nouvellePersonne;
        nouvellePersonne = new Personne(nom, mail, institut) {};
        nouvellePersonne.toString();

        // Ajouter la personne à la liste des auteurs
        listeAuteurs.getItems().add(nouvellePersonne);

        // Effacer les champs texte après l'ajout de l'auteur
        nomAuteur.clear();
        mailAuteur.clear();
        institutAuteur.clear();
    }

       public void fillListViewWithConferenceTitles() {
        ObservableList<String> conferenceTitles = FXCollections.observableArrayList();

        try (java.sql.Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/projetpoo3", "root", "")) {
            if (connection != null) {
                String query = "SELECT titre FROM conference";
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(query);

                while (resultSet.next()) {
                    String titre = resultSet.getString("titre");
                    conferenceTitles.add(titre);
                }

                // Peupler la ListView avec les titres des conférences
                listeConference.setItems(conferenceTitles);
            }
        } catch (SQLException e) {
            System.out.println("Erreur lors du chargement des titres de conférence : " + e.getMessage());
        }
    }

    
    @FXML
public void soumettreArticle() {
    String conference = listeConference.getSelectionModel().getSelectedItem();
    String titreArticle = titre.getText();
    String articleDescription = description.getText();
    List<Personne> auteurs = listeAuteurs.getItems();

    // Vérifier si tous les champs requis sont renseignés
    if (conference == null || titreArticle.isEmpty() || articleDescription.isEmpty() || auteurs.isEmpty() || pdfFiles.isEmpty()) {
        // Afficher un message d'erreur si des champs obligatoires sont manquants
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erreur");
        alert.setHeaderText("Champs obligatoires manquants");
        alert.setContentText("Veuillez remplir tous les champs pour soumettre l'article.");
        alert.showAndWait();
        return;
    }

    try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/projetpoo3", "root", "")) {
        // Récupérer l'ID de la conférence
        int conferenceId = getConferenceId(conference, connection);

        // Convertir la liste des auteurs en une chaîne textuelle
        StringBuilder auteursText = new StringBuilder();
        for (Personne auteur : auteurs) {
            auteursText.append(auteur.getNom()).append(", "); // Ajouter le nom de l'auteur à la chaîne
        }
        // Supprimer la virgule en trop à la fin
        if (auteursText.length() > 0) {
            auteursText.setLength(auteursText.length() - 2);
        }

        // Préparer la requête SQL pour insérer l'article
        String insertArticleQuery = "INSERT INTO article (titre, auteurs, descriptionArticle, chemin_pdf, conference_id) VALUES (?, ?, ?, ?, ?)";
        PreparedStatement articleStatement = connection.prepareStatement(insertArticleQuery);
        articleStatement.setString(1, titreArticle);
        articleStatement.setString(2, auteursText.toString());
        articleStatement.setString(3, articleDescription);
        articleStatement.setString(4, pdfFiles.get(0)); // Supposons que vous souhaitez insérer le premier fichier PDF de la liste
        articleStatement.setInt(5, conferenceId);

        // Exécuter la requête pour insérer l'article
        int rowsAffected = articleStatement.executeUpdate();
        if (rowsAffected > 0) {
            System.out.println("Article inséré avec succès dans la base de données.");

            // Effacer les champs après la soumission de l'article
            clearFields();
        } else {
            System.out.println("Erreur lors de l'insertion de l'article.");
        }
    } catch (SQLException e) {
        System.out.println("Erreur SQL lors de l'insertion de l'article : " + e.getMessage());
    }
}

// Méthode pour récupérer l'ID de la conférence à partir du titre
private int getConferenceId(String conferenceTitle, Connection connection) throws SQLException {
    int conferenceId = -1;
    String query = "SELECT conference_id FROM conference WHERE titre = ?";
    try (PreparedStatement statement = connection.prepareStatement(query)) {
        statement.setString(1, conferenceTitle);
        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()) {
            conferenceId = resultSet.getInt("conference_id");
        }
    }
    return conferenceId;
}




    private void clearFields() {
        // Effacer les champs texte après la soumission de l'article
        titre.clear();
        description.clear();
        listeAuteurs.getItems().clear();
        pdfFiles.clear();
        pathField.clear();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        try {
            // Établir la connexion à la base de données
            connection = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/projetpoo3", "root", "");

            // Charger les titres des conférences dans la ListView
            fillListViewWithConferenceTitles();
        } catch (SQLException e) {
            e.printStackTrace();
            // Gérez l'exception ici
        }
    
        
        listeAuteurs.setCellFactory(param -> new TextFieldListCell<>(new PersonneStringConverter()));
        
        
        
    }
}
