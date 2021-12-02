package com.ipiecoles.java.java220;

import org.joda.time.LocalDate;

import java.text.Collator;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

public class Manager extends Employe{

    private HashSet<Technicien> equipe = new HashSet<>();

    public Manager(){}

    public Manager(String nom, String prenom, String matricule, LocalDate dateEmbauche, Double salaire,Boolean tempsPartiel, String sexe, HashSet<Technicien> equipe){
        super(nom, prenom, matricule, dateEmbauche, salaire, tempsPartiel,sexe);
        this.equipe = equipe;
    }
    @Override
    public Double getPrimeAnnuelle() {
        return Entreprise.primeAnnuelleBase() + equipe.size() * Entreprise.PRIME_MANAGER_PAR_TECHNICIEN;
    }

    public HashSet<Technicien> getEquipe() {
        return equipe;
    }

    public void setEquipe(HashSet<Technicien> equipe) {
        this.equipe = equipe;
    }

    public void ajoutTechnicienEquipe(Technicien technicien){
        equipe.add(technicien);
    }

    public void ajoutTechnicienEquipe(String nom, String prenom, String matricule, LocalDate dateEmbauche, Double salaire, Integer grade,Boolean tempsPartiel, String sexe) throws TechnicienException {
        this.ajoutTechnicienEquipe(new Technicien(nom, prenom, matricule, dateEmbauche, salaire, grade, tempsPartiel,sexe));
    }

    public void setSalaire(Double salaire){
        super.setSalaire(salaire * Entreprise.INDICE_MANAGER + (salaire * (double)equipe.size() / 10));
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

    public List<Technicien> equipeParGrade(){
        return equipe.stream().sorted(Technicien::compareTo).collect(Collectors.toList());
    }

    public double salaireEquipeGrade1(){
        return equipe.stream().filter(t -> t.getGrade().equals(1)).mapToDouble(Technicien::getSalaire).sum();
    }

}
