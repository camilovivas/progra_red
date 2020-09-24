package controller;

import comm.TCPConnection;
import view.LoginWindow;

public class LoginController {

    private LoginWindow windows;
    private TCPConnection connection;

    public LoginController( LoginWindow input){
        this.windows= input;
        init();
    }

    public void init(){
        connection = TCPConnection.getInstance ();
        connection.setPuerto ( 5000 );
        connection.start ();
    }


}
