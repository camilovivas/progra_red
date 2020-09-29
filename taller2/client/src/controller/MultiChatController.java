package controller;

import com.google.gson.Gson;
import comm.*;
import javafx.application.Platform;
import javafx.scene.control.Button;
import model.*;
import view.MultichatWindows;

import java.util.ArrayList;
import java.util.UUID;

public class MultiChatController implements OnMessageListenner {
    private MultichatWindows windows;
    private TCPConnection connection;

    //esta es la pantalla de chat del usuario
    private String owner;


    public MultiChatController ( MultichatWindows input, String owner ) {
        connection = TCPConnection.getInstance ( );
        connection.getReceptor ( ).setListenner ( this );
        this.windows = input;
        this.owner = owner;
        mandame ( );
        btSendAction ( );
    }

    public void mandame ( ) {
        Gson gson = new Gson ( );
        UserInside s = new UserInside ( null );
        String json = gson.toJson ( s );
        connection.getEmisor ( ).sendMessage ( json );
        System.out.println ( "le pedi los usuarios" );
    }

    public void fillButtons ( ArrayList<String> input ) {
        Platform.runLater (
                ( ) -> {
                    windows.getClientsConnected ( ).clear ( );
                    Button b = new Button ( "todos" );
                    windows.getClientsConnected ( ).add ( b );
                    windows.getClients ( ).getChildren ( ).add ( b );

                    for (int i = 0; i < input.size ( ); i++) {
                        if ( input.get ( i ).equals ( owner ) ) {
                            Button bt = new Button ( input.get ( i ) + "(yo)" );
                            bt.setDisable ( true );
                            windows.getClientsConnected ( ).add ( bt );
                            windows.getClients ( ).getChildren ( ).add ( bt );
                        } else {
                            Button bt = new Button ( input.get ( i ) );
                            windows.getClientsConnected ( ).add ( bt );
                            windows.getClients ( ).getChildren ( ).add ( bt );
                        }
                    }
                }
        );
    }

    public String seleccted ( ) {
        ArrayList<Button> buttons = windows.getClientsConnected ( );
        String toReturn = "";
        for (int i = 0; i < buttons.size ( ); i++) {
            if ( buttons.get ( i ).isFocused ( ) ) {
                toReturn = buttons.get ( i ).getText ( );
            }
        }
        return toReturn;
    }

    public void btSendAction ( ) {
        windows.getSend ( ).setOnAction (
                ( e ) -> {
                    String option = seleccted ( );
                    String message = windows.getMessage ( ).getText ( );
                    String id = owner;
                    if ( option.equalsIgnoreCase ( "todos" ) ) {

                        Message msg = new Message ( id, message );
                        Gson gson = new Gson ( );
                        String toSend = gson.toJson ( msg );
                        connection.getEmisor ( ).sendMessage ( toSend );

                    } else {
                        DirectMessage dmsg = new DirectMessage ( id, message, option );
                        Gson gson = new Gson ( );
                        String json = gson.toJson ( dmsg );
                        connection.getEmisor ( ).sendMessage ( json );
                    }
                }
        );
    }

    @Override
    public void onMessage ( String msg ) {
        Platform.runLater (
                ( ) -> {
                    Gson gson = new Gson ( );
                    Generic type = gson.fromJson ( msg, Generic.class );
                    switch (type.getType ( )) {
                        case "Message":
                            Message m = gson.fromJson ( msg, Message.class );
                            windows.getMessageArea ( ).appendText ( m.getId ( ) + ": " + m.getBody ( ) );
                            break;
                        case "UserInside":
                            System.out.println ( "me llegaron los usuarios" );
                            UserInside us = gson.fromJson ( msg, UserInside.class );
                            fillButtons ( us.getSessions ( ) );

                            break;


                    }
                }
        );
    }
}
