package com.ipiecoles.java.java220;

public class Cadre extends Employe {
    private static Double coefficient = 1d;
    // (dans le doute car, je n'avais pas compris l'énoncé) public static final Integer NB_CONGES_BASE = 25;

    public Double getCoefficient() {
        return coefficient;
    }

    public void setCoefficient(Double coefficient) {
        this.coefficient = coefficient;
    }

    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        if (!super.equals(object)) return false;
        Cadre cadre = (Cadre) object;
        return java.util.Objects.equals(coefficient, cadre.coefficient);
    }

    public int hashCode() {
        return java.util.Objects.hash(super.hashCode(), coefficient);
    }

    @Override
    public String toString() {
        return "Cadre{" +
                "coefficient=" + coefficient +
                '}';
    }

    public Double getPrimeAnnuelle() throws Exception {
        return Entreprise.BASE_PRIME_ANNUELLE * Cadre.coefficient;
    }
    public double getNbConges() {
        return Entreprise.NB_CONGES_BASE + Cadre.coefficient;
    }

}
