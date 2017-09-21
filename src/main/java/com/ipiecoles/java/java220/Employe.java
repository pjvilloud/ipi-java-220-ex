package com.ipiecoles.java.java220;

import org.joda.time.DateTime;

public abstract class Employe {
	
	private String nom;
	
	private String prenom;

	private String matricule;
	
	private DateTime dateEmbauche;
	
	private double salaire;
	
	public Employe() {
		
	}
	
	public Employe(String nom, String prenom, String matricule, DateTime dateEmbauche, double salaire) {
		this.nom = nom;
		this.prenom = prenom;
		this.matricule = matricule;
		this.dateEmbauche = dateEmbauche;
		this.salaire = salaire;
	}

	public int getNombreAnneeAnciennete() {
		return DateTime.now().getYear() - dateEmbauche.getYear();
	}
	
	public int getNbConges() {
		return Entreprise.NB_CONGES_BASE;
	}
	
	public abstract double getPrimeAnnuelle();

	public void augmenterSalaire(double pourcentage) {
		this.salaire = this.getSalaire() * (1 + pourcentage);
	}
	
	/**
	 * @return the nom
	 */
	public String getNom() {
		return nom;
	}

	/**
	 * @param nom the nom to set
	 */
	public void setNom(String nom) {
		this.nom = nom;
	}

	/**
	 * @return the prenom
	 */
	public String getPrenom() {
		return prenom;
	}

	/**
	 * @param prenom the prenom to set
	 */
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	/**
	 * @return the matricule
	 */
	public String getMatricule() {
		return matricule;
	}

	/**
	 * @param matricule the matricule to set
	 */
	public void setMatricule(String matricule) {
		this.matricule = matricule;
	}

	/**
	 * @return the dateEmbauche
	 */
	public DateTime getDateEmbauche() {
		return dateEmbauche;
	}

	/**
	 * @param dateEmbauche the dateEmbauche to set
	 * @throws Exception 
	 */
	public void setDateEmbauche(DateTime dateEmbauche) throws Exception {
		if(dateEmbauche != null && dateEmbauche.isAfter(DateTime.now())) {
			throw new Exception("La date d'embauche ne peut être postérieure à la date courante");
		}
		this.dateEmbauche = dateEmbauche;
	}

	/**
	 * @return the salaire
	 */
	public double getSalaire() {
		return salaire;
	}
	
	/**
	 * @param salaire the salaire to set
	 */
	public void setSalaire(double salaire) {
		this.salaire = salaire;
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder("Employe{");
		sb.append("nom='").append(nom).append('\'');
		sb.append(", prenom='").append(prenom).append('\'');
		sb.append(", matricule='").append(matricule).append('\'');
		sb.append(", dateEmbauche=").append(dateEmbauche);
		sb.append(", salaire=").append(salaire);
		sb.append('}');
		return sb.toString();
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Employe)) return false;

		Employe employe = (Employe) o;

		if (Double.compare(employe.salaire, salaire) != 0) return false;
		if (nom != null ? !nom.equals(employe.nom) : employe.nom != null) return false;
		if (prenom != null ? !prenom.equals(employe.prenom) : employe.prenom != null) return false;
		if (matricule != null ? !matricule.equals(employe.matricule) : employe.matricule != null) return false;
		return dateEmbauche != null ? dateEmbauche.equals(employe.dateEmbauche) : employe.dateEmbauche == null;

	}

}
