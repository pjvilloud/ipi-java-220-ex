package com.ipiecoles.java.java220;

import org.joda.time.LocalDate;

import java.util.Comparator;

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

    //Methode pour savoir le nombre d'année d'ancienneté
    public final Integer getNombreAnneeAnciennete() {
        return LocalDate.now().getYear() - dateEmbauche.getYear();
    }
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
    public boolean equals(Object obj) {
        Employe other = (Employe) obj;

        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;

        /*if (nom != other.nom)
            return false;
        if (prenom == null) {
            if (other.prenom != null)
                return false;
        } else if (!prenom.equals(other.prenom))
            return false;
        if (matricule != other.matricule)
            return false;
        if (nom == null) {
            if (other.nom != null)
                return false;
        } else if (!nom.equals(other.nom))
            return false;
        if (prenom == null) {
            if (other.prenom != null)
                return false;
        } else if (!prenom.equals(other.prenom))
            return false;

        if (salaire == null) {
            if (other.salaire != null)
                return false;
        }else if (!salaire.equals(other.salaire))
            return false;

        if (dateEmbauche == null) {
            if (other.dateEmbauche != null)
                return false;
        }else if (!dateEmbauche.equals(other.dateEmbauche))
            return false;*/

        if (this.nom==null || this.prenom==null || this.matricule==null  || this.dateEmbauche==null || this.salaire==null){
            return false;
        }
        if (other.nom==null ||
                other.prenom==null ||
                other.matricule ==null ||
                other.dateEmbauche==null ||
                other.salaire==null){

            return false;
        }

        if (!nom.equals(other.nom) ||
                !prenom.equals( other.prenom) ||
                !matricule.equals( other.matricule) ||
                !dateEmbauche.equals(other.dateEmbauche) ||
                !salaire.equals(other.salaire)){
            return false;
        }

        return true;
    }
}
