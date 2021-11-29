package com.ipiecoles.java.java220;

import org.joda.time.LocalDate;

import java.util.Objects;

public class Commercial extends Employe {

    private Double caAnnuel = 0.0;
    private Integer performance;

    public Commercial(){
    }

    public Double getCaAnnuel() {
        return caAnnuel;
    }

    public void setCaAnnuel(Double caAnnuel) {
        this.caAnnuel = caAnnuel;
    }

    public Double getPrimeAnnuelle(){
        if(this.caAnnuel !=null){
            return Math.max(Math.ceil(this.getCaAnnuel()*0.05),500);
        }
        return 500d;
    }
    public Commercial(String nom, String prenom, String matricule, LocalDate dateEmbauche, Double salaire, Double caAnnuel, Boolean tempsPartiel, String sexe) {
        super(nom, prenom, matricule, dateEmbauche, salaire, tempsPartiel, sexe);
        this.caAnnuel = caAnnuel;
    }
    public Commercial(String nom, String prenom, String matricule, LocalDate dateEmbauche, Double salaire,Double caAnnuel,Boolean tempsPartiel, String sexe, Integer performance) {
        super(nom, prenom, matricule, dateEmbauche, salaire,tempsPartiel, sexe);
        this.caAnnuel = caAnnuel;
        this.performance = performance;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Commercial that = (Commercial) o;
        return Objects.equals(caAnnuel, that.caAnnuel) &&
                Objects.equals(performance, that.performance);
    }
    public Boolean performanceEgale(Integer perf){
        return this.performance.equals(perf);
    }
    public Note equivalenceNote(){
        switch (performance) {
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
    public Integer getPerformance() {
        return performance;
    }
    public void setPerformance(Integer performance) {
        this.performance = performance;
    }
    @Override
    public String toString() {
        return "Commercial{" +
                "caAnnuel=" + caAnnuel +
                ", performance=" + performance +
                "} " + super.toString();
    }
}
