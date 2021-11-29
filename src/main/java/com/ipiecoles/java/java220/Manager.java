package com.ipiecoles.java.java220;

import org.joda.time.LocalDate;

import java.util.HashSet;

public class Manager extends Employe {
    private HashSet<Technicien> equipe = new HashSet<>();

    public Manager(String nom, String prenom, String matricule, LocalDate dateEmbauche, Double salaire, Boolean tempsPartiel, String sexe, HashSet<Technicien> equipe) {
        super(nom, prenom, matricule, dateEmbauche, salaire, tempsPartiel, sexe);
        this.equipe = equipe;
    }

    public Manager() {
    }

    public HashSet<Technicien> getEquipe() {
        return equipe;
    }

    public void setEquipe(HashSet<Technicien> equipe) {
        this.equipe = equipe;
    }

    public void ajoutTechnicienEquipe(Technicien tech) {
        this.equipe.add(tech);
    }

    public void ajoutTechnicienEquipe(String nom, String prenom, String matricule, LocalDate dateEmbauche, Double salaire, Boolean tempsPartiel, String sexe, Integer grade) {
        this.ajoutTechnicienEquipe((new Technicien(nom, prenom, matricule, dateEmbauche, salaire, tempsPartiel, sexe, grade)));
    }

    @Override
    public void augmenterSalaire(Double pourcentage) throws Exception {
        super.augmenterSalaire(pourcentage);
        this.augmenterSalaireEquipe(pourcentage);
    }

    private void augmenterSalaireEquipe(Double pourcentage) throws Exception {
        for(Technicien technicien : this.equipe){
            technicien.augmenterSalaire(pourcentage);
        }
    }

    @Override
    public Double getPrimeAnnuelle() {
        return Entreprise.primeAnnuelleBase() + equipe.size() * Entreprise.PRIME_MANAGER_PAR_TECHNICIEN;
    }

    @Override
    public void setSalaire(Double salaire) {
        Double salaire2 = salaire * Entreprise.INDICE_MANAGER + (salaire * 10 / 100) * equipe.size();
        super.setSalaire(salaire2);
    }
}
