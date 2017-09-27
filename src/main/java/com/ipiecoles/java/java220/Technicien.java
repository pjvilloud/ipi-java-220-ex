package com.ipiecoles.java.java220;

import com.ipiecoles.java.java220.exceptions.TechnicienException;
import org.joda.time.LocalDate;

import java.util.Objects;


public class Technicien extends Employe implements Comparable<Technicien> {
	
	private Integer grade;
	
	public Technicien() {

	}
		
	public Technicien(String nom, String prenom, String matricule, LocalDate dateEmbauche, Double salaire, Integer grade) throws TechnicienException {
		super(nom, prenom, matricule, dateEmbauche, salaire);
		this.setGrade(grade);
	}

	public Double getPrimeAnnuelle() {
		Double salaireBase = Entreprise.primeAnnuelleBase();
		return salaireBase + salaireBase * (1 + (double) grade / 10) + Entreprise.PRIME_ANCIENNETE * this.getNombreAnneeAnciennete();
	}

	public Integer getNbConges() {
		return super.getNbConges() + this.getNombreAnneeAnciennete();
	}

	/**
	 * @return the grade
	 */
	public Integer getGrade() {
		return grade;
	}

	/**
	 * @param grade the grade to set
	 * @throws TechnicienException 
	 */
	public void setGrade(Integer grade) throws TechnicienException {
		if(grade <= 0 || grade > 5) {
			throw new TechnicienException(TechnicienException.GRADE, this, grade);
		}
		this.grade = grade;
	}

	@Override
	public void setSalaire(Double salaire) {
		super.setSalaire( salaire * (1 + (double) grade / 10));
	}

	@Override
	public String toString() {
		return "Technicien{" +
				"grade=" + grade +
				"} " + super.toString();
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Technicien)) return false;
		if (!super.equals(o)) return false;
		Technicien that = (Technicien) o;
		return Objects.equals(grade, that.grade);
	}

	@Override
	public int hashCode() {
		return Objects.hash(super.hashCode(), grade);
	}

	@Override
	public int compareTo(Technicien o) {
		return Integer.compare(o.getGrade(), this.grade);
	}
}
