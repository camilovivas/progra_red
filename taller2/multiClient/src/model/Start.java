package model;

import com.google.gson.Gson;
import comm.OnConnectionListenner;
import comm.OnMessageListenner;
import comm.TCPConnection;

public class Start implements OnConnectionListenner, OnMessageListenner {

    private TCPConnection connection;

    public Start(){
        connection = TCPConnection.getInstance ();
        connection.setPuerto ( 5000 );
        connection.start ();
        connection.setConnectionListenner ( this );
        connection.setMessageListenner ( this );

    }

    @Override
    public void onConnection ( String id) {

        System.out.println ("nuevo cliente conectado: " + id );
    }

    @Override
    public void onMessage ( String msg ) {
        Gson gson = new Gson();
        Generic type = gson.fromJson(msg, Generic.class);

        switch(type.getType()) {
            case "Message":
                connection.sendBroadcast ( msg );
                break;
            case "DirectMessage":

                break;
            case "update":
                break;

        }
    }
}
