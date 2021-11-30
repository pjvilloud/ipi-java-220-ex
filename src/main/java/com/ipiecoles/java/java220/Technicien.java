package com.ipiecoles.java.java220;

import org.joda.time.LocalDate;

public class Technicien extends Employe implements Comparable<Technicien>{
    private Integer grade;

    public Technicien(){

    }

    public Technicien(String nom, String prenom, String matricule, LocalDate dateEmbauche, Double salaire, Integer grade) {
        super(nom, prenom, matricule, dateEmbauche, salaire);
        this.grade = grade;
    }

    @Override
    public void setSalaire(Double salaire) {
        super.setSalaire(salaire * (1 + grade / 10d));
    }

    @Override
    public Integer getNbConges() {
        return super.getNbConges() + this.getNombreAnneeAnciennete();
    }

    @Override
    public Double getPrimeAnnuelle() {
        return Entreprise.primeAnnuelleBase()* (1 + grade / 10d)
                + Entreprise.PRIME_ANCIENNETE * this.getNombreAnneeAnciennete();
    }

    public Integer getGrade() {
        return grade;
    }

    public void setGrade(Integer grade) {
        this.grade = grade;
    }

    @Override
    public int compareTo(Technicien o) {
        if (this.grade <o.getGrade()){
            return -1;
        }
        else  if (this.grade > o.getGrade()){
            return 1;
        }
        return 0;
    }
}
