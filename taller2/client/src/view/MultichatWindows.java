package view;

import controller.MultiChatController;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.util.ArrayList;

public class MultichatWindows extends Stage {

    private HBox hb;

    private ScrollPane scroll;
    private VBox clients;
    private ArrayList<Button> clientsConnected;

    private VBox vb;

    private TextArea messageArea;

    private HBox msgAndSend;
    private TextField message;
    private Button send;

    private Scene scene;
    private MultiChatController controller;


    public MultichatWindows(){
        controller = new MultiChatController (this) ;
        clientsConnected =  new ArrayList<> ( );
        init ();
    }

    public void fillButtons(){
        for (int i = 0; i < 50; i++) {
            Button bt = new Button ( i+"" );
            clients.getChildren ().add( bt);
        }
    }

    public void init(){
        hb = new HBox ( );

        scroll = new ScrollPane ( );
        clients = new VBox ( );
        scroll.setContent ( clients );
        fillButtons ();

        vb = new VBox ( );
        messageArea = new TextArea ( );
        messageArea.setEditable ( false );

        message = new TextField ( "Mensaje" );
        send = new Button ( ">" );
        msgAndSend = new HBox ( );
        msgAndSend.getChildren ().addAll ( message, send );
        vb.getChildren ().addAll ( messageArea, msgAndSend );

        hb.getChildren ().addAll ( scroll, vb );

        scene = new Scene ( hb, 600,600 );
        this.setScene ( scene );

    }

    public Button getSend ( ) {
        return send;
    }

    public TextField getMessage ( ) {
        return message;
    }

    public TextArea getMessageArea ( ) {
        return messageArea;
    }

    public ArrayList<Button> getClientsConnected ( ) {
        return clientsConnected;
    }
}
