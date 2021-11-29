package com.ipiecoles.java.java220;

import org.joda.time.LocalDate;

import java.util.HashSet;
import java.util.Set;

public class Manager extends Employe{

    //private HashSet<Technicien> equipe;
   private HashSet<Technicien> equipe = new HashSet<>();
    @Override
    public Double getPrimeAnnuelle() {
        return Entreprise.primeAnnuelleBase() + Entreprise.PRIME_MANAGER_PAR_TECHNICIEN*equipe.size();
    }

    public HashSet<Technicien> getEquipe() {
        return equipe;
    }

    public void setEquipe(HashSet<Technicien> equipe) {
        this.equipe = equipe;
    }


    public Manager(String nom, String prenom, String matricule, LocalDate dateEmbauche, Double salaire, HashSet<Technicien> equipe) {
        super(nom, prenom, matricule, dateEmbauche, salaire);
        this.equipe = equipe;
    }
    public Manager (){

    }

    public Manager(HashSet<Technicien> equipe) {
        this.equipe = equipe;
    }


    public void setSalaire(Double salaire) {
        super.setSalaire(salaire*Entreprise.INDICE_MANAGER+0.10*salaire*equipe.size())  ;
    }

    @Override
    public void augmenterSalaire(Double augmentation) throws Exception {
        super.augmenterSalaire(augmentation);
        this.augmenterSalaireEquipe(augmentation);
    }

    private void augmenterSalaireEquipe(Double pourcentage) throws Exception {
        for (Technicien technicien:this.equipe) {
            technicien.augmenterSalaire(pourcentage);
        }
    }

    public void ajoutTechnicienEquipe(Technicien technicien){
        this.equipe.add(technicien);
    }
    public void ajoutTechnicienEquipe(String nom, String prenom, String matricule,LocalDate dateEmbauche,Double salaire,Integer grade){
        this.ajoutTechnicienEquipe(new Technicien(nom,prenom,matricule,dateEmbauche,salaire,grade));
    }

}
