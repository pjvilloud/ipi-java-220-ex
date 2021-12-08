package com.ipiecoles.java.java220;

import com.ipiecoles.java.java220.exceptions.TechnicienException;
import org.joda.time.LocalDate;

import java.util.HashSet;

public class Manager extends Employe{
    private HashSet<Technicien> equipe = new HashSet();

    public Manager() {
    }

    public Manager(String nom, String prenom, String matricule, LocalDate dateEmbauche, Double salaire, Boolean tempsPartiel, String sexe, HashSet<Technicien> equipe) {
        super(nom, prenom, matricule, dateEmbauche, salaire, tempsPartiel, sexe);
        this.equipe = equipe;
    }

    public HashSet<Technicien> getEquipe() {
        return equipe;
    }


    public void setEquipe(HashSet<Technicien> equipe) {
        this.equipe = equipe;
    }

    public void ajoutTechnicienEquipe(Technicien t){
        equipe.add(t);
    }

    @Override
    public void setSalaire(Double salaire) {
        super.setSalaire((salaire * Entreprise.INDICE_MANAGER) + (salaire * (equipe.size() * 0.1)));
    }

    @Override
    public Double getPrimeAnnuelle() {
        return Entreprise.primeAnnuelleBase() + equipe.size() * Entreprise.PRIME_MANAGER_PAR_TECHNICIEN;
    }

    private void augmenterSalaireEquipe(Double pourcentage){
        for(Technicien tech : equipe){
            tech.augmenterSalaire(pourcentage);
        }
    }

    @Override
    public void augmenterSalaire(Double pourcentage) {
        augmenterSalaireEquipe(pourcentage);
        super.augmenterSalaire(pourcentage);
    }

    public void ajoutTechnicienEquipe(String nom, String prenom, String matricule, LocalDate dateEmbauche, Double salaire, Boolean tempsPartiel, String sexe,Integer grade) throws TechnicienException {
        this.ajoutTechnicienEquipe(new Technicien(nom, prenom, matricule, dateEmbauche, salaire, tempsPartiel, sexe, grade));
    }

}
