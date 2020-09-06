package app;

import comm.OnMessageListener;
import comm.TCPConnection;

public class Aplication implements OnMessageListener {
    private TCPConnection connection;

    public Aplication(){
        connection = new TCPConnection();
        connection.setLitener(this);
        connection.start();
    }

    @Override
    public void onMessage(String msg) {
        System.out.println("enviado: " +msg);
    }

    @Override
    public void recibir (String msg) {
        System.out.println ("el servidor envia: "+ msg);
    }

    @Override
    public void time (String msg) {
        System.out.println ("el tiempo de ida y venida fue: "+msg );
    }

    @Override
    public void speed (String msg) {
        System.out.println ("la velocidad de transmicion fue: "+msg+ "Kb/s");
    }
}
