package com.ipiecoles.java.java220;

import org.joda.time.LocalDate;

import java.util.HashSet;

public class Manager extends Employe{

    private HashSet<Technicien> equipe = new HashSet();

    public  Manager(){

    }

    public Manager(String nom, String prenom, String matricule, LocalDate dateEmbauche, Double salaire, HashSet<Technicien> equipe) {
        super(nom, prenom, matricule, dateEmbauche, salaire);
        this.equipe = equipe;
    }

    @Override
    public Double getPrimeAnnuelle() {
        return Entreprise.primeAnnuelleBase() + equipe.size() * Entreprise.PRIME_MANAGER_PAR_TECHNICIEN;
    }

    public void ajoutTechnicienEquipe(Technicien technicien){
        equipe.add(technicien);
    }

    public void ajoutTechnicienEquipe(String nom, String prenom, String matricule, LocalDate dateEmbauche, Double salaire, Integer grade) throws TechnicienException {
        this.ajoutTechnicienEquipe(new Technicien(nom, prenom, matricule, dateEmbauche, salaire, grade));
    }

    @Override
    public void setSalaire(Double salaire) {
        super.setSalaire(salaire * Entreprise.INDICE_MANAGER + (salaire * equipe.size() / 10d));
    }

    public void augmenterSalaire(Double pourcentage) {
        super.augmenterSalaire(pourcentage);
        augmenterSalaireEquipe(pourcentage);
    }

    private void augmenterSalaireEquipe(Double pourcentage) {
        for (Technicien technicien : equipe) {
            technicien.augmenterSalaire(pourcentage);
        }
    }


    public HashSet<Technicien> getEquipe() {
        return equipe;
    }

    public void setEquipe(HashSet<Technicien> equipe) {
        this.equipe = equipe;
    }

    private class TechnicienException extends Exception {
    }

    @Override
    public String toString() {
        return "Manager{} " + super.toString();
    }
}
