package com.ipiecoles.java.java220;

import java.util.HashSet;

public class Manager extends Employe {
	
	private HashSet<Technicien> equipe = new HashSet();
	
	public void ajoutTechnicienEquipe(Technicien technicien) {
		equipe.add(technicien);
	}
	
	public void setSalaire(double salaire) {
		super.setSalaire(salaire * Entreprise.INDICE_MANAGER + (salaire * (double)equipe.size() / 10));
	}

	public double getPrimeAnnuelle() {
		return Entreprise.primeAnnuelleBase() + equipe.size() * Entreprise.PRIME_MANAGER_PAR_TECHNICIEN;
	}
	
	public void augmenterSalaire(double pourcentage) {
		super.augmenterSalaire(pourcentage);
		augmenterSalaireEquipe(pourcentage);
	}

	private void augmenterSalaireEquipe(double pourcentage) {
		for (Technicien technicien : equipe) {
			technicien.augmenterSalaire(pourcentage);
		}
	}

	/**
	 * @return the equipe
	 */
	public HashSet<Technicien> getEquipe() {
		return equipe;
	}

	/**
	 * @param equipe the equipe to set
	 */
	public void setEquipe(HashSet<Technicien> equipe) {
		this.equipe = equipe;
	}

}
