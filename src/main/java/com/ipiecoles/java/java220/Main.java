package com.ipiecoles.java.java220;

import org.joda.time.LocalDate;

import java.util.Objects;

public class Main {

    public static void main(String[] args){
        Employe d = new Employe("zzzzzzz", "prenom", "matricule", LocalDate.now(), 500.0);
        Employe d2 = new Employe("a", "zzzzzzzzzzzz", "matricule", LocalDate.now(), 500.0);
        System.out.println(d.toString());
        System.out.println(d.hashCode());
        System.out.println(d.getNom().hashCode());
        System.out.println(d.getMatricule().hashCode());
        System.out.println(d.getDateEmbauche().hashCode());
        System.out.println(d.getSalaire().hashCode());
        System.out.println(d2.toString());
        System.out.println(d2.hashCode());
    }

}
