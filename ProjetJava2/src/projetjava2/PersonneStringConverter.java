/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package projetjava2;
import projetjava2.models.Personne;
/**
 *
 * @author LENOVO
 */
import javafx.util.StringConverter;

public class PersonneStringConverter extends StringConverter<Personne> {

    @Override
    public String toString(Personne personne) {
        if (personne == null) {
            return null;
        }
        return personne.getNom() + " - " + personne.getMail() + " - " + personne.getInstitution();
    }

    @Override
    public Personne fromString(String string) {
        // Ne pas implémenter, car cela ne sera pas utilisé pour l'édition
        return null;
    }
}
