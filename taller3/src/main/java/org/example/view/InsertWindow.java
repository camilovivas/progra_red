package org.example.view;

import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.example.controller.InsertController;

public class InsertWindow extends Stage {

    private VBox pane;
    private Text movieName, movieYear, genero, actores, actorName,  actorApellido;
    private TextArea name, year, nameActor, apellidoActor;
    private ComboBox<String> listGenero;
    private ComboBox<String> listActores;
    private Scene scene;
    private Button addMovie, active, addActor;


    public InsertWindow ( ) {
        init();
        InsertController controller = new InsertController (this);
    }

    public void init(){
        pane = new VBox (  );
        movieName = new Text ( "inserte el nombre de la pelicula" );
        name = new TextArea (  );

        movieYear = new Text ( "inserte el año de creacion de la pelicula" );
        year = new TextArea (  );

        HBox noFound = new HBox ( );

        VBox b1 = new VBox ( );
        actores = new Text ( "seleccione el actor de la pelicula" );
        listActores =  new ComboBox<> ( );
        b1.getChildren ().addAll ( actores, listActores );

        VBox b2 = new VBox ( );
        active = new Button ( "si no encuentra el actor de la pelicula presione aqui" );
        actorName = new Text ( "inserte el nombre del actor");
        nameActor = new TextArea (  );
        nameActor.setDisable ( true );
        actorApellido = new Text ( "inserte el apellido del actor");
        apellidoActor = new TextArea (  );
        apellidoActor.setDisable ( true );
        b2.getChildren ().addAll ( active, actorName, nameActor, actorApellido, apellidoActor );

        noFound.getChildren ().addAll ( b1, b2 );

        addActor = new Button ( "ADD ACTOR" );

        genero = new Text ( "seleccione el genero de la pelicula" );
        listGenero = new ComboBox<> ( );

        addMovie = new Button ( "add movie" );
        pane.getChildren ().addAll ( movieName, name, movieYear, year, noFound,  addActor,  genero, listGenero, addMovie );

        scene = new Scene ( pane , 600, 600);
        this.setScene ( scene );
    }

    public Button getAddMovie ( ) {
        return addMovie;
    }

    public TextArea getName ( ) {
        return name;
    }

    public TextArea getYear ( ) {
        return year;
    }

    public TextArea getNameActor ( ) {
        return nameActor;
    }

    public TextArea getApellidoActor ( ) {
        return apellidoActor;
    }

    public ComboBox<String> getListGenero ( ) {
        return listGenero;
    }

    public ComboBox<String> getListActores ( ) {
        return listActores;
    }

    public Button getActive ( ) {
        return active;
    }

    public Button getAddActor ( ) {
        return addActor;
    }
}
