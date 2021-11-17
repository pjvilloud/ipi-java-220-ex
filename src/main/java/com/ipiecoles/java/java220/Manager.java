package com.ipiecoles.java.java220;

import org.joda.time.LocalDate;

import java.util.HashSet;
import java.util.List;

public class Manager  extends Employe{
    // attributs
    private HashSet<Technicien> equipe = new HashSet();

    // getters
    public HashSet<Technicien> getEquipe() {return equipe;}

    //setters
    public void setEquipe(HashSet<Technicien> newTechniciens) {
        for (Technicien technicien : newTechniciens) {
            this.equipe.add(technicien);
        }
    }
    public void setSalaire(Double newSalaire){
        super.setSalaire(newSalaire * Entreprise.INDICE_MANAGER + (newSalaire * (double)this.equipe.size()/ 10));
    }

    public void ajoutTechnicienEquipe(Technicien newTechnicien) {
        if (newTechnicien != null && (newTechnicien instanceof Technicien)){
            this.equipe.add(newTechnicien);
        }
    }

    public void ajoutTechnicienEquipe(String nom, String prenom, String matricule, LocalDate dateEmbauche, Double salaire, Integer grade) {
        this.ajoutTechnicienEquipe(new Technicien(nom,prenom,matricule,dateEmbauche, salaire, grade));

    }
    public Double getPrimeAnnuelle() {
        return (Entreprise.primeAnnuelleBase() + Entreprise.PRIME_MANAGER_PAR_TECHNICIEN * equipe.size());
    }

    public void augmenterSalaire(Double pourcentage){
        super.augmenterSalaire(pourcentage);
        augmenterSalaireEquipe(pourcentage);
    }
    private void augmenterSalaireEquipe(Double augmentation) {
        for (Technicien technicien : equipe) {
            technicien.augmenterSalaire(augmentation);
        }
    }

    //public List<Technicien> equipeParGrade(){
    //    return equipe.stream().filter(technicien -> )
    //}
}
