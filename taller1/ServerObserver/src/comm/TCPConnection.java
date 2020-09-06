package comm;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import model.*;

public class TCPConnection extends Thread {
    //ATTRIBUTES
    private ServerSocket serverSocket;
    private Socket socket;
    private int port;

    private OnMessageLisetener listener;
    private Commandos comandos;

    public void setListener(OnMessageLisetener listener) {
        this.listener = listener;
    }

    public void setPort(int port) {
        this.port = port;
    }

    @Override
    public void run() {
        try {

            System.out.println ("Esperando Conexion...");
            serverSocket = new ServerSocket (port);
            comandos = new Commandos ();
            socket = serverSocket.accept ();
            System.out.println ("conectado");

            InputStream is = socket.getInputStream ();
            BufferedReader reader = new BufferedReader (new InputStreamReader (is));

            OutputStream os = socket.getOutputStream ();
            BufferedWriter bfw = new BufferedWriter (new OutputStreamWriter (os));
            while ( true ) {
                String msg = reader.readLine ();
                listener.OnMessage (msg);

                String send = comandos.mensajes (msg);
                bfw.write (send+"\n");
                bfw.flush ();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
