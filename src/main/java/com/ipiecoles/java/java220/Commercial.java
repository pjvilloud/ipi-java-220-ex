package com.ipiecoles.java.java220;

import org.joda.time.LocalDate;

import java.lang.annotation.Target;

public class Commercial extends Employe {
    // attributs
    private Double caAnnuel;
    private Integer performance;

    // getters
    public Double getCaAnnuel() { return caAnnuel; }
    public Integer getPerformance() { return performance;}

    // setters
    public void setCaAnnuel(Double caAnnuel) { this.caAnnuel = caAnnuel; }
    public void setPerformance(Integer performance) {this.performance = performance;}

    // constructeurs
    public Commercial(){

    }

    public Commercial(String newNom, String newPrenom, String newMatricule, LocalDate newDateEmbauche, Double newSalaire, Double newCAAnnuel) {
        super(newNom,newPrenom,newMatricule,newDateEmbauche,newSalaire);
        this.caAnnuel = newCAAnnuel;
    }

    public Commercial(String newNom, String newPrenom, String newMatricule, LocalDate newDateEmbauche, Double newSalaire, Double newCAAnnuel, Integer newPerformance) {
        this(newNom,newPrenom,newMatricule,newDateEmbauche,newSalaire,newCAAnnuel);
        this.performance = newPerformance;
    }

    // methodes
    public Double getPrimeAnnuelle() {
        if(this.caAnnuel != null && this.caAnnuel*0.05 > 500) { return Math.ceil(this.caAnnuel*0.05); }
        return (500d);
    }

    public Boolean performanceEgale(Integer testPerformance){
        return this.performance.equals(testPerformance);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Commercial)) return false;

        if(!super.equals(o)) return false;

        Commercial commercial = (Commercial) o;

        return Double.compare(commercial.caAnnuel, caAnnuel) == 0;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Commercial{");
        sb.append("nom='").append(super.getNom()).append('\'');
        sb.append(", prenom='").append(super.getPrenom()).append('\'');
        sb.append(", matricule='").append(super.getMatricule()).append('\'');
        sb.append(", dateEmbauche=").append(super.getDateEmbauche());
        sb.append(", salaire=").append(super.getSalaire());
        sb.append(", tempsPartiel=").append(super.getTempsPartiel());
        sb.append(", sexe=").append(super.getSexe());
        sb.append(", caAnnuel=").append(caAnnuel);
        sb.append(", performance=").append(performance);
        sb.append('}');
        return sb.toString();
    }

    public Note equivalenceNote (){
        switch (this.performance){
            case 0:
            case 50:
                return Note.INSUFFISANT;
            case 100:
                return Note.PASSABLE;
            case 150:
                return Note.BIEN;
            case 200:
                return Note.TRES_BIEN;
            default : return null;
        }
    }
}
