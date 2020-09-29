package controller;

import com.google.gson.Gson;
import comm.*;
import javafx.application.Platform;
import model.*;
import view.*;

public class LoginController implements OnConnectionListenner {

    private LoginWindow windows;
    private TCPConnection connection;

    public LoginController( LoginWindow input){
        this.windows= input;
        init();
    }

    public void init(){
        connection = TCPConnection.getInstance ();
        connection.setConnectionListenner ( this );
        btAction ();
    }

    public void btAction ( ) {
        windows.getBtIngresar ( ).setOnAction (
                ( e ) -> {
                    connection.setIp ( "127.0.0.1" );
                    connection.setPuerto ( 5000 );
                    connection.start ( );
                }
        );
    }


    @Override
    public void onConnection ( ) {
        Platform.runLater (
                ()->{

                    MultichatWindows chat = new MultichatWindows ();
                    chat.show ();
                    windows.close ();
                }
        );
    }

    @Override
    public void onConnectionSend ( ) {
        String username = windows.getName ().getText ();
        User userToSend = new User (username);
        Gson gson = new Gson ();
        String json = gson.toJson ( userToSend );
        connection.getEmisor ().sendMessage ( json );

    }


}
