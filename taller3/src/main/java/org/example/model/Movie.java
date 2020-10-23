package org.example.model;

public class Movie {

    private int id;
    private String nombre;
    private int year;
    private int generoID;

    public Movie ( int id, String nombre, int year, int generoID ) {
        this.id = id;
        this.nombre = nombre;
        this.year = year;
        this.generoID = generoID;
    }

    public int getId ( ) {
        return id;
    }

    public String getNombre ( ) {
        return nombre;
    }

    public int getYear ( ) {
        return year;
    }

    public int getGeneroID ( ) {
        return generoID;
    }
}
