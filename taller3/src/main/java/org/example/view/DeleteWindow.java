package org.example.view;

import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.example.controller.DeleteController;

public class DeleteWindow extends Stage {

    private VBox pane;
    private ListView<String> listMovie;
    private Button delete;

    public DeleteWindow ( ) {
        init();
        DeleteController controller = new DeleteController ( this );
    }

    public void init(){
        pane = new VBox (  );
        Text tx = new Text ( "Selleccione la pelicula a eliminar" );
        ScrollPane sp = new ScrollPane (  );
        listMovie = new ListView (  );
        sp.setContent ( listMovie );
        delete = new Button ( "Eliminar" );

        pane.getChildren ().addAll ( tx, sp, delete );
        Scene scene = new Scene ( pane );
        this.setScene ( scene );
    }

    public ListView getListMovie ( ) {
        return listMovie;
    }

    public Button getDelete ( ) {
        return delete;
    }
}
