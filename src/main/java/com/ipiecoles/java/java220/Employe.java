package com.ipiecoles.java.java220;

import org.joda.time.LocalDate;

import java.util.Objects;

/**
 * Created by pjvilloud on 21/09/17.
 */
public abstract class Employe {
    // attributs
    private String nom;
    private String prenom;
    private String matricule;
    private LocalDate dateEmbauche;
    private Double salaire;
    private Boolean tempsPartiel;
    private String sexe;

    // getters
    public String getNom() {return nom;}
    public String getPrenom() {return prenom;}
    public String getMatricule() {return matricule;}
    public LocalDate getDateEmbauche(){return dateEmbauche;}
    public Double getSalaire(){return salaire;}
    public Boolean getTempsPartiel(){return tempsPartiel;}
    public String getSexe(){return sexe;}

    // setters
    public void setNom(String newNom){this.nom = newNom;}
    public void setPrenom(String newPrenom){this.prenom = newPrenom;}
    public void setMatricule(String newMatricule){this.matricule = newMatricule;}
    public void setDateEmbauche(LocalDate newDateEmbauche) {
        if (newDateEmbauche != null && newDateEmbauche.isAfter(LocalDate.now())) {
            throw new RuntimeException("La date d'embauche ne peut être postérieure à la date courante");
        }
        this.dateEmbauche = newDateEmbauche;
    }
    public void setSalaire(Double newSalaire){this.salaire = newSalaire;}
    public void setTempsPartiel(Boolean newTempsPartiel){this.tempsPartiel = newTempsPartiel;}
    public void setSexe(String newSexe){this.sexe = newSexe;}

    public Employe(){
    }

    public Employe(String newNom, String newPrenom, String newMatricule, LocalDate newDateEmbauche, Double newSalaire) {
        nom = newNom;
        prenom = newPrenom;
        matricule = newMatricule;
        dateEmbauche = newDateEmbauche;
        salaire = newSalaire;
    }

    public Employe(String newNom, String newPrenom, String newMatricule, LocalDate newDateEmbauche, Double newSalaire, Boolean newTempsPartiel, String newSexe) {
        this(newNom, newPrenom, newMatricule, newDateEmbauche, newSalaire);
        this.tempsPartiel = newTempsPartiel;
        this.sexe = newSexe;
    }

    public final Integer getNombreAnneeAnciennete() throws Exception {
        if (dateEmbauche.isAfter(LocalDate.now())){
            throw new Exception("La date d'embauche ne peut pas être ultérieur à la date courante!");
        }
        return LocalDate.now().getYear() - dateEmbauche.getYear();
    }

    public Integer getNbConges(){
        return Entreprise.NB_CONGES_BASE;
    }

    public String toString() {
        final StringBuilder sb = new StringBuilder("Employe{");
        sb.append("nom='").append(nom).append('\'');
        sb.append(", prenom='").append(prenom).append('\'');
        sb.append(", matricule='").append(matricule).append('\'');
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
        if (!(o instanceof Employe)) return false;

        Employe employe = (Employe)o;

        if (nom != null ? !nom.equals(employe.nom): employe.nom != null) return false;
        if (prenom != null ? !prenom.equals(employe.prenom): employe.prenom != null) return false;
        if (matricule != null ? !matricule.equals(employe.matricule): employe.matricule != null) return false;
        if (sexe != null ? !sexe.equals(employe.sexe): employe.sexe != null) return false;
        if (Double.compare(employe.salaire,salaire) != 0) return false;
        if (Boolean.compare(employe.tempsPartiel,tempsPartiel) != 0) return false;
        return dateEmbauche != null ? dateEmbauche.equals(employe.dateEmbauche) : employe.dateEmbauche == null;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.nom, this.prenom, this.matricule, this.dateEmbauche, this.salaire, this.tempsPartiel, this.sexe);
    }

    public void augmenterSalaire(Double pourcentage) {
        if (pourcentage < 0) {
            new Exception("Le pourcentage d'augmentation ne peut pas être négatif.");
        }
        this.salaire += this.salaire*pourcentage;
    }

    public abstract Double getPrimeAnnuelle();
}
