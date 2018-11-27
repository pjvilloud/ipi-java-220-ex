package com.ipiecoles.java.java220;

import org.joda.time.LocalDate;

/**
 * Created by pjvilloud on 21/09/17.
 */
public class Employe {

    private String nom;
    private String prenom;
    private String matricule;
    private LocalDate dateEmbauche;
    private Double salaire;

    public Employe(){

    }

    public Employe(String nom, String prenom, String matricule, LocalDate dateEmbauche, Double salaire) {
        this.nom = nom;
        this.prenom = prenom;
        this.matricule = matricule;
        this.dateEmbauche = dateEmbauche;
        this.salaire = salaire;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public void setMatricule(String matricule) {
        this.matricule = matricule;
    }

    public void setDateEmbauche(LocalDate dateEmbauche) throws DateEmbaucheException {
        if (dateEmbauche.getYear()<LocalDate.now().getYear()) throw new DateEmbaucheException("La date d'embauche ne peut être postérieure à la date courante");
        this.dateEmbauche = dateEmbauche;
    }

    public void setSalaire(Double salaire) {
        this.salaire = salaire;
    }

    public String getMatricule() {
        return matricule;
    }

    public LocalDate getDateEmbauche() {
        return dateEmbauche;
    }

    public Double getSalaire() {
        return salaire;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    private Integer getNombreAnneeAnciennete(){
        return LocalDate.now().getYear() - this.dateEmbauche.getYear();
    }

}
