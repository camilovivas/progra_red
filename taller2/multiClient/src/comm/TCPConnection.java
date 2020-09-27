package comm;

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

        private TCPConnection () {
            sessions = new ArrayList<> (  );
        }

        //SINGLETON
        public static synchronized comm.TCPConnection getInstance () {
            if(instance == null){
                instance = new comm.TCPConnection ();
            }
            return instance;
        }

        public void setPuerto ( int puerto ) {
            this.puerto = puerto;
        }

        @Override
        public void run () {
            try {
                server = new ServerSocket ( puerto );
                while ( true ) {
                    System.out.println ( "esperando cliente" );
                    Socket socket = server.accept ( );
                    System.out.println ( "conectado" );

                    Session session = new Session ( socket );
                    sessions.add ( session );
                }
            } catch ( IOException e ) {
                e.printStackTrace ( );
            }
        }

        public void setMessageListenner ( OnMessageListenner messageListenner ) {
            for (Session session : sessions) {
                session.getReceptor ( ).setListenner ( messageListenner );
            }
        }

        public void setConnectionListenner ( OnConnectionListenner connectionListenner ) {
            this.connectionListenner = connectionListenner;
        }

        public void sendBroadcast(String msg){
            for (Session session : sessions) {
                session.getEmisor ().sendMessage ( msg );
            }

        }

        public void sendDirectMessage(String id, String msg){
            boolean stop = false;
            for (int i = 0; i < sessions.size ( ) && !stop; i++) {
                if(sessions.get ( i ).getId ().equalsIgnoreCase (  id )){
                    stop = true;
                    sessions.get ( i ).getEmisor ().sendMessage ( msg );
                }
            }
        }
}
