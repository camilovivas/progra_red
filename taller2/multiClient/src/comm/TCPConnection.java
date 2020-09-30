package comm;

import model.User;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class TCPConnection extends Thread {

    private static comm.TCPConnection instance = null;

    private ServerSocket server;
    private int puerto;
    private OnConnectionListenner connectionListenner;
    private OnMessageListenner messageListenner;


    private ArrayList<Session> sessions;
    private ArrayList<Session> waitingRoom;

    private TCPConnection ( ) {
        sessions = new ArrayList<> ( );
        waitingRoom = new ArrayList<> ( );
    }

    //SINGLETON
    public static synchronized comm.TCPConnection getInstance ( ) {
        if ( instance == null ) {
            instance = new comm.TCPConnection ( );
        }
        return instance;
    }

    public ArrayList<String> getSessions ( ) {
        ArrayList<String> toReturn = new ArrayList<> ( );

        for (int i = 0; i < sessions.size ( ); i++) {
            toReturn.add ( sessions.get ( i ).getId ( ) );
        }
        return toReturn;
    }

    public void setPuerto ( int puerto ) {
        this.puerto = puerto;
    }

    @Override
    public void run ( ) {
        try {
            server = new ServerSocket ( puerto );
            while ( true ) {
                System.out.println ( "esperando cliente" );
                Socket socket = server.accept ( );
                System.out.println ( "Verificando..." );
                Session session = new Session ( socket );
                session.getReceptor ( ).setListenner ( messageListenner );
                waitingRoom.add ( session );
            }
        } catch ( IOException e ) {
            e.printStackTrace ( );
        }
    }

    public void setMessageListenner ( OnMessageListenner messageListenner ) {
        this.messageListenner = messageListenner;
    }

    public void setConnectionListenner ( OnConnectionListenner connectionListenner ) {
        this.connectionListenner = connectionListenner;
    }

    public void sendBroadcast ( String msg ) {
        for (Session session : sessions) {
            session.getEmisor ( ).sendMessage ( msg );
        }

    }


    public void sendDirectMessage ( String id, String msg ) {
        boolean stop = false;
        for (int i = 0; i < sessions.size ( ) && !stop; i++) {
            if ( sessions.get ( i ).getId ( ).equalsIgnoreCase ( id ) ) {
                stop = true;
                sessions.get ( i ).getEmisor ( ).sendMessage ( msg );
            }
        }
    }

    public void sendDirectMessageRepeat ( Session s, String msg ) {
            if ( waitingRoom.contains ( s )) {
                int i = waitingRoom.indexOf ( s );
                waitingRoom.get ( i ).getEmisor ( ).sendMessage ( msg );
            }

    }

    public boolean searchClient ( String username ) {
        boolean toReturn = false;
        for (int i = 0; i < sessions.size ( ) && !toReturn; i++) {
            if ( sessions.get ( i ).getId ( ).equalsIgnoreCase ( username ) ) {
                toReturn = true;
            }
        }
        return toReturn;
    }

    public void addClient ( Session session, User user ) {
        int index = waitingRoom.indexOf ( session );
        Session s = waitingRoom.remove ( index );
        s.setId ( user.getUserName ( ) );
        sessions.add ( s );
        connectionListenner.onConnection ( s.getId ( ) );
        System.out.println ( "tenemos " + sessions.size ( ) + " conectados" );
    }

    public void remove ( Session session ) {
        waitingRoom.remove ( session );
    }
}
