package com.ipiecoles.java.java220;

import org.joda.time.LocalDate;

import java.util.Objects;

/**
 * Created by pjvilloud on 21/09/17.
 */
public abstract class Employe extends Object {
    private String nom;
    private String prenom;
    private String matricule;
    private LocalDate dateEmbauche;
    private Double salaire;
    private Boolean tempsPartiel;
    private String sexe;

    public Employe() {
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

    public Boolean getTempsPartiel() { return tempsPartiel; }

    public String getSexe() { return sexe; }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public LocalDate getDateEmbauche() {
        return dateEmbauche;
    }

    public String getMatricule() {
        return matricule;
    }

    public Double getSalaire() {
        return salaire;
    }

    public void setTempsPartiel(Boolean tempsPartiel) { this.tempsPartiel = tempsPartiel; }

    public void setSexe(String sexe) { this.sexe = sexe; }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public void setMatricule(String matricule) {
        this.matricule = matricule;
    }

    public void setDateEmbauche(LocalDate dateEmbauche) throws Exception {
        if(dateEmbauche == null){
            throw new Exception("La date d'embauche ne peut être nulle");
        }
        if (dateEmbauche.isAfter(LocalDate.now())) {
            throw new Exception("La date d'embauche ne peut être postérieure à la date courante");
        }else{
            this.dateEmbauche = dateEmbauche;
        }
    }

    public void setSalaire(Double salaire) {
        this.salaire = salaire;
    }

    public final Integer getNombreAnneeAnciennete() {
        return LocalDate.now().getYear() - this.dateEmbauche.getYear();
    }

    public Integer getNbConges() {
        return Entreprise.NB_CONGES_BASE;
    }

    /**
     * Méthode permettant d'augmenter le salaire de l'employé
     *
     * Le salaire devra être renseigné
     * @param augmentation pourcentage d'augmentation du salaire
     * @throws Exception si le pourcentage est incorrect
     */
    public void augmenterSalaire(Double augmentation) throws Exception {
        if(augmentation == null || augmentation <= 0 || augmentation > 1){
            throw new Exception("L'augmentation ne peut être null ou > 1");
        }
        this.salaire = this.salaire + (this.salaire * augmentation);
    }

    public abstract Double getPrimeAnnuelle();

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Employe{");
        sb.append("nom='").append(nom).append('\'');
        sb.append(", prenom='").append(prenom).append('\'');
        sb.append(", matricule='").append(matricule).append('\'');
        sb.append(", dateEmbauche=").append(dateEmbauche);
        sb.append(", salaire=").append(salaire);
        sb.append(", tempsPartiel").append(tempsPartiel);
        sb.append(", sexe").append(sexe);
        sb.append('}');
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employe employe = (Employe) o;
        return Objects.equals(this.nom, employe.nom) &&
                Objects.equals(this.prenom, employe.prenom) &&
                Objects.equals(this.matricule, employe.matricule) &&
                Objects.equals(this.dateEmbauche, employe.dateEmbauche) &&
                Objects.equals(this.salaire, employe.salaire) &&
                Objects.equals(this.tempsPartiel, employe.tempsPartiel) &&
                Objects.equals(this.sexe, employe.sexe);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nom, prenom, matricule, dateEmbauche, salaire, tempsPartiel, sexe);
    }
}
