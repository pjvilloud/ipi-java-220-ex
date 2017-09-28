package com.ipiecoles.java.java220;

import org.assertj.core.api.Assertions;
import org.joda.time.LocalDate;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import utils.TestUtils;

import java.lang.reflect.InvocationTargetException;

@FixMethodOrder(MethodSorters.NAME_ASCENDING) 
public class TechnicienTest {
	
	@Test
	public void exo301TestHeritageEmploye() throws Exception {
		//Comme pour la classe Commercial, faire hériter la classe Technicien et implémenter
		//la méthode abstraite getPrimeAnnuelle pour qu'elle retourne quelque chose et que
		//la compilation passe (la méthode sera implémentée plus tard)
		//Ajouter un constructeur par défaut
		
		Assertions.assertThat(TestUtils.getClasse("Technicien").getSuperclass()).isEqualTo(TestUtils.getClasse("Employe"));
		TestUtils.checkNotAbstractClass("Technicien");
		TestUtils.checkConstructor("Technicien");
	}
	
	@Test
	public void exo302AjoutAttributGrade() throws Exception {
		//Modifier la classe Technicien pour ajouter un attribut grade de type int
		//avec son getter et son setter
		
		TestUtils.checkPrivateField("Technicien", "grade", TestUtils.INTEGER);
		TestUtils.checkMethod("Technicien", "getGrade", TestUtils.INTEGER);
		TestUtils.checkMethod("Technicien", "setGrade", "void", TestUtils.INTEGER);
		Object d = TestUtils.getClasse("Technicien").newInstance();
		TestUtils.invokeSetter(d, "grade", 2);
		Assertions.assertThat(TestUtils.invokeGetter(d, "grade")).isEqualTo(2);
	}

	@Test
	public void exo303Constructeur() throws Exception {
		//Créer un constructeur pour la classe Technicien qui initialise tous les attributs
		//hérités de la classe Employe en faisant appel au constructeur d'Employe
		//et qui initialise également l'attribut grade
		TestUtils.checkConstructor("Technicien", TestUtils.STRING, TestUtils.STRING, TestUtils.STRING, TestUtils.LOCAL_DATE, TestUtils.DOUBLE, TestUtils.INTEGER);
		LocalDate dateTime = LocalDate.now();
		Object d = TestUtils.getClasse("Technicien").getConstructor(String.class, String.class, String.class, LocalDate.class, Double.class, Integer.class).newInstance("nom", "prenom", "matricule", dateTime, 500.0, 2);
		Assertions.assertThat(TestUtils.invokeGetter(d, "nom")).isEqualTo("nom");
		Assertions.assertThat(TestUtils.invokeGetter(d, "prenom")).isEqualTo("prenom");
		Assertions.assertThat(TestUtils.invokeGetter(d, "matricule")).isEqualTo("matricule");
		Assertions.assertThat(TestUtils.invokeGetter(d, "dateEmbauche")).isEqualTo(dateTime);
		Assertions.assertThat(TestUtils.invokeGetter(d, "salaire")).isEqualTo(500.0);
		Assertions.assertThat(TestUtils.invokeGetter(d, "grade")).isEqualTo(2);
	}

	@Test
	public void exo304SetGrade() throws Exception {
		//Modifier le setter de l'attribut grade pour qu'il lève une exception de la classe TechnicienException (à créer)
		//et dont le message est :
		//"Le grade doit être compris entre 1 et 5 : X, technicien : Technicien{grade=X} Employe{nom='NOM', prenom='PRENOM', matricule='MATRICULE', dateEmbauche=DATE, salaire=SALAIRE}"
		//Avec X = valeur incorrecte passée au setter et NOM, PRENOM... les valeurs des attributs d'Employe
		//Astuce : Ajouter une méthode toString à Technicien
		LocalDate dateTime = new LocalDate(0L);
		Object d = TestUtils.getClasse("Technicien").getConstructor(String.class, String.class, String.class, LocalDate.class, Double.class, Integer.class).newInstance("nom", "prenom", "matricule", dateTime, 500.0, 2);

		try {
			TestUtils.invokeSetter(d, "grade", 0);
			Assertions.fail("L'affectation aurait du lancer une exception");
		}
		catch(Exception technicienException){
			Assertions.assertThat(technicienException.getCause().getClass().getSimpleName()).isEqualTo("TechnicienException");
			Assertions.assertThat(technicienException.getCause().getMessage()).isEqualTo("Le grade doit être compris entre 1 et 5 : 0, technicien : Technicien{grade=2} Employe{nom='nom', prenom='prenom', matricule='matricule', dateEmbauche=1970-01-01, salaire=500.0}");
		}

		try {
			TestUtils.invokeSetter(d, "grade", 6);
			Assertions.fail("L'affectation aurait du lancer une exception");
		}
		catch(Exception technicienException){
			Assertions.assertThat(technicienException.getCause().getClass().getSimpleName()).isEqualTo("TechnicienException");
			Assertions.assertThat(technicienException.getCause().getMessage()).isEqualTo("Le grade doit être compris entre 1 et 5 : 6, technicien : Technicien{grade=2} Employe{nom='nom', prenom='prenom', matricule='matricule', dateEmbauche=1970-01-01, salaire=500.0}");
		}

		try {
			TestUtils.invokeSetter(d, "grade", 3);
			Assertions.assertThat(TestUtils.invokeGetter(d, "grade")).isEqualTo(3);
		}
		catch(Exception technicienException){
			Assertions.fail("La méthode setGrade ne devrait pas renvoyer d'exceptions avec la valeur 3");
		}
	}

