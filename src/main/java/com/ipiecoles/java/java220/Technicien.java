package com.ipiecoles.java.java220;

import org.joda.time.LocalDate;

import java.util.Objects;

public class Technicien extends Employe implements Comparable<Technicien> {

    // Attributs
    private Integer grade;

    // Constructeurs
    public Technicien() {
    }

    public Technicien(String nom, String prenom, String matricule, LocalDate dateEmbauche, Double salaire, Integer grade) {
        super(nom, prenom, matricule, dateEmbauche, salaire);
        this.grade = grade;
    }

    // Methodes
    @Override
    public Integer getNbConges() {
        return super.getNbConges() + super.getNombreAnneeAnciennete();
    }

    @Override
    public Double getPrimeAnnuelle() {
        Double primeAnnuellebase = Entreprise.primeAnnuelleBase();
        return primeAnnuellebase + primeAnnuellebase * ((double) this.grade / 10) + Entreprise.PRIME_ANCIENNETE * this.getNombreAnneeAnciennete();
    }

    @Override
    public void setSalaire(Double salaire) {
        super.setSalaire(salaire + salaire * (this.grade * 0.1));
    }

    @Override
    public int compareTo(Technicien o) {
        return this.grade.compareTo(o.grade);
    }

    // Getters / Setters
    public Integer getGrade() {
        return grade;
    }

    public void setGrade(Integer grade) {
        this.grade = grade;
    }

    // Methodes Objet

    @Override
    public String toString() {
        return String.format("Technicien{nom='%s', prenom='%s', matricule='%s', dateEmbauche=%s, salaire=%s, grade=%s}",
                super.getNom(), super.getPrenom(), super.getMatricule(), super.getDateEmbauche(), super.getSalaire().toString(), this.grade);
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (!(object instanceof Technicien)) return false;
        if (!super.equals(object)) return false;

        Technicien technicien = (Technicien) object;

        return Objects.equals(this.grade, technicien.grade);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), this.grade);
    }
}
