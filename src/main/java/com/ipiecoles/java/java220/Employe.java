package com.ipiecoles.java.java220;

import org.joda.time.LocalDate;

import java.util.Objects;

/**
 * Created by pjvilloud on 21/09/17.
 */
public abstract class Employe {

    private String nom;
    private String prenom;
    private String matricule;
    private LocalDate dateEmbauche;
    private Double salaire;
    private boolean tempsPartiel;
    private String sexe;

    public Employe(){

    }

    public Employe(String nom, String prenom, String matricule, LocalDate dateEmbauche, Double salaire) {
        this.nom = nom;
        this.prenom = prenom;
        this.matricule = matricule;
        try{
            this.setDateEmbauche(dateEmbauche);
        }
        catch (DateEmbaucheException e){
            System.out.println(e.getMessage());
        }
        this.salaire = salaire;
    }

    public Employe(String nom, String prenom, String matricule, LocalDate dateEmbauche, Double salaire, boolean tempsPartiel, String sexe) {
        this.nom = nom;
        this.prenom = prenom;
        this.matricule = matricule;
        try{
            this.setDateEmbauche(dateEmbauche);
        }
        catch (DateEmbaucheException e){
            System.out.println(e.getMessage());
        }
        this.salaire = salaire;
        this.tempsPartiel = tempsPartiel;
        this.sexe = sexe;
    }

    public boolean isTempsPartiel() {
        return tempsPartiel;
    }

    public void setTempsPartiel(boolean tempsPartiel) {
        this.tempsPartiel = tempsPartiel;
    }

    public String getSexe() {
        return sexe;
    }

    public void setSexe(String sexe) {
        this.sexe = sexe;
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
        if (dateEmbauche != null && dateEmbauche.isAfter(LocalDate.now())) throw new DateEmbaucheException("La date d'embauche ne peut être postérieure à la date courante");
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

    public final Integer getNombreAnneeAnciennete(){
        return LocalDate.now().getYear() - this.dateEmbauche.getYear();
    }

    public Integer getNbConges() {
        return Entreprise.NB_CONGES_BASE;
    }

    public Integer anciennete(){
        return LocalDate.now().getYear() - this.getDateEmbauche().getYear();
    }

    public String toString(){
        String message = "Employe{";
        message += "nom='"+this.nom+"', ";
        message += "prenom='"+this.prenom+"', ";
        message += "matricule='"+this.matricule+"', ";
        message += "dateEmbauche="+this.dateEmbauche+", ";
        message += "salaire="+this.salaire+"}";
        message += "tempsPartiel="+(this.tempsPartiel?"oui":"non")+"}";
        message += "sexe="+this.sexe+"}";
        return message;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employe employe = (Employe) o;
        return this.hashCode() == o.hashCode();
    }

    @Override
    public int hashCode() {
        return Objects.hash(nom, prenom, matricule, dateEmbauche, salaire, tempsPartiel, sexe);
    }

    public void augmenterSalaire(Double pourcent){
        this.salaire = this.salaire*(1+pourcent);
    }

    public abstract Double getPrimeAnnuelle();

}

