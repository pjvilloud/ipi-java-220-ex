package com.ipiecoles.java.java220;

import org.joda.time.LocalDate;

import java.util.Comparator;
import java.util.Objects;

/**
 * Created by pjvilloud on 21/09/17.
 */
public abstract class Employe{
    //Variable
    private String nom;
    private String prenom;
    private String matricule;
    private LocalDate dateEmbauche;
    private Double salaire;


    //Constructor
    public Employe(){
        nom="nom";
        prenom="prenom";
        matricule="matricule";
        dateEmbauche=LocalDate.now();
        salaire=500d;
    }

    //Constructeur avec arguments

    public Employe(String name,String fName,String matric,LocalDate dateEmb,Double salary){
        this.nom=name;
        this.prenom=fName;
        this.matricule=matric;
        this.dateEmbauche=dateEmb;
        this.salaire=salary;
    }

    //Methode pour savoir le nombre d'année d'ancienneté
    public final Integer getNombreAnneeAnciennete() {
        return LocalDate.now().getYear() - dateEmbauche.getYear();
    }

    //Methode augmenter salaire
    public Double augmenterSalaire(Double tauxAugmentation){
        Double NewSalaire = this.salaire*tauxAugmentation+this.salaire;

        return this.salaire=NewSalaire;
    }

    //Methode getPrimeAnnuelle
    public abstract Double getPrimeAnnuelle();

    //Surdefinition toString
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Employe{");
        sb.append("nom='").append(nom).append("'");
        sb.append(", prenom='").append(prenom).append("'");
        sb.append(", matricule='").append(matricule).append("'");
        sb.append(", dateEmbauche=").append(dateEmbauche);
        sb.append(", salaire=").append(salaire);
        sb.append('}');
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employe employe = (Employe) o;
        return Objects.equals(nom, employe.nom) && Objects.equals(prenom, employe.prenom) && Objects.equals(matricule, employe.matricule) && Objects.equals(dateEmbauche, employe.dateEmbauche) && Objects.equals(salaire, employe.salaire);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nom, prenom, matricule, dateEmbauche, salaire);
    }




    //Getter//////////////////////
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
    ////Setter///////////////
    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public void setMatricule(String matricule) {
        this.matricule = matricule;
    }

    public void setDateEmbauche(LocalDate NewDateEmbauche)throws Exception {
        if(NewDateEmbauche != null && NewDateEmbauche.isAfter(LocalDate.now())) {
            throw new Exception("La date d'embauche ne peut être postérieure à la date courante");
        }
        this.dateEmbauche = NewDateEmbauche;
    }

    public void setSalaire(Double salaire) {
        this.salaire = salaire;
    }

    public Integer getNbConges(){

        return Entreprise.NB_CONGES_BASE;
    }
}
