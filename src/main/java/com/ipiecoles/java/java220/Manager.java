package com.ipiecoles.java.java220;

import org.joda.time.LocalDate;

import java.util.HashSet;


public class Manager extends Employe{

    private HashSet<Technicien> equipe = new HashSet();

    public Manager() {

    }

    public Manager(String name, String fName, String matric, LocalDate dateEmb, Double salary, HashSet<Technicien> equipe) {
        super(name, fName, matric, dateEmb, salary);
        this.equipe = equipe;
    }

    public void ajoutTechnicienEquipe(Technicien tec){
        equipe.add(tec);
    }

    public void ajoutTechnicienEquipe(String nom, String prenom, String matricule, LocalDate dateEmbauche, Double salaire, Integer grade) {
        this.ajoutTechnicienEquipe(new Technicien(nom, prenom, matricule, dateEmbauche, salaire, grade));
    }

    private void augmenterSalaireEquipe(Double pourcentage) {
        for (Technicien tec : equipe) {
            tec.augmenterSalaire(pourcentage);
        }
    }
    public void augmenterSalaire(Double pourcentage) {
        super.augmenterSalaire(pourcentage);
        augmenterSalaireEquipe(pourcentage);
    }

    @Override
    public void setSalaire(Double salaire) {
        super.setSalaire(salaire * Entreprise.INDICE_MANAGER + (salaire * (double)equipe.size() / 10));
    }

    public Double getPrimeAnnuelle(){
        System.out.println(equipe.size());
        System.out.println(equipe);
        return Entreprise.primeAnnuelleBase() + equipe.size() * Entreprise.PRIME_MANAGER_PAR_TECHNICIEN;
    }


    //getter and setter
    public HashSet<Technicien> getEquipe() {
        return equipe;
    }

    public void setEquipe(HashSet<Technicien> equipe) {
        this.equipe= equipe;
    }

    @Override
    public String toString() {
        return "Manager{} " + super.toString();
    }
}
