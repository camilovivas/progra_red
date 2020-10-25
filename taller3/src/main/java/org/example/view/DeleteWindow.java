package org.example.view;

import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;

public class DeleteWindow {

    private Scene scene;
    private VBox pane;
    private ListView listMovie;
    private Button delete;

    public DeleteWindow ( ) {
        init();
    }

    public void init(){
        pane = new VBox (  );

        listMovie = new ListView (  );

        delete = new Button ( "Buscar" );

    }
}
