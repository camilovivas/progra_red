package view;

import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class MultichatWindows extends Stage {

    private HBox hb;

    private ScrollPane scroll;
    private VBox clients;
    private VBox vb;
    private VBox msg;

    private HBox msgAndSend;
    private TextField message;
    private Button send;

    public MultichatWindows(){

        scroll.setContent ( clients );

        message = new TextField ( "Mensaje" );
        send = new Button ( ">" );
        msgAndSend = new HBox ( );
        msgAndSend.getChildren ().addAll ( message, send );

    }

}
