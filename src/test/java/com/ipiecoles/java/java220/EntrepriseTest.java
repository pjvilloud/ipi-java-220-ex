package com.ipiecoles.java.java220;

import org.assertj.core.api.Assertions;
import org.joda.time.DateTime;
import org.joda.time.DateTimeUtils;
import org.joda.time.LocalDate;
import org.junit.AfterClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import utils.TestUtils;

import java.lang.reflect.InvocationTargetException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class EntrepriseTest {

	@Test
	public void exo001ConstantesClasse() throws Exception {
		//Déclarer au niveau de la classe Entreprise les constantes de classe suivantes
		//                     							   NOM CONSTANTE                    TYPE (sans .class)  Valeur
		TestUtils.checkStaticFinalField("Entreprise", "SALAIRE_BASE", 					TestUtils.DOUBLE, 		1480.27);
		TestUtils.checkStaticFinalField("Entreprise", "NB_CONGES_BASE", 				TestUtils.INTEGER, 		25);
		TestUtils.checkStaticFinalField("Entreprise", "INDICE_MANAGER", 				TestUtils.DOUBLE, 		1.3);
		TestUtils.checkStaticFinalField("Entreprise", "PRIME_MANAGER_PAR_TECHNICIEN", 	TestUtils.DOUBLE, 		250.0);
		TestUtils.checkStaticFinalField("Entreprise", "PRIME_ANCIENNETE", 				TestUtils.DOUBLE, 		100.0);
	}

	@Test
	public void exo002PrimeAnnuelleBase() throws Exception {
		//Déclarer au niveau de la classe Entreprise, une méthode primeAnnuelleBase, publique et statique
		//calculant la prime de base pour tous les employés de l'entreprise de la manière suivante :
		//Utiliser la classe LocalDate pour obtenir l'année courante.
		//
		//Prime = 50% de l'année en cours. Ex : 2017 : 2017 / 2 = 1008.5

		TestUtils.checkStaticMethod("Entreprise", "primeAnnuelleBase", TestUtils.DOUBLE, 0);

		DateTime d = new DateTime(2017,2,5,1,1);
		DateTimeUtils.setCurrentMillisFixed(d.getMillis());
		Object resultat = TestUtils.callMethod("Entreprise", "primeAnnuelleBase");
		Assertions.assertThat(resultat).isEqualTo(1008.5);

		DateTimeUtils.setCurrentMillisFixed(0L);

		resultat = TestUtils.callMethod("Entreprise", "primeAnnuelleBase");
		Assertions.assertThat(resultat).isEqualTo(985.0);
	}

	/*@Test
	public void exo003ProgGenerique() throws Exception {
		//Créer la classe générique Unite ayant deux attributs responsable de type générique et membres, qui est un
		//hashset de type générique. Générer les getters/setters
		//Créer un constructeur par défaut, un constructeur avec un membre qui affecte ce membre en tant que responsable et l'ajoute aux membres
		//Ajouter une méthode ajouterMembre qui peut prendre autant de type générique que l'on veut
		//Générer une méthode toString

		TestUtils.checkPrivateField("Unite", "responsable", TestUtils.OBJECT);
		TestUtils.checkPrivateField("Unite", "membres", TestUtils.HASHSET);

		TestUtils.checkMethod("Unite", "getResponsable", TestUtils.OBJECT);
		TestUtils.checkMethod("Unite", "setResponsable", "void", TestUtils.OBJECT);

		TestUtils.checkMethod("Unite", "getMembres", TestUtils.HASHSET);
		TestUtils.checkMethod("Unite", "setMembres", "void", TestUtils.HASHSET);

		TestUtils.checkConstructor("Unite");
		TestUtils.checkConstructor("Unite", TestUtils.OBJECT);


		//noinspection deprecation
		TestUtils.checkMethod(TestUtils.getClasse("Unite"), "ajouterMembre", void.class, Object[].class);
		TestUtils.checkMethod("Unite", "toString", TestUtils.STRING);


		//Décommenter tout le code ci-dessous une fois les développements effectués et analyser les 4 dernières lignes
		/*Technicien t1 = new Technicien("Roussel", "Clémence", "000T01", new LocalDate(), Entreprise.SALAIRE_BASE, 1);
		Technicien t2 = new Technicien("Chevalier", "Lucie", "000T02", new LocalDate(), Entreprise.SALAIRE_BASE, 2);
		Technicien t3 = new Technicien("Brunet", "Tom", "000T03", new LocalDate(), Entreprise.SALAIRE_BASE, 3);
		Technicien t4 = new Technicien("Leroux", "Louise", "000T04", new LocalDate(), Entreprise.SALAIRE_BASE, 4);
		Technicien t5 = new Technicien("Noa", "Philippe", "000T05", new LocalDate(), Entreprise.SALAIRE_BASE, 5);

		Unite<Technicien> employeEquipeT1 = new Unite<>(t1);
		employeEquipeT1.ajouterMembre(t2);

		Unite<Technicien> employeEquipeT2 = new Unite<>(t3);
		employeEquipeT1.ajouterMembre(t4, t5);

		Manager m1 = new Manager("Gaillard", "Victor", "000M01", new LocalDate(), Entreprise.SALAIRE_BASE, employeEquipeT1.getMembres());
		Manager m2 = new Manager("Lambert", "Arthur", "000M02", new LocalDate(), Entreprise.SALAIRE_BASE, employeEquipeT2.getMembres());

		Unite<Manager> employeEquipeM1 = new Unite<>(m1);
		employeEquipeM1.ajouterMembre(m2);

		Commercial c1 = new Commercial("Schneider", "Ines", "000C01", new LocalDate(), Entreprise.SALAIRE_BASE, 10000.0);
		Commercial c2 = new Commercial("Bourgeois", "Lisa", "000C02", new LocalDate(), Entreprise.SALAIRE_BASE, 20000.0);
		Commercial c3 = new Commercial("Renard", "Noah", "000C03", new LocalDate(), Entreprise.SALAIRE_BASE, 30000.0);

		Unite<Commercial> employeEquipeC1 = new Unite<>(c1);
		employeEquipeC1.ajouterMembre(c2, c3);

		Unite<Unite> entreprise = new Unite<>(employeEquipeM1);
		entreprise.ajouterMembre(employeEquipeT1, employeEquipeC1);

		System.out.println(entreprise);

		Unite<Employe> ensembleEmployes = new Unite<>(t1);
		ensembleEmployes.ajouterMembre(t2, t3, t4, t5, m1, m2, c1, c2, c3);
		ensembleEmployes.getMembres().stream().mapToDouble(Employe::getPrimeAnnuelle).forEach(System.out::println);
		System.out.println(ensembleEmployes);
	}*/


	class Derived extends Employe {
		//A decommenter quand le constructeur avec les 5 arguments est codé
		public Derived(String nom, String prenom, String matricule, LocalDate dateEmbauche, Double salaire) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
			super(nom, prenom, matricule, dateEmbauche, salaire);
		}

		public Derived() {
		}

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
	public void exo104TestSetDateEmbauchePosterieure() throws Exception {
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
	public void exo110TestMethodGetPrimeAnnuelle() throws Exception{
		//Déclarer dans la classe Employe une méthode abstraite getPrimeAnnuelle retournant un Double
		TestUtils.checkMethod("Employe", "getPrimeAnnuelle", TestUtils.DOUBLE);
	}

	@Test
	public void exo201TestHeritageEmploye() throws Exception {
		//Faire en sorte que la classe Commercial hérite de la classe Employé
		//Analyser le message d'erreur remonté par l'IDE et utiliser l'IDE pour
		//résoudre le problème

		Assertions.assertThat(TestUtils.getClasse("Commercial").getSuperclass()).isEqualTo(TestUtils.getClasse("Employe"));
		TestUtils.checkNotAbstractClass("Commercial");
	}

	@Test
	public void exo202AjoutAttributCaAnnuel() throws Exception {
		//Modifier la classe Commercial pour ajouter un attribut caAnnuel de type Double
		//avec son getter et son setter

		TestUtils.checkPrivateField("Commercial", "caAnnuel", TestUtils.DOUBLE);
		TestUtils.checkMethod("Commercial", "getCaAnnuel", TestUtils.DOUBLE);
		TestUtils.checkMethod("Commercial", "setCaAnnuel", "void", TestUtils.DOUBLE);
		Object d = TestUtils.getClasse("Commercial").newInstance();
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

		Object d = TestUtils.getClasse("Commercial").newInstance();
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

		TestUtils.checkConstructor("Commercial", TestUtils.STRING, TestUtils.STRING, TestUtils.STRING, TestUtils.LOCAL_DATE, TestUtils.DOUBLE, TestUtils.DOUBLE);
		LocalDate dateTime = LocalDate.now();
		Object d = TestUtils.getClasse("Commercial").getConstructor(String.class, String.class, String.class, LocalDate.class, Double.class, Double.class).newInstance("nom", "prenom", "matricule", dateTime, 500.0, 50000.0);
		Assertions.assertThat(TestUtils.invokeGetter(d, "nom")).isEqualTo("nom");
		Assertions.assertThat(TestUtils.invokeGetter(d, "prenom")).isEqualTo("prenom");
		Assertions.assertThat(TestUtils.invokeGetter(d, "matricule")).isEqualTo("matricule");
		Assertions.assertThat(TestUtils.invokeGetter(d, "dateEmbauche")).isEqualTo(dateTime);
		Assertions.assertThat(TestUtils.invokeGetter(d, "salaire")).isEqualTo(500.0);
		Assertions.assertThat(TestUtils.invokeGetter(d, "caAnnuel")).isEqualTo(50000.0);
	}

	@Test
	public void exo205TestEquals() throws Exception {
		//Surcharger la méthode equals pour permettre de tester l'égalité entre deux instances de la classe Commercial.
		//Appeler la méthode equals de la classe Employe

		LocalDate dateTime = LocalDate.now();
		Object c = TestUtils.getClasse("Commercial").getConstructor(String.class, String.class, String.class, LocalDate.class, Double.class, Double.class).newInstance("nom", "prenom", "matricule", dateTime, 500.0, 90000.0);
		Object c2 = TestUtils.getClasse("Commercial").getConstructor(String.class, String.class, String.class, LocalDate.class, Double.class, Double.class).newInstance("nom", "prenom", "matricule", dateTime, 500.0, 50000.0);
		Assertions.assertThat(c).isNotEqualTo(c2);

		c = TestUtils.getClasse("Commercial").getConstructor(String.class, String.class, String.class, LocalDate.class, Double.class, Double.class).newInstance("nom", "prenom", "matricule", dateTime, 500.0, 50000.0);
		c2 = TestUtils.getClasse("Commercial").getConstructor(String.class, String.class, String.class, LocalDate.class, Double.class, Double.class).newInstance("nom", "prenom", "matricule", dateTime, 500.0, 50000.0);
		Assertions.assertThat(c).isEqualTo(c2);
	}

	@Test
	public void exo206TestPerformanceEgale() throws Exception {
		//Ajouter un attribut Integer performance
		//Ajouter une méthode performanceEgale prenant un Integer en paramètre, dans Commercial qui renvoie true si la performance du commercial
		//est égale à celle passée en paramètre, false sinon
		LocalDate dateTime = LocalDate.now();
		Object c = TestUtils.getClasse("Commercial").getConstructor(String.class, String.class, String.class, LocalDate.class, Double.class, Double.class, Integer.class).newInstance("nom", "prenom", "matricule", dateTime, 500.0, 0.0, 50);

		Assertions.assertThat(TestUtils.callMethod(c, "performanceEgale", 50)).isEqualTo(true);

		c = TestUtils.getClasse("Commercial").getConstructor(String.class, String.class, String.class, LocalDate.class, Double.class, Double.class, Integer.class).newInstance("nom", "prenom", "matricule", dateTime, 500.0, 9000000.0, new Integer (150));

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

		TestUtils.checkEnum("Note");

		LocalDate dateTime = LocalDate.now();
		Object c = TestUtils.getClasse("Commercial").getConstructor(String.class, String.class, String.class, LocalDate.class, Double.class, Double.class, Integer.class).newInstance("nom", "prenom", "matricule", dateTime, 500.0, 0.0, 50);

		Assertions.assertThat(TestUtils.callMethod(c, "equivalenceNote")).isEqualTo(TestUtils.getClasse("Note").getEnumConstants()[0]);

		TestUtils.invokeSetter(c, "performance", 100);
		Assertions.assertThat(TestUtils.callMethod(c, "equivalenceNote")).isEqualTo(TestUtils.getClasse("Note").getEnumConstants()[1]);

		TestUtils.invokeSetter(c, "performance", 150);
		Assertions.assertThat(TestUtils.callMethod(c, "equivalenceNote")).isEqualTo(TestUtils.getClasse("Note").getEnumConstants()[2]);

		TestUtils.invokeSetter(c, "performance", 200);
		Assertions.assertThat(TestUtils.callMethod(c, "equivalenceNote")).isEqualTo(TestUtils.getClasse("Note").getEnumConstants()[3]);

		TestUtils.invokeSetter(c, "performance", 58);
		Assertions.assertThat(TestUtils.callMethod(c, "equivalenceNote")).isNull();

	}

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

	/*@Test
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
	}*/

	@Test
	public void exo304SetSalaire() throws Exception {
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
	public void exo305GetNbConges() throws Exception {
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
	public void exo306GetPrimeAnnuelle() throws Exception {
		//Modifier le code de la méthode getPrimeAnnuelle pour qu'elle renvoie la prime annuelle de base
		//à laquelle on ajoute un pourcentage en fonction du grade (idem exo 305) ainsi que la prime d'ancienneté
		//multipliée par le nombre d'année d'ancienneté
		Object d = TestUtils.getClasse("Technicien").getConstructor().newInstance();
		DateTime date = new DateTime(2017,2,5,1,1);
		DateTimeUtils.setCurrentMillisFixed(date.getMillis());
		try {
			TestUtils.invokeSetter(d, "grade", 3);
			TestUtils.invokeSetter(d, "dateEmbauche", LocalDate.now());
			Assertions.assertThat(TestUtils.callMethod(d, "getPrimeAnnuelle")).isEqualTo(1311.05);//1008.5 + 302.55 + 0
		}
		catch(Exception technicienException){
			Assertions.fail("L'affectation n'aurait pas du lancer une exception");
		}

		try {
			TestUtils.invokeSetter(d, "grade", 5);
			TestUtils.invokeSetter(d, "dateEmbauche", LocalDate.now().minusYears(3));
			Assertions.assertThat(TestUtils.callMethod(d, "getPrimeAnnuelle")).isEqualTo(1812.75);//1008.5 + 504.25 + 300
		}
		catch(Exception technicienException){
			Assertions.fail("L'affectation n'aurait pas du lancer une exception");
		}

	}

	@Test
	public void exo307TestImplementComparable() throws Exception{
		//Implémenter l'interface Comparable pour que l'on puisse comparer deux Techniciens en fonction de leur grade
		//plus le grade est haut, plus le technicien est compétent

		Assertions.assertThat(TestUtils.getClasse("Technicien").getInterfaces().length).isEqualTo(1);
		Assertions.assertThat(TestUtils.getClasse("Technicien").getInterfaces()[0].getSimpleName()).isEqualTo("Comparable");

		Object d = TestUtils.getClasse("Technicien").getConstructor().newInstance();
		TestUtils.invokeSetter(d, "grade", 3);

		Object d2 = TestUtils.getClasse("Technicien").getConstructor().newInstance();
		TestUtils.invokeSetter(d2, "grade", 2);

		Assertions.assertThat(TestUtils.callMethod(d, "compareTo", d2)).isEqualTo(1);//-1
		Assertions.assertThat(TestUtils.callMethod(d, "compareTo", d)).isEqualTo(0);
		Assertions.assertThat(TestUtils.callMethod(d2, "compareTo", d)).isEqualTo(-1);//1
	}



	@Test
	public void exo401testClasseAbstraite() throws Exception {
		//Rendre la classe Employe abstraite
		TestUtils.checkAbstractClass("Employe");
	}

	@Test
	public void exo402TestMethodAbstract() throws Exception{
		//Déclarer dans la classe Employe une méthode abstraite getPrimeAnnuelle retournant un Double
		TestUtils.checkAbstractMethod("Employe", "getPrimeAnnuelle", TestUtils.DOUBLE);
	}


	@Test
	public void exo501Heritage() throws Exception {
		//Comme pour la classe Commercial, faire hériter la classe Manager d'Employe et implémenter
		//la méthode abstraite getPrimeAnnuelle pour qu'elle retourne quelque chose et que
		//la compilation passe (la méthode sera implémentée plus tard)
		Assertions.assertThat(TestUtils.getClasse("Manager").getSuperclass().getSimpleName()).isEqualTo("Employe");
		TestUtils.checkNotAbstractClass("Manager");
	}

	@Test
	public void exo502Equipe() throws Exception {
		//Modifier la classe Manager pour ajouter un attribut equipe permettant de stocker un ensemble non ordonné de techniciens
		//avec son getter et son setter

		TestUtils.checkPrivateField("Manager", "equipe", TestUtils.HASHSET);
		TestUtils.checkMethod("Manager", "getEquipe", TestUtils.HASHSET);
		TestUtils.checkMethod("Manager", "setEquipe", "void", TestUtils.HASHSET);
		Object d = TestUtils.getClasse("Manager").newInstance();
		Assertions.assertThat(TestUtils.invokeGetter(d, "equipe").getClass().getName()).isEqualTo(TestUtils.HASHSET);
		Assertions.assertThat((HashSet)TestUtils.invokeGetter(d, "equipe")).isNotNull();
		Assertions.assertThat((HashSet)TestUtils.invokeGetter(d, "equipe")).hasSize(0);

		HashSet techniciens = new HashSet<>();
		techniciens.add(TestUtils.getClasse("Technicien").newInstance());

		TestUtils.invokeSetter(d, "equipe", techniciens);
		Assertions.assertThat(TestUtils.invokeGetter(d, "equipe")).isEqualTo(techniciens);
	}

	@Test
	public void exo503AjoutMembreEquipe() throws Exception {
		//Ajouter une méthode ajoutTechnicienEquipe qui prend en paramètre un technicien et qui
		//l'ajoute dans l'équipe

		Object d = TestUtils.getClasse("Manager").newInstance();

		Object t = TestUtils.getClasse("Technicien").newInstance();

		TestUtils.callMethod(d, "ajoutTechnicienEquipe", t);
		Assertions.assertThat(TestUtils.invokeGetter(d, "equipe").getClass().getName()).isEqualTo(TestUtils.HASHSET);
		Assertions.assertThat((HashSet)TestUtils.invokeGetter(d, "equipe")).isNotNull();
		Assertions.assertThat((HashSet)TestUtils.invokeGetter(d, "equipe")).hasSize(1);
		Assertions.assertThat(((HashSet) TestUtils.invokeGetter(d, "equipe")).iterator().next()).isEqualTo(t);
	}

	@Test
	public void exo504SetSalaire() throws Exception {
		//Surcharger le setter de l'attribut salaire pour qu'il renvoie la valeur du salaire multipliée par l'index manager,
		//auquel on ajoute 10% (sur le salaire passé en paramètre) par membre d'équipe

		Object d = TestUtils.getClasse("Manager").getConstructor().newInstance();
		try {
			TestUtils.invokeSetter(d, "salaire", 1000.0);
			Assertions.assertThat(TestUtils.invokeGetter(d, "salaire")).isEqualTo(1300.0);
		}
		catch(Exception exception){
			Assertions.fail("L'affectation n'aurait pas du lancer une exception");
		}

		try {
			TestUtils.invokeSetter(d, "equipe", Stream.of(TestUtils.getClasse("Technicien").newInstance()).collect(Collectors.toSet()));
			TestUtils.invokeSetter(d, "salaire", 1000.0);
			Assertions.assertThat(TestUtils.invokeGetter(d, "salaire")).isEqualTo(1400.0);
		}
		catch(Exception exception){
			Assertions.fail("L'affectation n'aurait pas du lancer une exception");
		}
	}

	@Test
	public void exo505GetPrimeAnnuelle() throws Exception {
		//Modifier le code de la méthode getPrimeAnnuelle pour qu'elle renvoie la prime de base, à laquelle on ajoute
		//la prime du manager en fonction du nombre de membres
		//de son équipe (en utilisant Entreprise.PRIME_MANAGER_PAR_TECHNICIEN)
		Object d = TestUtils.getClasse("Manager").getConstructor().newInstance();
		DateTime da = new DateTime(2017,2,5,1,1);
		DateTimeUtils.setCurrentMillisFixed(da.getMillis());
		try {
			Assertions.assertThat(TestUtils.callMethod(d, "getPrimeAnnuelle")).isEqualTo(1008.5);
		}
		catch(Exception exception){
			Assertions.fail("L'affectation n'aurait pas du lancer une exception");
		}

		try {
			TestUtils.invokeSetter(d, "equipe", Stream.of(TestUtils.getClasse("Technicien").getConstructor(String.class, String.class, String.class, LocalDate.class, Double.class, Integer.class).newInstance(null, null, null, null, 0.0, 1)).collect(Collectors.toSet()));
			Assertions.assertThat(TestUtils.callMethod(d, "getPrimeAnnuelle")).isEqualTo(1258.5);
		}
		catch(Exception technicienException){
			Assertions.fail("L'affectation n'aurait pas du lancer une exception");
		}

		try {
			TestUtils.invokeSetter(d, "equipe", Stream.of(TestUtils.getClasse("Technicien").getConstructor(String.class, String.class, String.class, LocalDate.class, Double.class, Integer.class).newInstance(null, null, null, null, 0.0, 1), TestUtils.getClasse("Technicien").getConstructor(String.class, String.class, String.class, LocalDate.class, Double.class, Integer.class).newInstance(null, null, null, null, 0.0, 2)).collect(Collectors.toSet()));
			Assertions.assertThat(TestUtils.callMethod(d, "getPrimeAnnuelle")).isEqualTo(1508.5);
		}
		catch(Exception technicienException){
			Assertions.fail("L'affectation n'aurait pas du lancer une exception");
		}

	}

	@Test
	public void exo506AugmenterSalaireEquipe() throws Exception {
		//Ajoutée une méthode interne augmenterSalaireEquipe qui augmente le salaire de tous les membres de l'équipe
		//d'un manager par un pourcentage (Double) avec la méthode précédemment définie dans Employe
		//Voir ensuite les deux dernières lignes du test et essayer de comprendre pourquoi

		TestUtils.checkPrivateMethod("Manager", "augmenterSalaireEquipe", "void", TestUtils.DOUBLE);

		Object d = TestUtils.getClasse("Manager").getConstructor().newInstance();
		TestUtils.invokeSetter(d, "equipe", Stream.of(
				TestUtils.getClasse("Technicien").getConstructor(String.class, String.class, String.class, LocalDate.class, Double.class, Integer.class).newInstance(null, null, null, null, 0.0, 1) ,
				TestUtils.getClasse("Technicien").getConstructor(String.class, String.class, String.class, LocalDate.class, Double.class, Integer.class).newInstance(null, null, null, null, 0.0, 2)
		).collect(Collectors.toSet()));
		TestUtils.callDeclaredMethod(d, "augmenterSalaireEquipe", 0.05d);
		Assertions.assertThat((HashSet)TestUtils.invokeGetter(d, "equipe")).isNotNull();
		Assertions.assertThat((HashSet)TestUtils.invokeGetter(d, "equipe")).hasSize(2);
		Iterator iterator = ((HashSet) TestUtils.invokeGetter(d, "equipe")).iterator();
		Assertions.assertThat(TestUtils.invokeGetter(iterator.next(), "salaire")).isEqualTo(0.0);
		Assertions.assertThat(TestUtils.invokeGetter(iterator.next(), "salaire")).isEqualTo(0.0);

		d = TestUtils.getClasse("Manager").getConstructor().newInstance();
		TestUtils.invokeSetter(d, "equipe", Stream.of(
				TestUtils.getClasse("Technicien").getConstructor(String.class, String.class, String.class, LocalDate.class, Double.class, Integer.class).newInstance(null, null, null, null, 1000.0, 1) ,
				TestUtils.getClasse("Technicien").getConstructor(String.class, String.class, String.class, LocalDate.class, Double.class, Integer.class).newInstance(null, null, null, null, 1000.0, 2)
		).collect(Collectors.toSet()));
		TestUtils.callDeclaredMethod(d, "augmenterSalaireEquipe", 0.50d);
		Assertions.assertThat((HashSet)TestUtils.invokeGetter(d, "equipe")).isNotNull();
		Assertions.assertThat((HashSet)TestUtils.invokeGetter(d, "equipe")).hasSize(2);
		iterator = ((HashSet) TestUtils.invokeGetter(d, "equipe")).iterator();
		Assertions.assertThat(TestUtils.invokeGetter(iterator.next(), "salaire")).isEqualTo(1500.0);
		Assertions.assertThat(TestUtils.invokeGetter(iterator.next(), "salaire")).isEqualTo(1500.0);
	}

	@Test
	public void exo507AugmenterSalaire() throws Exception {
		//Surcharger la méthode augmenterSalaire pour systématiquement augmenter le salaire de l'équipe
		//d'un manager avant d'augmenter le salaire du manager...

		TestUtils.checkMethod("Manager", "augmenterSalaire", "void", TestUtils.DOUBLE);

		Object d = TestUtils.getClasse("Manager").getConstructor().newInstance();
		TestUtils.invokeSetter(d, "salaire", 1000.0);

		TestUtils.invokeSetter(d, "equipe", Stream.of(
				TestUtils.getClasse("Technicien").getConstructor(String.class, String.class, String.class, LocalDate.class, Double.class, Integer.class).newInstance(null, null, null, null, 0.0, 1) ,
				TestUtils.getClasse("Technicien").getConstructor(String.class, String.class, String.class, LocalDate.class, Double.class, Integer.class).newInstance(null, null, null, null, 0.0, 2)
		).collect(Collectors.toSet()));
		TestUtils.callMethod(d, "augmenterSalaire", 0.05d);
		Assertions.assertThat((HashSet)TestUtils.invokeGetter(d, "equipe")).isNotNull();
		Assertions.assertThat((HashSet)TestUtils.invokeGetter(d, "equipe")).hasSize(2);
		Iterator iterator = ((HashSet) TestUtils.invokeGetter(d, "equipe")).iterator();
		Assertions.assertThat(TestUtils.invokeGetter(iterator.next(), "salaire")).isEqualTo(0.0);
		Assertions.assertThat(TestUtils.invokeGetter(iterator.next(), "salaire")).isEqualTo(0.0);
		Assertions.assertThat(TestUtils.invokeGetter(d, "salaire")).isEqualTo(1365.0);


		d = TestUtils.getClasse("Manager").getConstructor().newInstance();
		TestUtils.invokeSetter(d, "salaire", 1000.0);
		TestUtils.invokeSetter(d, "equipe", Stream.of(TestUtils.getClasse("Technicien").getConstructor(String.class, String.class, String.class, LocalDate.class, Double.class, Integer.class).newInstance(null, null, null, null, 1000.0, 1), TestUtils.getClasse("Technicien").getConstructor(String.class, String.class, String.class, LocalDate.class, Double.class, Integer.class).newInstance(null, null, null, null, 1000.0, 2)).collect(Collectors.toSet()));
		TestUtils.callMethod(d, "augmenterSalaire", 0.50d);
		Assertions.assertThat((HashSet)TestUtils.invokeGetter(d, "equipe")).isNotNull();
		Assertions.assertThat((HashSet)TestUtils.invokeGetter(d, "equipe")).hasSize(2);
		iterator = ((HashSet) TestUtils.invokeGetter(d, "equipe")).iterator();
		Assertions.assertThat(TestUtils.invokeGetter(iterator.next(), "salaire")).isEqualTo(1500.0);
		Assertions.assertThat(TestUtils.invokeGetter(iterator.next(), "salaire")).isEqualTo(1500.0);
		Assertions.assertThat(TestUtils.invokeGetter(d, "salaire")).isEqualTo(1950.0);
	}

	@Test
	public void exo508AjoutMembreEquipeSurcharge() throws Exception {
		//Surcharger ajoutTechnicienEquipe pour permettre l'ajout d'un technicien en passant directement les
		//paramètres nom, prenom, matricule, date, salaire et grade

		Object d = TestUtils.getClasse("Manager").newInstance();

		LocalDate dateTime = new LocalDate();
		Object t = TestUtils.getClasse("Technicien").getConstructor(String.class, String.class, String.class, LocalDate.class, Double.class, Integer.class).newInstance("nom", "prenom", "matricule", dateTime, 500.0, 2);
		TestUtils.callMethod(d, "ajoutTechnicienEquipe", "nom", "prenom", "matricule", dateTime, 500.0, 2);
		Assertions.assertThat(TestUtils.invokeGetter(d, "equipe").getClass().getName()).isEqualTo(TestUtils.HASHSET);
		Assertions.assertThat((HashSet)TestUtils.invokeGetter(d, "equipe")).isNotNull();
		Assertions.assertThat((HashSet)TestUtils.invokeGetter(d, "equipe")).hasSize(1);
		Assertions.assertThat(((HashSet) TestUtils.invokeGetter(d, "equipe")).iterator().next()).isEqualToComparingFieldByField(t);
	}

	@Test
	public void exo509TestStreamLambda() throws Exception {
		//Ajouter une méthode equipeParGrade renvoyant une liste des techniciens de l'équipe triée par grade décroissant en utilisant les Streams
		//et les lambdas

		LocalDate dateTime = new LocalDate();
		Object t3 = TestUtils.getClasse("Technicien").getConstructor(String.class, String.class, String.class, LocalDate.class, Double.class, Integer.class).newInstance("nom", "prenom", "matricule", dateTime, 500.0, 3);
		Object t = TestUtils.getClasse("Technicien").getConstructor(String.class, String.class, String.class, LocalDate.class, Double.class, Integer.class).newInstance("nom", "prenom", "matricule", dateTime, 500.0, 1);
		Object t2 = TestUtils.getClasse("Technicien").getConstructor(String.class, String.class, String.class, LocalDate.class, Double.class, Integer.class).newInstance("nom", "prenom", "matricule", dateTime, 500.0, 2);

		Object d = TestUtils.getClasse("Manager").newInstance();
		TestUtils.callMethod(d, "ajoutTechnicienEquipe", t3);
		TestUtils.callMethod(d, "ajoutTechnicienEquipe", t);
		TestUtils.callMethod(d, "ajoutTechnicienEquipe", t2);

		Object listeTech = TestUtils.callMethod(d, "equipeParGrade");
		Assertions.assertThat(listeTech).isInstanceOf(List.class);
		List liste = (List)listeTech;
		Assertions.assertThat(liste.size()).isEqualTo(3);

		Assertions.assertThat(TestUtils.invokeGetter(liste.get(0), "grade")).isEqualTo(1);
		Assertions.assertThat(TestUtils.invokeGetter(liste.get(1), "grade")).isEqualTo(2);
		Assertions.assertThat(TestUtils.invokeGetter(liste.get(2), "grade")).isEqualTo(3);

	}

	@Test
	public void exo510TestStreamLambdaReference() throws Exception{
		//Ajouter une méthode salaireEquipeGrade1 qui renvoie la somme des salaires des membres de l'équipe dont le grade
		//est égal à 1 en une ligne avec des lambdas

		LocalDate dateTime = new LocalDate();
		Object t3 = TestUtils.getClasse("Technicien").getConstructor(String.class, String.class, String.class, LocalDate.class, Double.class, Integer.class).newInstance("nom", "prenom", "matricule", dateTime, 400.0, 1);
		Object t = TestUtils.getClasse("Technicien").getConstructor(String.class, String.class, String.class, LocalDate.class, Double.class, Integer.class).newInstance("nom", "prenom", "matricule", dateTime, 500.0, 2);
		Object t2 = TestUtils.getClasse("Technicien").getConstructor(String.class, String.class, String.class, LocalDate.class, Double.class, Integer.class).newInstance("nom", "prenom", "matricule", dateTime, 600.0, 1);

		Object d = TestUtils.getClasse("Manager").newInstance();
		TestUtils.callMethod(d, "ajoutTechnicienEquipe", t3);
		TestUtils.callMethod(d, "ajoutTechnicienEquipe", t);
		TestUtils.callMethod(d, "ajoutTechnicienEquipe", t2);

		Assertions.assertThat(TestUtils.callMethod(d, "salaireEquipeGrade1")).isEqualTo(1000.0);
	}

	@AfterClass
	public static void tearDown(){
		DateTimeUtils.setCurrentMillisSystem();
	}
}
