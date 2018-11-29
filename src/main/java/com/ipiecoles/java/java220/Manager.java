package com.ipiecoles.java.java220;

import org.joda.time.LocalDate;

import java.util.ArrayList;
import java.util.HashSet;

public class Manager extends Employe {

    private HashSet<Technicien> equipe = new HashSet<>();

    public Manager() {
    }

    public Manager(String nom, String prenom, String matricule, LocalDate dateEmbauche, Double salaire) {
        super(nom, prenom, matricule, dateEmbauche, salaire);
    }

    public void ajoutTechnicienEquipe(Technicien technicien){
        this.equipe.add(technicien);
    }

    public void ajoutTechnicienEquipe(String nom, String prenom, String matricule, LocalDate dateEmbauche, Double salaire, Integer grade){
        this.equipe.add(new Technicien(nom,prenom,matricule,dateEmbauche,salaire,grade));
    }

    private void augmenterSalaireEquipe(Double pourcentage){
        for(Technicien technicien : this.equipe){
            technicien.augmenterSalaire(pourcentage);
        }
    }

    public ArrayList<Technicien> equipeParGrade(){
        ArrayList<Technicien> equipeTriee= new ArrayList<>();
        /*
        for (Technicien technicien : equipe){
            equipeTriee.add(technicien);
        }
        */
        this.equipe.stream().forEach( technicien -> equipeTriee.add(technicien) );

        equipeTriee.sort(Technicien::compareTo);
        return equipeTriee;
    }

    public Double salaireEquipeGrade1() {
        return this.equipe.stream()
                .filter(technicien -> technicien.getGrade().equals(1))
                .mapToDouble(Technicien::getSalaire)
                .sum();
    }

    @Override
    public void augmenterSalaire(Double pourcent) {
        this.augmenterSalaireEquipe(pourcent);
        super.augmenterSalaire(pourcent);
    }

    @Override
    public Double getPrimeAnnuelle() {
        return Entreprise.primeAnnuelleBase()+Entreprise.PRIME_MANAGER_PAR_TECHNICIEN*this.equipe.size();
    }

    @Override
    public void setSalaire(Double salaire) {
        super.setSalaire(Math.rint(salaire*(Entreprise.INDICE_MANAGER+0.1*this.equipe.size())));
    }

    public HashSet<Technicien> getEquipe() {
        return equipe;
    }

    public void setEquipe(HashSet<Technicien> equipe) {
        this.equipe = equipe;
    }

}
