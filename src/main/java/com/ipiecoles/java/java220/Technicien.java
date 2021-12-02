package com.ipiecoles.java.java220;

import org.joda.time.LocalDate;
import com.ipiecoles.java.java220.TechnicienException;

import java.util.Objects;

public class Technicien extends Employe implements Comparable<Technicien>{

    private Integer grade;
    public Technicien(){}

    public Technicien (String nom, String prenom, String matricule, LocalDate dateEmbauche, Double salaire, Integer grade,Boolean tempsPartiel, String sexe) throws TechnicienException{
        super(nom, prenom, matricule, dateEmbauche, salaire, tempsPartiel,sexe);
        this.grade = grade;
    }

    public Integer getGrade() {
        return grade;
    }

    /**
     * @param grade the grade to set
     * @Throws TechnicienException
     */
    public void setGrade(Integer grade) throws TechnicienException {
        if(grade <= 0 || grade > 5) {
            throw new TechnicienException(TechnicienException.GRADE, this ,grade);
        }
        this.grade = grade;
    }

    @Override
    public void setSalaire (Double salaire){
        super.setSalaire(salaire * (1+ (double) grade / 10));
    }

    @Override
    public Integer getNbConges(){
        return super.getNbConges() + this.getNombreAnneeAnciennete();
    }

    @Override
    public Double getPrimeAnnuelle(){
        Double primeAnnuelleBase = Entreprise.primeAnnuelleBase();
        return primeAnnuelleBase + primeAnnuelleBase * ((double)grade / 10 )+ Entreprise.PRIME_ANCIENNETE * this.getNombreAnneeAnciennete();
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), grade);
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
        return Objects.equals(grade, that.grade);
    }

    @Override
    public String toString() {
        return "Technicien{" +
                "grade=" + grade +
                "} " + super.toString();
    }

}
