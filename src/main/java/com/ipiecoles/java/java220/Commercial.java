package com.ipiecoles.java.java220;

import org.joda.time.LocalDate;

import java.util.Objects;

import static jdk.nashorn.internal.parser.TokenType.CASE;
import static jdk.nashorn.internal.parser.TokenType.DEFAULT;

public class Commercial extends Employe {

    private Double caAnnuel;
    private Integer performance;

    public Integer getPerformance() {
        return performance;
    }

    public void setPerformance(Integer performance) {
        this.performance = performance;
    }

    public Commercial() {
    }

    public Commercial(String nom, String prenom, String matricule, LocalDate dateEmbauche, Double salaire, Double caAnnuel) {
        super(nom, prenom, matricule, dateEmbauche, salaire);
        this.caAnnuel = caAnnuel;
    }

    public Commercial(String nom, String prenom, String matricule, LocalDate dateEmbauche, Double salaire, Double caAnnuel, Integer performance) {
        super(nom, prenom, matricule, dateEmbauche, salaire);
        this.caAnnuel = caAnnuel;
        this.performance = performance;
    }

    public boolean performanceEgale(Integer performance){
        return this.performance.equals(performance);
    }

    public Note equivalenceNote(){
        switch (this.performance){
            case 200:
                return Note.TRES_BIEN;
            case 150:
                return Note.BIEN;
            case 100:
                return Note.PASSABLE;
            case 50:
            case 0:
            DEFAULT:
               return Note.INSUFFISANT;
        }
        return null;
    }

    @Override
    public Double getPrimeAnnuelle() {
        if(this.caAnnuel==null) return 500d;
        return (this.caAnnuel*0.05<500 ? 500 : Math.ceil(this.caAnnuel*0.05));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Commercial com = (Commercial) o;
        if (!Objects.equals(caAnnuel, com.caAnnuel))
            return false;
        return super.equals(o);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), caAnnuel);
    }

    public Double getCaAnnuel() {
        return caAnnuel;
    }

    public void setCaAnnuel(Double caAnnuel) {
        this.caAnnuel = caAnnuel;
    }
}
