package comm;

import java.io.IOException;
import java.net.Socket;

public class Session extends Thread {

    private Socket socket;
    private Receptor receptor;
    private Emisor emisor;


    public Session(Socket socket){
        this.socket = socket;
    }

    @Override
    public void run () {
        while(true){
            try {
                receptor = new Receptor (socket.getInputStream ());
                receptor.start ();
                emisor = new Emisor ( socket.getOutputStream () );
            } catch (IOException e) {
                e.printStackTrace ( );
            }
        }
    }



    public Emisor getEmisor () {
        return emisor;
    }

    public Receptor getReceptor () {
        return receptor;
    }
}
