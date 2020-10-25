package org.example.controller;

import com.sun.nio.sctp.Notification;
import javafx.scene.control.Alert;
import org.example.comm.SQLConnection;
import org.example.model.*;
import org.example.view.InsertWindow;

import java.util.ArrayList;

public class InsertController {

    private SQLConnection sql;
    private InsertWindow window;
    public ArrayList<Integer> actores;

    public InsertController ( InsertWindow window ) {
        this.window = window;
        sql = new SQLConnection ( );
        actores = new ArrayList<> ( );
        getActores ( );
        getGeneros ( );
        active ( );
        addMovie ( );
        addActor ( );
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

    public int createMovie ( ) {
        String nombrePeli = window.getName ( ).getText ( );
        int year = Integer.parseInt ( window.getYear ( ).getText ( ) );
        String genero = window.getListGenero ( ).getValue ( );
        int generoID = sql.searchIdGenero ( genero );
        Movie movie = new Movie ( -1, nombrePeli, year, generoID );
        sql.insertMovie ( movie );
        return sql.searchIdMovie ( nombrePeli );
    }

    public int createActor ( ) {
        int toReturn;
        if ( !window.getNameActor ( ).isDisable ( ) ) {
            String name = window.getNameActor ( ).getText ( );
            String apellido = window.getApellidoActor ( ).getText ( );
            Actor actor = new Actor ( -1, name, apellido );
            sql.insertActor ( actor );
            toReturn = sql.searchIdActor ( name );
        } else {
            String name = window.getListActores ( ).getValue ( );
            toReturn = sql.searchIdActor ( name );
        }
        return toReturn;
    }

    public void addMovie ( ) {
        window.getAddMovie ( ).setOnAction (
                e -> {
                    int idMovie = createMovie ( );
                    for (int i = 0; i < actores.size ( ); i++) {
                        sql.joinMovieAndActor ( idMovie, actores.get ( i ) );
                    }

                    sql.offConnect ( );
                    window.close ( );
                }
        );
    }

    public void addActor ( ) {
        window.getAddActor ( ).setOnAction (
                e -> {
                    int idActor = createActor ( );
                    actores.add ( idActor );
                    emptyCampos ( );
                    Alert noti = new Alert ( Alert.AlertType.CONFIRMATION );
                    noti.setHeaderText ( "Hemmos agregado al actor y estamos listos para seguir agregando" );
                    noti.setContentText ( "Si ya terminaste de agregar los actores, selecciona el genero y pesiona el boton add movie" );
                    noti.showAndWait ();
                }
        );
    }

    public void emptyCampos ( ) {
        if ( !window.getNameActor ( ).isDisable ( ) ) {
            window.getNameActor ( ).clear ( );
            window.getNameActor ( ).setDisable ( true );
            window.getApellidoActor ( ).clear ( );
            window.getApellidoActor ( ).setDisable ( true );
        } else {
            window.getListActores ( ).setValue ( "" );
        }
    }
}
