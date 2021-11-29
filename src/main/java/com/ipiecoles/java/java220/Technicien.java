package com.ipiecoles.java.java220;

import org.joda.time.LocalDate;

import java.util.Objects;

public class Technicien extends Employe implements Comparable<Technicien>{
    private Integer grade;

    public Technicien(String nom, String prenom, String matricule, LocalDate dateEmbauche, Double salaire, Boolean tempsPartiel, String sexe, Integer grade) {
        super(nom, prenom, matricule, dateEmbauche, salaire, tempsPartiel, sexe);
        this.grade = grade;
    }

    public Technicien(){
    }

    public Integer getGrade() {
        return grade;
    }

    public void setGrade(Integer grade) {
        this.grade = grade;
    }

    @Override
    public void setSalaire(Double salaire) {
        Double salaire2 = salaire + (salaire * grade * 10 / 100);
        super.setSalaire(salaire2);
    }

    @Override
    public Integer getNbConges() {
        return super.getNbConges() + getNombreAnneeAnciennete();
    }

    @Override
    public Double getPrimeAnnuelle() {
        return Entreprise.primeAnnuelleBase() + (Entreprise.primeAnnuelleBase() * grade * 10 / 100) + (Entreprise.PRIME_ANCIENNETE * getNombreAnneeAnciennete());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Technicien that = (Technicien) o;
        return Objects.equals(grade, that.grade);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), grade);
    }

    @Override
    public int compareTo(Technicien o) {
        if(this.getGrade() < o.getGrade()){
            return -1;
        } else if (this.getGrade() > o.getGrade()){
            return 1;
        }
        return 0;
    }
}
