package org.example.view;

import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.example.controller.MainController;


public class MainWindows extends Stage {


    private VBox pane;
    private Scene scene;
    private Text tittle, accion;
    private HBox pane2;
    private Button addMovie, deleteMovie, search;

    public MainWindows (){
        init();
        MainController controller = new MainController (this);
    }

    public void init(){
       pane = new VBox ( );
       tittle = new Text ( "Bienvenid@!" );
       accion = new Text ( "Que accion desea realizar" );
       pane2 = new HBox ( );
       addMovie = new Button ( "agregar pelicula" );
       deleteMovie = new Button ( "eliminar pelicula" );
       search = new Button ( "buscar" );
       pane2.getChildren ().addAll ( addMovie, deleteMovie, search );
       pane.getChildren ().addAll ( tittle, accion, pane2 );
       scene = new Scene ( pane ,300, 200);
       this.setTitle ( "programa de pelis" );
       this.setScene ( scene );
    }

    public Button getAddMovie ( ) {
        return addMovie;
    }

    public Button getDeleteMovie ( ) {
        return deleteMovie;
    }

    public Button getSearch ( ) {
        return search;
    }
}
