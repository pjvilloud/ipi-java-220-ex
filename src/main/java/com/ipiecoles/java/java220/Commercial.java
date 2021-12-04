package com.ipiecoles.java.java220;

import org.joda.time.LocalDate;

public class Commercial extends Employe {

    private Double caAnnuel = 0d;

    private Integer performance;

    public Commercial() {

    }

    public Commercial(String nom, String prenom, String matricule, LocalDate dateEmbauche, Double salaire, Boolean tempsPartiel, String sexe, Double caAnnuel, Integer performance) {
        super(nom, prenom, matricule, dateEmbauche, salaire, tempsPartiel, sexe);
        this.caAnnuel = caAnnuel;
        this.performance = performance;
    }

    public Double getPrimeAnnuelle() {
        return Math.max(Math.ceil(this.getCaAnnuel() * 0.05), 500);
    }

    public Double getCaAnnuel() {
        return caAnnuel;
    }

    public void setCaAnnuel(Double caAnnuel) {
        this.caAnnuel = caAnnuel;
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Commercial)) return false;
        if (!super.equals(o)) return false;

        Commercial that = (Commercial) o;

        return Double.compare(that.caAnnuel, caAnnuel) == 0;

    }

    @Override
    public String toString() {
        return "Commercial{" +
                "caAnnuel=" + caAnnuel +
                "} " + super.toString();
    }
}