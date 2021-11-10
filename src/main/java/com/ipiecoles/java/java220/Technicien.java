package com.ipiecoles.java.java220;
import org.joda.time.LocalDate;

public class Technicien extends Employe{

    public Technicien(String name, String fName, String matric, LocalDate dateEmb, Double salary, Integer grade) {
        super(name, fName, matric, dateEmb, salary);
        this.grade = grade;
    }

    private Integer grade;
    public Technicien(){

    }
    public Double getPrimeAnnuelle(){
        return Entreprise.primeAnnuelleBase()*(1+grade)
    }
    public Integer getGrade() {
        return grade;

    }
    public void setGrade(Integer grade) {
        this.grade = grade;
    }

}
