package com.ipiecoles.java.java220;

import org.joda.time.LocalDate;

public class Main {

    public static void main(String[] args){
        Employe d = new Employe("nom", "prenom", "matricule", LocalDate.now(), 500.0);
        System.out.println(d.toString());
    }

}
