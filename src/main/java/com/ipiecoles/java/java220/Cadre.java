package com.ipiecoles.java.java220;

import org.joda.time.LocalDate;

import java.util.Objects;

public class Cadre extends Employe{
    private Double coefficient;

    public Cadre(String nom, String prenom, String matricule, LocalDate dateEmbauche, Double salaire, Boolean tempsPartiel, String sexe, Double coefficient) {
        super(nom, prenom, matricule, dateEmbauche, salaire, tempsPartiel, sexe);
        this.coefficient = coefficient;
    }

    public Cadre() {
    }

    @Override
    public Integer getNbConges() {
        return super.getNbConges() + this.coefficient.intValue();
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

    @Override
    public String toString() {
        return "Cadre{" +
                "nom='" + this.getNom() + '\'' +
                ", prenom='" + this.getPrenom() + '\'' +
                ", matricule='" + this.getMatricule() + '\'' +
                ", sexe='" + this.getSexe() + '\'' +
                ", dateEmbauche=" + this.getDateEmbauche() +
                ", salaire=" + this.getSalaire() +
                ", coefficiant=" + this.coefficient +
                '}';
    }

    @Override
    public Double getPrimeAnnuelle() {
        return Entreprise.primeAnnuelleBase() * this.coefficient;
    }
}
