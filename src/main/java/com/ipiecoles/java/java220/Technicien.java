package com.ipiecoles.java.java220;
import org.joda.time.LocalDate;

import java.util.Objects;

public class Technicien extends Employe implements Comparable<Technicien>{

    private Integer grade;

    public Technicien(){

    }

    public Technicien(String name, String fName, String matric, LocalDate dateEmb, Double salary, Integer grade) {
        super(name, fName, matric, dateEmb, salary);
        this.setGrade(grade);

    }



    //Getter and setter
    public Integer getGrade() {
        return grade;
    }
    public void setGrade(Integer grade) {
        this.grade = grade;
    }

    @Override
    public Double getPrimeAnnuelle(){

        return Entreprise.primeAnnuelleBase()+
                (Entreprise.primeAnnuelleBase()*Double.parseDouble("0."+this.getGrade()))+
                super.getNombreAnneeAnciennete()*100;
    }

    @Override
    public void setSalaire(Double salaire) {

        super.setSalaire(salaire+this.getGrade()*100);
    }
    @Override
    public Integer getNbConges(){

        //return Entreprise.NB_CONGES_BASE+this.getNombreAnneeAnciennete();
        return super.getNbConges() + this.getNombreAnneeAnciennete();
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
    public int compareTo(Technicien tech){
        return Integer.compare(this.grade, tech.getGrade());
    }
}
