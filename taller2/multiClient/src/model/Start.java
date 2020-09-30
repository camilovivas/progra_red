package model;

import com.google.gson.Gson;
import comm.*;

public class Start implements OnConnectionListenner, OnMessageListenner {

    private TCPConnection connection;

    public Start ( ) {
        connection = TCPConnection.getInstance ( );
        connection.setPuerto ( 5000 );
        connection.start ( );
        connection.setConnectionListenner ( this );
        connection.setMessageListenner ( this );

    }

    @Override
    public void onConnection ( String id ) {

        System.out.println ( "nuevo cliente conectado: " + id );
    }

    @Override
    public void onMessage ( Session s, String msg ) {
        Gson gson = new Gson ( );
        Generic type = gson.fromJson ( msg, Generic.class );

        switch (type.getType ( )) {
            case "Message":
                connection.sendBroadcast ( msg );
                break;
            case "DirectMessage":
                DirectMessage priv = gson.fromJson ( msg, DirectMessage.class );
                Message m = new Message ( priv.getId ( ), priv.getBody ( ) );
                String msgToSend = gson.toJson ( m );
                connection.sendDirectMessage ( priv.getClientId (), msgToSend );
                break;
            case "User":
                System.out.println ("si llego el user" );
                User user = gson.fromJson ( msg, User.class );
                if ( !connection.searchClient ( user.getUserName ( ) ) ) {
                    System.out.println ("fue aceptado" );
                    connection.addClient ( s, user );
                    connection.sendDirectMessage ( user.getUserName (), "permitido" );

                } else {
                    connection.sendDirectMessageRepeat ( s, "noPermitido" );
                    System.out.println ("esta repetido" );
                    connection.remove ( s );
                    //s.endConnection ( );
                }
                break;
            case "UserInside":
                System.out.println ("me esta pidiendo los usuarios" );
                UserInside us = new UserInside ( connection.getSessions () );
                String users = gson.toJson ( us );
                connection.sendBroadcast ( users );

        }
    }
}
