package com.ipiecoles.java.java220;

import org.assertj.core.api.Assertions;
import org.joda.time.DateTime;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import utils.TestUtils;

import java.lang.reflect.InvocationTargetException;

@FixMethodOrder(MethodSorters.NAME_ASCENDING) 
public class CommercialTest {

	@Test
	public void exo201TestHeritageEmploye() throws IllegalAccessException {
		//Faire en sorte que la classe Commercial hérite de la classe Employé
		//Analyser le message d'erreur remonté par l'IDE et utiliser l'IDE pour
		//résoudre le problème
		
		Assertions.assertThat(Commercial.class.getSuperclass()).isEqualTo(Employe.class);
		TestUtils.checkNotAbstractClass(Commercial.class);
	}
	
	@Test
	public void exo202AjoutAttributCaAnnuel() throws Exception {
		//Modifier la classe Commercial pour ajouter un attribut caAnnuel de type double
		//avec son getter et son setter
		
		TestUtils.checkPrivateField(Commercial.class, "caAnnuel", double.class);
		TestUtils.checkMethod(Commercial.class, "getCaAnnuel", double.class);
		TestUtils.checkMethod(Commercial.class, "setCaAnnuel", void.class, double.class);
		Commercial d = Commercial.class.newInstance();
		TestUtils.invokeSetter(d, "caAnnuel", 500.2);
		Assertions.assertThat(TestUtils.invokeGetter(d, "caAnnuel")).isEqualTo(500.2);
	}
	
	@Test
	public void exo203TestSurchargePrime() throws Exception {
		//Modifier la méthode getPrimeAnnuelle précédemment générée par l'IDE
		//pour que la prime soit égale à 5% du caAnnuel, avec un minimum de
		//500 € même en cas de chiffre d'affaire nul
		//Faire en sorte que la prime soit toujours arrondi à l'euro supérieur
		//Voir la classe Math
		
		Commercial d = Commercial.class.newInstance();
		Assertions.assertThat(TestUtils.callMethod(d, "getPrimeAnnuelle")).isEqualTo(500.0);
		
		TestUtils.invokeSetter(d, "caAnnuel", 0);
		Assertions.assertThat(TestUtils.callMethod(d, "getPrimeAnnuelle")).isEqualTo(500.0);
		
		TestUtils.invokeSetter(d, "caAnnuel", 50000);
		Assertions.assertThat(TestUtils.callMethod(d, "getPrimeAnnuelle")).isEqualTo(2500.0);
		
		TestUtils.invokeSetter(d, "caAnnuel", 10000);
		Assertions.assertThat(TestUtils.callMethod(d, "getPrimeAnnuelle")).isEqualTo(500.0);
		
		TestUtils.invokeSetter(d, "caAnnuel", 5000);
		Assertions.assertThat(TestUtils.callMethod(d, "getPrimeAnnuelle")).isEqualTo(500.0);
		
		TestUtils.invokeSetter(d, "caAnnuel", 50049);
		Assertions.assertThat(TestUtils.callMethod(d, "getPrimeAnnuelle")).isEqualTo(2503.0);
		
		
	}
	
	@Test
	public void exo204TestCreationConstructeur() throws Exception {
		//Créer un constructeur pour la classe Commercial qui initialise tous les attributs
		//hérités de la classe Employe en faisant appel au constructeur d'Employe
		//et qui initialise également l'attribut caAnnuel
		
		TestUtils.checkConstructor(Commercial.class, String.class, String.class, String.class, DateTime.class, double.class, double.class);
		DateTime dateTime = DateTime.now();
		Commercial d = Commercial.class.getConstructor(String.class, String.class, String.class, DateTime.class, double.class, double.class).newInstance("nom", "prenom", "matricule", dateTime, 500.0, 50000.0);
		Assertions.assertThat(TestUtils.invokeGetter(d, "nom")).isEqualTo("nom");
		Assertions.assertThat(TestUtils.invokeGetter(d, "prenom")).isEqualTo("prenom");
		Assertions.assertThat(TestUtils.invokeGetter(d, "matricule")).isEqualTo("matricule");
		Assertions.assertThat(TestUtils.invokeGetter(d, "dateEmbauche")).isEqualTo(dateTime);
		Assertions.assertThat(TestUtils.invokeGetter(d, "salaire")).isEqualTo(500.0);
		Assertions.assertThat(TestUtils.invokeGetter(d, "caAnnuel")).isEqualTo(50000.0);
	}

	@Test
	public void exo205TestEquals() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
		//Surcharger la méthode equals pour permettre de tester l'égalité entre deux instances de la classe Commercial.
		//Appeler la méthode equals de la classe Employe

		DateTime dateTime = DateTime.now();
		Commercial c = Commercial.class.getConstructor(String.class, String.class, String.class, DateTime.class, double.class, double.class).newInstance("nom", "prenom", "matricule", dateTime, 500.0, 90000.0);
		Commercial c2 = Commercial.class.getConstructor(String.class, String.class, String.class, DateTime.class, double.class, double.class).newInstance("nom", "prenom", "matricule", dateTime, 500.0, 50000.0);
		Assertions.assertThat(c).isNotEqualTo(c2);

		c = Commercial.class.getConstructor(String.class, String.class, String.class, DateTime.class, double.class, double.class).newInstance("nom", "prenom", "matricule", dateTime, 500.0, 50000.0);
		c2 = Commercial.class.getConstructor(String.class, String.class, String.class, DateTime.class, double.class, double.class).newInstance("nom", "prenom", "matricule", dateTime, 500.0, 50000.0);
		Assertions.assertThat(c).isEqualTo(c2);
	}
}
