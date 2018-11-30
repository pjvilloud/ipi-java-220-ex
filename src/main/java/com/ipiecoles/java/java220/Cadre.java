package com.ipiecoles.java.java220;

import org.joda.time.LocalDate;

import java.text.DecimalFormat;
import java.util.Objects;

public class Cadre extends Employe {

    private Double coefficient = 1d;

    public Cadre() {
        super();
    }

    public Cadre(String nom, String prenom, String matricule, LocalDate dateEmbauche, Double salaire) {
        super(nom, prenom, matricule, dateEmbauche, salaire);
    }

    public Cadre(String nom, String prenom, String matricule, LocalDate dateEmbauche, Double salaire, boolean tempsPartiel, String sexe, Double coefficient) {
        super(nom, prenom, matricule, dateEmbauche, salaire, tempsPartiel, sexe);
        this.coefficient = coefficient;
    }

    public Double getCoefficient() {
        return coefficient;
    }

    public void setCoefficient(Double coefficient) {
        this.coefficient = coefficient;
    }

    // pas de fonction equals()
    // car la fonction equals hérité de la classe Employe compare les hashcode renvoyé par la fonction hashCode()

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), coefficient);
    }

    @Override
    public String toString() {
        return "Cadre{" +
                "coefficient=" + coefficient +
                '}'+ super.toString();
    }

    @Override
    public Double getPrimeAnnuelle() {
        int prime = (int) (Entreprise.primeAnnuelleBase()*this.coefficient*100);
        return (prime)/10000d;
    }
}
