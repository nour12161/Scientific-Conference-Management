

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package projetjava2.models;

/**
 *
 * @author LENOVO
 */
import java.util.List;

public class Article {
    private String titre;
    private String auteurs;
    private String cheminPdf;
    private String statut;

    public Article(String titre, String auteurs, String cheminPdf, String statut) {
        this.titre = titre;
        this.auteurs = auteurs;
        this.cheminPdf = cheminPdf;
        this.statut = statut;
    }

    public String getTitre() {
        return titre;
    }

    public String getAuteurs() {
        return auteurs;
    }

    public String getCheminPdf() {
        return cheminPdf;
    }

    public String getStatut() {
        return statut;
    }
}