	@Test
	public void exo305SetSalaire() throws Exception {
		//Redéfinir le setter de l'attribut salaire pour qu'il renvoie la valeur de l'attribut salaire,
		//auquel on ajoute la bonification du grade qui est égale à une augmentation de X0% par rapport au salaire de base :
		//Ex : Grade 3 : 30% d'augmentation : 1000.0 de salaire avec grade 1 : 1100.0

		Object d = TestUtils.getClasse("Technicien").getConstructor().newInstance();
		try {
			TestUtils.invokeSetter(d, "grade", 1);
			TestUtils.invokeSetter(d, "salaire", 1000.0);
			Assertions.assertThat(TestUtils.invokeGetter(d, "salaire")).isEqualTo(1100.0);
		}
		catch(Exception technicienException){
			Assertions.fail("L'affectation n'aurait pas du lancer une exception");
		}

		try {
			TestUtils.invokeSetter(d, "grade", 2);
			TestUtils.invokeSetter(d, "salaire", 1000.0);
			Assertions.assertThat(TestUtils.invokeGetter(d, "salaire")).isEqualTo(1200.0);
		}
		catch(Exception technicienException){
			Assertions.fail("L'affectation n'aurait pas du lancer une exception");
		}
	}

	@Test
	public void exo306GetNbConges() throws Exception {
		//Redéfinir le getter de nbConges pour retourner le nombre de congés de base + autant de congés que
		//d'année d'ancienneté.

		Object d = TestUtils.getClasse("Technicien").getConstructor().newInstance();
		try {
			TestUtils.invokeSetter(d, "dateEmbauche", LocalDate.now());
			Assertions.assertThat(TestUtils.callMethod(d, "getNbConges")).isEqualTo(TestUtils.getStaticFinalField("Entreprise", "NB_CONGES_BASE"));
		}
		catch(Exception technicienException){
			Assertions.fail("L'affectation n'aurait pas du lancer une exception");
		}

		try {
			TestUtils.invokeSetter(d, "dateEmbauche", LocalDate.now().minusYears(4));
			Assertions.assertThat(TestUtils.callMethod(d, "getNbConges")).isEqualTo((Integer)TestUtils.getStaticFinalField("Entreprise", "NB_CONGES_BASE") + 4);
		}
		catch(Exception technicienException){
			Assertions.fail("L'affectation n'aurait pas du lancer une exception");
		}
	}

	@Test
	public void exo307GetPrimeAnnuelle() throws Exception {
		//Modifier le code de la méthode getPrimeAnnuelle pour qu'elle renvoie la prime annuelle de base
		//à laquelle on ajoute un pourcentage en fonction du grade (idem exo 305) ainsi que la prime d'ancienneté
		//multipliée par le nombre d'année d'ancienneté
		Object d = TestUtils.getClasse("Technicien").getConstructor().newInstance();
		try {
			TestUtils.invokeSetter(d, "grade", 3);
			TestUtils.invokeSetter(d, "dateEmbauche", LocalDate.now());
			Assertions.assertThat(TestUtils.callMethod(d, "getPrimeAnnuelle")).isEqualTo(2319.55);//1008.5 + 1311.05 + 100
		}
		catch(Exception technicienException){
			Assertions.fail("L'affectation n'aurait pas du lancer une exception");
		}

		try {
			TestUtils.invokeSetter(d, "grade", 5);
			TestUtils.invokeSetter(d, "dateEmbauche", LocalDate.now().minusYears(3));
			Assertions.assertThat(TestUtils.callMethod(d, "getPrimeAnnuelle")).isEqualTo(2821.25);//1008.5 + 1512.75 + 300
		}
		catch(Exception technicienException){
			Assertions.fail("L'affectation n'aurait pas du lancer une exception");
		}

	}

	@Test
	public void exo308TestImplementComparable() throws Exception{
		//Implémenter l'interface Comparable pour que l'on puisse comparer deux Techniciens en fonction de leur grade
		//plus le grade est haut, plus le technicien est compétent

		Assertions.assertThat(TestUtils.getClasse("Technicien").getInterfaces().length).isEqualTo(1);
		Assertions.assertThat(TestUtils.getClasse("Technicien").getInterfaces()[0].getSimpleName()).isEqualTo("Comparable");

		Object d = TestUtils.getClasse("Technicien").getConstructor().newInstance();
		TestUtils.invokeSetter(d, "grade", 3);

		Object d2 = TestUtils.getClasse("Technicien").getConstructor().newInstance();
		TestUtils.invokeSetter(d2, "grade", 2);

		Assertions.assertThat(TestUtils.callMethod(d, "compareTo", d2)).isEqualTo(-1);
		Assertions.assertThat(TestUtils.callMethod(d, "compareTo", d)).isEqualTo(0);
		Assertions.assertThat(TestUtils.callMethod(d2, "compareTo", d)).isEqualTo(1);
	}


	//Méthode protected avc exemple autre package

	//Interface avec code

	//Classe interne
}
