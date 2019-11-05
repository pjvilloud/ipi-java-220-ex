# ipi-java-220-ex 
![Build status](https://travis-ci.org/pjvilloud/ipi-java-220-ex.svg?branch=correction)

Cours de Java, module 220 pour l'IPI

# Exercice 1 : Entreprise

## Déclarer au niveau de la classe Entreprise les constantes de classe suivantes

- SALAIRE_BASE de type Double et de valeur 1480.27
- NB_CONGES_BASE de type Integer et de valeur 25
- INDICE_MANAGER de type Double et de valeur 1.3
- PRIME_MANAGER_PAR_TECHNICIEN de type Double et de valeur 250
- PRIME_ANCIENNETE de type Double et de valeur 100

## Déclarer au niveau de la classe Entreprise, une méthode primeAnnuelleBase, publique et statique calculant la prime de base pour tous les employés de l'entreprise de la manière suivante :

Utiliser la classe LocalDate (de joda time) pour obtenir l'année courante.
Prime = 50% de l'année en cours. Ex : 2017 : 2017 / 2 = 1008.5

# Exercice 2 : Employé

## Ajouter dans la classe Employe les champs suivants avec leurs getters/setters : 

- nom : String
- prenom : String
- matricule : String
- dateEmbauche : LocalDate
- salaire : Double

## Définir un constructeur par défaut dans la classe Employé, puis un constructeur avec l'ensemble des arguments précédemment créés, dans le même ordre

## Déclarer et développer la méthode getNombreAnneeAnciennete calculant le nombre d'année d'ancienneté d'un employé

Faire en sorte qu'elle ne puisse être redéfinie dans d'éventuelles sous-classes. Un employé enbauché cette année a une ancienneté de 0

## Modifier le setter de dateEmbauche pour lever une Exception avec le message "La date d'embauche ne peut être postérieure à la date courante" lorsque la date d'embauche est postérieure à la date courante

## Développer une méthode getNbConges retournant la constante de classe NB_CONGES_BASE de la classe Entreprise

## Redéfinir la méthode toString (héritée d'Object) pour afficher un employé de la manière suivante :

```"Employe{nom='nom', prenom='prenom', matricule='12345', dateEmbauche=1970-01-01, salaire=500.0}"```

## Redéfinir la méthode equals (héritée d'Object) testant l'égalité sur l'ensemble des attributs de la classe Employe

## Redéfinir la méthode hashCode (héritée d'Object) en utilisant Objects.hash(...) et en respectant l'ordre nom, prenom, matricule, dateEmbauche, salaire

## Coder la méthode augmenterSalaire prenant en paramètre un pourcentage d'augmentation de type Double et augmentant l'attribut salaire du pourcentage passé en paramètre :

Ex : un salaire de 500.0, avec une augmentation de 0.50, cela donne un salaire de 750.0

## Rendre la classe Employe abstraite

## Déclarer dans la classe Employe une méthode abstraite getPrimeAnnuelle retournant un Double

# Exercice 3 : Commercial

## Faire en sorte que la classe Commercial hérite de la classe Employé

Analyser le message d'erreur remonté par l'IDE et utiliser l'IDE pour résoudre le problème

## Modifier la classe Commercial pour ajouter un attribut caAnnuel de type Double avec son getter et son setter

## Modifier la méthode getPrimeAnnuelle précédemment générée par l'IDE pour que la prime soit égale à 5% du caAnnuel, avec un minimum den500 € même en cas de chiffre d'affaire nul.

Faire en sorte que la prime soit toujours arrondi à l'euro supérieur. Voir la classe Math

## Créer un constructeur pour la classe Commercial qui initialise tous les attributs hérités de la classe Employe en faisant appel au constructeur d'Employe et qui initialise également l'attribut caAnnuel

## Redéfinir la méthode equals pour permettre de tester l'égalité entre deux instances de la classe Commercial.

Appeler la méthode equals de la classe Employe

## Ajouter les éléments suivants dans la classe Commercial 

- un attribut Integer performance
- une méthode performanceEgale prenant un Integer en paramètre qui renvoie true si la performance du commercial est égale à celle passée en paramètre, false sinon

## Créer un enum note avec les valeurs INSUFFISANT, PASSABLE, BIEN, TRES_BIEN et créer une méthode equivalenceNote (sans utiliser de if) dans Commercial traduisant une performance en Note :

- Si performance = 0 ou 50 : INSUFFISANT
- Si performance = 100 : PASSABLE
- Si performance = 150 : BIEN
- Si performance = 200 : TRES_BIEN

# Exercice 4 : Technicien

## Comme pour la classe Commercial, faire hériter la classe Technicien et implémenter la méthode abstraite getPrimeAnnuelle pour qu'elle retourne quelque chose et que la compilation passe (la méthode sera implémentée plus tard)

Ajouter un constructeur par défaut

## Modifier la classe Technicien pour ajouter un attribut grade de type Integer avec son getter et son setter

## Créer un constructeur pour la classe Technicien qui initialise tous les attributs hérités de la classe Employe en faisant appel au constructeur d'Employe et qui initialise également l'attribut grade.

## Modifier le setter de l'attribut grade pour qu'il lève une exception de la classe TechnicienException (à créer) et dont le message est : "Le grade doit être compris entre 1 et 5 : X, technicien : Technicien{grade=X} Employe{nom='NOM', prenom='PRENOM', matricule='MATRICULE', dateEmbauche=DATE, salaire=SALAIRE}"

Avec X = valeur incorrecte passée au setter et NOM, PRENOM... les valeurs des attributs d'Employe. Astuce : Ajouter une méthode toString à Technicien

## Redéfinir le setter de l'attribut salaire pour qu'il renvoie la valeur de l'attribut salaire, auquel on ajoute la bonification du grade qui est égale à une augmentation de X0% par rapport au salaire de base :

Ex : Grade 3 : 30% d'augmentation : 1000.0 de salaire avec grade 1 : 1100.0

## Redéfinir le getter de nbConges pour retourner le nombre de congés de base + autant de congés que d'année d'ancienneté.

## Modifier le code de la méthode getPrimeAnnuelle pour qu'elle renvoie la prime annuelle de base à laquelle on ajoute un pourcentage en fonction du grade (idem exo 305) ainsi que la prime d'ancienneté multipliée par le nombre d'année d'ancienneté

## Implémenter l'interface Comparable pour que l'on puisse comparer deux Techniciens en fonction de leur grade plus le grade est haut, plus le technicien est compétent

# Exercice 5 : Manager

## Comme pour la classe Commercial, faire hériter la classe Manager d'Employe et implémenter la méthode abstraite getPrimeAnnuelle pour qu'elle retourne quelque chose et que la compilation passe (la méthode sera implémentée plus tard)

## Modifier la classe Manager pour ajouter un attribut equipe permettant de stocker un ensemble non ordonné de techniciens avec son getter et son setter

## Ajouter une méthode ajoutTechnicienEquipe qui prend en paramètre un technicien et qui l'ajoute dans l'équipe

## Redéfinir le setter de l'attribut salaire pour qu'il renvoie la valeur du salaire multipliée par l'index manager, auquel on ajoute 10% (sur le salaire passé en paramètre) par membre d'équipe

## Modifier le code de la méthode getPrimeAnnuelle pour qu'elle renvoie la prime de base, à laquelle on ajoute la prime du manager en fonction du nombre de membres de son équipe (en utilisant Entreprise.PRIME_MANAGER_PAR_TECHNICIEN)

## Ajoutée une méthode interne augmenterSalaireEquipe qui augmente le salaire de tous les membres de l'équipe d'un manager par un pourcentage (Double) avec la méthode précédemment définie dans Employe

Voir ensuite les deux dernières lignes du test et essayer de comprendre pourquoi on teste ces résultats.

## Redéfinir la méthode augmenterSalaire pour systématiquement augmenter le salaire de l'équipe d'un manager avant d'augmenter le salaire du manager...

## Surcharger ajoutTechnicienEquipe pour permettre l'ajout d'un technicien en passant directement les paramètres nom, prenom, matricule, date, salaire et grade

## Ajouter une méthode equipeParGrade renvoyant une liste des techniciens de l'équipe triée par grade décroissant en utilisant les Streams et les lambdas

## Ajouter une méthode salaireEquipeGrade1 qui renvoie la somme des salaires des membres de l'équipe dont le grade est égal à 1 en une ligne avec des lambdas
