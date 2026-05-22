package projetjava2;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.Alert.AlertType;
import projetjava2.models.Article;
import projetjava2.models.Personne;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

public class affecterArticleController implements Initializable {

    @FXML
    private TableColumn<Personne, String> nomColumn;

    @FXML
    private TableColumn<Personne, String> institutColumn;

    @FXML
    private TableColumn<Personne, String> mailColumn;

    @FXML
    private TableView<Personne> tableViewC;

    @FXML
    private TableColumn<Article, String> titreColumn;

    @FXML
    private TableColumn<Article, String> auteursColumn;

    @FXML
    private TableColumn<Article, String> cheminPdfColumn;

    @FXML
    private TableColumn<Article, String> statutColumn;

    @FXML
    private TableView<Article> tableViewA;

    @FXML
    private TextField articleField;

    @FXML
    private Button btnaffecterInvite;

    @FXML
    private TextField membreField;

    private Connection getConnection() throws SQLException {
        String url = "jdbc:mysql://localhost/projetpoo3";
        String username = "root";
        String password = "";
        return DriverManager.getConnection(url, username, password);
    }

    @FXML
    private void fillTableView() {
        try (Connection connection = getConnection()) {
            if (connection != null) {
                String query = "SELECT nom, email, institution FROM comitescientifique";
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(query);

                ObservableList<Personne> personnes = FXCollections.observableArrayList();
                while (resultSet.next()) {
                    String nom = resultSet.getString("nom");
                    String mail = resultSet.getString("email");
                    String institution = resultSet.getString("institution");

                    personnes.add(new Personne(nom, mail, institution) {});
                }

                // Associer les colonnes avec les attributs de Personne
                nomColumn.setCellValueFactory(new PropertyValueFactory<>("nom"));
                mailColumn.setCellValueFactory(new PropertyValueFactory<>("mail"));
                institutColumn.setCellValueFactory(new PropertyValueFactory<>("institution"));

                // Remplir le TableView avec les données
                tableViewC.setItems(personnes);

                tableViewC.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
                    if (newSelection != null) {
                        // Remplir les champs avec les valeurs de la ligne sélectionnée
                        membreField.setText(newSelection.getNom());
                    }
                });
            }
        } catch (SQLException e) {
            showErrorAlert("Erreur lors de la récupération des membres : " + e.getMessage());
        }
    }

    @FXML
    private void fillTableView2() {
        try (Connection connection = getConnection()) {
            if (connection != null) {
                String query = "SELECT titre, auteurs, chemin_pdf, statut FROM article";
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(query);

                ObservableList<Article> articles = FXCollections.observableArrayList();
                while (resultSet.next()) {
                    String titre = resultSet.getString("titre");
                    String auteurs = resultSet.getString("auteurs");
                    String cheminPdf = resultSet.getString("chemin_pdf");
                    String statut = resultSet.getString("statut");

                    articles.add(new Article(titre, auteurs, cheminPdf, statut));
                }

                // Associer les colonnes avec les attributs de Article
                titreColumn.setCellValueFactory(new PropertyValueFactory<>("titre"));
                auteursColumn.setCellValueFactory(new PropertyValueFactory<>("auteurs"));
                cheminPdfColumn.setCellValueFactory(new PropertyValueFactory<>("cheminPdf"));
                statutColumn.setCellValueFactory(new PropertyValueFactory<>("statut"));

                // Remplir le TableView avec les données d'articles
                tableViewA.setItems(articles);
            }
        } catch (SQLException e) {
            showErrorAlert("Erreur lors de la récupération des articles : " + e.getMessage());
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Initialiser les colonnes avec les valeurs de Personne
        nomColumn.setCellValueFactory(new PropertyValueFactory<>("nom"));
        mailColumn.setCellValueFactory(new PropertyValueFactory<>("mail"));
        institutColumn.setCellValueFactory(new PropertyValueFactory<>("institution"));

        // Initialiser les colonnes avec les valeurs d'Article
        titreColumn.setCellValueFactory(new PropertyValueFactory<>("titre"));
        auteursColumn.setCellValueFactory(new PropertyValueFactory<>("auteurs"));
        cheminPdfColumn.setCellValueFactory(new PropertyValueFactory<>("cheminPdf"));
        statutColumn.setCellValueFactory(new PropertyValueFactory<>("statut"));

        // Remplir les TableViews avec les données de la base de données
        fillTableView();
        fillTableView2();
    }

    private void showErrorAlert(String message) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Erreur");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
