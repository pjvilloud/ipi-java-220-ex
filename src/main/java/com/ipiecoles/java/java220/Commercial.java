package com.ipiecoles.java.java220;

import org.joda.time.LocalDate;

public class Commercial extends Employe {

	private Double caAnnuel = 0d;

	private Integer performance;

	public Commercial() {
		
	}
	
	public Commercial(String nom, String prenom, String matricule, LocalDate dateEmbauche, Double salaire,
			Double caAnnuel) {
		super(nom, prenom, matricule, dateEmbauche, salaire);
		this.caAnnuel = caAnnuel;
	}

	public Commercial(String nom, String prenom, String matricule, LocalDate dateEmbauche, Double salaire,
					  Double caAnnuel, Integer performance) {
		this(nom, prenom, matricule, dateEmbauche, salaire, caAnnuel);
		this.performance = performance;
	}

	public Double getPrimeAnnuelle() {
		return Math.max(Math.ceil(this.getCaAnnuel() * 0.05), 500);
	}

	public Double getCaAnnuel() {
		return caAnnuel;
	}

	public Note equivalenceNote(){
		switch (performance) {
			case 0:
			case 50:
				return Note.INSUFFISANT;
			case 100:
				return Note.PASSABLE;
			case 150:
				return Note.BIEN;
			case 200:
				return Note.TRES_BIEN;
			default:
				return null;
		}
	}

	public Boolean performanceEgale(Integer performance){
		return this.performance.equals(performance);
	}

	public void setCaAnnuel(Double caAnnuel) {
		this.caAnnuel = caAnnuel;
	}

	public void setPerformance(Integer performance) {
		this.performance = performance;
	}

	public Integer getPerformance() {
		return performance;
	}

	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Commercial)) return false;
		if (!super.equals(o)) return false;

		Commercial that = (Commercial) o;

		return Double.compare(that.caAnnuel, caAnnuel) == 0;

	}

	@Override
	public String toString() {
		return "Commercial{" +
				"caAnnuel=" + caAnnuel +
				", performance=" + performance +
				"} " + super.toString();
	}
}
