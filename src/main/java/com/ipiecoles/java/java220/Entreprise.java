package com.ipiecoles.java.java220;

import com.sun.source.tree.ReturnTree;
import org.joda.time.LocalDate;

public final class Entreprise {
    public static final Double SALAIRE_BASE = 1480.27;
    public static final Integer NB_CONGES_BASE = 25;
    public static final Integer NB_RTT_BASE = 12;
    public static final Double INDICE_MANAGER = 1.3;
    public static final Double PRIME_MANAGER_PAR_TECHNICIEN = 250d;
    public static final Double PRIME_ANCIENNETE = 100d;
    public static final Double BASE_PRIME_ANNUELLE = 20d;

    public static Double primeAnnuelleBase() {
        return BASE_PRIME_ANNUELLE;
    }
}