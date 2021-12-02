package com.ipiecoles.java.java220;

import java.util.Arrays;
import java.util.HashSet;

public class Unite<T> {
    private T responsable;

    private HashSet<T> membres = new HashSet<>();

    public Unite() {

    }

    public Unite(T membre) {
        responsable = membre;
        membres.add(membre);
    }

    public void ajouterMembre(T... membres) {
        this.membres.addAll(Arrays.asList(membres));
    }

    public T getResponsable() {
        return responsable;
    }

    public void setResponsable(T responsable) {
        this.responsable = responsable;
    }

    public HashSet<T> getMembres() {
        return membres;
    }

    public void setMembres(HashSet<T> membres) {
        this.membres = membres;
    }

    @Override
    public String toString() {
        return "Unite{" +
                "responsable=" + responsable +
                ", membres=" + membres +
                '}';
    }
}
