package com.ipiecoles.java.java220;

import java.lang.reflect.InvocationTargetException;

import org.assertj.core.api.Assertions;
import org.joda.time.DateTimeUtils;
import org.junit.AfterClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import utils.TestUtils;

@FixMethodOrder(MethodSorters.NAME_ASCENDING) 
public class EntrepriseTest {
	
	@Test
	public void exo001ConstantesClasse() throws IllegalAccessException {
		//Déclarer au niveau de la classe Entreprise les constantes de classe suivantes
		//                     							   NOM CONSTANTE                    TYPE (sans .class)  Valeur
		TestUtils.checkStaticFinalField(Entreprise.class, "SALAIRE_BASE", 					double.class, 		1480.27);
		TestUtils.checkStaticFinalField(Entreprise.class, "NB_CONGES_BASE", 				int.class, 			25);
		TestUtils.checkStaticFinalField(Entreprise.class, "INDICE_MANAGER", 				double.class, 		1.3);
		TestUtils.checkStaticFinalField(Entreprise.class, "PRIME_MANAGER_PAR_TECHNICIEN", 	double.class, 		250.0);
		TestUtils.checkStaticFinalField(Entreprise.class, "PRIME_ANCIENNETE", 				double.class, 		100.0);
	}
	
	@Test
	public void exo002PrimeAnnuelleBase() throws IllegalAccessException, NoSuchMethodException, SecurityException, IllegalArgumentException, InvocationTargetException {
		//Déclarer au niveau de la classe Entreprise, une méthode primeAnnuelleBase, publique et statique 
		//calculant la prime de base pour tous les employés de l'entreprise de la manière suivante :
		//Utiliser la classe DateTime pour obtenir l'année courante.
		//
		//Prime = 50% de l'année en cours. Ex : 2017 : 2017 / 2 = 1008.5
		
		TestUtils.checkStaticMethod(Entreprise.class, "primeAnnuelleBase", double.class, 0);
		
		Object resultat = TestUtils.callMethod(Entreprise.class, "primeAnnuelleBase");
		Assertions.assertThat(resultat).isEqualTo(1008.5);
		
		DateTimeUtils.setCurrentMillisFixed(0L);
		
		resultat = TestUtils.callMethod(Entreprise.class, "primeAnnuelleBase");
		Assertions.assertThat(resultat).isEqualTo(985.0);
	}

	@AfterClass
	public static void tearDown(){
		DateTimeUtils.setCurrentMillisSystem();
	}
}
