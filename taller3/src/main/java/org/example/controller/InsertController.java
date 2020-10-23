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
        sql = new SQLConnection ();
        getActores ();
        getGeneros ();
    }

    public void getActores(){
        ArrayList<Actor> actores = sql.getAllActores ();
        for (int i = 0; i < actores.size ( ); i++) {
            String nombre =  actores.get ( i ).getNombre ();
            String apellido = actores.get ( i ).getApellido ();
            window.getListActores ().getItems ().add ( nombre +" "+ apellido );

        }
    }

    public void getGeneros(){
        ArrayList<Genero> generos = sql.getAllGeneros ();
        for (int i = 0; i < generos.size ( ); i++) {
            window.getListGenero ().getItems ().addAll ( generos.get ( i ).getTipo () );
        }
    }
}
