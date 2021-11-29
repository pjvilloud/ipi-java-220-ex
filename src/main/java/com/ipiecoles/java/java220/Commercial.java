package com.ipiecoles.java.java220;

import org.joda.time.LocalDate;

import java.util.Objects;

public class Commercial extends Employe{
    private Double caAnnuel;
    private Integer performance;

    public Commercial(){

    }

    public Commercial(String nom, String prenom, String matricule, LocalDate dateEmbauche, Double salaire, Boolean tempsPartiel, String sexe, Double caAnnuel) {
        super(nom, prenom, matricule, dateEmbauche, salaire, tempsPartiel, sexe);
        this.caAnnuel = caAnnuel;
    }

    public Commercial(String nom, String prenom, String matricule, LocalDate dateEmbauche, Double salaire, Boolean tempsPartiel, String sexe, Double caAnnuel, Integer performance) {
        this(nom, prenom, matricule, dateEmbauche, salaire, tempsPartiel, sexe, caAnnuel);
        this.performance = performance;
    }

    public Boolean performanceEgale(Integer performance) {
        return Objects.equals(this.performance, performance);
    }

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

    @Override
    public Double getPrimeAnnuelle() {
        if(this.caAnnuel == null){
            return 500.0;
        }
        return Math.max(Math.ceil(this.caAnnuel * 0.05), 500);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Commercial that = (Commercial) o;
        return Objects.equals(caAnnuel, that.caAnnuel) && Objects.equals(performance, that.performance);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), caAnnuel, performance);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Commercial{");
        sb.append("caAnnuel=").append(caAnnuel);
        sb.append(", performance=").append(performance);
        sb.append('}');
        return super.toString() + sb.toString();
    }
}
