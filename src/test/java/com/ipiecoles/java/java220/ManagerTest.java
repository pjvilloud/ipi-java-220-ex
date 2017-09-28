package com.ipiecoles.java.java220;

import org.assertj.core.api.Assertions;
import org.joda.time.LocalDate;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import utils.TestUtils;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@FixMethodOrder(MethodSorters.NAME_ASCENDING) 
public class ManagerTest {

    @Test
    public void exo400Heritage() throws Exception {
        //Comme pour la classe Commercial, faire hériter la classe Manager d'Employe et implémenter
        //la méthode abstraite getPrimeAnnuelle pour qu'elle retourne quelque chose et que
        //la compilation passe (la méthode sera implémentée plus tard)
        Assertions.assertThat(TestUtils.getClasse("Manager").getSuperclass().getSimpleName()).isEqualTo("Employe");
        TestUtils.checkNotAbstractClass("Manager");
    }

    @Test
    public void exo401Equipe() throws Exception {
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
    public void exo402AjoutMembreEquipe() throws Exception {
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
    public void exo403SetSalaire() throws Exception {
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
    public void exo404GetPrimeAnnuelle() throws Exception {
        //Modifier le code de la méthode getPrimeAnnuelle pour qu'elle renvoie la prime de base, à laquelle on ajoute
        //la prime du manager en fonction du nombre de membres
        //de son équipe (en utilisant Entreprise.PRIME_MANAGER_PAR_TECHNICIEN)
        Object d = TestUtils.getClasse("Manager").getConstructor().newInstance();
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
    public void exo405AugmenterSalaireEquipe() throws Exception {
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
    public void exo406AugmenterSalaire() throws Exception {
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
    public void exo407AjoutMembreEquipeSurcharge() throws Exception {
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
    public void exo408TestStreamLambda() throws Exception {
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

        Assertions.assertThat(TestUtils.invokeGetter(liste.get(0), "grade")).isEqualTo(3);
        Assertions.assertThat(TestUtils.invokeGetter(liste.get(1), "grade")).isEqualTo(2);
        Assertions.assertThat(TestUtils.invokeGetter(liste.get(2), "grade")).isEqualTo(1);

    }

    @Test
    public void exo409TestStreamLambdaReference() throws Exception{
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
}
