package com.ipiecoles.java.java220;

import com.ipiecoles.java.java220.exceptions.TechnicienException;
import org.joda.time.LocalDate;

import java.util.Objects;

public class Technicien extends Employe implements Comparable<Technicien>{
    private Integer grade;

    public Technicien(){}

    public Technicien(String nom, String prenom, String matricule, LocalDate dateEmbauche, Double salaire, Integer grade) {
        super(nom, prenom, matricule, dateEmbauche, salaire);
        this.grade = grade;
    }

    public Integer getGrade() {
        return grade;
    }

    public void setGrade(Integer grade) throws TechnicienException{
        if(grade <= 0 || grade > 5) {
            throw new TechnicienException(TechnicienException.GRADE, this, grade);
        }
        this.grade = grade;
    }

    @Override
    public void setSalaire(Double salaire) {
        super.setSalaire(salaire*(1.0+grade/10.0));
    }

    @Override
    public Integer getNbConges() {
        return super.getNbConges()+super.getNombreAnneeAnciennete();
    }

    @Override
    public Double getPrimeAnnuelle() {
        return Entreprise.primeAnnuelleBase() * (1.0+grade/10.0) + Entreprise.PRIME_ANCIENNETE * super.getNombreAnneeAnciennete();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Technicien)) return false;
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
    //if(this.grade < o.getGrade())){
    //    return -1;
    //} else if(this.grade > o.getGrade()) {
    //    return 1;
    //}
    //return 0;
        return Integer.compare(this.grade, o.getGrade());
    }
}
