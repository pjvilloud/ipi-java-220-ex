package com.ipiecoles.java.java220;

import org.joda.time.LocalDate;

import java.util.HashSet;
import java.util.List;

public class Manager extends Employe {

    // Attributs
    private HashSet<Technicien> equipe = new HashSet<>();

    // Constructeurs
    public Manager() {
    }

    public Manager(HashSet<Technicien> equipe) {
        this.equipe = equipe;
    }

    // Methodes
    public void ajoutTechnicienEquipe(Technicien technicien) {
        this.equipe.add(technicien);
    }

    public void ajoutTechnicienEquipe(String nom, String prenom, String matricule, LocalDate dateEmbauche, Double salaire, Integer grade) {
        this.equipe.add(new Technicien(nom, prenom, matricule, dateEmbauche, salaire, grade));
    }

    public Double getPrimeAnnuelle() {
        Double primeManager = Entreprise.PRIME_MANAGER_PAR_TECHNICIEN;
        // Prime de base + prime manager en fonction du nombre de techniciens
        return Entreprise.primeAnnuelleBase() + (primeManager * this.equipe.size());

    }

    public List<Technicien> equipeParGrade() {
        // List techniciens par grade décroissant
        return this.equipe.stream().sorted().toList();
    }

    public Double salaireEquipeGrade1() {
        // Somme des salaires des technicien de grade 1
        return this.equipe.stream().filter(x -> x.getGrade() == 1).mapToDouble(x -> x.getSalaire()).sum();
    }

    @Override
    public void setSalaire(Double salaire) {
        Double indiceManager = Entreprise.INDICE_MANAGER;
        // Salaire multiplié par indice manager + 10% du salaire pour chaque technicien
        super.setSalaire(salaire * indiceManager + salaire * (this.equipe.size() * 0.1));
    }

    private void augmenterSalaireEquipe(Double pourcentage) {
        for (Technicien technicien : this.equipe) {
            technicien.augmenterSalaire(pourcentage);
        }
    }
    
    @Override
    public void augmenterSalaire(Double pourcentage) {
        this.augmenterSalaireEquipe(pourcentage);
        super.augmenterSalaire(pourcentage);
    }

    // Getters / Setters
    public HashSet<Technicien> getEquipe() {
        return this.equipe;
    }

    public void setEquipe(HashSet<Technicien> equipe) {
        this.equipe = equipe;
    }
}
