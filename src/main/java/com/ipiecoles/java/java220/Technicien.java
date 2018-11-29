package com.ipiecoles.java.java220;

import org.joda.time.LocalDate;

import java.util.Objects;

public class Technicien extends Employe implements Comparable<Technicien> {

    private Integer grade;

    public Technicien() {
    }

    public Technicien(String nom, String prenom, String matricule, LocalDate dateEmbauche, Double salaire, Integer grade) {
        super(nom, prenom, matricule, dateEmbauche, salaire);
        try {
            this.setGrade(grade);
        }
        catch (TechnicienException e){
            System.out.println(e.getMessage());
        }
    }

    @Override
    public Double getPrimeAnnuelle() {
        return Entreprise.primeAnnuelleBase()*(1+grade*0.1)+Entreprise.PRIME_ANCIENNETE*this.anciennete();
    }

    @Override
    public String toString() {
        return "Technicien{" +
                "grade=" + this.grade +
                '}'+ super.toString();
    }

    public String toString(Integer grade) {
        return "Technicien{" +
                "grade=" + grade +
                '}'+ super.toString();
    }

    public Integer getGrade() {
        return grade;
    }

    public void setGrade(Integer grade) throws TechnicienException {
        if (grade < 1 || grade > 5)
            throw new TechnicienException("Le grade doit être compris entre 1 et 5 :" + grade + ", technicien : " + this.toString(grade));
        else
            this.grade = grade;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), grade);
    }

    public void setSalaire(Double salaire) {
        this.setSalaire(this.getSalaire()*(1+grade*0.1));
    }

     @Override
     public Integer getNbConges() {
         return super.getNbConges() + this.anciennete();
     }


    @Override
    public int compareTo(Technicien technicien) {
        if (this==technicien) return 0;
        if(technicien.getGrade().equals(this.grade))
            return 0;
        else if(technicien.getGrade()<this.grade)
            return -1;
        else
            return 1;
    }
}
