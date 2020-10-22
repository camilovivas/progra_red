package org.example.view;

import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class InsertWindow extends Stage {

    private VBox pane;
    private Text movieName, movieYear, genero;
    private TextArea name, year;
    private ListView<String> listGenero;
    private Scene scene;
    private Button addMovie;


    public InsertWindow ( ) {
        init();
    }

    public void init(){
        pane = new VBox (  );
        movieName = new Text ( "inserte el nombre de la pelicula" );
        name = new TextArea (  );

        movieYear = new Text ( "inserte el año de creacion de la pelicula" );
        year = new TextArea (  );

        genero = new Text ( "seleccione el genero de la pelicula" );
        listGenero = new ListView<> (  );

        addMovie = new Button ( "add movie" );
        pane.getChildren ().addAll ( movieName, name, movieYear, year, genero, listGenero );

        scene = new Scene ( pane );
        this.setScene ( scene );
    }

    public ListView<String> getListGenero ( ) {
        return listGenero;
    }

    public Button getAddMovie ( ) {
        return addMovie;
    }
}
