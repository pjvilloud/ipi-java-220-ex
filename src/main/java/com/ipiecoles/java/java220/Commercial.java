package com.ipiecoles.java.java220;

import org.joda.time.LocalDate;

public class Commercial extends Employe {

    private Double caAnnuel;

    public Commercial() {
    }

    public Commercial(String nom, String prenom, String matricule, LocalDate dateEmbauche, Double salaire) {
        super(nom, prenom, matricule, dateEmbauche, salaire);
        this.caAnnuel=0d;
    }

    @Override
    public Double getPrimeAnnuelle() {
        if(this.caAnnuel==null) return 500d;
        return (this.caAnnuel*0.05<500 ? 500 : Math.rint(this.caAnnuel*0.05));
    }

    public Double getCaAnnuel() {
        return caAnnuel;
    }

    public void setCaAnnuel(Double caAnnuel) {
        this.caAnnuel = caAnnuel;
    }
}
