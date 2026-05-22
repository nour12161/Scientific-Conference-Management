/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package projetjava2;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 *
 * @author LENOVO
 */
public class interfaceAuteurController implements Initializable {
    
     private Connection getConnection() throws SQLException {
        String url = "jdbc:mysql://localhost/projetpoo3";
        String username = "root";
        String password = "";
        return DriverManager.getConnection(url, username, password);
    }
    
     
     @FXML
    private Button bntAjouter;

    @FXML
    private TextField institutAuteur;

    @FXML
    private TextField mailAuteur;

    @FXML
    private TextField nomAuteur;


    @FXML
    void ajouterAuteur(ActionEvent event) {

    }
     
     
      @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        
    }
}
