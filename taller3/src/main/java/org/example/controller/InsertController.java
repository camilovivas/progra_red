package org.example.controller;

import org.example.comm.SQLConnection;
import org.example.model.*;
import org.example.view.InsertWindow;

import java.util.ArrayList;

public class InsertController {

    private SQLConnection sql;
    private InsertWindow window;

    public InsertController ( InsertWindow window ) {
        this.window = window;
        sql = new SQLConnection ( );
        getActores ( );
        getGeneros ( );
        active ( );
        addMovie ();
    }

    public void getActores ( ) {
        ArrayList<Actor> actores = sql.getAllActores ( );
        for (int i = 0; i < actores.size ( ); i++) {
            String nombre = actores.get ( i ).getNombre ( );
            String apellido = actores.get ( i ).getApellido ( );
            window.getListActores ( ).getItems ( ).add ( nombre + " " + apellido );

        }
    }

    public void getGeneros ( ) {
        ArrayList<Genero> generos = sql.getAllGeneros ( );
        for (int i = 0; i < generos.size ( ); i++) {
            window.getListGenero ( ).getItems ( ).addAll ( generos.get ( i ).getTipo ( ) );
        }
    }

    public void active ( ) {
        window.getActive ( ).setOnAction (
                e -> {
                    window.getNameActor ( ).setDisable ( false );
                    window.getApellidoActor ( ).setDisable ( false );
                }
        );
    }

    public void createMovie ( ) {
        String nombrePeli = window.getName ( ).getText ( );
        int year = Integer.parseInt ( window.getYear ( ).getText ( ) );
        String genero = window.getListGenero ( ).getValue ( );
        int generoID = sql.searchIdGenero ( genero );
        Movie movie = new Movie ( -1, nombrePeli, year, generoID );
        sql.insertMovie ( movie );
    }

    public int createActor ( ) {
        int toReturn= 0;
        if ( !window.getNameActor ( ).isDisable ( ) ) {
            String name = window.getNameActor ( ).getText ( );
            String apellido = window.getApellidoActor ( ).getText ( );
            Actor actor = new Actor ( -1, name, apellido );
            sql.insertActor ( actor );
            toReturn = sql.searchIdActor ( name );
        }
        else{
            String name = window.getListActores ().getValue ();
            toReturn = sql.searchIdActor ( name );
        }
        return toReturn;
    }

    public void addMovie ( ) {
        window.getAddMovie ( ).setOnAction (
                e -> {
                    createActor ( );
                    createMovie ( );


                    sql.offConnect ( );
                }
        );
    }
}
