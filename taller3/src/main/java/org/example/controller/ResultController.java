package org.example.controller;

import org.example.comm.SQLConnection;
import org.example.model.*;
import org.example.view.Result;

import java.util.ArrayList;

public class ResultController {

    private SQLConnection sql;
    private Result window;

    public ResultController ( Result window, int index ) {
        this.window = window;
        sql = new SQLConnection ( );
        star ( index );
    }


    void star ( int index ) {
        switch (index) {
            case 1:
                fillGenero ( );
                byGenero ( );
                break;
            case 2:
                fillActores ();
                byActor ();
                break;

            case 3:
               fillMovies ();
               actoresByMovie ();
                break;

        }
    }

    public void fillGenero ( ) {
        ArrayList<Genero> dates = sql.getAllGeneros ( );
        for (int i = 0; i < dates.size ( ); i++) {
            window.getSelection ( ).getItems ( ).add ( dates.get ( i ).getTipo ( ) );
        }
    }

    public void fillMovies ( ) {
        ArrayList<Movie> dates = sql.getAllMovies ( );
        for (int i = 0; i < dates.size ( ); i++) {
            window.getSelection ( ).getItems ( ).add ( dates.get ( i ).getNombre ( ) );
        }
    }

    public void fillActores ( ) {
        ArrayList<Actor> dates = sql.getAllActores ();
        for (int i = 0; i < dates.size ( ); i++) {
            window.getSelection ().getItems ().add ( dates.get ( i ).getNombre () );
        }
    }

    public void byGenero ( ) {
        window.getSelection ( ).setOnAction (
                e -> {
                    String genero = window.getSelection ( ).getValue ( );
                    ArrayList<String> dates = sql.search ( genero, 1 );
                    for (int i = 0; i < dates.size ( ); i++) {
                        window.getResults ( ).getItems ( ).add ( dates.get ( i ) );
                    }
                    sql.offConnect ();
                }
        );
    }

    public void byActor ( ) {
        window.getSelection ().setOnAction (
                e->{
                    String actor = window.getSelection ( ).getValue ( );
                    ArrayList<String> dates = sql.search ( actor, 2 );
                    for (int i = 0; i < dates.size ( ); i++) {
                        window.getResults ( ).getItems ( ).add ( dates.get ( i ) );
                    }
                    sql.offConnect ();
                }
        );
    }

    public void actoresByMovie ( ) {
        window.getSelection ().setOnAction (
                e->{
                    String movie = window.getSelection ( ).getValue ( );
                    ArrayList<String> dates = sql.search ( movie, 3 );
                    for (int i = 0; i < dates.size ( ); i++) {
                        window.getResults ( ).getItems ( ).add ( dates.get ( i ) );
                    }
                    sql.offConnect ();
                }
        );
    }
}
