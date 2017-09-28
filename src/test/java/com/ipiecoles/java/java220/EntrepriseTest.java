package com.ipiecoles.java.java220;

import org.assertj.core.api.Assertions;
import org.joda.time.DateTimeUtils;
import org.joda.time.LocalDate;
import org.junit.AfterClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import utils.TestUtils;

import java.lang.reflect.InvocationTargetException;

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

		Object resultat = TestUtils.callMethod("Entreprise", "primeAnnuelleBase");
		Assertions.assertThat(resultat).isEqualTo(1008.5);

		DateTimeUtils.setCurrentMillisFixed(0L);

		resultat = TestUtils.callMethod("Entreprise", "primeAnnuelleBase");
		Assertions.assertThat(resultat).isEqualTo(985.0);
	}

	@Test
	public void exo003() throws Exception {
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
		Technicien t1 = new Technicien("Roussel", "Clémence", "000T01", new LocalDate(), Entreprise.SALAIRE_BASE, 1);
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
	}

	@AfterClass
	public static void tearDown(){
		DateTimeUtils.setCurrentMillisSystem();
	}
}
