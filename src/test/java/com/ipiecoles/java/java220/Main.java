package com.ipiecoles.java.java220;

import java.util.HashSet;

public class Main {
    public static void main(String[] args) {
        Manager mg = new Manager();
        HashSet<Technicien> eq = new HashSet();
        /*eq.add(new Technicien());
        eq.add(new Technicien());*/

        mg.setEquipe(eq);

        System.out.println(eq.size());
        System.out.println(mg.getEquipe().size());

        eq.add(new Technicien());
        eq.add(new Technicien());

        System.out.println(eq.size());
        System.out.println(mg.getEquipe().size());

        HashSet<String> test = new HashSet();
        test.add("tic");
        System.out.println(test.size());
        test.add("tac");
        System.out.println(test.size());
        test.add("toc");
        System.out.println(test.size());
    }
}
