/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package projetjava2.models;

/**
 *
 * @author LENOVO
 */
public class ConferencierInvite extends Personne {
    private String pays;

    // Constructeur
    public ConferencierInvite(String nom, String mail, String institution, String pays) {
        super(nom, mail, institution);
        this.pays = pays;
    }

    // Getter et Setter pour l'attribut spécifique
    public String getPays() {
        return pays;
    }

    public void setPays(String pays) {
        this.pays = pays;
    }
}
