package com.ipiecoles.java.java220;

import org.joda.time.LocalDate;

import java.util.Objects;

public class Commercial extends Employe{
    private Double caAnnuel = null;
    private Integer performance = null;

    public Commercial() {   }

    public Commercial(Double caAnnuel) {
        this.caAnnuel = caAnnuel;
    }

    public Commercial(String nom, String prenom, String matricule, LocalDate dateEmbauche, Double salaire, Boolean tempsPartiel, String sexe, Double caAnnuel, Integer performance) {
        super(nom, prenom, matricule, dateEmbauche, salaire, tempsPartiel, sexe);
        this.caAnnuel = caAnnuel;
        this.performance = performance;
    }

    public Double getCaAnnuel() {
        return caAnnuel;
    }

    public void setCaAnnuel(Double caAnnuel) {
        this.caAnnuel = caAnnuel;
    }

    public void setCaAnnuel(Double caAnnuel, Integer performance) {
        this.caAnnuel = caAnnuel;
        this.performance = performance;
    }

    @Override
    public Double getPrimeAnnuelle() {
        return Math.max(Math.ceil(this.getCaAnnuel() * 0.05), 500);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Commercial that = (Commercial) o;
        return Objects.equals(caAnnuel, that.caAnnuel);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), caAnnuel);
    }

    public Boolean performanceEgale(Integer perfDemande ){
        return this.performance.equals(perfDemande);
    }

    public Note equivalenceNote(){

        switch (performance){
            case 0:

            case 50:
                return Note.INSUFISSANT;

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
