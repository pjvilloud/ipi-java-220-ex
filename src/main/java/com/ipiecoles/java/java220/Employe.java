package com.ipiecoles.java.java220;

import org.joda.time.LocalDate;

/**
 * Created by pjvilloud on 21/09/17.
 */
public class Employe {
    //Variable
    private String nom;
    private String prenom;
    private String matricule;
    private LocalDate dateEmbauche;
    private Double salaire;
    //Getter
    public LocalDate getDateEmbauche() {
        return dateEmbauche;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getMatricule() {
        return matricule;
    }

    public Double getSalaire() {
        return salaire;
    }
    ////Setter
    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public void setMatricule(String matricule) {
        this.matricule = matricule;
    }

    public void setDateEmbauche(LocalDate NewDateEmbauche) {
        if (NewDateEmbauche.isBefore(this.dateEmbauche)){
            System.out.println("La date d'embauche ne peut être postérieure à la date courante");
        }
        else {
            this.dateEmbauche = NewDateEmbauche;
        }
    }

    public void setSalaire(Double salaire) {
        this.salaire = salaire;
    }

    //Constructeur
    public Employe(){
    }

    //Constructeur avec arguments
    public Employe(String name,String fName,String matr,LocalDate dateEmb,double salary){
        this.nom=name;
        this.prenom=fName;
        this.matricule=matr;
        this.dateEmbauche=dateEmb;
        this.salaire=salary;
    }
    //Methode pour savoir le nombre d'année d'ancienneté
    public Integer getNombreAnneeAnciennete(){
        return LocalDate.now().getYear() - this.dateEmbauche.getYear();
    }
    //Surdefinition toString
    @Override
    public String toString(){
        String result = "Employe{nom = "+this.nom+",\nprenom = "+
                this.prenom+",\nmatricule = "+
                this.matricule+",\ndateEmbauche = "+
                this.dateEmbauche+",\nsalaire = "+
                this.salaire+"}";

        return result;
    }
}
