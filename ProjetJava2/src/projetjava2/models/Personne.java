/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package projetjava2.models;

/**
 *
 * @author LENOVO
 */
public abstract class Personne {
    private String nom;
    private String mail;
    private String institution;
    
    
    
    public Personne(String nom,String mail,String institution)
            {
                this.nom = nom;
                this.mail = mail;
                this.institution = institution;
            }
    // getters
    public String getNom()
    {
        return nom;
    }
    public String getMail()
    {
        return mail;
    }
    public String getInstitution()
    {
        return institution;
    }
    
    // setters
    public void setNom(String nom)
    {this.nom = nom;}
    
    public void setMail(String mail)
    {
        this.mail = mail;
    }
    public void setInstitution(String institution)
    {
        this.institution = institution;
    }
}


