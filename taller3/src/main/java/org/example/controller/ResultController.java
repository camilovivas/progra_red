package org.example.controller;

import org.example.comm.SQLConnection;
import org.example.model.Genero;
import org.example.view.Result;

import java.util.ArrayList;

public class ResultController {

    private SQLConnection sql;
    private Result window;
    private int index;

    public ResultController ( Result window, int index ) {
        this.window = window;
        this.index = index;
        sql = new SQLConnection ( );
        star ( index );
    }


    void star ( int index ) {
        switch (index) {
            case 1:
                fillGenero ();
                byGenero ();
                break;
            case 2:

                break;

        }
    }

    public void fillGenero ( ) {
        ArrayList<Genero> dates = sql.getAllGeneros ( );
        for (int i = 0; i < dates.size ( ); i++) {
            window.getSelection ( ).getItems ( ).add ( dates.get ( i ).getTipo ( ) );
        }
    }

    public void fillMovies(){
        //TODO
    }

    public void fillActores(){
        //TODO
    }

    public void byGenero ( ) {
        window.getSelection ( ).setOnAction (
                e -> {
                    String genero = window.getSelection ( ).getValue ( );
                    ArrayList<String> dates = sql.searchMovieByGenero ( genero );
                    for (int i = 0; i < dates.size ( ); i++) {
                        window.getResults ( ).getItems ( ).add ( dates.get ( i ) );
                    }

                }
        );
    }

    public void byActor(){
        //TODO
    }

    public void ActoresByMovie(){

    }
}
