package com.ipiecoles.java.java220;

import org.joda.time.LocalDate;
import org.w3c.dom.ranges.Range;

import java.awt.font.NumericShaper;
import java.util.Objects;

public class Commercial extends Employe{

    private Double caAnnuel;

    public Integer getPerformance() {
        return performance;
    }

    public void setPerformance(Integer performance) {
        this.performance = performance;
    }

    public Integer performance;


    public Commercial(){

    }

    public Commercial(String name, String fName, String matric, LocalDate dateEmb, Double salary, Double caAnnuel) {
        super(name, fName, matric, dateEmb, salary);
        this.caAnnuel = caAnnuel;
    }

    public Commercial(String name, String fName, String matric, LocalDate dateEmb, Double salary, Double caAnnuel,Integer perf) {
        this(name, fName, matric, dateEmb, salary,caAnnuel);
        this.caAnnuel = caAnnuel;
        this.performance=perf;
    }

    public Boolean performanceEgale(Integer perfCompare){

        return Objects.equals(this.performance, perfCompare);

    }

    @Override
    public Double getPrimeAnnuelle() {

        if (getCaAnnuel()==null){
            return Math.ceil(500d);
        }else if (getCaAnnuel()*0.05 <500){
            return Math.ceil(500d);
        }

        return Math.ceil(getCaAnnuel()*0.05);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Commercial that = (Commercial) o;
        return Objects.equals(caAnnuel, that.caAnnuel) && Objects.equals(performance,that.performance);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), caAnnuel, performance);
    }

    @Override
    public String toString() {
        return "Commercial{" +
                "caAnnuel=" + caAnnuel +
                ", performance=" + performance +
                '}';
    }

    public Double getCaAnnuel() {
        return caAnnuel;
    }

    public void setCaAnnuel(Double caAnnuel) {
        this.caAnnuel = caAnnuel;
    }
}
