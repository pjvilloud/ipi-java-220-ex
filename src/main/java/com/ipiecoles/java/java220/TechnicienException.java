package com.ipiecoles.java.java220;

public class TechnicienException extends Exception {

    public static final String COMPARE = "L'objet comparé n'est pas une instance de Technicien. L'objet comparé est :";

    public TechnicienException(){
        super();
    }

    public TechnicienException(String message){
        super(message);
    }

    public TechnicienException(String message, Technicien technicien, Object o){
        super(message + o.toString() + " \n comparé au Technicien :" + technicien.toString());
    }
}
