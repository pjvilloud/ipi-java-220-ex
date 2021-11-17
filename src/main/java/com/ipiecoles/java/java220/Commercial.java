package com.ipiecoles.java.java220;

import org.joda.time.LocalDate;

import java.util.Objects;

public class Commercial extends Employe {

    // Attributs
    private Double caAnnuel = 0d;
    private Integer performance;

    // Constructeurs
    public Commercial() {
    }

    public Commercial(String nom, String prenom, String matricule, LocalDate dateEmbauche, Double salaire, Double caAnnuel) {
        super(nom, prenom, matricule, dateEmbauche, salaire);
        this.caAnnuel = caAnnuel;
    }

    public Commercial(String nom, String prenom, String matricule, LocalDate dateEmbauche, Double salaire, Double caAnnuel, Integer performance) {
        this(nom, prenom, matricule, dateEmbauche, salaire, caAnnuel);
        this.performance = performance;
    }

    // Methodes
    @Override
    public Double getPrimeAnnuelle() {
        Double primeMinimum = 500d;
        Double primeAnnuelle = Math.ceil(this.caAnnuel * 0.05);
        if (this.caAnnuel.equals(0d) || primeAnnuelle < 500) return primeMinimum;
        return primeAnnuelle;
    }

    public boolean performanceEgale(Integer performance) {
        return this.performance.equals(performance);
    }

    public boolean equals(Object object) {
        if (this == object) return true;
        if (!(object instanceof Commercial)) return false;
        if (!super.equals(object)) return false;

        Commercial commercial = (Commercial) object;

        return Objects.equals(this.caAnnuel, commercial.caAnnuel);
    }

    public Note equivalenceNote() {
        switch (this.performance) {
            case 0:
            case 50:
                return Note.INSUFFISANT;
            case 100:
                return Note.PASSABLE;
            case 150:
                return Note.BIEN;
            case 200:
                return Note.TRES_BIEN;
            default:
                return null;
        }
    }

    // Getters Setters
    public Double getCaAnnuel() {
        return caAnnuel;
    }

    public void setCaAnnuel(Double caAnnuel) {
        this.caAnnuel = caAnnuel;
    }

    public Integer getPerformance() {
        return performance;
    }

    public void setPerformance(Integer performance) {
        this.performance = performance;
    }

    // Methodes objet


}
