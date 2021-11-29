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
    private Boolean tempsPartiels;
    private String sexe;

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getMatricule() {
        return matricule;
    }

    public void setMatricule(String matricule) {
        this.matricule = matricule;
    }

    public LocalDate getDateEmbauche() {
        return dateEmbauche;

    }

    public Boolean getTempsPartiels() {
        return tempsPartiels;
    }

    public void setTempsPartiels(Boolean tempsPartiels) {
        this.tempsPartiels = tempsPartiels;
    }

    public String getSexe() {
        return sexe;
    }

    public void setSexe(String sexe) {
        this.sexe = sexe;
    }

    public void setDateEmbauche(LocalDate newDateEmbauche) {
        if (newDateEmbauche != null && newDateEmbauche.isAfter(LocalDate.now())) {
            throw new RuntimeException("La date d'embauche ne peut être postérieure à la date courante");
        }
        this.dateEmbauche = newDateEmbauche;
    }

    public Double getSalaire() {
        return salaire;
    }

    public void setSalaire(Double salaire) {
        this.salaire = salaire;
    }

    public Employe(String nom, String prenom, String matricule, LocalDate dateEmbauche, Double salaire, Boolean tempsPartiels, String sexe) {
        this.nom = nom;
        this.prenom = prenom;
        this.matricule = matricule;
        this.dateEmbauche = dateEmbauche;
        this.salaire = salaire;
        this.tempsPartiels = tempsPartiels;
        this.sexe = sexe;
    }


    public Employe() {
    }

    public final Integer getNombreAnneeAnciennete() {

        if (LocalDate.now().getYear() <= this.dateEmbauche.getYear()) {
            return 0;
        } else {
            return LocalDate.now().getYear() - this.dateEmbauche.getYear();
        }
    }

    public Integer getNbConges() {
        return Entreprise.NB_CONGES_BASE;
    }

    public void augmenterSalaire(Double augmentation) throws Exception {
        this.salaire = (this.salaire * augmentation) + this.salaire;
    }

    public abstract Double getPrimeAnnuelle();


    public String toString() {
        return "Employe{nom='" + this.nom + "', prenom='" + this.prenom + "', matricule='" + this.matricule + "', dateEmbauche=" + this.dateEmbauche + ", salaire=" + this.salaire + "}";
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Employe)) return false;
        Employe employe = (Employe) o;
        return Objects.equals(nom, employe.nom) && Objects.equals(prenom, employe.prenom) && Objects.equals(matricule, employe.matricule) && Objects.equals(dateEmbauche, employe.dateEmbauche) && Objects.equals(salaire, employe.salaire) && Objects.equals(tempsPartiels, employe.tempsPartiels)&& Objects.equals(sexe, employe.sexe);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nom, prenom, matricule, dateEmbauche, salaire);
    }

}
