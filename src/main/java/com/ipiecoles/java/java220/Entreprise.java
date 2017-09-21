package com.ipiecoles.java.java220;

import org.joda.time.DateTime;

public final class Entreprise {
	public static final double SALAIRE_BASE = 1480.27;
	public static final int NB_CONGES_BASE = 25;
	public static final int NB_RTT_BASE = 12;
	public static final double INDICE_MANAGER = 1.3;
	public static final double PRIME_MANAGER_PAR_TECHNICIEN = 250;
	public static final double PRIME_ANCIENNETE = 100;
	
	public static double primeAnnuelleBase() {
		return DateTime.now().getYear() * 0.5;
	}
}
