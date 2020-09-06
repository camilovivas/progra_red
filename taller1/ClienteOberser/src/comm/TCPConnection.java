package comm;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class TCPConnection extends Thread{
    //ATTRIBUTES
    private Socket socket;
    private int port;
    private String ip;

    private OnMessageListener listener;

    public TCPConnection(){
        port = 5000;
        ip = "127.0.0.1";
    }

    public void setLitener(OnMessageListener litener) {
        this.listener = litener;
    }

    @Override
    public void run() {
        try {
            socket = new Socket(ip,port);
            OutputStream os = socket.getOutputStream();
            BufferedWriter bfw = new BufferedWriter(new OutputStreamWriter(os));


            InputStream is = socket.getInputStream ();
            BufferedReader reader = new BufferedReader (new InputStreamReader (is));

            Scanner scanner = new Scanner(System.in);

            while ( true ){
                String line = scanner.nextLine();
                if(line.equalsIgnoreCase ("RTT")){
                    long timeI = System.nanoTime ();
                    byte[] buffer = new byte[1024];
                    String msg = new String (buffer);
                    bfw.write(msg+"\n");
                    bfw.flush();
                    System.out.println ("esperando archivo");
                    String recibido =reader.readLine ();
                    listener.recibir ( recibido.getBytes ().length+" bytes" );
                    long timeF = System.nanoTime () - timeI;
                    listener.time ( timeF+"");
                }
                else if(line.equalsIgnoreCase ( "speed" )){
                    long timeI = System.nanoTime ();
                    byte[] buffer = new byte[8192];
                    String msg = new String (buffer);
                    bfw.write(msg+"\n");
                    bfw.flush();
                    System.out.println ("esperando archivo");
                    String recibido =reader.readLine ();
                    listener.recibir ( recibido.getBytes ().length+" bytes" );
                    long rtt = (System.nanoTime () - timeI)/1000000;//conversion de nano a segundo
                    long kb = ((8192+8192)/rtt)*1000;
                    listener.speed ( kb+"" );
                }

                else{
                bfw.write(line+"\n");
                bfw.flush();
                listener.onMessage(line);
                String recibido =reader.readLine ();
                listener.recibir (recibido);
                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
