/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package projetjava2.models;

/**
 *
 * @author LENOVO
 */
public class Participant extends Personne {
    private ModePaiement modePaiement;

    // Constructeur
    public Participant(String nom, String email, String institution, ModePaiement modePaiement) {
        super(nom, email, institution);
        this.modePaiement = modePaiement;
    }

    // Getter et Setter pour l'attribut spécifique
    public ModePaiement getModePaiement() {
        return modePaiement;
    }

    public void setModePaiement(ModePaiement modePaiement) {
        this.modePaiement = modePaiement;
    }
}

