package com.ipiecoles.java.java220;

import org.joda.time.LocalDate;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.HashSet;
import java.util.Objects;


public class Technicien extends Employe implements Comparable<Technicien>{

    private Integer grade;
    private Integer salaire;

    public Integer getGrade() {
        return grade;
    }

    public void setGrade(Integer grade) {
        this.grade = grade;
    }

    public Technicien(String nom, String prenom, String matricule, LocalDate dateEmbauche, Double salaire, Integer grade) {
        super(nom, prenom, matricule, dateEmbauche, salaire);
        this.grade = grade;
    }

    public Technicien() {
    }

    @Override
    public void setSalaire(Double salaire) {
        super.setSalaire(salaire+((this.grade*0.10)*salaire));
    }

    @Override
    public Integer getNbConges() {
        return (super.getNbConges()+getNombreAnneeAnciennete());
    }

    @Override
    public Double getPrimeAnnuelle() {
        try {
            return Entreprise.primeAnnuelleBase() * (1 + (double) grade / 10) + Entreprise.PRIME_ANCIENNETE * this.getNombreAnneeAnciennete() ;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public int compareTo(Technicien o) {
       return Integer.compare(this.grade, o.getGrade());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Technicien)) return false;
        if (!super.equals(o)) return false;
        Technicien that = (Technicien) o;
        return Objects.equals(grade, that.grade) && Objects.equals(salaire, that.salaire);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), grade, salaire);
    }
}
