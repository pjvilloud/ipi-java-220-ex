# ipi-java-220-ex 
![Build status](https://travis-ci.org/pjvilloud/ipi-java-220-ex.svg?branch=correction)

Cours de Java, module 220 pour l'IPI

La classe `EntrepriseTest` contient des tests permettant de corriger automatiquement les exercices. 
Pour les exécuter, clic droit sur la classe `EntrepriseTest` : 
- IntelliJ : => Run EntrepriseTest 
- Eclipse : => Run As => Junit Test

# Exercice 0 : Entreprise

## 001 : Déclarer au niveau de la classe `Entreprise` les constantes de classe suivantes

- `SALAIRE_BASE` de type `Double` et de valeur 1480.27
- `NB_CONGES_BASE` de type `Integer` et de valeur 25
- `INDICE_MANAGER` de type `Double` et de valeur 1.3
- `PRIME_MANAGER_PAR_TECHNICIEN` de type `Double` et de valeur 250
- `PRIME_ANCIENNETE` de type `Double` et de valeur 100

## 002 : Déclarer au niveau de la classe `Entreprise`, une méthode `primeAnnuelleBase`, publique et statique calculant la prime de base pour tous les employés de l'entreprise de la manière suivante :

Utiliser la classe `LocalDate` (de **joda time**, attention aux imports) pour obtenir l'année courante.
Prime = 50% de l'année en cours. Ex : 2017 : 2017 / 2 = 1008.5

# Exercice 1 : Employé

## 101 : Ajouter dans la classe `Employe` les champs suivants avec leurs getters/setters : 

- `nom` : `String`
- `prenom` : `String`
- `matricule` : `String`
- `dateEmbauche` : `LocalDate`
- `salaire` : `Double`

## 102 : Définir un constructeur par défaut dans la classe `Employe`, puis un constructeur avec l'ensemble des arguments précédemment créés, dans le même ordre puis décommenter la ligne 20 de `EmployeTest`

## 103 : Déclarer et développer la méthode `getNombreAnneeAnciennete` calculant le nombre d'année d'ancienneté d'un employé

Faire en sorte qu'elle ne puisse être redéfinie dans d'éventuelles sous-classes. Un employé embauché cette année a une ancienneté de 0

## 104 : Modifier le setter de `dateEmbauche` pour lever une `Exception` avec le message `"La date d'embauche ne peut être postérieure à la date courante"` lorsque la date d'embauche est postérieure à la date courante

## 105 : Développer une méthode `getNbConges` retournant la constante de classe `NB_CONGES_BASE` de la classe `Entreprise`

## 106 : Redéfinir la méthode `toString` (héritée d'`Object`) pour afficher un employé de la manière suivante :

```"Employe{nom='nom', prenom='prenom', matricule='12345', dateEmbauche=1970-01-01, salaire=500.0}"```

## 107 : Redéfinir la méthode `equals` (héritée d'`Object`) testant l'égalité sur l'ensemble des attributs de la classe `Employe`

## 108 : Redéfinir la méthode `hashCode` (héritée d'`Object`) en utilisant `Objects.hash(...)` et en respectant l'ordre `nom, prenom, matricule, dateEmbauche, salaire`

## 109 : Coder la méthode `augmenterSalaire` prenant en paramètre un pourcentage d'augmentation de type `Double` et augmentant l'attribut `salaire` du pourcentage passé en paramètre :

Ex : un salaire de 500.0, avec une augmentation de 0.50, cela donne un salaire de 750.0

## 110 : Déclarer dans la classe `Employe` une méthode `getPrimeAnnuelle` retournant la prime annuelle de base définie dans la classe `Entreprise`

# Exercice 2 : Commercial

## 201 : Créer la classe `Commercial` et faire en sorte qu'elle hérite de la classe `Employe`

## 202 : Modifier la classe `Commercial` pour ajouter un attribut `caAnnuel` de type `Double` avec son getter et son setter

## 203 : Modifier la méthode `getPrimeAnnuelle` précédemment générée par l'IDE pour que la prime soit égale à 5% du `caAnnuel`, avec un minimum de 500 € même en cas de chiffre d'affaire nul(l).

Faire en sorte que la prime soit toujours arrondi à l'euro supérieur. Voir la classe Math

## 204 : Créer un constructeur pour la classe `Commercial` qui initialise tous les attributs hérités de la classe `Employe` en faisant appel au constructeur d'`Employe` et qui initialise également l'attribut `caAnnuel`

## 205 : Redéfinir la méthode `equals` pour permettre de tester l'égalité entre deux instances de la classe `Commercial`.

Appeler la méthode `equals` de la classe `Employe`

## 206 : Ajouter les éléments suivants dans la classe `Commercial` 

- un attribut `performance` de type `Integer`
- une méthode `performanceEgale` prenant un `Integer` en paramètre qui renvoie `true` si la performance du commercial est égale à celle passée en paramètre, `false` sinon

## 207 : Créer un enum note dans un fichier à part avec les valeurs `INSUFFISANT, PASSABLE, BIEN, TRES_BIEN` et créer une méthode `equivalenceNote` (sans utiliser de `if`) dans `Commercial` traduisant une performance en `Note` :

- Si performance = 0 ou 50 : `INSUFFISANT`
- Si performance = 100 : `PASSABLE`
- Si performance = 150 : `BIEN`
- Si performance = 200 : `TRES_BIEN`
- Si performance autre : `null`

# Exercice 3 : Technicien

## 301 : Comme pour la classe `Commercial`, créer la classe `Technicien` et la faire hériter d'`Employe`

Ajouter un constructeur par défaut

## 302 : Modifier la classe `Technicien` pour ajouter un attribut `grade` de type Integer avec son getter et son setter

## 303 : Créer un constructeur pour la classe `Technicien` qui initialise tous les attributs hérités de la classe `Employe` en faisant appel au constructeur d'`Employe` et qui initialise également l'attribut `grade`.

## 304 : Redéfinir le setter de l'attribut `salaire` pour qu'il renvoie la valeur de l'attribut `salaire`, auquel on ajoute la bonification du grade qui est égale à une augmentation de X0% par rapport au salaire de base :

Ex : Grade 3 : 30% d'augmentation : 1000.0 de salaire avec grade 1 : 1100.0

## 305 : Redéfinir le getter de `nbConges` pour retourner le nombre de congés de base + autant de congés que d'année d'ancienneté.

## 306 : Redéfinir la méthode `getPrimeAnnuelle` pour qu'elle renvoie la prime annuelle de base à laquelle on ajoute un pourcentage en fonction du grade (+10% pour grade 1, +30% pour grade 3...) ainsi que la prime d'ancienneté multipliée par le nombre d'année d'ancienneté

## 307 : Implémenter l'interface `Comparable` pour que l'on puisse comparer deux techniciens en fonction de leur grade plus le grade est haut, plus le technicien est compétent

# Exercice 4

## 401 : Rendre la classe `Employe` abstraite

## 402 : Rendre la méthode `getPrimeAnnuelle` d'`Employe` abstraite. 

# Exercice 5 : Manager

## 501 : Comme pour la classe `Commercial`, créer la classe `Manager` et la faire hériter d'`Employe`. Constater le problème de compilation et utiliser l'IDE pour le résoudre.

## 502 : Modifier la classe `Manager` pour ajouter un attribut `equipe` permettant de stocker un ensemble non ordonné de techniciens avec son getter et son setter

## 503 : Ajouter une méthode `ajoutTechnicienEquipe` qui prend en paramètre un technicien et qui l'ajoute dans l'équipe

## 504 : Redéfinir le setter de l'attribut salaire pour qu'il renvoie la valeur du salaire multipliée par l'indice manager (`Entreprise.INDICE_MANAGER`, auquel on ajoute 10% (sur le salaire passé en paramètre) par membre d'équipe

## 505 : Modifier le code de la méthode `getPrimeAnnuelle` pour qu'elle renvoie la prime de base, à laquelle on ajoute la prime du manager en fonction du nombre de membres de son équipe (en utilisant `Entreprise.PRIME_MANAGER_PAR_TECHNICIEN`)

## 506 : Ajoutée une méthode interne `augmenterSalaireEquipe` qui augmente le salaire de tous les membres de l'équipe d'un manager par un pourcentage (`Double`) avec la méthode précédemment définie dans `Employe`

## 507 : Redéfinir la méthode `augmenterSalaire` pour systématiquement augmenter le salaire de l'équipe d'un manager avant d'augmenter le salaire du manager...

## 508 : Surcharger `ajoutTechnicienEquipe` pour permettre l'ajout d'un technicien en passant directement les paramètres nom, prenom, matricule, date, salaire et grade

## 509 : Bonus : Ajouter une méthode `equipeParGrade` renvoyant une liste des techniciens de l'équipe triée par grade décroissant en utilisant les Streams et les lambdas

## 510 : Bonus : Ajouter une méthode `salaireEquipeGrade1` qui renvoie la somme des salaires des membres de l'équipe dont le grade est égal à 1 en une ligne avec des lambdas
