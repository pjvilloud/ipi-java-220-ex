package com.ipiecoles.java.java220;

import org.joda.time.LocalDate;

import java.util.Objects;

import static jdk.nashorn.internal.parser.TokenType.CASE;
import static jdk.nashorn.internal.parser.TokenType.DEFAULT;

public class Commercial extends Employe {

    private Double caAnnuel;
    private Integer performance;

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

    public String equivalenceNote(Integer performance){
        switch (performance){
            case 200:
                return Objects.toString(Note.TRES_BIEN);
            case 150:
                return Objects.toString(Note.BIEN);
            case 100:
                return Objects.toString(Note.PASSABLE);
            case 50:
            case 0:
            DEFAULT:
               return Objects.toString(Note.INSUFFISANT);
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
        Employe emp = (Employe) o;
        return emp.equals(this);
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
