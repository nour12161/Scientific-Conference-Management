/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package Controller;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class connexion {
    public static Connection obtenirConnexion() throws SQLException {
    try {
        Class.forName("com.mysql.cj.jdbc.Driver");
    } catch (ClassNotFoundException e) {
        e.printStackTrace();
        throw new SQLException("Driver MySQL introuvable");
    }
    String url = "jdbc:mysql://localhost/projetpoo3";
    String utilisateur = "root";
    String motDePasse = "";
    
    Connection connexion = DriverManager.getConnection(url, utilisateur, motDePasse);
    System.out.println("Connexion à la base réussie");
    return connexion;
}

}
