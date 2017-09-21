package com.ipiecoles.java.java220;

import com.ipiecoles.java.java220.exceptions.TechnicienException;
import org.joda.time.DateTime;


public class Technicien extends Employe {
	
	private int grade;
	
	public Technicien() {
		
	}
		
	public Technicien(String nom, String prenom, String matricule, DateTime dateEmbauche, double salaire, int grade) throws TechnicienException {
		super(nom, prenom, matricule, dateEmbauche, salaire);
		this.setGrade(grade);
	}

	public double getPrimeAnnuelle() {
		double salaireBase = Entreprise.primeAnnuelleBase();
		return salaireBase + salaireBase * (1 + (double) grade / 10) + Entreprise.PRIME_ANCIENNETE * this.getNombreAnneeAnciennete();
	}

	public int getNbConges() {
		return super.getNbConges() + this.getNombreAnneeAnciennete();
	}

	/**
	 * @return the grade
	 */
	public int getGrade() {
		return grade;
	}

	/**
	 * @param grade the grade to set
	 * @throws TechnicienException 
	 */
	public void setGrade(int grade) throws TechnicienException {
		if(grade <= 0 || grade > 5) {
			throw new TechnicienException(TechnicienException.GRADE, this, grade);
		}
		this.grade = grade;
	}

	@Override
	public void setSalaire(double salaire) {
		super.setSalaire( salaire * (1 + (double) grade / 10));
	}
		
}
