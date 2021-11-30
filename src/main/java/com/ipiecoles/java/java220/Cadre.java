package com.ipiecoles.java.java220;

import org.joda.time.LocalDate;

import java.util.Objects;

public class Cadre extends Employe {

	private Double coefficient = 1.0;

	public Cadre() {

	}

	public Cadre(String nom, String prenom, String matricule, LocalDate dateEmbauche, Double salaire) {
		super(nom, prenom, matricule, dateEmbauche, salaire);
	}


	public Double getCoefficient() {
		return coefficient;
	}

	public void setCoefficient(Double coefficient) {
		this.coefficient = coefficient;
	}

	public Double getPrimeAnnuelle() {

		return Entreprise.primeAnnuelleBase() * coefficient;
	}

	public Integer getNbConges() {
		return super.getNbConges() + (int)Math.ceil(this.coefficient);
	}

	@Override
	public int hashCode() {
		return Objects.hash(super.hashCode(), coefficient);
	}

	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Cadre)) return false;
		if (!super.equals(o)) return false;

		Cadre cadre = (Cadre) o;
		return coefficient != null ? coefficient.equals(cadre.coefficient) : cadre.coefficient == null;
	}

	@Override
	public String toString() {
		return "Cadre{" +
				"coefficient=" + coefficient +
				"} " + super.toString();
	}
}
