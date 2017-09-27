package com.ipiecoles.java.java220;

import org.joda.time.LocalDate;
import org.joda.time.LocalDate;

import java.util.Objects;

public abstract class Employe {
	
	private String nom;
	
	private String prenom;

	private String matricule;
	
	private LocalDate dateEmbauche;
	
	private Double salaire = Entreprise.SALAIRE_BASE;
	
	public Employe() {
		
	}
	
	public Employe(String nom, String prenom, String matricule, LocalDate dateEmbauche, Double salaire) {
		this.nom = nom;
		this.prenom = prenom;
		this.matricule = matricule;
		this.dateEmbauche = dateEmbauche;
		this.salaire = salaire;
	}

	public final Integer getNombreAnneeAnciennete() {
		return LocalDate.now().getYear() - dateEmbauche.getYear();
	}
	
	public Integer getNbConges() {
		return Entreprise.NB_CONGES_BASE;
	}
	
	public abstract Double getPrimeAnnuelle();

	public void augmenterSalaire(Double pourcentage) {
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
	public LocalDate getDateEmbauche() {
		return dateEmbauche;
	}

	/**
	 * @param dateEmbauche the dateEmbauche to set
	 * @throws Exception 
	 */
	public void setDateEmbauche(LocalDate dateEmbauche) throws Exception {
		if(dateEmbauche != null && dateEmbauche.isAfter(LocalDate.now())) {
			throw new Exception("La date d'embauche ne peut être postérieure à la date courante");
		}
		this.dateEmbauche = dateEmbauche;
	}

	/**
	 * @return the salaire
	 */
	public Double getSalaire() {
		return salaire;
	}
	
	/**
	 * @param salaire the salaire to set
	 */
	public void setSalaire(Double salaire) {
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

	@Override
	public int hashCode() {
		return Objects.hash(nom, prenom, matricule, dateEmbauche, salaire);
	}
}
