package com.ipiecoles.java.java220;

import org.joda.time.LocalDate;

import java.util.Objects;

public class Cadre extends Employe{

    // attributs
    private Double coefficient = 1d;

    // getters
    public Double getCoefficient() { return coefficient; }

    // setters
    public void setCoefficient(Double coefficient) {
        this.coefficient = coefficient;
    }

    public Cadre() {

    }

    public Cadre(String newNom, String newPrenom, String newMatricule, LocalDate newDateEmbauche, Double newSalaire, Boolean newTempsPartiel, String newSexe, Double newCoefficient) {
        super(newNom, newPrenom, newMatricule, newDateEmbauche, newSalaire, newTempsPartiel, newSexe);
        this.coefficient = newCoefficient;
    }

    @Override
    public Double getPrimeAnnuelle() {
        return Entreprise.primeAnnuelleBase() * coefficient;
    }

    @Override
    public Integer getNbConges(){
        return Entreprise.NB_CONGES_BASE + coefficient.intValue();
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Cadre{");
        sb.append("nom='").append(super.getNom()).append('\'');
        sb.append(", prenom='").append(super.getPrenom()).append('\'');
        sb.append(", matricule='").append(super.getMatricule()).append('\'');
        sb.append(", dateEmbauche=").append(super.getDateEmbauche());
        sb.append(", salaire=").append(super.getSalaire());
        sb.append(", tempsPartiel=").append(super.getTempsPartiel());
        sb.append(", sexe=").append(super.getSexe());
        sb.append(", coefficient=").append(coefficient);
        sb.append('}');
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Cadre)) return false;

        if(!super.equals(o)) return false;

        Cadre cadre = (Cadre) o;

        return Double.compare(cadre.coefficient, coefficient) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(),this.coefficient);
    }
}
