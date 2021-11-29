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
        return Entreprise.NB_CONGES_BASE+coefficient.intValue();
    }

    @Override
    public String toString() {
        return "Cadre{" +
                "coefficient=" + coefficient +
                '}'+'\''+
                "Employe{" +
                "nom='" + super.getNom() + '\'' +
                ", prenom='" + super.getPrenom() + '\'' +
                ", matricule='" + super.getMatricule() + '\'' +
                ", dateEmbauche=" + super.getDateEmbauche() +
                ", salaire=" + super.getSalaire() +
                ", tempsPartiel=" + super.getTempsPartiel() +
                ", sexe=" + super.getSexe() +
                '}'+super.toString();
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
