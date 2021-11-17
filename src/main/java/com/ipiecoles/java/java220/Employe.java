package com.ipiecoles.java.java220;

import org.joda.time.LocalDate;

import java.util.Objects;

/**
 * Created by pjvilloud on 21/09/17.
 */
public abstract class Employe {

    // Attributs
    private String nom;
    private String prenom;
    private String matricule;
    private LocalDate dateEmbauche;
    private Double salaire;

    // Constructeurs
    public Employe() {
    }

    public Employe(String nom, String prenom, String matricule, LocalDate dateEmbauche, Double salaire) {
        this.nom = nom;
        this.prenom = prenom;
        this.matricule = matricule;
        this.dateEmbauche = dateEmbauche;
        this.salaire = salaire;
    }

    // Methodes
    public final Integer getNombreAnneeAnciennete() {
        return LocalDate.now().getYear() - this.dateEmbauche.getYear();
    }

    public Integer getNbConges() {
        return Entreprise.NB_CONGES_BASE;
    }

    public abstract Double getPrimeAnnuelle();

    public void augmenterSalaire(Double pourcentage) {
        this.salaire = this.salaire + (this.salaire * pourcentage);
    }

    // Getters Setters
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

    public void setDateEmbauche(LocalDate dateEmbauche) throws Exception {
        if (dateEmbauche == null) {
            throw new Exception("La date d'embauche ne peut être nulle");
        }
        if (dateEmbauche.isAfter(LocalDate.now())) {
            throw new Exception("La date d'embauche ne peut être postérieure à la date courante");
        }
        this.dateEmbauche = dateEmbauche;
    }

    public Double getSalaire() {
        return salaire;
    }

    public void setSalaire(Double salaire) {
        this.salaire = salaire;
    }

    // Methodes objet
    // Décorateur qui permet de "override" une méthode héritée.
    @Override
    public String toString() {
        return String.format("Employe{nom='%s', prenom='%s', matricule='%s', dateEmbauche=%s, salaire=%s}",
                this.nom, this.prenom, this.matricule, this.dateEmbauche, this.salaire.toString());
    }

    @Override
    public boolean equals(Object object) {
        // Deux instances identiques
        if (this == object) return true;
        if (object == null) return false;
        // Objects n'est pas une instance d'Employe
        if (!(object instanceof Employe)) return false;

        // Définis l'object en paramètre comme une instance de la classe Employe
        Employe employe = (Employe) object;
        // Compare les salaires avec la méthode Double.compare()
        if (Double.compare(this.salaire, employe.salaire) != 0) return false;
        // Compare les attributs avec la méthode equals() d'Objects
        if (!Objects.equals(this.nom, employe.nom)) return false;
        if (!Objects.equals(this.prenom, employe.prenom)) return false;
        if (!Objects.equals(this.matricule, employe.matricule)) return false;
        return Objects.equals(this.dateEmbauche, employe.dateEmbauche);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.nom, this.prenom, this.matricule, this.dateEmbauche, this.salaire);
    }
}
