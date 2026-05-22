/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package projetjava2.models;

/**
 *
 * @author LENOVO
 */

import projetjava2.models.Article;
import java.util.Date;
import java.util.List;

public class Conference {
    private String titre;
    private President President;
    private Date dateDebut;
    private Date dateFin;
    private String lieu;
    private ComiteOrganisateur comiteOrganisateur;
    private ComiteScientifique comiteScientifique;
    private List<ConferencierInvite> conferenciersInvites;
    private List<Article> articles;
    private List<Participant> participants;
    private double fraisInscription;
    private Date dateLimiteSoumission;
    private Date dateLimiteInscription;

    // Constructeur
    public Conference(String titre,President President, Date dateDebut, Date dateFin, String lieu, 
                      ComiteOrganisateur comiteOrganisateur, ComiteScientifique comiteScientifique, 
                      List<ConferencierInvite> conferenciersInvites, List<Article> articles, 
                      List<Participant> participants, double fraisInscription, 
                      Date dateLimiteSoumission, Date dateLimiteInscription) {
        this.titre = titre;
        this.President = President;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.lieu = lieu;
        this.comiteOrganisateur = comiteOrganisateur;
        this.comiteScientifique = comiteScientifique;
        this.conferenciersInvites = conferenciersInvites;
        this.articles = articles;
        this.participants = participants;
        this.fraisInscription = fraisInscription;
        this.dateLimiteSoumission = dateLimiteSoumission;
        this.dateLimiteInscription = dateLimiteInscription;
    }

    // Getters et Setters
    public String getTitre() {
        return titre;
    }
    public President getPresident()
    {
        return President;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }
    public void setPresident(President President)
    {
        this.President = President;
    }

    public Date getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(Date dateDebut) {
        this.dateDebut = dateDebut;
    }

    public Date getDateFin() {
        return dateFin;
    }

    public void setDateFin(Date dateFin) {
        this.dateFin = dateFin;
    }

    public String getLieu() {
        return lieu;
    }

    public void setLieu(String lieu) {
        this.lieu = lieu;
    }

    public ComiteOrganisateur getComiteOrganisateur() {
        return comiteOrganisateur;
    }

    public void setComiteOrganisateur(ComiteOrganisateur comiteOrganisateur) {
        this.comiteOrganisateur = comiteOrganisateur;
    }

    public ComiteScientifique getComiteScientifique() {
        return comiteScientifique;
    }

    public void setComiteScientifique(ComiteScientifique comiteScientifique) {
        this.comiteScientifique = comiteScientifique;
    }

    public List<ConferencierInvite> getConferenciersInvites() {
        return conferenciersInvites;
    }

    public void setConferenciersInvites(List<ConferencierInvite> conferenciersInvites) {
        this.conferenciersInvites = conferenciersInvites;
    }

    public List<Article> getArticles() {
        return articles;
    }

    public void setArticles(List<Article> articles) {
        this.articles = articles;
    }

    public List<Participant> getParticipants() {
        return participants;
    }

    public void setParticipants(List<Participant> participants) {
        this.participants = participants;
    }

    public double getFraisInscription() {
        return fraisInscription;
    }

    public void setFraisInscription(double fraisInscription) {
        this.fraisInscription = fraisInscription;
    }

    public Date getDateLimiteSoumission() {
        return dateLimiteSoumission;
    }

    public void setDateLimiteSoumission(Date dateLimiteSoumission) {
        this.dateLimiteSoumission = dateLimiteSoumission;
    }

    public Date getDateLimiteInscription() {
        return dateLimiteInscription;
    }

    public void setDateLimiteInscription(Date dateLimiteInscription) {
        this.dateLimiteInscription = dateLimiteInscription;
    }
}

        
    