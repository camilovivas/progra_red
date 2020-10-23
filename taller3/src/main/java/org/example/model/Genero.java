package org.example.model;

public class Genero {

    private int id;
    private String tipo;

    public Genero ( int id, String tipo ) {
        this.id = id;
        this.tipo = tipo;
    }

    public int getId ( ) {
        return id;
    }

    public String getTipo ( ) {
        return tipo;
    }
}
