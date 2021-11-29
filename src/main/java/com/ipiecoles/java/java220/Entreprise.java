package com.ipiecoles.java.java220;


import org.joda.time.LocalDate;

public class Entreprise {
    public static final Double SALAIRE_BASE = 1480.27;
    public static final Integer NB_CONGES_BASE = 25;
    public static final Double INDICE_MANAGER = 1.3;
    public static final Double PRIME_MANAGER_PAR_TECHNICIEN = 250.0;
    public static final Double PRIME_ANCIENNETE = 100d;//pour definir une valeur en double sois on met ".0" a la fin sois on rajoute un "d"

    public static Double primeAnnuelleBase() {
        double prime;
        prime = (LocalDate.now().getYear()/2d);
        return prime;
    }
}
