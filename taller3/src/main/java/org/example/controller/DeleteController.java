package org.example.controller;

import javafx.scene.control.Alert;
import javafx.scene.control.MultipleSelectionModel;
import javafx.scene.control.SelectionMode;
import org.example.comm.SQLConnection;
import org.example.model.Movie;
import org.example.view.DeleteWindow;

import java.util.ArrayList;

public class DeleteController {

    private DeleteWindow window;
    private SQLConnection sql;

    public DeleteController ( DeleteWindow window ) {
        this.window = window;
        sql = new SQLConnection ();
        fillMovie ();
        delete ();
    }

    public void fillMovie(){
        ArrayList<Movie> date = sql.getAllMovies ();
        for (int i = 0; i < date.size ( ); i++) {
            window.getListMovie ().getItems ().add ( date.get ( i ).getNombre () );
        }
    }

    public void delete(){
        window.getDelete ().setOnAction (
                e->{
                    window.getListMovie ().getSelectionModel ().setSelectionMode ( SelectionMode.SINGLE );
                    MultipleSelectionModel m = window.getListMovie ().getSelectionModel ();
                    String peli = m.getSelectedItem ().toString ();
                    sql.deleteMovie ( peli );
                    Alert aler = new Alert ( Alert.AlertType.CONFIRMATION );
                    aler.setContentText ( "se ha eliminado la pelicula: "+peli );
                    aler.setHeaderText ( "Eliminacion Exitosa" );
                    aler.showAndWait ();
                    sql.offConnect ();
                    window.close ();
                }
        );
    }
}
