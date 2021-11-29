package com.ipiecoles.java.java220;

import org.joda.time.LocalDate;

import java.util.Objects;

public class Commercial extends Employe {


    private Double caAnnuel;
    private Integer performance;


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

    public Commercial() { // constructeur par défault
    }

    public Commercial(String nom, String prenom, String matricule, LocalDate dateEmbauche, Double salaire, Double caAnnuel, Boolean tempsPartiels,String sexe) {
        super(nom, prenom, matricule, dateEmbauche, salaire,tempsPartiels,sexe);
        this.caAnnuel = caAnnuel;

    }

    public Commercial(String nom, String prenom, String matricule, LocalDate dateEmbauche, Double salaire, Double caAnnuel, Integer performance, Boolean tempsPartiels, String sexe) {
        this(nom, prenom, matricule, dateEmbauche, salaire, caAnnuel,tempsPartiels,sexe);
        this.performance = performance;
    }

    public Double getPrimeAnnuelle() {
        if (this.caAnnuel == null) {
            return 500d;
        }
        return Math.max(Math.ceil(this.caAnnuel * 0.05), 500);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Commercial)) return false;
        if (!super.equals(o)) return false;
        Commercial that = (Commercial) o;
        return Objects.equals(caAnnuel, that.caAnnuel) && Objects.equals(performance, that.performance);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), caAnnuel, performance);
    }

    public boolean performanceEgale(Integer performance) {
        return Objects.equals(this.performance, performance);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Commercial{");
        sb.append("caAnnuel=").append(caAnnuel);
        sb.append(", performance=").append(performance);
        sb.append('}');
        return super.toString() + sb.toString();
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
}

