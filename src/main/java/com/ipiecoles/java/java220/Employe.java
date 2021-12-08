package com.ipiecoles.java.java220;

import org.joda.time.LocalDate;

import java.util.Objects;

public abstract class Employe{
    //Variable
    private String nom;
    private String prenom;
    private String matricule;
    private LocalDate dateEmbauche;
    private Double salaire = Entreprise.SALAIRE_BASE;



    private Boolean tempsPartiel;
    private String sexe;


    //Constructor
    public Employe(){

    }

    //Constructeur avec arguments

    public Employe(String name,String fName,String matric,LocalDate dateEmb,Double salary){
        this.nom=name;
        this.prenom=fName;
        this.matricule=matric;
        this.dateEmbauche=dateEmb;
        this.salaire=salary;
    }

    public Employe(String nom, String prenom, String matricule, LocalDate dateEmbauche, Double salaire, Boolean tempsPartiel, String sexe) {
        this.nom = nom;
        this.prenom = prenom;
        this.matricule = matricule;
        this.dateEmbauche = dateEmbauche;
        this.salaire = salaire;
        this.tempsPartiel = tempsPartiel;
        this.sexe = sexe;
    }

    //Methode pour savoir le nombre d'année d'ancienneté
    public final Integer getNombreAnneeAnciennete() {
        return LocalDate.now().getYear() - dateEmbauche.getYear();
    }

    //Methode augmenter salaire
    public void augmenterSalaire(Double tauxAugmentation){
        this.salaire = this.getSalaire() * (1 + tauxAugmentation);
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
        sb.append(", tempsPartiel=").append(tempsPartiel);
        sb.append(", sexe=").append(sexe);
        sb.append('}');
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employe employe = (Employe) o;
        return Objects.equals(nom, employe.nom) && Objects.equals(prenom, employe.prenom) && Objects.equals(matricule, employe.matricule) && Objects.equals(dateEmbauche, employe.dateEmbauche) && Objects.equals(salaire, employe.salaire)  && Objects.equals(tempsPartiel, employe.tempsPartiel) && Objects.equals(sexe, employe.sexe);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nom, prenom, matricule, dateEmbauche, salaire, tempsPartiel, sexe);
    }



    //Getter//////////////////////
    public Boolean getTempsPartiel() {
        return tempsPartiel;
    }

    public String getSexe() {
        return sexe;
    }

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
    public void setTempsPartiel(Boolean tempsPartiel) {
        this.tempsPartiel = tempsPartiel;
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
