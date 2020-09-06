package app;

import comm.OnMessageLisetener;
import comm.TCPConnection;

import javax.xml.bind.SchemaOutputResolver;

public class Aplication implements OnMessageLisetener {

    private TCPConnection connection;

    public Aplication(){
        connection = new TCPConnection();
        connection.setListener(this);
        connection.setPort(5000);
        connection.start();
    }

    @Override
    public void OnMessage ( String msg ) {
        if ( msg.getBytes ( ).length == 1024 ) {
            System.out.println ( "recibido: RTT" );
        } else if ( msg.getBytes ( ).length == 8192 ) {
            System.out.println ( "recibido: speed" );
        } else {
            System.out.println ( "recibido: " + msg );
        }
    }

    @Override
    public void sendMessage (String msg) {
        System.out.println ("enviamos");
    }
}
