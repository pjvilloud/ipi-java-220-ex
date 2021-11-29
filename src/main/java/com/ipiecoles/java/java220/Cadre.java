package com.ipiecoles.java.java220;

import org.joda.time.LocalDate;

import java.util.Objects;

public class Cadre extends  Employe{

    private Double coefficient = 1d;

    public Cadre(){
    }

    public Cadre(String nom, String prenom, String matricule, LocalDate dateEmbauche, Double salaire, Boolean tempsPartiel, String sexe, Double coefficient) {
        super(nom, prenom, matricule, dateEmbauche, salaire, tempsPartiel, sexe);
        this.coefficient = coefficient;
    }

    public Cadre(Double coefficient){
        this.coefficient= coefficient;
    }


    public Double getCoefficient() {
        return coefficient;
    }

    public void setCoefficient(Double coefficient) {
        this.coefficient = coefficient;
    }

    @Override
    public Double getPrimeAnnuelle() {
        return Entreprise.primeAnnuelleBase()*coefficient;
        //LocalDate.now().getYear()*0.5*coefficient
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
        sb.append(", dateEmbauche=").append(super.getDateEmbauche()).append('\'');
        sb.append(", salaire=").append(super.getSalaire()).append('\'');
        sb.append(", tempsPartiel=").append(getTempsPartiel()).append('\'');
        sb.append(", sexe=").append(getSexe()).append('\'');
        sb.append(", coefficient=").append(coefficient).append('\'');
        sb.append('}');
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Cadre cadre = (Cadre) o;
        return Objects.equals(coefficient, cadre.coefficient);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), coefficient);
    }

}
