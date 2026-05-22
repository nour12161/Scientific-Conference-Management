/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
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


/**
 *
 * @author LENOVO
 */
public class gererInviteController implements Initializable {
    
     private Connection getConnection() throws SQLException {
        String url = "jdbc:mysql://localhost/projetpoo3";
        String username = "root";
        String password = "";
        return DriverManager.getConnection(url, username, password);
    }
    
    
    @FXML
    private TextField institution;
    
    @FXML
    private TextField mail;
    
    @FXML
    private TextField name;
     
     
    @FXML
    private TableColumn<Personne, String> institutionColumn;

    @FXML
    private TableColumn<Personne, String> mailColumn;

    @FXML
    private TableColumn<Personne, String> nomColumn;

    @FXML
    private TableView<Personne> tableView;
    
    
    
    
    
    
    
      @FXML
    private void fillTableView() {
        try (Connection connection = getConnection()) {
            if (connection != null) {
                String query = "SELECT nom, email, institution FROM conferencierInvite";
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
                institutionColumn.setCellValueFactory(new PropertyValueFactory<>("institution"));

                // Remplir le TableView avec les données
                tableView.setItems(personnes);
                
                tableView.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                // Remplir les champs avec les valeurs de la ligne sélectionnée
                name.setText(newSelection.getNom());
                mail.setText(newSelection.getMail());
                institution.setText(newSelection.getInstitution());
            }
        });         
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    
    
    public void supprimerInvite() {
    // Récupérer l'invité sélectionné dans la TableView
    Personne personne = tableView.getSelectionModel().getSelectedItem();
    
    if (personne != null) {
        // Supprimer l'invité de la base de données
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement("DELETE FROM conferencierInvite WHERE nom = ?")) {
            statement.setString(1, personne.getNom());
            int rowsDeleted = statement.executeUpdate();
            
            if (rowsDeleted > 0) {
                // Rafraîchir les données de la TableView après la suppression
                tableView.getItems().remove(personne);
                Alert successAlert = new Alert(AlertType.INFORMATION);
                        successAlert.setTitle("Succès");
                        successAlert.setHeaderText(null);
                        successAlert.setContentText("Invité supprimé avec succès.");
                        successAlert.showAndWait();
            } else {
                Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText("erreur lors de la suppression");
            alert.setContentText("Veuillez vérifier tous les champs.");
            alert.showAndWait();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    } else {
        // Aucun invité sélectionné, afficher un message d'erreur ou une alerte
         Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText("erreur lors de la suppression");
            alert.setContentText("Veuillez vérifier tous les champs.");
            alert.showAndWait();
    }
}


    
    
    public void modifierInvite() {
    // Récupérer l'invité sélectionné dans la TableView
    Personne personne = tableView.getSelectionModel().getSelectedItem();
    if (personne != null) {
        // Modifier les données de l'invité dans la base de données
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement("UPDATE conferencierInvite SET email = ?, institution = ? WHERE nom = ?")) {
            
            // Récupérer les nouvelles valeurs des champs
            statement.setString(1, mail.getText());
            statement.setString(2, institution.getText());
            statement.setString(3, personne.getNom());
            
            int rowsUpdated = statement.executeUpdate();
            
            if (rowsUpdated > 0) {
                // Rafraîchir les données de la TableView après la modification
                personne.setMail(mail.getText());
                personne.setInstitution(institution.getText());
                tableView.refresh();
                Alert successAlert = new Alert(AlertType.INFORMATION);
                        successAlert.setTitle("Succès");
                        successAlert.setHeaderText(null);
                        successAlert.setContentText("Invité modifié avec succès.");
                        successAlert.showAndWait();
            } else {
                Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText("erreur lors de la modification");
            alert.setContentText("Veuillez vérifier tous les champs.");
            alert.showAndWait();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    } else {
        // Aucun invité sélectionné, afficher un message d'erreur ou une alerte
         Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText("erreur lors de la suppression");
            alert.setContentText("Veuillez vérifier tous les champs.");
            alert.showAndWait();
    }
}


    
    
     @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        nomColumn.setCellValueFactory(new PropertyValueFactory<>("nom"));
        mailColumn.setCellValueFactory(new PropertyValueFactory<>("mail"));
        institutionColumn.setCellValueFactory(new PropertyValueFactory<>("institution"));

        // Remplir le TableView avec les données de la base de données
        fillTableView();
        
    }
    
    
}
