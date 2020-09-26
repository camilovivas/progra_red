package view;

import controller.MultiChatController;
import javafx.scene.Scene;
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

    private Scene scene;
    private MultiChatController controller;

    public MultichatWindows(){
        controller = new MultiChatController (this) ;

    }

    public void init(){
        scroll.setContent ( clients );

        message = new TextField ( "Mensaje" );
        send = new Button ( ">" );
        msgAndSend = new HBox ( );
        msgAndSend.getChildren ().addAll ( message, send );

    }

    public Button getSend ( ) {
        return send;
    }

    public TextField getMessage ( ) {
        return message;
    }
}
