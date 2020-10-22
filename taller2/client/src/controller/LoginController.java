package controller;

import com.google.gson.Gson;
import comm.OnConnectionListenner;
import comm.OnMessageListenner;
import comm.TCPConnection;
import javafx.application.Platform;
import model.User;
import view.LoginWindow;
import view.MultichatWindows;

public class LoginController implements OnConnectionListenner, OnMessageListenner {

    private LoginWindow windows;
    private TCPConnection connection;
    private int stop;

    public LoginController ( LoginWindow input ) {
        this.windows = input;
        stop = 0;
        init ( );
    }

    public void init ( ) {
        connection = TCPConnection.getInstance ( );
        connection.setConnectionListenner ( this );
        connection.setMessageListenner ( this );
        btAction ( );
    }

    public void btAction ( ) {
        windows.getBtIngresar ( ).setOnAction (
                ( e ) -> {
                    connection.setIp ( "127.0.0.1" );
                    connection.setPuerto ( 5000 );
                    //if ( stop == 0 ) {
                        connection.start ( );
                        stop++;
                    //}
                }
        );
    }


    @Override
    public void onConnection ( ) {
        Platform.runLater (
                ( ) -> {

                    MultichatWindows chat = new MultichatWindows ( windows.getName ( ).getText ( ) );
                    chat.show ( );
                    windows.close ( );
                }
        );
    }

    @Override
    public void onConnectionSend ( ) {
        String username = windows.getName ( ).getText ( );
        User userToSend = new User ( username );
        Gson gson = new Gson ( );
        String json = gson.toJson ( userToSend );
        connection.getEmisor ( ).sendMessage ( json );

    }


    @Override
    public void onMessage ( String msg ) {
        if ( msg.equals ( "permitido" ) ) {
            connection.entre ( );
        } else if ( msg.equals ( "noPermitido" ) ) {
            //Platform.runLater (
              //      ( ) -> {
//                        windows.close ( );
  //                      LoginWindow n = new LoginWindow ( );
                        windows.getNotAccess ( ).setText ( "NOMBRE REPETIDO" );
      //                  n.show ( );


                //    }
            //);
        } else {
            System.out.println ( "mk me llegaron al login" );
        }
    }
}
