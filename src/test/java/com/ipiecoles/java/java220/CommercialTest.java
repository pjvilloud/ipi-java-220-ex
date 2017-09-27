package com.ipiecoles.java.java220;

import org.assertj.core.api.Assertions;
import org.joda.time.LocalDate;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import utils.TestUtils;

import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;

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
		//Modifier la classe Commercial pour ajouter un attribut caAnnuel de type Double
		//avec son getter et son setter
		
		TestUtils.checkPrivateField(Commercial.class, "caAnnuel", Double.class);
		TestUtils.checkMethod(Commercial.class, "getCaAnnuel", Double.class);
		TestUtils.checkMethod(Commercial.class, "setCaAnnuel", void.class, Double.class);
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
		
		TestUtils.invokeSetter(d, "caAnnuel", 0d);
		Assertions.assertThat(TestUtils.callMethod(d, "getPrimeAnnuelle")).isEqualTo(500.0);
		
		TestUtils.invokeSetter(d, "caAnnuel", 50000d);
		Assertions.assertThat(TestUtils.callMethod(d, "getPrimeAnnuelle")).isEqualTo(2500.0);
		
		TestUtils.invokeSetter(d, "caAnnuel", 10000d);
		Assertions.assertThat(TestUtils.callMethod(d, "getPrimeAnnuelle")).isEqualTo(500.0);
		
		TestUtils.invokeSetter(d, "caAnnuel", 5000d);
		Assertions.assertThat(TestUtils.callMethod(d, "getPrimeAnnuelle")).isEqualTo(500.0);
		
		TestUtils.invokeSetter(d, "caAnnuel", 50049d);
		Assertions.assertThat(TestUtils.callMethod(d, "getPrimeAnnuelle")).isEqualTo(2503.0);
		
		
	}
	
	@Test
	public void exo204TestCreationConstructeur() throws Exception {
		//Créer un constructeur pour la classe Commercial qui initialise tous les attributs
		//hérités de la classe Employe en faisant appel au constructeur d'Employe
		//et qui initialise également l'attribut caAnnuel
		
		TestUtils.checkConstructor(Commercial.class, String.class, String.class, String.class, LocalDate.class, Double.class, Double.class);
		LocalDate dateTime = LocalDate.now();
		Commercial d = Commercial.class.getConstructor(String.class, String.class, String.class, LocalDate.class, Double.class, Double.class).newInstance("nom", "prenom", "matricule", dateTime, 500.0, 50000.0);
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

		LocalDate dateTime = LocalDate.now();
		Commercial c = Commercial.class.getConstructor(String.class, String.class, String.class, LocalDate.class, Double.class, Double.class).newInstance("nom", "prenom", "matricule", dateTime, 500.0, 90000.0);
		Commercial c2 = Commercial.class.getConstructor(String.class, String.class, String.class, LocalDate.class, Double.class, Double.class).newInstance("nom", "prenom", "matricule", dateTime, 500.0, 50000.0);
		Assertions.assertThat(c).isNotEqualTo(c2);

		c = Commercial.class.getConstructor(String.class, String.class, String.class, LocalDate.class, Double.class, Double.class).newInstance("nom", "prenom", "matricule", dateTime, 500.0, 50000.0);
		c2 = Commercial.class.getConstructor(String.class, String.class, String.class, LocalDate.class, Double.class, Double.class).newInstance("nom", "prenom", "matricule", dateTime, 500.0, 50000.0);
		Assertions.assertThat(c).isEqualTo(c2);
	}

	@Test
	public void exo206TestPerformanceEgale() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
		//Ajouter un attribut Integer performance
		//Ajouter une méthode performanceEgale prenant un Integer en paramètre, dans Commercial qui renvoie true si la performance du commercial
		//est égale à celle passée en paramètre, false sinon
		LocalDate dateTime = LocalDate.now();
		Commercial c = Commercial.class.getConstructor(String.class, String.class, String.class, LocalDate.class, Double.class, Double.class, Integer.class).newInstance("nom", "prenom", "matricule", dateTime, 500.0, 0.0, 50);

		Assertions.assertThat(TestUtils.callMethod(c, "performanceEgale", 50)).isEqualTo(true);

		c = Commercial.class.getConstructor(String.class, String.class, String.class, LocalDate.class, Double.class, Double.class, Integer.class).newInstance("nom", "prenom", "matricule", dateTime, 500.0, 9000000.0, new Integer (150));

		Assertions.assertThat(TestUtils.callMethod(c, "performanceEgale", new Integer(150))).isEqualTo(true);

	}

	@Test
	public void exo207TestEnum() throws Exception {
		//Créer un enum note avec les valeurs INSUFFISANT, PASSABLE, BIEN, TRES_BIEN et créer une méthode equivalenceNote
		//(sans utiliser de if) 	dans Commercial traduisant une performance en Note :
		//Si performance = 0 ou 50 : INSUFFISANT
		//Si performance = 100 : PASSABLE
		//Si performance = 150 : BIEN
		//Si performance = 200 : TRES_BIEN

		TestUtils.checkEnum("com.ipiecoles.java.java220.Note");

		LocalDate dateTime = LocalDate.now();
		Commercial c = Commercial.class.getConstructor(String.class, String.class, String.class, LocalDate.class, Double.class, Double.class, Integer.class).newInstance("nom", "prenom", "matricule", dateTime, 500.0, 0.0, 50);

		Assertions.assertThat(TestUtils.callMethod(c, "equivalenceNote")).isEqualTo(Class.forName("com.ipiecoles.java.java220.Note").getEnumConstants()[0]);

		TestUtils.invokeSetter(c, "performance", 100);
		Assertions.assertThat(TestUtils.callMethod(c, "equivalenceNote")).isEqualTo(Class.forName("com.ipiecoles.java.java220.Note").getEnumConstants()[1]);

		TestUtils.invokeSetter(c, "performance", 150);
		Assertions.assertThat(TestUtils.callMethod(c, "equivalenceNote")).isEqualTo(Class.forName("com.ipiecoles.java.java220.Note").getEnumConstants()[2]);

		TestUtils.invokeSetter(c, "performance", 200);
		Assertions.assertThat(TestUtils.callMethod(c, "equivalenceNote")).isEqualTo(Class.forName("com.ipiecoles.java.java220.Note").getEnumConstants()[3]);


	}
}
