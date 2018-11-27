package com.ipiecoles.java.java220;

import java.lang.reflect.InvocationTargetException;

import org.assertj.core.api.Assertions;
import org.joda.time.LocalDate;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import utils.TestUtils;

@FixMethodOrder(MethodSorters.NAME_ASCENDING) 
public class EmployeTest {
	
	
	class Derived extends Employe {
		//A decommenter quand le constructeur avec les 5 arguments est codé
		public Derived(String nom, String prenom, String matricule, LocalDate dateEmbauche, Double salaire) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
			super(nom, prenom, matricule, dateEmbauche, salaire);
		}

		public Derived() {
		}

		//A decommenter quand la méthode getPrimeAnnuelle est déclarée
		public Double getPrimeAnnuelle() {
			return 0d;
		}
	   
	}

	@Test
	public void exo101TestPrivateFields() throws Exception {
		//Ajouter dans la classe Employe les champs suivants avec leurs getters/setters
		//										   Nom champ	Type
		TestUtils.checkPrivateField("Employe", "nom", 		TestUtils.STRING);
		TestUtils.checkPrivateField("Employe", "prenom", 	TestUtils.STRING);
		TestUtils.checkPrivateField("Employe", "matricule", TestUtils.STRING);
		TestUtils.checkPrivateField("Employe", "dateEmbauche", TestUtils.LOCAL_DATE);
		TestUtils.checkPrivateField("Employe", "salaire", 	TestUtils.DOUBLE);
		
		TestUtils.checkMethod("Employe", "getNom", TestUtils.STRING);
		TestUtils.checkMethod("Employe", "getPrenom", TestUtils.STRING);
		TestUtils.checkMethod("Employe", "getMatricule", TestUtils.STRING);
		TestUtils.checkMethod("Employe", "getDateEmbauche", TestUtils.LOCAL_DATE);
		TestUtils.checkMethod("Employe", "getSalaire", TestUtils.DOUBLE);
		
		TestUtils.checkMethod("Employe", "setNom", "void", TestUtils.STRING);
		TestUtils.checkMethod("Employe", "setPrenom", "void", TestUtils.STRING);
		TestUtils.checkMethod("Employe", "setMatricule", "void", TestUtils.STRING);
		TestUtils.checkMethod("Employe", "setDateEmbauche", "void", TestUtils.LOCAL_DATE);
		TestUtils.checkMethod("Employe", "setSalaire", "void", TestUtils.DOUBLE);
		
		Derived d = new Derived();
		LocalDate dateTime = new LocalDate();
		TestUtils.invokeSetter(d, "nom", "nom");
		TestUtils.invokeSetter(d, "prenom", "prenom");
		TestUtils.invokeSetter(d, "matricule", "matricule");
		TestUtils.invokeSetter(d, "dateEmbauche", dateTime);
		TestUtils.invokeSetter(d, "salaire", 500.0);
		
		Assertions.assertThat(TestUtils.invokeGetter(d, "nom")).isEqualTo("nom");
		Assertions.assertThat(TestUtils.invokeGetter(d, "prenom")).isEqualTo("prenom");
		Assertions.assertThat(TestUtils.invokeGetter(d, "matricule")).isEqualTo("matricule");
		Assertions.assertThat(TestUtils.invokeGetter(d, "dateEmbauche")).isEqualTo(dateTime);
		Assertions.assertThat(TestUtils.invokeGetter(d, "salaire")).isEqualTo(500.0);
	}
	
	@Test
	public void exo102TestConstructeur() throws Exception {
		//Définir un constructeur par défaut dans la classe Employé, puis un constructeur avec l'ensemble des arguments
		//précédemment créés, dans le même ordre
		//
		TestUtils.checkConstructor("Employe");
		TestUtils.checkConstructor("Employe", TestUtils.STRING, TestUtils.STRING, TestUtils.STRING, TestUtils.LOCAL_DATE, TestUtils.DOUBLE);
		
		LocalDate dateTime = new LocalDate();
		Derived d = null;
		d = new Derived("nom", "prenom", "matricule", dateTime, 500.0);
		Assertions.assertThat(TestUtils.invokeGetter(d, "nom")).isEqualTo("nom");
		Assertions.assertThat(TestUtils.invokeGetter(d, "prenom")).isEqualTo("prenom");
		Assertions.assertThat(TestUtils.invokeGetter(d, "matricule")).isEqualTo("matricule");
		Assertions.assertThat(TestUtils.invokeGetter(d, "dateEmbauche")).isEqualTo(dateTime);
		Assertions.assertThat(TestUtils.invokeGetter(d, "salaire")).isEqualTo(500.0);
		
	}
	
	@Test
	public void exo103TestGetNombreAnneeAnciennete() throws Exception {
		//Déclarer et développer la méthode getNombreAnneeAnciennete calculant le nombre d'année d'ancienneté d'un employé
		//Faire en sorte qu'elle ne puisse être redéfinie dans d'éventuelles sous-classes.
		//Un employé enbauché en 2017 a une ancienneté de 0
		
		TestUtils.checkFinalMethod("Employe", "getNombreAnneeAnciennete", TestUtils.INTEGER);
		
		LocalDate dateTime = new LocalDate();
		Derived d = null;
		d = new Derived();
		TestUtils.invokeSetter(d, "dateEmbauche", dateTime);
		Assertions.assertThat(TestUtils.callMethod(d, "getNombreAnneeAnciennete")).isEqualTo(0);
		
		TestUtils.invokeSetter(d, "dateEmbauche", dateTime.minusYears(5));
		Assertions.assertThat(TestUtils.callMethod(d, "getNombreAnneeAnciennete")).isEqualTo(5);
		
	}
	
	@Test
	public void exo104TestGetNombreAnneeAncienneteNegatif() throws Exception {
		//Modifier le setter de dateEmbauche pour lever une Exception avec le message "La date d'embauche ne peut être postérieure à la date courante"
		//lorsque la date d'embauche est postérieure
		//à la date courante
		
		
		LocalDate dateTime = new LocalDate();
		Derived d = new Derived();
		TestUtils.invokeSetter(d, "dateEmbauche", dateTime);
		Assertions.assertThat(TestUtils.invokeGetter(d, "dateEmbauche")).isEqualTo(dateTime);
		
		TestUtils.invokeSetter(d, "dateEmbauche", dateTime.minusDays(1));
		Assertions.assertThat(TestUtils.invokeGetter(d, "dateEmbauche")).isEqualTo(dateTime.minusDays(1));
		
		try {
			TestUtils.invokeSetter(d, "dateEmbauche", dateTime.plusDays(6));
			Assertions.fail("Aucune exception levée lorsqu'on essaye de mettre une date d'embauche postérieure à la date courante");
		} catch (Exception e) {
			Assertions.assertThat(e.getCause().getMessage()).isEqualTo("La date d'embauche ne peut être postérieure à la date courante");
		}
		
	}
	
	@Test
	public void exo105TestGetNbConges() throws Exception {
		//Développer une méthode getNbConges retournant la constante de classe NB_CONGES_BASE de la classe Entreprise
		
		TestUtils.checkMethod("Employe", "getNbConges", TestUtils.INTEGER);
		
		Assertions.assertThat(TestUtils.callMethod(new Derived(), "getNbConges")).isEqualTo(25);
		
	}

	@Test
	public void exo106TestToString() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
		//Redéfinir la méthode toString (héritée d'Object) pour afficher un employé de la manière suivante :
		//"Employe{nom='nom', prenom='prenom', matricule='12345', dateEmbauche=1970-01-01, salaire=500.0}"
		LocalDate dateTime = new LocalDate(0L);
		Derived d = new Derived("nom", "prenom", null, dateTime, 500.0);
		Assertions.assertThat(TestUtils.callMethod(d, "toString")).isEqualTo("Employe{nom='nom', prenom='prenom', matricule='null', dateEmbauche=1970-01-01, salaire=500.0}");
	}

	@Test
	public void exo107TestEquals() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
		//Redéfinir la méthode equals (héritée d'Object) testant l'égalité sur l'ensemble des attributs de la classe Employe
		LocalDate dateTime = new LocalDate(0L);
		Derived d = new Derived("nom", "prenom", "matricule", dateTime, 500.0);
		Derived d2 = new Derived("nom", "prenom", "matricule", dateTime, 500.0);
		Assertions.assertThat(d).isEqualTo(d2);

		d = new Derived("nom", "prenom", null, dateTime, 500.0);
		d2 = new Derived("nom", "prenom", null, dateTime, 500.0);
		Assertions.assertThat(d).isEqualTo(d2);

		d = new Derived("nom", "XXX", null, dateTime, 500.0);
		d2 = new Derived("nom", "prenom", null, dateTime, 500.0);
		Assertions.assertThat(d).isNotEqualTo(d2);
	}

	@Test
	public void exo108TestHashcode() throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
		//Redéfinir la méthode hashCode (héritée d'Object) en utilisant Objects.hash(...) et en respectant l'ordre
		//nom, prenom, matricule, dateEmbauche, salaire

		LocalDate dateTime = new LocalDate(0L);
		Derived d = new Derived("nom", "prenom", "matricule", dateTime, 500.0);
		Assertions.assertThat(d.hashCode()).isEqualTo(-1582750688);

		d = new Derived("nom", "XXX", "matricule", dateTime, 500.0);
		Assertions.assertThat(d.hashCode()).isEqualTo(2076504497);
	}

	@Test
	public void exo109TestAugmenterSalaire() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
		//Coder la méthode augmenterSalaire prenant en paramètre un pourcentage d'augmentation de type Double
		//et augmentant l'attribut salaire du pourcentage passé en paramètre :
		//Ex : un salaire de 500.0, avec une augmentation de 0.50, cela donne un salaire de 750.0
		LocalDate dateTime = new LocalDate(0L);
		Derived d = new Derived("nom", "prenom", "matricule", dateTime, 500.0);
		TestUtils.callMethod(d, "augmenterSalaire", 0.50);
		Assertions.assertThat(TestUtils.invokeGetter(d, "salaire")).isEqualTo(750.0);
	}

	@Test
	public void exo110testClasseAbstraite() throws Exception {
		//Rendre la classe Employe abstraite
		TestUtils.checkAbstractClass("Employe");
	}

	@Test
	public void exo111TestMethodAbstract() throws Exception{
		//Déclarer dans la classe Employe une méthode abstraite getPrimeAnnuelle retournant un Double
		TestUtils.checkAbstractMethod("Employe", "getPrimeAnnuelle", TestUtils.DOUBLE);
	}

}
