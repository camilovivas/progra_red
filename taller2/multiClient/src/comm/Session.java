package comm;

import java.io.IOException;
import java.net.Socket;

public class Session {

    private String id;
    private Socket socket;
    private Receptor receptor;
    private Emisor emisor;


    public Session (Socket socket ) {
        this.socket = socket;
        try {
            emisor = new Emisor ( socket.getOutputStream ( ) );
            receptor = new Receptor (this, socket.getInputStream ( ) );
            receptor.start ( );
        } catch ( IOException e ) {
            e.printStackTrace ( );
        }
    }


    public Emisor getEmisor ( ) {
        return emisor;
    }

    public Receptor getReceptor ( ) {
        return receptor;
    }

    public String getId ( ) {
        return id;
    }

    public void setId ( String id ) {
        this.id = id;
    }

    public void endConnection ( ) {
        try {
            socket.close ();
        } catch ( IOException e ) {
            e.printStackTrace ( );
        }
    }
}
