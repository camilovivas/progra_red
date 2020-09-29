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
    private String username;
    private TCPConnection connection;


    public MultiChatController(MultichatWindows input){
        connection = TCPConnection.getInstance ();
        connection.setMessageListenner ( this );
        this.windows = input;
        btSendAction ();
    }

    public void setUsername ( String username ) {
        this.username = username;
    }

    public String seleccted(){
        ArrayList<Button> buttons = windows.getClientsConnected ( );
        String toReturn = "";
        for (int i = 0; i < buttons.size (); i++) {
            if(buttons.get(i).isFocused ()){
                toReturn =buttons.get ( i ).getText ();
            }
        }
        return toReturn;
    }

    public void btSendAction(){
        windows.getSend ().setOnAction (
                ( e ) -> {
                    String option = seleccted ( );
                    String message = windows.getMessage ( ).getText ( );
                    String id = UUID.randomUUID ( ).toString ( );
                    if ( option.equalsIgnoreCase ( "todos" ) ) {

                        Message msg = new Message ( id, message );
                        Gson gson = new Gson ( );
                        String toSend = gson.toJson ( msg );
                        connection.getEmisor ( ).sendMessage ( toSend );

                    }
                    else{
                     DirectMessage dmsg = new DirectMessage ( id, message, option);
                     Gson gson = new Gson ();
                     String json = gson.toJson ( dmsg );
                     connection.getEmisor ().sendMessage ( json );
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
                    switch (type.getType ()){
                        case "Message":
                            Message m = gson.fromJson ( msg, Message.class );
                            windows.getMessageArea ( ).appendText ( m.getBody ( ) );
                            break;
                        case "UserInside":
                            //UserInside

                            break;


                    }
                }
        );
    }
}
