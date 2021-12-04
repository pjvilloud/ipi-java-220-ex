package com.ipiecoles.java.java220;
import org.joda.time.LocalDate;

import java.util.Objects;

public class Technicien extends Employe implements Comparable<Technicien> {

    private Integer grade;

    public Technicien() {
    }

    public Technicien(String nom, String prenom, String matricule, LocalDate dateEmbauche, Double salaire, Boolean tempsPartiel, String sexe, Integer grade) {
        super(nom, prenom, matricule, dateEmbauche, salaire, tempsPartiel, sexe);
        this.grade = grade;
    }


    public Integer getGrade() {
        return grade;
    }

    public void setGrade(Integer grade) {
        this.grade = grade;
    }

    public void setSalaire(Double salaire) {
        super.salaire = salaire;
    }

    public double getNbConges() {
        return super.getNbConges()+this.getNombreAnneeAnciennete();
    }

    public Double getPrimeAnnuelle() throws Exception {
        Double primeAnnuelleBase = Entreprise.primeAnnuelleBase();
        return primeAnnuelleBase + primeAnnuelleBase * ((double) grade / 10) + Entreprise.PRIME_ANCIENNETE * this.getNombreAnneeAnciennete();
    }


    public int compareTo(Technicien o) {
        return Integer.compare(this.grade, o.getGrade());
    }

    public int hashCode() {
        return Objects.hash(super.hashCode(), grade);
    }

}
