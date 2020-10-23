package org.example.view;

import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.example.controller.SearchController;

public class SearchWindow extends Stage {

    private VBox pane;
    private Text tittle;
    private HBox Hpane;
    private Button b1, b2, b3;

    public SearchWindow ( ) {
        init();
        SearchController controller = new SearchController (this);
    }

    private void init ( ) {
        pane = new VBox (  );
        tittle = new Text ( "buscar:" );
        Hpane = new HBox ( );
        b1 = new Button ( "peliculas de un genero" );
        b2 = new Button ( "peliculas de un actor" );
        b3 = new Button ( "actores de una pelicula" );
        Hpane.getChildren ().addAll ( b1, b2, b3 );
        pane.getChildren ().addAll ( tittle, Hpane );

        Scene sc = new Scene ( pane );
        this.setScene ( sc );
    }

    public Button getB1 ( ) {
        return b1;
    }

    public Button getB2 ( ) {
        return b2;
    }

    public Button getB3 ( ) {
        return b3;
    }
}
