package comm;

import java.io.IOException;
import java.net.Socket;

public class TCPConnection extends Thread {

    private static TCPConnection instance = null;

    private Socket socket;
    private Receptor receptor;
    private Emisor emisor;
    private String ip;
    private int puerto;
    private OnMessageListenner messageListenner;
    private OnConnectionListenner connectionListenner;


    private TCPConnection () {

    }

    //SINGLETON
    public static synchronized TCPConnection getInstance () {
        if(instance == null){
            instance = new TCPConnection ();
        }
        return instance;
    }

    public void setPuerto ( int puerto ) {
        this.puerto = puerto;
    }

    @Override
    public void run ( ) {
        try {
            System.out.println ( "conectandome..." );
            socket = new Socket ( ip, puerto );
            System.out.println ( "conectado" );
            receptor = new Receptor ( socket.getInputStream ( ) );
            receptor.setListenner ( messageListenner );
            receptor.start ( );
            emisor = new Emisor ( socket.getOutputStream ( ) );
            connectionListenner.onConnectionSend ();

        } catch ( IOException e ) {
            e.printStackTrace ( );
        }
    }


    public void setIp ( String ip ) {
        this.ip = ip;
    }

    public void setMessageListenner ( OnMessageListenner messageListenner ) {
        this.messageListenner = messageListenner;
    }

    public void setConnectionListenner ( OnConnectionListenner connectionListenner ) {
        this.connectionListenner = connectionListenner;
    }

    public void entre(){
        connectionListenner.onConnection ();

    }

    public Emisor getEmisor () {
        return emisor;
    }


}
