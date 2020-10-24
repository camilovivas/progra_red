package org.example.view;

import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.example.controller.ResultController;

public class Result extends Stage {

    private VBox pane;
    private ListView results;
    private  ComboBox<String> selection;


    public Result ( int index ) {
        init ();
        ResultController controller = new ResultController ( this, index );
    }

    public void init(){
        Text tx = new Text ( "Selecione:" );
        pane = new VBox (  );

        selection = new ComboBox<> (  );
        results = new ListView (  );
        ScrollPane scrollPane = new ScrollPane (  );
        scrollPane.setContent ( results );
        pane.getChildren ().addAll ( tx, selection, scrollPane );
        Scene scene = new Scene ( pane );
        this.setScene ( scene );
    }

    public ListView getResults ( ) {
        return results;
    }

    public ComboBox<String> getSelection ( ) {
        return selection;
    }
}
