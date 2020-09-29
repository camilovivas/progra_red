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



    public void fillList ( ArrayList<String> input ) {
        Platform.runLater (
                ()->{

                    windows.getListUsers ( ).getItems ( ).clear ( );
                    windows.getListUsers ( ).getItems ( ).add ( "todos" );
                    for (int i = 0; i < input.size ( ); i++) {
                        String item = input.get ( i );
                        if ( item.equals ( owner ) ) {
                            windows.getListUsers ( ).getItems ( ).add ( item + " (yo)" );
                        } else {
                            windows.getListUsers ( ).getItems ( ).add ( item );
                        }

                    }
                }
        );
    }

    public String seleccted2(){
         return windows.getListUsers ().getSelectionModel ().getSelectedItem ().toString ();
      }


    public void btSendAction ( ) {
        windows.getSend ( ).setOnAction (
                ( e ) -> {
                    System.out.println (seleccted2 () );
                    String option = seleccted2 ( );
                    String message = windows.getMessage ( ).getText ( );
                    String id = owner;
                    if ( option.equalsIgnoreCase ( "todos" ) ) {

                        Message msg = new Message ( id, message );
                        Gson gson = new Gson ( );
                        String toSend = gson.toJson ( msg );
                        connection.getEmisor ( ).sendMessage ( toSend );

                    } else if ( option.equalsIgnoreCase ( "" ) ) {
                        System.out.println ( "no selecionaste a quien enviar" );
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
                            fillList ( us.getSessions () );

                            break;


                    }
                }
        );
    }
}
