package controller;

import comm.OnConnectionListenner;
import comm.TCPConnection;
import javafx.application.Platform;
import view.LoginWindow;
import view.MultichatWindows;

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
    }

    public void btAction ( ) {
        windows.getBtIngresar ( ).setOnAction (
                ( e ) -> {
                    String user = windows.getName ().getText ();
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
}
