package com.ipiecoles.java.java220;

import org.joda.time.DateTime;

public class Commercial extends Employe {

	private double caAnnuel;
	
	public Commercial() {
		
	};
	
	public Commercial(String nom, String prenom, String matricule, DateTime dateEmbauche, double salaire,
			double caAnnuel) {
		super(nom, prenom, matricule, dateEmbauche, salaire);
		this.caAnnuel = caAnnuel;
	}

	public double getPrimeAnnuelle() {
		return Math.max(Math.ceil(this.getCaAnnuel() * 0.05), 500);
	}

	public double getCaAnnuel() {
		return caAnnuel;
	}

	public void setCaAnnuel(double caAnnuel) {
		this.caAnnuel = caAnnuel;
	}

	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Commercial)) return false;
		if (!super.equals(o)) return false;

		Commercial that = (Commercial) o;

		return Double.compare(that.caAnnuel, caAnnuel) == 0;

	}
}
